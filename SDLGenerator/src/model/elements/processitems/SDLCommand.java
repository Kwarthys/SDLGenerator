package model.elements.processitems;

import model.SDLElement;
import visitor.Visitor;

public class SDLCommand extends SDLElement{
	
	protected String transition;

	public SDLCommand(String name) {
		super(name);
	}

	@Override
	public void accept(Visitor visitor)
	{
		visitor.visitSDLCommand(this);		
	}

}
