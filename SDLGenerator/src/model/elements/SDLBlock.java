package model.elements;

import java.util.ArrayList;

import model.SDLElement;
import visitor.Visitor;

public class SDLBlock extends SDLElement {
	
	protected ArrayList<SDLProcess> processes = new ArrayList<>();
	protected ArrayList<SDLChannel> channels = new ArrayList<>();

	protected ArrayList<String> connections = new ArrayList<>();

	public SDLBlock(String name) {
		super(name);
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visitSDLBlock(this);
	}
	
	public void addConnect(String connection)
	{
		connections.add(connection);
	}

	public ArrayList<String> getConnections() {
		return connections;
	}
	
	public void addProcess(SDLProcess p)
	{
		if(!processes.contains(p))
		{
			processes.add(p);
		}
	}

	public ArrayList<SDLProcess> getProcesses() {
		return processes;
	}

	public void addChannel(SDLChannel c)
	{
		if(!channels.contains(c))
		{
			channels.add(c);
		}		
	}

	public ArrayList<SDLChannel> getChannels() {
		return channels;
	}

}
