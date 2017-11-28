package model.elements;

import java.util.ArrayList;

import model.SDLElement;
import visitor.Visitor;

public class SDLSystem extends SDLElement {

	protected ArrayList<SDLProcess> processes = new ArrayList<>();
	protected ArrayList<SDLChannel> channels = new ArrayList<>();

	public ArrayList<SDLProcess> getProcesses() {
		return processes;
	}

	public ArrayList<SDLChannel> getChannels() {
		return channels;
	}

	public SDLSystem(String name)
	{
		super(name);
	}
	
	public void addProcess(SDLProcess p)
	{
		if(!processes.contains(p))
		{
			processes.add(p);
		}
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visitSDLSystem(this);
	}

	public void addChannel(SDLChannel c)
	{
		if(!channels.contains(c))
		{
			channels.add(c);
		}		
	}
}
