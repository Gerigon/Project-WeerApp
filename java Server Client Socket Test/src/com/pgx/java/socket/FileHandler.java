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
    
    
    
    public static float clamp(float val, float min, float max) {
        // v = delayPreClamp
        // if v < min, returns the greater between min and v, thus min
        // if v > max, returns the greater between min and max, thus max
        // if v is between min and max, returns the greater between min and v, thus v
        return Math.max(min, Math.min(val, max));
    }
    public static int clamp(int val, int min, int max) {
        // v = delayPreClamp
        // if v < min, returns the greater between min and v, thus min
        // if v > max, returns the greater between min and max, thus max
        // if v is between min and max, returns the greater between min and v, thus v
        return Math.max(min, Math.min(val, max));
    }
    
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
    
    public static void completeMissingDataGZIP(ArrayList<Measurement> measurementList) throws IOException {
    	
            String output="",line="";
            String[] weatherDataLine = null;
            ArrayList<String[]> weatherData = new ArrayList<String[]>();
            float newData = 0;
            //System.out.println("Correction took place");
            for (int i = 0; i < measurementList.size(); i++) 
            {
            	//TEMP
            	//fix missing data
            	if (measurementList.get(i).getTEMP() == null)
            	{
            		//System.out.println("Correction took place1");
            		Float newValue = 0f;
            		Float firstValue = measurementList.get(0).getTEMP();
            		if (firstValue != null)
            		{
        				Float prevValue = measurementList.get(i-1).getTEMP();
            			if (prevValue != null)
                		{
                			newValue = prevValue+(prevValue - firstValue);
            			}
            			else
            			{
            				newValue = firstValue;
            			}
            		}
        			else
        			{
        				Float prevValue = measurementList.get(i-1).getTEMP();
            			if (prevValue != null)
            			{
            				newValue = prevValue;
            			}
        			}
            		newValue = clamp(newValue, -9999.9f, 9999.9f);
            		
            		measurementList.get(i).setTEMP(newValue);
            	}
    			//fix unplausable data
            	else
            	{
            		Float newValue = 0f;
            		Float firstValue = measurementList.get(0).getTEMP();
            		if (firstValue != null && i > 0)
            		{
                		Float prevValue = measurementList.get(i-1).getTEMP();
            			if (prevValue != null)
            			{
            				newValue = prevValue+(prevValue - firstValue);
            				measurementList.get(i).setTEMP(clamp(measurementList.get(i).getTEMP(), 
            				newValue * 0.8f, 
            				newValue * 1.2f));
            			}
            		}
            		measurementList.get(i).setTEMP(clamp(measurementList.get(i).getTEMP(), -9999.9f, 9999.9f));
            	}
            	//DEWP
            	
            	if (measurementList.get(i).getDEWP() == null)
            	{
            		//System.out.println("Correction took place2");
            		Float newValue = 0f;
            		Float firstValue = measurementList.get(0).getDEWP();
            		if (firstValue != null)
            		{
            			Float prevValue = measurementList.get(i-1).getDEWP();
            			if (prevValue == null)
            			{
            				newValue = firstValue;
            			}
            			else
            			{
            				newValue = prevValue+(prevValue - firstValue);
            			}
            		}
            		newValue = clamp(newValue, -9999.9f, 9999.9f);
            		
            		measurementList.get(i).setDEWP(newValue);
            	}
            	else
            	{
            		measurementList.get(i).setDEWP(clamp(measurementList.get(i).getDEWP(), -9999.9f, 9999.9f));
            	}
            	//STP
            	//System.out.println("equal to null :");
            	//System.out.print((measurementList.get(i).getSTP() == null || measurementList.get(i).getSTP().equals(null)) +"\n");
            	if (measurementList.get(i).getSTP() == null)
            	{
            		//System.out.println("Correction took place3");
            		Float newValue = 0f;
            		Float firstValue = measurementList.get(0).getSTP();
            		if (firstValue != null)
            		{
            			Float prevValue = measurementList.get(i-1).getSTP();
            			if (prevValue == null)
            			{
            				newValue = firstValue;
            			}
            			else
            			{
            				newValue = prevValue+(prevValue - firstValue);
            			}
            		}
            		newValue = clamp(newValue, 0f, 9999.9f);
            		
            		measurementList.get(i).setSTP(newValue);
            	}
            	else
            	{
            		measurementList.get(i).setSTP(clamp(measurementList.get(i).getSTP(), 0f, 9999.9f));
            	}
            	//SLP
            	if (measurementList.get(i).getSLP() == null)
            	{
            		//System.out.println("Correction took place4");
            		Float newValue = 0f;
            		Float firstValue = measurementList.get(0).getSLP();
            		if (firstValue != null)
            		{
            			Float prevValue = measurementList.get(i-1).getSLP();
            			if (prevValue == null)
            			{
            				newValue = firstValue;
            			}
            			else
            			{
            				newValue = prevValue+(prevValue - firstValue);
            			}
            		}
            		newValue = clamp(newValue, 0f, 9999.9f);
            		
            		measurementList.get(i).setSLP(newValue);
            	}
            	else
            	{
            		measurementList.get(i).setSLP(clamp(measurementList.get(i).getSLP(),  0f, 9999.9f));
            	}
            	//VISIB
            	if (measurementList.get(i).getVISIB() == null)
            	{
            		//System.out.println("Correction took place5");
            		Float newValue = 0f;
            		Float firstValue = measurementList.get(0).getVISIB();
            		if (firstValue != null)
            		{
            			Float prevValue = measurementList.get(i-1).getVISIB();
            			if (prevValue == null)
            			{
            				newValue = firstValue;
            			}
            			else
            			{
            				newValue = prevValue+(prevValue - firstValue);
            			}
            		}
            		newValue = clamp(newValue, 0.0f, 999.9f);
            		
            		measurementList.get(i).setVISIB(newValue);
            	}
            	else
            	{
            		measurementList.get(i).setVISIB(clamp(measurementList.get(i).getVISIB(), 0.0f, 999.9f));
            	}
            	//WDSP
            	if (measurementList.get(i).getWDSP() == null)
            	{
            		//System.out.println("Correction took place6");
            		Float newValue = 0f;
            		Float firstValue = measurementList.get(0).getWDSP();
            		if (firstValue != null)
            		{
            			Float prevValue = measurementList.get(i-1).getWDSP();
            			if (prevValue == null)
            			{
            				newValue = firstValue;
            			}
            			else
            			{
            				newValue = prevValue+(prevValue - firstValue);
            			}
            		}
            		newValue = clamp(newValue, 0.0f, 999.9f);
            		
            		measurementList.get(i).setWDSP(newValue);
            	}
            	else
            	{
            		measurementList.get(i).setWDSP(clamp(measurementList.get(i).getWDSP(),  0.0f, 999.9f));
            	}
            	//WDSP
            	if (measurementList.get(i).getPRCP() == null)
            	{
            		//System.out.println("Correction took place7");
            		Float newValue = 0f;
            		Float firstValue = measurementList.get(0).getPRCP();
            		if (firstValue != null)
            		{
            			Float prevValue = measurementList.get(i-1).getPRCP();
            			if (prevValue == null)
            			{
            				newValue = firstValue;
            			}
            			else
            			{
            				newValue = prevValue+(prevValue - firstValue);
            			}
            		}
            		newValue = clamp(newValue, 0.0f, 999.9f);
            		
            		measurementList.get(i).setPRCP(newValue);
            	}
            	else
            	{
            		measurementList.get(i).setPRCP(clamp(measurementList.get(i).getPRCP(), 0.0f, 999.9f));
            	}
            	//SNDP
            	if (measurementList.get(i).getSNDP() == null)
            	{
            		//System.out.println("Correction took place8");
            		Float newValue = 0f;
            		Float firstValue = measurementList.get(0).getSNDP();
            		if (firstValue != null)
            		{
            			Float prevValue = measurementList.get(i-1).getSNDP();
            			if (prevValue == null)
            			{
            				newValue = firstValue;
            			}
            			else
            			{
            				newValue = prevValue+(prevValue - firstValue);
            			}
            		}
            		newValue = clamp(newValue, -9999.9f, 9999.9f);
            		
            		measurementList.get(i).setSNDP(newValue);
            	}
            	else
            	{
            		measurementList.get(i).setSNDP(clamp(measurementList.get(i).getSNDP(), -9999.9f, 9999.9f));
            	}
            	//FRSHTT
            	if (measurementList.get(i).getFRSHTT() == null)
            	{
            		//System.out.println("Correction took place9");
            		String newValue = "000000";
            		String value = measurementList.get(0).getFRSHTT();
            		if (i != 0)
            		{
            			newValue = measurementList.get(i-1).getFRSHTT();
            		}
            		
            		measurementList.get(i).setFRSHTT(newValue);
            	}
            	//CLDC
            	if (measurementList.get(i).getCLDC() == null)
            	{
            		//System.out.println("Correction took place10");
            		Float newValue = 0f;
            		Float firstValue = measurementList.get(0).getCLDC();
            		if (firstValue != null)
            		{
            			Float prevValue = measurementList.get(i-1).getCLDC();
            			if (prevValue == null)
            			{
            				newValue = firstValue;
            			}
            			else
            			{
            				newValue = prevValue+(prevValue - firstValue);
            			}
            		}
            		newValue = clamp(newValue, 0.0f, 99.9f);
            		
            		measurementList.get(i).setCLDC(newValue);
            	}
            	else
            	{
            		measurementList.get(i).setCLDC(clamp(measurementList.get(i).getCLDC(), 0.0f, 99.9f));
            	}
            	//WNDDIR
            	if (measurementList.get(i).getWNDDIR() == null)
            	{
            		//System.out.println("Correction took place11");
            		Integer newValue = 0;
            		Integer firstValue = measurementList.get(0).getWNDDIR();
            		if (firstValue != null)
            		{
            			Integer prevValue = measurementList.get(i-1).getWNDDIR();
            			if (prevValue == null)
            			{
            				newValue = firstValue;
            			}
            			else
            			{
            				newValue = prevValue+(prevValue - firstValue);
            			}
            		}
            		newValue = clamp(newValue, 0,359);
            		
            		measurementList.get(i).setWNDDIR(newValue);
            	}
            	else
            	{
            		measurementList.get(i).setWNDDIR(clamp(measurementList.get(i).getWNDDIR(), 0,359));
            	}
            	
			}
            /*
            try {
            	// If weatherdata has more than 2 rows and there is missing data extrapolate the last received value with up to the 30st last received value.
            	if (weatherData.size() > 2 && (column == 3 || column == 4  || column == 5  || column == 6  || column == 7  || column == 8 || column == 9 || column == 10 || column == 12))
            	{
					float prevData1,prevData2;
					int floor = (weatherData.size()) -30;
					
					if (floor < 0) 
					{
						floor = 0;
					}
					
					//Get the 30st last received value
					prevData1 = (Float.parseFloat((weatherData.get(floor)[column])));
					
					//Get the last successfully received value
					prevData2 = (Float.parseFloat(weatherData.get(weatherData.size()-2)[column]));
					
					// Linear extrapolation
					newData = prevData2+(prevData2 - prevData1);
            	}
            	// If weatherdata has only 1 row with missing data fill the column with 0.
            	else if (weatherData.size() == 1)
            	{
            		newData = 0;
            	}
            	// If weatherdata has at least 2 rows of data with the latest value missing, copy the preceding value from that column.
            	else if (weatherData.size() >= 2)
            	{
            		newData = Float.parseFloat(weatherData.get(weatherData.size()-2)[column]);	
            	}
            	
            	//System.out.println("de nieuwe gegenereerde waarde is: "+newData);
				
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
        	
            //createAndCompressGZIPFile(output,file);
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
            	weatherDataLine = line.split(",");
            	weatherData.add(weatherDataLine);
            	//output += line + "\n";
            }
            float newData = 0;

			//System.out.println("\ncorrecting temp");
            if (weatherData.size() > 2)
        	{
        		//System.out.println("bracket 1");
				float prevData1,prevData2;
				int floor = (weatherData.size()) -30;
				if (floor < 0) 
				{
					floor = 0;
				}
				
				
				prevData1 = (Float.parseFloat((weatherData.get(floor)[3])));
				prevData2 = (Float.parseFloat(weatherData.get(weatherData.size()-2)[3]));
				
				newData = prevData2+(prevData2 - prevData1);
				
				newData = clamp(Float.parseFloat(weatherData.get(weatherData.size()-1 )[3]),  (float)(newData * 0.8), (float)(newData * 1.2));
				
        	}
            for(String[] dataLine : weatherData ) 
			{
				int count = 0;
				for (String string : dataLine) {
					if (count == 3) 
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
            //System.out.println(output + "\n\n");

            createAndCompressGZIPFile(output,input);
        }*/
    }
}