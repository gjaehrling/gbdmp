import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/*
 * -------------------------------------------------------------------------------------------------------------------
	File name: $HeadURL: file:///home/hadoop/Dropbox/SVNRepository/trunk/java_dev/JDOMExample/src/JDOMExample.java $
	Revision: $Revision: 95 $
	Last modified: $Date: 2016-07-03 13:24:22 +0200 (Sun, 03 Jul 2016) $
	Last modified by: $Author: hadoop $
	$Id: JDOMExample.java 95 2016-07-03 11:24:22Z hadoop $
 * -------------------------------------------------------------------------------------------------------------------
 */

public class JDOMExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File("/home/hadoop/SVNWorkplace/trunk/java_dev/JDOMExample/file.xml");
		//File xmlFile = new File("/home/hadoop/SVNWorkplace/trunk/java_dev/JDOMExample/temp.xml");

		try {

			Document document = (Document) builder.build(xmlFile);

			Element rootNode = document.getRootElement();
			Namespace ns = rootNode.getNamespace();
			List list = rootNode.getChildren("action", ns);
			List<String> strParams = new ArrayList<String>();
			
			System.out.println("Namespace: " + ns);
			System.out.println("Root Node: " + rootNode);
			System.out.println("Root Namespace: " + rootNode.getNamespace());
			System.out.println("Root Children: " + list);

			for (int i = 0; i < list.size(); i++) {
				// on level "action"
				Element node = (Element) list.get(i);
				
				// set the Namespace of the sub Element!!!
				Namespace elNs =  Namespace.getNamespace("uri:oozie:bdmp-python-action:0.1");
				List elementList = node.getChildren("python", elNs);
				System.out.println("Element python: " + elementList);

				for (int n = 0; n < elementList.size(); n++) {
					Element subElement = (Element) elementList.get(n);
					List argumentList = subElement.getChildren("arg", elNs);
									
					System.out.println("size of the argument list: " + argumentList.size());
					
					
					for(int e = 0; e < argumentList.size(); e++){
						Element argument = (Element) argumentList.get(e);
						strParams.add(elementList.get(n).toString());
						System.out.println(argument.getText());
					}
				}
				System.out.println("list of arguments as string array: " + strParams.toString() );
				
				String propertyTest = System.getProperty("bla.bla.bla");
				System.out.println("System getProperty test: " + propertyTest);
			}

		} catch (IOException io) {
			System.out.println(io.getMessage());
		} catch (JDOMException jdomex) {
			System.out.println(jdomex.getMessage());
		}
		
		//modifyXML();
		checkBoolean();
	}
	
	public static void modifyXML(){
		try {
			SAXBuilder builder = new SAXBuilder();
			File xmlFile = new File("/home/hadoop/SVNWorkplace/trunk/java_dev/JDOMExample/actionXML.xml");
			
			Document actionXmlDocument = (Document) builder.build(xmlFile);
			Element rootNode = actionXmlDocument.getRootElement();
			Element property = rootNode.getChild("property");
			List properties = rootNode.getChildren("property");
			
			System.out.println(rootNode.toString());
			System.out.println(property.getChild("name"));
			
			System.out.println(properties.size());
			
			for(int i =0; i < properties.size(); i++){
				Element propertyElement = (Element) properties.get(i);

				//System.out.println(propertyElement.getChild("value").getText());
//				if(propertyElement.getChildText("name").contentEquals("BDMP_ENVVAR_BDMP_CONFIG_EXT")){
//					//System.out.println(propertyElement.getValue());
//					System.out.println(propertyElement.getChild("value").getText());
//					propertyElement.getChild("value").setText("bla");
//					
//				}
				
				if(propertyElement.getChild("value").getText().isEmpty() &&
						!propertyElement.getChild("name").getText().contentEquals("dfs.https.address")){
					System.out.println("name: " + propertyElement.getChild("name").getText() + " value: " + propertyElement.getChild("value").getText());
					propertyElement.getChild("value").setText("TEST");
				}
				
			}

			XMLOutputter xmlOutput = new XMLOutputter();
			//xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(actionXmlDocument, new FileWriter("/home/hadoop/SVNWorkplace/trunk/java_dev/JDOMExample/actionXml.xml"));

			
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void checkBoolean(){
		String test = new String();
		boolean b = false;
		
		test = "test";
		test = "TRUE";
		
		if(!test.isEmpty()){
			b = Boolean.valueOf(test.toLowerCase());
		}
		
		if(b){
			System.out.println(test + " is equal to true");
		}
		else {
			System.out.println(test + " is equal to false");
		}

	}

}
