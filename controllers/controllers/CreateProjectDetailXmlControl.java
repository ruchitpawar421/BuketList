package controllers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 * Servlet implementation class CreateProjectDetailXmlControl
 */
public class CreateProjectDetailXmlControl extends HttpServlet {
	private static final long serialVersionUID = 1L;


	String fileName=null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateProjectDetailXmlControl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String projectName=request.getParameter("name");
		String rootPath=request.getParameter("rootPath");
		String xmlLocation=request.getParameter("xmlLocation");
		
//		CreateXml(projectName);
		if(CreateXml(projectName)==true){
			addProjectDetailsChild(projectName, projectName, rootPath, xmlLocation);
		}
		
		response.sendRedirect("index.jsp?projectName="+projectName);
	}


	public boolean CreateXml(String fileName){
		try {
			/*
			File f=new File("dataFiles//"+fileName+".xml");
			f.createNewFile();*/

			Element Dfiles = new Element("project-details");
			Document doc = new Document(Dfiles);
			doc.setRootElement(Dfiles);

			// new XMLOutputter().output(doc, System.out);
			XMLOutputter xmlOutput = new XMLOutputter();

			// display nice nice
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter("dataFiles//"+fileName+".xml"));

			System.out.println("File Saved!");
		} catch (IOException io) {
			System.out.println(io.getMessage());
		}
		return true;
	}

	public void addProjectDetailsChild(String fileName,String name,String rootPath,String xmlLocation){
		try {

			SAXBuilder builder = new SAXBuilder();
			File xmlFile = new File("dataFiles\\"+fileName+".xml");

			Document doc = (Document) builder.build(xmlFile);
			Element rootNode = doc.getRootElement();

			rootNode.addContent(new Element("name").setText(name));
			rootNode.addContent(new Element("root-path").setText(rootPath));
			rootNode.addContent(new Element("xml-location").setText(xmlLocation));

			XMLOutputter xmlOutput = new XMLOutputter();

			// display nice nice
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter("dataFiles\\"+fileName+".xml"));

			System.out.println("File updated!");
		} catch (IOException io) {
			io.printStackTrace();
		} catch (JDOMException e) {
			e.printStackTrace();
		}
	}

}
