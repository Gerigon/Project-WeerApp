package com.pgx.java.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

//import com.sun.org.slf4j.internal.Logger;
//import com.sun.org.slf4j.internal.LoggerFactory;

public class NioSocketClient {
	//private static final Logger LOG = LoggerFactory.getLogger(NioSocketClient.class);
	
	AsynchronousSocketChannel socket;
	private Future<Void> future;
	private static NioSocketClient instance;
	
	public NioSocketClient() {
		try {
			socket = AsynchronousSocketChannel.open();
			InetSocketAddress hostAddress = new InetSocketAddress("145.37.136.133", 5000);
			future = socket.connect(hostAddress);
			
			start();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public static NioSocketClient getInstance() {
		if (instance == null) {
			instance = new NioSocketClient();
		}
		return instance;
	}
	
	private void start() {
		try {
			future.get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public String sentMessage(String message) {
		byte[] byteMsg = message.getBytes();
		ByteBuffer buffer = ByteBuffer.wrap(byteMsg);
		Future<Integer> writeResult = socket.write(buffer);
		
		try {
			writeResult.get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		buffer.flip();
		Future<Integer> readResult = socket.read(buffer);
		
		try {
			readResult.get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String echo = new String(buffer.array()).trim();
		
		buffer.clear();
		
		return echo;
	}
	
	public void stop() {
		try {
			socket.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
		NioSocketClient socket = NioSocketClient.getInstance();
		socket.start();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line;
		while((line = br.readLine()) != null) {
			String response = socket.sentMessage(line);
		}
	}
}
