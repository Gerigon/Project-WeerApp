package com.pgx.java.socket;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;



public class FileHandler {

    private FileHandler() {}

    public static void createAndCompressGZIPFile(String input, File output) throws IOException {
        try (GZIPOutputStream out = new GZIPOutputStream(new FileOutputStream(output))){
        	ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
            byte[] buffer = new byte[1024];
            int len;
            while((len=inputStream.read(buffer)) != -1){
                out.write(buffer, 0, len);
            }
        }
    }

    public static void appendToGZIPFile(String newLine, File file) throws IOException {
        try (BufferedReader br = new BufferedReader(
        							new InputStreamReader(
        								new GZIPInputStream(
        									new FileInputStream(file))))){
            
            String output="",line="";

            
            while((line = br.readLine()) != null)
            {
            	output += line + "\n";
            }
            output += newLine;
           
            createAndCompressGZIPFile(output, file);
        }
    }
    
    public static void completeMissingDataGZIP(File file,int column) throws IOException {
    	try (BufferedReader br = new BufferedReader(
				new InputStreamReader(
					new GZIPInputStream(
						new FileInputStream(file))))){
    		
            String output="",line="";
            String[] weatherDataLine = null;
            ArrayList<String[]> weatherData = new ArrayList<String[]>();
            float newData = 0;
            
            while((line = br.readLine()) != null)
            {
            	// Fill arraylist of weatherdata with rows of weatherdata
            	weatherDataLine = line.split(",");
            	weatherData.add(weatherDataLine);
            }
            
            try {
            	// If weatherdata has more than 2 rows and there is missing data extrapolate the last recieved value with up to the 30st last recieved value.
            	if (weatherData.size() > 2)
            	{
					float prevData1,prevData2;
					int floor = (weatherData.size()) -30;
					
					if (floor < 0) 
					{
						floor = 0;
					}
					
					//Get the 30st last recieved value
					prevData1 = (Float.parseFloat((weatherData.get(floor)[column])));
					
					//Get the last successfully recieved value
					prevData2 = (Float.parseFloat(weatherData.get(weatherData.size()-2)[column]));
					
					// Linear extrapolation
					newData = prevData2+(prevData2 - prevData1);
            	}
            	// If weatherdata has only 1 row with missing data fill the column with 0.
            	else if (weatherData.size() == 1)
            	{
            		newData = 0;
            	}
            	// If weatherdata has only 2 rows of data with the latest value missing, copy the first value from that column.
            	else if (weatherData.size() == 2)
            	{
            		newData = Float.parseFloat(weatherData.get(0)[column]);
            	}
            	
            	System.out.println("de nieuwe gegenereerde waarde is: "+newData);
				
            	for(String[] dataLine : weatherData ) 
				{
					int count = 0;
					for (String string : dataLine) {
						if (count == column) 
						{
							output += Float.toString(newData);
						}
						else
						{
							output += string;
						}
						if (count != dataLine.length - 1)
						{
							output += ",";
						}
						
						count++;
					}
					output += "\n";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
        	
            createAndCompressGZIPFile(output,new File("E:\\School\\2020\\Project 2.2\\WeatherStations\\"+weatherDataLine[0]+"\\"+weatherDataLine[1] + ".gz"));
        }
    }
    public static void correctTempDataGZIP(File input) throws IOException {
        try (Reader decoder = new InputStreamReader(new GZIPInputStream(new FileInputStream(input)))){
            BufferedReader br = new BufferedReader(decoder);
            //String STN,DATE,TIME,TEMP,DEWP,STP,SLP,VISIB,WDSP,PRCP,SNDP,FRSHTT,CLDC,WNDDIR = "";
            String output="",line="";
            String[] weatherDataLine = null;
            ArrayList<String[]> weatherData = new ArrayList<String[]>();
            while((line = br.readLine()) != null)
            {
            	System.out.println(line);
            	weatherDataLine = line.split(",");
            	weatherData.add(weatherDataLine);
            	//output += line + "\n";
            }
            float newData = 0;
            
            if (weatherData.size() > 2)
        	{
        		System.out.println("bracket 1");
				float prevData1,prevData2;
				int floor = (weatherData.size()) -30;
				if (floor < 0) 
				{
					floor = 0;
				}
				
				System.out.println("\nadding missing numbers");
				
				prevData1 = (Float.parseFloat((weatherData.get(floor)[3])));
				prevData2 = (Float.parseFloat(weatherData.get(weatherData.size()-2)[3]));
				newData = prevData2+(prevData2 - prevData1);
				
				if (Float.parseFloat(weatherData.get(weatherData.size()-1 )[3])>= newData * 0.8) 
				{

					// do action
				}
				System.out.println(newData);
        	}
        	else if (weatherData.size() == 1)
        	{
        		System.out.println("bracket 2");
        		newData = 0;
        	}
        	else if (weatherData.size() == 2)
        	{
        		System.out.println("bracket 3");
        	}
        	
            System.out.println(output + "\n\n");

            createAndCompressGZIPFile(output,new File("E:\\School\\2020\\Project 2.2\\WeatherStations\\"+weatherDataLine[0]+"\\"+weatherDataLine[1] + ".gz"));
        }
    }
}