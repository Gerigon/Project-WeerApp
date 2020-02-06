package com.pgx.java.socket;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XmlHandler extends DefaultHandler 
{
	private List<Measurement> measurementList = null;
	private Measurement measurement = null;
	private StringBuilder data = null;
	
	public List<Measurement> getMeasurementList()
	{
		return measurementList;
	}
	
	private boolean BSTN = false;
	private boolean BDATE = false;
	private boolean BTIME  = false;
	private boolean BTEMP = false;
	private boolean BDEWP  = false;
	private boolean BSTP = false;
	private boolean BSLP  = false;
	private boolean BVISIB  = false;
	private boolean BWDSP  = false;
	private boolean BPRCP  = false;
	private boolean BSNDP = false;
	private boolean BFRSHTT = false;
	private boolean BCLDC = false;
	private boolean BWNDDIR = false;
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException 
	{
		if (qName.equalsIgnoreCase("MEASUREMENT"))
		{
			measurement = new Measurement();
			
			if (measurementList == null)
			{
				measurementList = new ArrayList<>();
			}
		}
		else if (qName.equalsIgnoreCase("STN"))
		{
			BSTN = true;
		}
		else if (qName.equalsIgnoreCase("DATE"))
		{
			BDATE = true;
		}
		else if (qName.equalsIgnoreCase("TIME"))
		{
			BTIME = true;
		}
		else if (qName.equalsIgnoreCase("TEMP"))
		{
			BTEMP = true;
		}
		else if (qName.equalsIgnoreCase("DEWP"))
		{
			BDEWP = true;
		}
		else if (qName.equalsIgnoreCase("STP"))
		{
			BSTP = true;
		}
		else if (qName.equalsIgnoreCase("SLP"))
		{
			BSLP = true;
		}
		else if (qName.equalsIgnoreCase("VISIB"))
		{
			BVISIB = true;
		}
		else if (qName.equalsIgnoreCase("WDSP"))
		{
			BWDSP = true;
		}
		else if (qName.equalsIgnoreCase("PRCP"))
		{
			BPRCP = true;
		}
		else if (qName.equalsIgnoreCase("SNDP"))
		{
			BSNDP = true;
		}
		else if (qName.equalsIgnoreCase("FRSHTT"))
		{
			BFRSHTT = true;
		}
		else if (qName.equalsIgnoreCase("CLDC"))
		{
			BCLDC = true;
		}
		else if (qName.equalsIgnoreCase("WNDDIR"))
		{
			BWNDDIR = true;
		}
		
		data = new StringBuilder();
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (BSTN) 
		{
			// age element, set Employee age
			measurement.setSTN(Integer.parseInt(data.toString().trim()));
			BSTN = false;
		} else if (BDATE) {
			measurement.setDATE(data.toString());
			BDATE = false;
		}else if (BTIME) {
			String time = data.toString();
			measurement.setTIME(time.replace(":", "-"));
			BTIME = false;
		}else if (BTEMP) {
			if (!data.toString().trim().equals(""))
			{
				measurement.setTEMP(Float.parseFloat(data.toString().trim()));
			}
			else
			{
				measurement.setTEMP(null);
			}
			BTEMP = false;
		}else if (BDEWP) {
			if (!data.toString().trim().equals(""))
			{
				measurement.setDEWP(Float.parseFloat(data.toString().trim()));
			}
			else
			{
				measurement.setDEWP(null);
			}
			BDEWP = false;
		}else if (BSTP) {
			if (!data.toString().trim().equals(""))
			{
				measurement.setSTP(Float.parseFloat(data.toString().trim()));
			}
			else
			{
				measurement.setSTP(null);
			}
			BSTP = false;
		}else if (BSLP) {
			if (!data.toString().trim().equals(""))
			{
				measurement.setSLP(Float.parseFloat(data.toString().trim()));
			}
			else
			{
				measurement.setSLP(null);
			}
			BSLP = false;
		}else if (BVISIB) {
			if (!data.toString().trim().equals(""))
			{
				measurement.setVISIB(Float.parseFloat(data.toString().trim()));
			}
			else
			{
				measurement.setVISIB(null);
			}
			BVISIB = false;
		}else if (BWDSP) {
			if (!data.toString().trim().equals(""))
			{
				measurement.setWDSP(Float.parseFloat(data.toString().trim()));
			}
			else
			{
				measurement.setWDSP(null);
			}
			BWDSP = false;
		}else if (BPRCP) {
			if (!data.toString().trim().equals(""))
			{
				measurement.setPRCP(Float.parseFloat(data.toString().trim()));
			}
			else
			{
				measurement.setPRCP(null);
			}
			BPRCP = false;
		}else if (BSNDP) {
			if (!data.toString().trim().equals(""))
			{
				measurement.setSNDP(Float.parseFloat(data.toString().trim()));
			}
			else
			{
				measurement.setSNDP(null);
			}
			BSNDP = false;
		}else if (BFRSHTT) {
			measurement.setFRSHTT(data.toString().trim());
			BFRSHTT = false;
		}else if (BCLDC) {
			if (!data.toString().trim().equals(""))
			{
				measurement.setCLDC(Float.parseFloat(data.toString().trim()));
			}
			else
			{
				measurement.setCLDC(null);
			}
			BCLDC = false;
		}else if (BWNDDIR) {
			if (!data.toString().trim().equals(""))
			{
				measurement.setWNDDIR(Integer.parseInt(data.toString().trim()));
			}
			else
			{
				measurement.setWNDDIR(null);
			}
			BWNDDIR = false;
		} 
		if (qName.equalsIgnoreCase("MEASUREMENT")) {
			// add Employee object to list
			measurementList.add(measurement);
		}
	}
	
	@Override
	public void characters(char ch[], int start, int length) throws SAXException 
	{
		data.append(new String(ch, start, length));
	}
}
