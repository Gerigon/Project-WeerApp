package com.pgx.java.socket;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.Socket;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;




class ServerClientThread extends Thread 
{
	Domparser domparser;
    Socket serverClient;
    int clientNo;
    int squre;
    
    String output = "";
    
    
    ServerClientThread(Socket inSocket,int counter, Domparser domparser)
    {
        serverClient = inSocket;
        clientNo=counter;
        this.domparser = domparser;
    }
    public void run()
    {
    	try
    	{
            //DataInputStream inStream = new DataInputStream(serverClient.getInputStream());
        	BufferedReader inStream = new BufferedReader(new InputStreamReader(serverClient.getInputStream()));
            DataOutputStream outStream = new DataOutputStream(serverClient.getOutputStream());
            String clientMessage="", serverMessage="";
            String xmlCluster = "";
            boolean collect = false;
            while((clientMessage = inStream.readLine()) != null)
            {
                if(clientMessage.equals("<?xml version=\"1.0\"?>")) 
                {
                	collect = true;
                } 
                else if(clientMessage.equals("</WEATHERDATA>")) 
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
                	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                	DocumentBuilder builder = factory.newDocumentBuilder();
                	Document doc = builder.parse(new InputSource(new StringReader(xmlCluster)));
                	domparser.parseWeatherData(doc);
                	xmlCluster = "";
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
    
//    public static void stringToDom(String xmlSource) 
//            throws SAXException, ParserConfigurationException, IOException, TransformerException 
//    {
//        // Parse the given input
//        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder builder = factory.newDocumentBuilder();
//        Document doc = builder.parse(new InputSource(new StringReader(xmlSource)));
//
//        // Write the parsed document to an xml file
//        TransformerFactory transformerFactory = TransformerFactory.newInstance();
//        Transformer transformer = transformerFactory.newTransformer();
//        DOMSource source = new DOMSource(doc);
//
//        StreamResult result =  new StreamResult(new File("test.xml"));
//        transformer.transform(source, result);
//    }
}