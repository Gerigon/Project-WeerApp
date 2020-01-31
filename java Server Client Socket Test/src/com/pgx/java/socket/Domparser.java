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

public class Domparser 
{
	ArrayList<String> stationIDNamesMap;
	

	public ArrayList<String> getStationIDNamesMap() {
		return stationIDNamesMap;
	}

	public void parseWeatherData(Document weatherData) throws IOException {
		String delimiter = ",";
        
	
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			try {
				
				NodeList MList = weatherData.getElementsByTagName("MEASUREMENT");
				
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
		                
		                //hier controleren of een van de strings leeg zijn
		                //als een string leeg is de corepsonderende folder/file openen van de juiste dag
		                //in deze file de laatste 30 gegevens extrapoleren
		                //dan vullen we de missende data hiermee in

		                //this gets the file id from the xml file and links it to the stations name from the hashmap
		                String foldername = STN;
		                String filename = strs[1];
		                File Pathfile = new File("E:\\School\\2020\\Project 2.2\\WeatherStations\\");
		                File newDir = new File(Pathfile, foldername);
		                newDir.mkdir();
		                File file = new File("E:\\School\\2020\\Project 2.2\\WeatherStations\\"+foldername+"\\"+filename + ".txt");
		                
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
 