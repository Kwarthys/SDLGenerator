package parser;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import model.elements.SDLSystem;

public class ParseManager {
	
	/**
	 * 
	 * @param toParse
	 * @return the SDLSystem item that will hold all the data.
	 */
	public static SDLSystem parse(File toParse)
	{
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		
		DocumentBuilder documentBuilder;
		try{
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(toParse);
			
			Node system = document.getElementsByTagName("System").item(0);
			
			System.out.println(getItemProperty(system, "name"));
			
			Node process = document.getElementsByTagName("Process").item(0);
			
			System.out.println(getItemProperty(process, "name"));
			
			//String usr = document.getElementsByTagName("user").item(0).getTextContent();
			//String pwd = document.getElementsByTagName("password").item(0).getTextContent();
			
			return new SDLSystem(getItemProperty(system, "name"));
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Epic fail");
		}	
		
		//Not supposed to get here 
		return null;
	}
	
	
	/**
	 * Will look for the Property in the Node in his first generation of Children. Non-recursive.
	 * 
	 * @param node Node to explore
	 * @param property Property to look for
	 * @return The text associated to the property, null if no matching property found.
	 */
	protected static String getItemProperty(Node node, String property)
	{
		for(int i = 0; i < node.getChildNodes().getLength(); i++)
		{
			Node currentNode = node.getChildNodes().item(i);
			if(currentNode.getNodeName().compareTo(property) == 0)
			{
				return currentNode.getTextContent();
			}
		}
		
		return null;
	}
}
