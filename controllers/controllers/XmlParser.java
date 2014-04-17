package controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class XmlParser {
	

	HashMap<String, ArrayList<String>> testMap=new HashMap<String, ArrayList<String>>();
	HashMap<String, String> suiteMap=new HashMap<String, String>();
	
	public String returnTargetRootPath(String fileName){
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File("dataFiles\\"+fileName+".xml");
		Document doc = null;
		try {
			doc = (Document) builder.build(xmlFile);
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Element rootNode = doc.getRootElement();
		
		Element rootPath=rootNode.getChild("root-path");
		
		String path=rootPath.getText().replace("\\", "\\\\");
		return path;
	}
	
	public String returnTargetXmlLocation(String fileName){
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File("dataFiles\\"+fileName+".xml");

		Document doc = null;
		try {
			doc = (Document) builder.build(xmlFile);
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Element rootNode = doc.getRootElement();
Element rootPath=rootNode.getChild("root-path");
		
		String path=rootPath.getText().replace("\\", "\\\\");
		Element xml=rootNode.getChild("xml-location");
		System.out.println(xml.getText());
		return path+"\\\\"+xml.getText();
	}

	@SuppressWarnings({ "rawtypes" })
	public HashMap<String, String> getSuite(String filename){
		SAXBuilder builder = new SAXBuilder();

		File xmlFile = new File(filename);
		System.out.println(filename);

		try {
			Document document = (Document) builder.build(xmlFile);

			//Get root node from XML Document
			Element rootNode = document.getRootElement();

			//Get child test node from XML Document
			List list = rootNode.getChildren("test");			

			//Get name of each node from XML Document
			for (int i = 0; i < list.size(); i++) {
				
				ArrayList<String> testcases=new ArrayList<String>();
				Element node = (Element) list.get(i);
				suiteMap.put("Suite"+(i), node.getAttributeValue("name"));

				List list1=node.getChild("classes").getChildren("class");
				for (int j=0;j<list1.size();j++){
					Element node1 =(Element)list1.get(j);
					testcases.add(node1.getAttributeValue("name"));
				}
				testMap.put(node.getAttributeValue("name"), testcases);
			}
			
		} catch (IOException io) {
			System.out.println(io.getMessage());
		} catch (JDOMException jdomex) {
			System.out.println(jdomex.getMessage());
		}
		return suiteMap;
	}
	
	public HashMap<String, ArrayList<String>> getTestCases(){
		return testMap;
	}
}