package model.elements;

import java.util.ArrayList;

import model.SDLElement;

public class SDLSystem extends SDLElement {
	
	protected ArrayList<SDLProcess> processes = new ArrayList<>();

	public SDLSystem(String name)
	{
		this.name = name;
	}
	
	public void addProcess(SDLProcess p)
	{
		if(!processes.contains(p))
		{
			processes.add(p);
		}
	}
}
