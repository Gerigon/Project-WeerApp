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



public class De_compress {

    private De_compress() {}

    public static void compressGZIP(InputStream inputStream, File output) throws IOException {
        try (GZIPOutputStream out = new GZIPOutputStream(new FileOutputStream(output))){
            byte[] buffer = new byte[1024];
            int len;
            while((len=inputStream.read(buffer)) != -1){
                out.write(buffer, 0, len);
            }
        }
    }

    public static void appendGZIP(File input, String inputString) throws IOException {
        try (Reader decoder = new InputStreamReader(new GZIPInputStream(new FileInputStream(input)))){
            BufferedReader br = new BufferedReader(decoder);
            //String STN,DATE,TIME,TEMP,DEWP,STP,SLP,VISIB,WDSP,PRCP,SNDP,FRSHTT,CLDC,WNDDIR = "";
            String output="",line="";
            String[] weatherDataLine = null;
            while((line = br.readLine()) != null)
            {
            	weatherDataLine = line.split(",");
            	output += line + "\n";
            }
            output += inputString;
            
            ByteArrayInputStream inputStream = new ByteArrayInputStream(output.getBytes());
            compressGZIP(inputStream,new File("E:\\School\\2020\\Project 2.2\\WeatherStations\\"+weatherDataLine[0]+"\\"+weatherDataLine[1] + ".gz"));
            
        	//byte[] buffer = new byte[1024];
            //int len;
//            System.out.println("test");
//            while((len = in.read(buffer)) != -1){
//                //out.write(buffer, 0, len);
//            	System.out.println("test: "+len);
//            	//tot 30 vorige data van de missende kolom extrapoleren
                	
//            }
        }
    }
    
    public static void completeMissingDataGZIP(File input,int column) throws IOException {
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
            try {
            	if (weatherData.size() > 2)
            	{
            		System.out.println("bracket 1");
					float prevData1,prevData2;
					int floor = (weatherData.size()) -30;
					
					if (floor < 0) 
					{
						floor = 0;
					}
					
					System.out.println("Getting first number from row " + floor + " and column " + column);
					System.out.println("First number reads: " + (weatherData.get(floor)[column]));
					
					System.out.println("Getting second number from row " + (weatherData.size()-2) + " and column " + column);
					System.out.println("Second number reads: " + weatherData.get(weatherData.size()-2)[column]);
					
					
					System.out.println("\nadding missing numbers");
					
					prevData1 = (Float.parseFloat((weatherData.get(floor)[column])));
					prevData2 = (Float.parseFloat(weatherData.get(weatherData.size()-2)[column]));
					newData = prevData2+(prevData2 - prevData1);
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
            System.out.println(output + "\n\n");
            ByteArrayInputStream inputStream = new ByteArrayInputStream(output.getBytes());
            compressGZIP(inputStream,new File("E:\\School\\2020\\Project 2.2\\WeatherStations\\"+weatherDataLine[0]+"\\"+weatherDataLine[1] + ".gz"));
        }
    }
}