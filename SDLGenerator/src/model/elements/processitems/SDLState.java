package model.elements.processitems;

import java.util.ArrayList;

import model.SDLElement;
import visitor.Visitor;

public class SDLState extends SDLElement{	

	protected ArrayList<SDLCommand> commands = new ArrayList<>();

	public SDLState(String name) {
		super(name);
	}
	
	public ArrayList<SDLCommand> getCommands()
	{
		return commands;
	}
	
	public void addCommand(SDLCommand c)
	{
		if(!commands.contains(c))
		{
			commands.add(c);
		}
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visitSDLState(this);	
	}

}