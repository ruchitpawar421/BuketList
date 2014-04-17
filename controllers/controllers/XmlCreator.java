package controllers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class XmlCreator {


	String fileName=null;
	
	public boolean CreateXml(String fileName){
		try {

			Element Dfiles = new Element("project-details");
			Document doc = new Document(Dfiles);
			doc.setRootElement(Dfiles);

			// new XMLOutputter().output(doc, System.out);
			XMLOutputter xmlOutput = new XMLOutputter();

			// display nice nice
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter("data\\"+fileName+".xml"));

			System.out.println("File Saved!");
		} catch (IOException io) {
			System.out.println(io.getMessage());
		}
		return true;
	}

	public void addProjectDetailsChild(String fileName,String name,String rootPath,String xmlLocation){
		try {

			SAXBuilder builder = new SAXBuilder();
			File xmlFile = new File("data//"+fileName+".xml");

			Document doc = (Document) builder.build(xmlFile);
			Element rootNode = doc.getRootElement();

			rootNode.addContent(new Element("name").setText(name));
			rootNode.addContent(new Element("root-path").setText(rootPath));
			rootNode.addContent(new Element("xml-location").setText(xmlLocation));

			XMLOutputter xmlOutput = new XMLOutputter();

			// display nice nice
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter("data//"+fileName+".xml"));

			System.out.println("File updated!");
		} catch (IOException io) {
			io.printStackTrace();
		} catch (JDOMException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void updateSolutionFileChild(String fileName,String solutionFileName,String assignmentName){
		try {

			SAXBuilder builder = new SAXBuilder();
			File xmlFile = new File("data//"+fileName+".xml");

			Document doc = (Document) builder.build(xmlFile);
			Element rootNode = doc.getRootElement();

			List<Element> assignmentNode = rootNode.getChildren("assignment");
			for(Element eachNode:assignmentNode)
			if(eachNode.getAttributeValue("name").equals(assignmentName)){
				Element child=eachNode.getChild("solution-file");
				child.setText(solutionFileName);
			}
			XMLOutputter xmlOutput = new XMLOutputter();

			// display nice nice
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter("data//"+fileName+".xml"));

			System.out.println("File updated!");
		} catch (IOException io) {
			io.printStackTrace();
		} catch (JDOMException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void updateInstructionFileChild(String fileName,String instructionFileName,String assignmentName){
		try {

			SAXBuilder builder = new SAXBuilder();
			File xmlFile = new File("data//"+fileName+".xml");

			Document doc = (Document) builder.build(xmlFile);
			Element rootNode = doc.getRootElement();

			List<Element> assignmentNode = rootNode.getChildren("assignment");
			for(Element eachNode:assignmentNode)
			if(eachNode.getAttributeValue("name").equals(assignmentName)){
			eachNode.getChild("instruction-file").setText(instructionFileName);
			}
			XMLOutputter xmlOutput = new XMLOutputter();

			// display nice nice
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter("data//"+fileName+".xml"));

			System.out.println("File updated!");
		} catch (IOException io) {
			io.printStackTrace();
		} catch (JDOMException e) {
			e.printStackTrace();
		}
	}
	@SuppressWarnings("unchecked")
	public void updateStartFileChild(String fileName,String startFileName,String assignmentName){
		try {

			SAXBuilder builder = new SAXBuilder();
			File xmlFile = new File("data//"+fileName+".xml");

			Document doc = (Document) builder.build(xmlFile);
			Element rootNode = doc.getRootElement();

			List<Element> assignmentNode = rootNode.getChildren("assignment");
			for(Element eachNode:assignmentNode)
			if(eachNode.getAttributeValue("name").equals(assignmentName)){
				eachNode.getChild("start-file").setText(startFileName);
			}

			XMLOutputter xmlOutput = new XMLOutputter();

			// display nice nice
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter("data//"+fileName+".xml"));

			System.out.println("File updated!");
		} catch (IOException io) {
			io.printStackTrace();
		} catch (JDOMException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void updateReportFileChild(String fileName,String startFileName,String assignmentName){
		try {

			SAXBuilder builder = new SAXBuilder();
			File xmlFile = new File("data//"+fileName+".xml");

			Document doc = (Document) builder.build(xmlFile);
			Element rootNode = doc.getRootElement();

			List<Element> assignmentNode = rootNode.getChildren("assignment");
			for(Element eachNode:assignmentNode)
			if(eachNode.getAttributeValue("name").equals(assignmentName)){
				eachNode.getChild("report-file").setText(startFileName);
			}

			XMLOutputter xmlOutput = new XMLOutputter();

			// display nice nice
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter("data//"+fileName+".xml"));

			System.out.println("File updated!");
		} catch (IOException io) {
			io.printStackTrace();
		} catch (JDOMException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void updateFileToUploadChild(String fileName,String fileToUpload,String assignmentName){
		try {

			SAXBuilder builder = new SAXBuilder();
			File xmlFile = new File("data//"+fileName+".xml");

			Document doc = (Document) builder.build(xmlFile);
			Element rootNode = doc.getRootElement();

			List<Element> assignmentNode = rootNode.getChildren("assignment");
			for(Element eachNode:assignmentNode)
			if(eachNode.getAttributeValue("name").equals(assignmentName)){
				eachNode.getChild("file-to-upload").setText(fileToUpload);
			}

			XMLOutputter xmlOutput = new XMLOutputter();

			// display nice nice
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter("data//"+fileName+".xml"));

			System.out.println("File updated!");
		} catch (IOException io) {
			io.printStackTrace();
		} catch (JDOMException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<String> listOfAssignments(String fileName){
		ArrayList<String> assignmentList=new ArrayList<String>();
		try{
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File("data//"+fileName+".xml");

		Document doc = (Document) builder.build(xmlFile);
		Element rootNode = doc.getRootElement();

		List<Element> assignmentElements = rootNode.getChildren("assignment");
		for(Element e: assignmentElements){
			assignmentList.add(e.getAttributeValue("name"));
		}
		} catch (IOException io) {
			io.printStackTrace();
		} catch (JDOMException e) {
			e.printStackTrace();
		}
		
		return assignmentList;
	}
	
	@SuppressWarnings("unchecked")
	public String getStartFileName(String xmlFileName){
		try{
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File("data\\"+xmlFileName+".xml");

		Document doc = (Document) builder.build(xmlFile);
		Element rootNode = doc.getRootElement();

		List<Element> assignmentElements = rootNode.getChildren("assignment");
		for(Element e: assignmentElements){
			fileName=e.getChild("start-file").getText();
		}
		} catch (IOException io) {
			io.printStackTrace();
		} catch (JDOMException e) {
			e.printStackTrace();
		}
		
		return fileName;
	}
	
	@SuppressWarnings("unchecked")
	public String getReportFileName(String xmlFileName){
		try{
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File("data\\"+xmlFileName+".xml");

		Document doc = (Document) builder.build(xmlFile);
		Element rootNode = doc.getRootElement();

		List<Element> assignmentElements = rootNode.getChildren("assignment");
		for(Element e: assignmentElements){
			fileName=e.getChild("report-file").getText();
		}
		} catch (IOException io) {
			io.printStackTrace();
		} catch (JDOMException e) {
			e.printStackTrace();
		}
		
		return fileName;
	}
	
	@SuppressWarnings("unchecked")
	public String getInstructionFileName(String xmlFileName){
		try{
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File("data//"+xmlFileName+".xml");

		Document doc = (Document) builder.build(xmlFile);
		Element rootNode = doc.getRootElement();

		List<Element> assignmentElements = rootNode.getChildren("assignment");
		for(Element e: assignmentElements){
			fileName=e.getChild("instruction-file").getText();
		}
		} catch (IOException io) {
			io.printStackTrace();
		} catch (JDOMException e) {
			e.printStackTrace();
		}
		
		return fileName;
	}
	
	@SuppressWarnings("unchecked")
	public String getSolutionFileName(String xmlFileName){
		try{
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File("data//"+xmlFileName+".xml");

		Document doc = (Document) builder.build(xmlFile);
		Element rootNode = doc.getRootElement();

		List<Element> assignmentElements = rootNode.getChildren("assignment");
		for(Element e: assignmentElements){
			fileName=e.getChild("solution-file").getText();
		}
		} catch (IOException io) {
			io.printStackTrace();
		} catch (JDOMException e) {
			e.printStackTrace();
		}
		
		return fileName;
	}
	
	@SuppressWarnings("unchecked")
	public String getFileToUploadFileName(String xmlFileName){
		try{
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File("data//"+xmlFileName+".xml");

		Document doc = (Document) builder.build(xmlFile);
		Element rootNode = doc.getRootElement();

		List<Element> assignmentElements = rootNode.getChildren("assignment");
		for(Element e: assignmentElements){
			fileName=e.getChild("file-to-upload").getText();
		}
		} catch (IOException io) {
			io.printStackTrace();
		} catch (JDOMException e) {
			e.printStackTrace();
		}
		
		return fileName;
	}
}
