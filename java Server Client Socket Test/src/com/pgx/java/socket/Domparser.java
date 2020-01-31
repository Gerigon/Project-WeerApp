package com.pgx.java.socket;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Stream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Domparser {
	public static void main(String[] args) throws IOException {
		
		
		
        String delimiter = ",";
        Map<String, String> stationIDNamesMap = new HashMap<>();

        try(Stream<String> lines = Files.lines(Paths.get("StationIdNames.txt"))){
            lines.filter(line -> line.contains(delimiter)).forEach(
                line -> stationIDNamesMap.putIfAbsent(line.split(delimiter)[0], line.split(delimiter)[1])
            );
        }
	
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			try {
				Document doc = builder.parse("file.xml");
				
				NodeList MList = doc.getElementsByTagName("MEASUREMENT");
				
		        for (int i = 0; i < MList.getLength(); i++) {
		            Node MNode = MList.item(i);
		            		            
		            if (MNode.getNodeType() == Node.ELEMENT_NODE) {

		                Element elem = (Element) MNode;
		               
		                Node node1 = elem.getElementsByTagName("STN").item(0);
		                String STN = node1.getTextContent();

		                Node node2 = elem.getElementsByTagName("DATE").item(0);
		                String DATE = node2.getTextContent();
		                
		                Node node3 = elem.getElementsByTagName("TIME").item(0);
		                String TIME = node3.getTextContent();

		                Node node4 = elem.getElementsByTagName("TEMP").item(0);
		                String TEMP = node4.getTextContent();

		                Node node5 = elem.getElementsByTagName("DEWP").item(0);
		                String DEWP = node5.getTextContent();

		                Node node6 = elem.getElementsByTagName("STP").item(0);
		                String STP = node6.getTextContent();

		                Node node7 = elem.getElementsByTagName("SLP").item(0);
		                String SLP = node7.getTextContent();

		                Node node8 = elem.getElementsByTagName("VISIB").item(0);
		                String VISIB = node8.getTextContent();
		                
		                Node node9 = elem.getElementsByTagName("WDSP").item(0);
		                String WDSP = node9.getTextContent();

		                Node node10 = elem.getElementsByTagName("PRCP").item(0);
		                String PRCP = node10.getTextContent();
		                
		                Node node11 = elem.getElementsByTagName("SNDP").item(0);
		                String SNDP = node11.getTextContent();

		                Node node12 = elem.getElementsByTagName("FRSHTT").item(0);
		                String FRSHTT = node12.getTextContent();
		                
		                Node node13 = elem.getElementsByTagName("CLDC").item(0);
		                String CLDC = node13.getTextContent();

		                Node node14 = elem.getElementsByTagName("WNDDIR").item(0);
		                String WNDDIR = node14.getTextContent();
		                

		                String[] strs = { STN,DATE,TIME,TEMP,DEWP,STP,SLP,VISIB,WDSP,PRCP,SNDP,FRSHTT,CLDC,WNDDIR};
		                
		                System.out.print(stationIDNamesMap.get(strs[0])+"\n");

		                //this gets the file id from the xml file and links it to the stations name from the hashmap
		                String foldername = stationIDNamesMap.get(strs[0]);
		                String filename = strs[1];
		                
		                File file = new File("C:\\Users\\ludew\\eclipse-workspace\\leertaak3\\stationsWeatherData\\"+foldername+"\\"+filename + ".txt"); 
		                FileWriter fr = new FileWriter(file, true);
		                
		                for(int j = 0; j < strs.length; j++){
		                	if(j != strs.length-1){
		                	    fr.write(strs[j]+",");}
		                	else {
		                		fr.write(strs[j]);
		                	}
		                }
		               
		                fr.write("\n");
						fr.close();  
		            }

		        }
				
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
 