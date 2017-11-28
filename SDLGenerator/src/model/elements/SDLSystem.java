package model.elements;

import java.util.ArrayList;

import model.SDLElement;
import visitor.Visitor;

public class SDLSystem extends SDLElement {
	
	protected ArrayList<SDLProcess> processes = new ArrayList<>();

	public ArrayList<SDLProcess> getProcesses() {
		return processes;
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
}
