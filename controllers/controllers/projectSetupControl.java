package controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.Executor;

import controllers.CreateReportHtml;
import controllers.XmlParser;
import controllers.XmlParserForReport;

/**
 * Servlet implementation class projectSetupControl
 */
public class projectSetupControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	XmlParser read = new XmlParser();

    /**
     * Default constructor. 
     */
    public projectSetupControl() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    static Process process = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fileName=request.getParameter("project");
		XmlParserForReport xmlParserForReport=null;	
		CreateReportHtml createReportHtml=null;
		double totalTimeCount=0;
		long hours=0;
		long minutes=0;
		long seconds=0;
		String xmlFileName=read.returnTargetXmlLocation(fileName);
		String root=xmlFileName.substring(0, xmlFileName.lastIndexOf("\\"));
		System.out.println("this is : "+root);
		String root1=root.replace("\\\\", "\\");
		HashMap<String, String> listSuite = read.getSuite(xmlFileName);
		HashMap<String, ArrayList<String>> listTest = read.getTestCases();
		HashMap<String, ArrayList<String>> testResultMap=new HashMap<String, ArrayList<String>>();
		ArrayList<String> executedTestList=new ArrayList<String>();
		String result=null;
		int exitValue = 9;
		String suite_name=request.getParameter("suite");
		
		File surefireDir=new File(root+"\\target\\surefire-reports\\Surefire suite");
		File reportDir=new File(root+"\\report\\tests");

		if(!surefireDir.exists()){
			System.out.println("surefire created");
			surefireDir.mkdirs();
			File f=new File(root+"\\target\\surefire-reports\\Surefire suite\\Surefire test.html");
			f.createNewFile();
			File x=new File(root+"\\target\\surefire-reports\\Surefire suite\\Surefire test.xml");
			x.createNewFile();
		}
		
		if(!reportDir.exists()){
			System.out.println("report created");
			reportDir.mkdirs();
		}
		
		if((request.getParameter("suite") != null)&&(request.getParameter("test")==null)){

			ArrayList<String> tests = listTest.get(request.getParameter("suite"));
			System.out.println(tests);
			System.out.println(listTest);
			for (String test : tests) {
				executedTestList.add(test.split("\\.")[test.split("\\.").length-1]);
				String line="cmd.exe /c mvn -Dtest="+test+" test";

				DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();

				CommandLine cmdLine = CommandLine.parse(line);
				Executor executor = new DefaultExecutor();
				executor.execute(cmdLine,resultHandler);				

				//Wait to get exit value
				try {
					resultHandler.waitFor();
					int exitValue1=resultHandler.getExitValue();
					if(executor.isFailure(exitValue1)){
						executor.setExitValue(1);
					}
					moveReportFile(test,root);
					System.out.println("\n\nExit Value is " + exitValue);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ArrayList<String> testResultDetails=new ArrayList<String>();
				xmlParserForReport=new XmlParserForReport(read.returnTargetRootPath(fileName)+"\\");	
				createReportHtml=new CreateReportHtml(read.returnTargetRootPath(fileName)+"\\");
				testResultDetails.add(Double.toString(xmlParserForReport.getTotalTime()));
				testResultDetails.add(xmlParserForReport.getTotalNumberOfPassedTests());
				testResultDetails.add(xmlParserForReport.getTotalNumberOfTestsFailed());
				testResultDetails.add(xmlParserForReport.getTotalNumberOfTestsSkipped());
				testResultMap.put(test.split("\\.")[test.split("\\.").length-1], testResultDetails);
				totalTimeCount=totalTimeCount+(xmlParserForReport.getTotalTime()*1000);
			}
			hours=TimeUnit.MILLISECONDS.toHours((long)totalTimeCount);
			minutes=TimeUnit.MILLISECONDS.toMinutes((long)totalTimeCount)-TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours((long)totalTimeCount));
			seconds=TimeUnit.MILLISECONDS.toSeconds((long)totalTimeCount)-TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)totalTimeCount));
			createReportHtml.createReportSummary(suite_name, hours+"hour"+minutes+"minute"+seconds+"second", executedTestList, testResultMap);
			
			
		}
		else if((request.getParameter("suite") == null)&&(request.getParameter("test")!=null)){
			
			String test=request.getParameter("test");
			
			String []testNameArray=test.split("\\.");

			String testName=testNameArray[testNameArray.length-1];			

			String[] command = {"cmd.exe", "/c", "cd \""+root1+"\" && mvn -Dtest="+test+" test"};
			ProcessBuilder probuilder = new ProcessBuilder( command );
			//You can set up your work directory
			probuilder.directory(new File(System.getProperty("user.dir")));
			process = probuilder.start();
			//Read out dir output
			InputStream is = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line;
			System.out.printf("Output of running %s is:\n",
					Arrays.toString(command));
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}

			//Wait to get exit value
			try {
				exitValue = process.waitFor();
				moveReportFile(test,root);
				System.out.println("\n\nExit Value is " + exitValue);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ArrayList<String> testResultDetails=new ArrayList<String>();
			testResultDetails.add(Double.toString(xmlParserForReport.getTotalTime()));
			testResultDetails.add(xmlParserForReport.getTotalNumberOfPassedTests());
			testResultDetails.add(xmlParserForReport.getTotalNumberOfTestsFailed());
			testResultDetails.add(xmlParserForReport.getTotalNumberOfTestsSkipped());
			testResultMap.put(test.split("\\.")[5], testResultDetails);
			totalTimeCount=totalTimeCount+(xmlParserForReport.getTotalTime()*1000);
			hours=TimeUnit.MILLISECONDS.toHours((long)totalTimeCount);
			minutes=TimeUnit.MILLISECONDS.toMinutes((long)totalTimeCount)-TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours((long)totalTimeCount));
			seconds=TimeUnit.MILLISECONDS.toSeconds((long)totalTimeCount)-TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)totalTimeCount));
			createReportHtml.createReportSummary(testName, hours+"hour"+minutes+"minute"+seconds+"second", executedTestList, testResultMap);
			
		}
		
		
		PrintWriter out=response.getWriter();
		out.println(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	public void moveReportFile(String testName,String projectRoot){
		InputStream inStream = null;
		OutputStream outStream = null;
		File afile = null;
		File bfile=null;
		try{
			afile =new File(projectRoot+"\\target\\surefire-reports\\Surefire suite\\Surefire test.html");
			bfile =new File(projectRoot+"\\report\\tests\\"+testName.split("\\.")[testName.split("\\.").length-1]+".html");

			inStream = new FileInputStream(afile);
			outStream = new FileOutputStream(bfile);

			byte[] buffer = new byte[1024];

			int length;
			//copy the file content in bytes 
			while ((length = inStream.read(buffer)) > 0){
				outStream.write(buffer, 0, length);
			}

			inStream.close();
			outStream.close();

			System.out.println("File is copied successful!");

		}catch(IOException e){
			try {
				afile.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

}
