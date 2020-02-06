package com.pgx.java.socket;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.Socket;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;




class ServerClientThread extends Thread 
{
	XMLParser parser;
    Socket serverClient;
    int clientNo;
    int squre;
    
    String output = "";
    
    
    ServerClientThread(Socket inSocket,int counter, XMLParser parser)
    {
        serverClient = inSocket;
        clientNo=counter;
        this.parser = parser;
    }
    public void run()
    {
    	try
    	{
    		int count = 0;
            //DataInputStream inStream = new DataInputStream(serverClient.getInputStream());
    		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        	DocumentBuilder builder = factory.newDocumentBuilder();
        	
        	
        	BufferedReader inStream = new BufferedReader(new InputStreamReader(serverClient.getInputStream()));
            DataOutputStream outStream = new DataOutputStream(serverClient.getOutputStream());
            String clientMessage="", serverMessage="";
            String xmlCluster = "";
            boolean collect = false;
            while((clientMessage = inStream.readLine()) != null)
            {
                
                collect = true;
                if(clientMessage.equals("</WEATHERDATA>")) 
                {
                	xmlCluster += clientMessage;
                	collect = false;
                }
                if(collect) 
                {
                	xmlCluster += clientMessage;
                } 
                else 
                {
                	InputSource XMLSource = new InputSource(new StringReader(xmlCluster));
                	parser.parseWeatherData(XMLSource, count);
                	xmlCluster = "";
                	count++;
                	if (count == 30)
                		count = 0;
                }
            }
            inStream.close();
            outStream.close();
            serverClient.close();
            
        }
    	catch(Exception ex)
    	{
            System.out.println(ex);
        }
    	finally
        {
            System.out.println("Client -" + clientNo + " exit!! ");
        }
    }
}