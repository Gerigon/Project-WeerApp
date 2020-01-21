package com.pgx.java.socket;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class CapitalizeClient 
{
	static int messageCount = 0;
    public static void main(String[] args) throws Exception 
    {
        if (args.length != 1) {
            System.err.println("Pass the server IP as the sole command line argument");
            return;
        }
        
        var socket = new Socket(args[0], 59898);
        ClientServerOutputReader in = new ClientServerOutputReader(socket);
        in.start();
        ClientUserInputReader out = new ClientUserInputReader(socket);
        out.start();
//        try (var socket = new Socket(args[0], 59898)) {
//            System.out.println("Enter lines of text then Ctrl+D or Ctrl+C to quit");
//            var scanner = new Scanner(System.in);
//            var in = new Scanner(socket.getInputStream());
//            var out = new PrintWriter(socket.getOutputStream(), true);
//            
//            while (scanner.hasNextLine()) 
//            {
//            	if (messageCount == 0)
//            	{
//            		setMessageCount(Integer.parseInt(in.nextLine()));
//            	}
//            	else
//            	{
//            		out.println(scanner.nextLine());
//                	System.out.println(in.nextLine());
//            	}
//            }
//        }
    }
    public static void setMessageCount(int _messageCount)
    {
    	messageCount = _messageCount;
    	System.out.println("De message count is gezet op: "+ messageCount+"\n");
    }
}

class ClientServerOutputReader extends Thread {
	Socket serverSocket;
	
	public ClientServerOutputReader(Socket serverSocket) {
		this.serverSocket = serverSocket;
	}
	
	public void run() {
		try {
			var in = new Scanner(serverSocket.getInputStream());
			
			String serverOutput = "";
			String tempOutput = "";
			while( (tempOutput = serverOutput) != ((serverOutput = in.nextLine()))) {
				System.out.println(serverOutput);
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}

class ClientUserInputReader extends Thread {
	Socket serverSocket;
	
	public ClientUserInputReader(Socket serverSocket) {
		this.serverSocket = serverSocket;
	}
	
	public void run() {
		try {
			var out = new PrintWriter(serverSocket.getOutputStream(), true);
			var scanner = new Scanner(System.in); 
			while(scanner.hasNextLine()) {
				out.println(scanner.nextLine());
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
