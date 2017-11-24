package parser;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class ParseManager {
	
	public static void parse(File toParse)
	{
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		
		DocumentBuilder documentBuilder;
		try{
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(toParse);
			
			Node system = document.getElementsByTagName("System").item(0);
			
			System.out.println(getItemName(system));
			
			Node process = document.getElementsByTagName("Process").item(0);
			
			System.out.println(getItemName(process));
			
			//String usr = document.getElementsByTagName("user").item(0).getTextContent();
			//String pwd = document.getElementsByTagName("password").item(0).getTextContent();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Epic fail");
		}		
	}
	
	
	protected static String getItemName(Node node)
	{
		for(int i = 0; i < node.getChildNodes().getLength(); i++)
		{
			Node currentNode = node.getChildNodes().item(i);
			if(currentNode.getNodeName().compareTo("name") == 0)
			{
				return currentNode.getTextContent();
			}
		}
		
		return null;
	}
}
