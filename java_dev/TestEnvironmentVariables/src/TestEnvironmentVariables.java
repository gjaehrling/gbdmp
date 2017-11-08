import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.collections.Transformer;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class TestEnvironmentVariables {

	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, TransformerConfigurationException, JDOMException {
		// TODO Auto-generated method stub+
		String dir = System.getProperty("user.dir");
		System.out.println("current directory: " + dir);
		String[] test_variable = System.getenv("SPARK_HOME").split("/");
		System.out.println("test variable: " + test_variable[test_variable.length-1]);
		//System.out.println(parts[parts.length-1]);
		System.out.println(dir + "/" + test_variable[test_variable.length-1]);
		
		List<String> myList = new ArrayList<String>();
		myList.add("parameter 1");
		myList.add("parameter 2");
		
		for(ListIterator<String> iter = myList.listIterator(); iter.hasNext(); )
		System.out.println(iter.next());
		
		/*
		File f = new File("/home/hadoop/SVNWorkplace/SVNRepository/trunk/java_dev/TestEnvironmentVariables/test_link");
		System.out.println("link: " + f.getAbsolutePath());
		System.out.println("follow link: " + f.getCanonicalFile());
		File to = new File("/home/hadoop/SVNWorkplace/SVNRepository/trunk/java_dev/TestEnvironmentVariables/test_file");
		
		Files.copy(f.getCanonicalFile().toPath(), to.toPath());
		*/
		setEnvironmentVariable();
		
		System.out.println("call additionalEnvVars:");

		additonalEnvVars();
	}
	
	static void setEnvironmentVariable() throws IOException{
		System.out.println(System.getenv("JAVA_HOME"));
		ProcessBuilder pb = new ProcessBuilder("/bin/sh", "set");
	    Map<String, String> env = pb.environment();
	    System.out.println(env);
	    env.put("HADOOP_HOME", "/home/hadoop");
	    pb.start();
	    System.out.println(System.getenv());
	}
	
	static void additonalEnvVars() throws FileNotFoundException, IOException, ParserConfigurationException, SAXException, TransformerConfigurationException, JDOMException {
		List<String> parameters = new ArrayList<String>();
		Map<String, String> envParameters = new HashMap<String, String>();
		parameters.add("test1=/home/hadoop");
		parameters.add("test2=/opt/cloudera");
		parameters.add("test3=1234567");
		parameters.add(" ");
		
		for(String p:parameters){
			if(p.split("=").length > 1){
				String[] parameterList = p.split("=");
					
				System.out.println("Key: " + parameterList[0] + " Value: "+ parameterList[1]);
				envParameters.put(parameterList[0], parameterList[1]);
			}
		}
		
		System.out.println(envParameters);
		
		System.out.println("test properties:");
		File actionXML = new File("/home/hadoop/SVNWorkplace/SVNRepository/trunk/java_dev/TestEnvironmentVariables/action.xml");
		
		/*
		Properties prop = new Properties();
		prop.load(new FileInputStream(actionXML));
		prop.put("<property><name>"+"BDMP_TMP_OOZIE_JOB_ID"+"</name>", "<value>"+"9999999999999"+"</value>");
		FileOutputStream output = new FileOutputStream(actionXML);
		//prop.store(output, "this is manually edited");
		
		
		System.out.println("test PropertiesConfiguration;");
		PropertiesConfiguration config;
		
		*/
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(actionXML);  
        
        SAXBuilder builder = new SAXBuilder();
        Document actionXMLDocument = (Document) builder.build(actionXML);
        Element rootNode = actionXMLDocument.getDocumentElement();
        List properties = (List) rootNode.getChildNodes();		

	}

}
