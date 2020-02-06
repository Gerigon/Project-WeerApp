package com.pgx.java.socket;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;



public class XMLParser 
{
	ArrayList<String> stationIDNamesMap;
	private static int cacheSize = 300;
	private static ThreadLocal<ConcurrentHashMap<Integer, ArrayList<Measurement>>> WeatherStationMap = new ThreadLocal<ConcurrentHashMap <Integer, ArrayList<Measurement>>>(){
		@Override
	    protected ConcurrentHashMap<Integer, ArrayList<Measurement>> initialValue() {
	        return new ConcurrentHashMap<>();
	    }
	};
	private ThreadLocal<Integer> measurementCount = ThreadLocal.withInitial(() -> 0);
	
	public void parseWeatherData(InputSource XMLSource, int count) throws IOException 
	{
		
		//windows
		//String pathDivider ="\\";
		//String Pathfile ="E:\\School\\2020\\Project 2.2\\WeatherStations\\";
		
		//linux
		String pathDivider ="/";
		String Pathfile ="/mnt/weerstation_share/";
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		try 
		{
			
			SAXParser saxParser = saxParserFactory.newSAXParser();
			XmlHandler handler = new XmlHandler();
			saxParser.parse(XMLSource,handler);
			
			List<Measurement> measurementList = handler.getMeasurementList();
			
			for (Measurement measurement : measurementList) 
			{
				
				Integer folderNumber = measurement.getSTN();
				String folderName = Integer.toString(folderNumber);
				if (!WeatherStationMap.get().containsKey(folderNumber))
				{
					WeatherStationMap.get().put(folderNumber, new ArrayList<Measurement>());
				}
				WeatherStationMap.get().get(folderNumber).add(measurement);
				measurementCount.set(measurementCount.get().intValue()+1);
				File newDir = new File(Pathfile, folderName);
		        newDir.mkdir();
		        
		        
		        
		        
			}
			/*for (Map.Entry<Integer,ArrayList<Measurement>> WeatherStation : WeatherStationMap.entrySet())
			{
				//System.out.println("Weatherstation: "+WeatherStation.getKey());
				for (int i = 0; i < WeatherStation.getValue().size(); i++) 
				{
					//System.out.println(WeatherStation.getValue().get(i));
				}
			}
			//System.out.println(last30Sec.size()+"\n\n");*/
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		try {
		
		if(measurementCount.get() == cacheSize) 
		{
			//ystem.out.println("being called");
			for (Map.Entry<Integer,ArrayList<Measurement>> WeatherStation : WeatherStationMap.get().entrySet())
			{
				Integer folderNumber = WeatherStation.getKey();
				String folderName = Integer.toString(folderNumber);
				String fileNameDate = "";
				String fileNameTime = "";
				//System.out.println("Weatherstation: "+WeatherStation.getKey());
				FileHandler.completeMissingDataGZIP(WeatherStation.getValue());
				
				String outputString = "";
				for (int i = 0; i < WeatherStation.getValue().size(); i++) 
				{
					fileNameDate = WeatherStation.getValue().get(i).getDATE();
					fileNameTime = WeatherStation.getValue().get(i).getTIME();
					outputString += (WeatherStation.getValue().get(i) + "\n");
				}
				//System.out.println(outputString);
				FileHandler.createAndCompressGZIPFile(outputString,new File(Pathfile+pathDivider+folderName+pathDivider+fileNameDate+"_"+fileNameTime+".gz"));
				
			}
			WeatherStationMap.remove();
			measurementCount.set(0);
			System.out.println("cycle completed");
			/*
			for (Map.Entry<Integer,ArrayList<Measurement>> WeatherStation : WeatherStationMap.get().entrySet())
			{
				//System.out.println("Weatherstation: "+WeatherStation.getKey());
				//FileHandler.completeMissingDataGZIP(WeatherStation.getValue());
				for (int i = 0; i < WeatherStation.getValue().size(); i++) 
				{
					System.out.println(WeatherStation.getValue().get(i));
				}
				
			}*/
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		
		/*
		long startTime = System.nanoTime();
		
        String foldername = STN;
        String filename = DATE;
        File newDir = new File(Pathfile, foldername);
        newDir.mkdir();
        File fullPath = new File(Pathfile+foldername+pathDivider+filename + ".gz");
        
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.print("Iniatilize folders:\ntime in seconds: "); 
        System.out.printf("%f\n\n",(float)(duration / 1e+9));
        
        startTime = System.nanoTime();
        String outputString = "";
        for (int j = 0; j < strs.length; j++) {
        	if(j != strs.length-1)
        	{
        		outputString += strs[j] + ",";
        	}
        	else
        	{
        		outputString += strs[j];
        	}
		}
        
        endTime = System.nanoTime();
        duration = (endTime - startTime);
        System.out.print("Iniatilize Data String:\ntime in seconds: "); 
        System.out.printf("%f\n\n",(float)(duration / 1e+9));
        
        startTime = System.nanoTime();
        if (fullPath.exists())
		{
        	startTime = System.nanoTime();
        	// Appending file
        	FileHandler.appendToGZIPFile(outputString, new File(Pathfile+pathDivider+foldername+pathDivider+filename + ".gz"));
        	
        	endTime = System.nanoTime();
            duration = (endTime - startTime);
            System.out.print("Appending a Gzip:\ntime in seconds: "); 
            System.out.printf("%f\n\n",(float)(duration / 1e+9));
		}
        else
        {
        	startTime = System.nanoTime();
        	// Creating file
        	FileHandler.createAndCompressGZIPFile(outputString,new File(Pathfile+pathDivider+foldername+pathDivider+filename + ".gz"));

        	endTime = System.nanoTime();
            duration = (endTime - startTime);
            System.out.print("Creating a Gzip:\ntime in seconds: "); 
            System.out.printf("%f\n\n",(float)(duration / 1e+9));
        }
        
        int column = 0;
        for (String string : strs) 
        {
			if (string == "")
			{
				
				System.out.println("missing column: "+ column);
				
				
				if (fullPath.exists())
				{
					startTime = System.nanoTime();
					
					FileHandler.completeMissingDataGZIP(fullPath, column);
					
					endTime = System.nanoTime();
		            duration = (endTime - startTime);
		            System.out.print("Filling in missing data on a Gzip:\ntime in seconds: "); 
		            System.out.printf("%f\n\n",(float)(duration / 1e+9));
				} 
				else
				{
					System.out.println("file bestaat niet");
				}
				break;
			}
			//temp
			else if (column == 3)
			{
				startTime = System.nanoTime();
				FileHandler.correctTempDataGZIP(fullPath);
				endTime = System.nanoTime();
	            duration = (endTime - startTime);
	            System.out.print("correcting wrong data on a Gzip:\ntime in seconds: "); 
	            System.out.printf("%f\n\n",(float)(duration / 1e+9));
			}
			column++;
		}*/
    }
}
/*
String STN,DATE,TIME,TEMP,DEWP,STP,SLP,VISIB,WDSP,PRCP,SNDP,FRSHTT,CLDC,WNDDIR;
STN=DATE=TIME=TEMP=DEWP=STP=SLP=VISIB=WDSP=PRCP=SNDP=FRSHTT=CLDC=WNDDIR = "";
try 
{
	long startTime = System.nanoTime();
	
	NodeList MList = weatherData.getElementsByTagName("MEASUREMENT");
	
    for (int i = 0; i < MList.getLength(); i++) 
    {
        Node MNode = MList.item(i);
        		            
        if (MNode.getNodeType() == Node.ELEMENT_NODE) 
        {

            Element elem = (Element) MNode;
           
            Node node1 = elem.getElementsByTagName("STN").item(0);
            STN = node1.getTextContent();

            Node node2 = elem.getElementsByTagName("DATE").item(0);
            DATE = node2.getTextContent();
            
            Node node3 = elem.getElementsByTagName("TIME").item(0);
            TIME = node3.getTextContent();

            Node node4 = elem.getElementsByTagName("TEMP").item(0);
            TEMP = node4.getTextContent();

            Node node5 = elem.getElementsByTagName("DEWP").item(0);
            DEWP = node5.getTextContent();

            Node node6 = elem.getElementsByTagName("STP").item(0);
            STP = node6.getTextContent();

            Node node7 = elem.getElementsByTagName("SLP").item(0);
            SLP = node7.getTextContent();

            Node node8 = elem.getElementsByTagName("VISIB").item(0);
            VISIB = node8.getTextContent();
            
            Node node9 = elem.getElementsByTagName("WDSP").item(0);
            WDSP = node9.getTextContent();

            Node node10 = elem.getElementsByTagName("PRCP").item(0);
            PRCP = node10.getTextContent();
            
            Node node11 = elem.getElementsByTagName("SNDP").item(0);
            SNDP = node11.getTextContent();

            Node node12 = elem.getElementsByTagName("FRSHTT").item(0);
            FRSHTT = node12.getTextContent();
            
            Node node13 = elem.getElementsByTagName("CLDC").item(0);
            CLDC = node13.getTextContent();

            Node node14 = elem.getElementsByTagName("WNDDIR").item(0);
            WNDDIR = node14.getTextContent();
        }
        
    }
    long endTime = System.nanoTime();
    long duration = (endTime - startTime);
    System.out.print("Iniatilize Variables:\ntime in seconds: "); 
    System.out.printf("%f\n\n", +(float)(duration / 1e+9));
    
}
catch (Exception e) 
{
	// TODO Auto-generated catch block
	e.printStackTrace();
}
String[] strs = {STN,DATE,TIME,TEMP,DEWP,STP,SLP,VISIB,WDSP,PRCP,SNDP,FRSHTT,CLDC,WNDDIR};
*/
 