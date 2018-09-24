package com.middlepath.ageofsail.mapgenerator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MyHandler extends DefaultHandler {

	boolean encounteredData = false;
	public String stuff = null;
	List<String> tileTags = new ArrayList<String>();
	
	StringBuffer tmp = new StringBuffer();
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if ("tile" == qName) {
			encounteredData = true;
			String g = attributes.getValue("gid");
			tileTags.add(g);
		} else if ("data".equals(qName)) {
			System.out.println(tmp);
			stuff = tmp.toString();
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if ("data".equals(qName)) {
			
		}
	}
	
	public LinkedList<String> getTileTags() throws Exception {
		LinkedList<String> retVal = new LinkedList<String>();
		for (String t : tileTags) {
			StringBuffer sb = new StringBuffer();
			sb.append("<tile gid=\"");
			sb.append(t);
			sb.append("\" />");
			retVal.add(sb.toString());
		}
		return retVal;
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		// TODO Auto-generated method stub
		if (encounteredData)
			return;
		tmp.append(new String(ch, start, length));
	}
}
