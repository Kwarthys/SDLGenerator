package model.elements;

import java.util.ArrayList;

import model.SDLElement;
import model.elements.processitems.SDLState;
import visitor.Visitor;

public class SDLProcess extends SDLElement {
	
	protected ArrayList<SDLState> states = new ArrayList<>();

	public SDLProcess(String name) {
		super(name);
	}
	
	public ArrayList<SDLState> getStates()
	{
		return states;
	}
	
	public void addState(SDLState s)
	{
		if(!states.contains(s))
		{
			states.add(s);
		}
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visitSDLProcess(this);		
	}

}
