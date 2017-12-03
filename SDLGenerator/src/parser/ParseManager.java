package parser;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import model.elements.SDLChannel;
import model.elements.SDLProcess;
import model.elements.SDLSystem;
import model.elements.processitems.SDLCommand;
import model.elements.processitems.SDLState;

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
			
			SDLSystem sys =  new SDLSystem(getItemProperty(system, "name"));
			

			NodeList sysChildren = system.getChildNodes();
			
			for(int i = 0; i < sysChildren.getLength(); i++)		/*** LOOKING FOR PROCESSES ***/
			{
				if(sysChildren.item(i).getNodeName().compareTo("Process") == 0)
				{
					SDLProcess p = new SDLProcess(getItemProperty(sysChildren.item(i), "name"));
					
					
					NodeList processChildren = sysChildren.item(i).getChildNodes();
					
					for(int statei = 0; statei < processChildren.getLength(); statei++) /*** LOOKING FOR STATES IN PROCESSES ***/
					{
						if(processChildren.item(statei).getNodeName().compareTo("State") == 0)
						{
							SDLState state = new SDLState(getItemProperty(processChildren.item(statei), "name"));
							
							
							NodeList stateChildren = processChildren.item(statei).getChildNodes();
							
							for(int commandi = 0; commandi < stateChildren.getLength(); commandi++) /*** LOOKING FOR COMMANDS IN STATES ***/
							{
								if(stateChildren.item(commandi).getNodeName().compareTo("command") == 0)
								{
									SDLCommand c = new SDLCommand(getItemProperty(stateChildren.item(commandi), "name"));
									
									state.addCommand(c);
								}
							}
							
							p.addState(state);
						}
					}
					
					
					sys.addProcess(p);
				}
				
				else if(sysChildren.item(i).getNodeName().compareTo("Channel") == 0)
				{
					SDLChannel chan = new SDLChannel(getItemProperty(sysChildren.item(i), "name"), getItemProperty(sysChildren.item(i), "signal"), getItemProperty(sysChildren.item(i), "end"));
					chan.setFromTo(getItemProperty(sysChildren.item(i), "in"), getItemProperty(sysChildren.item(i), "out"));
					sys.addChannel(chan);
				}
			}			
			
			/*
			System.out.println(getItemProperty(system, "name"));
			
			Node process = document.getElementsByTagName("Process").item(0);
			
			System.out.println(getItemProperty(process, "name"));
			*/
			//String usr = document.getElementsByTagName("user").item(0).getTextContent();
			//String pwd = document.getElementsByTagName("password").item(0).getTextContent();
			
			return sys;
			
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
