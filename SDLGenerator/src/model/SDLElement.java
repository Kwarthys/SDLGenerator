package model;

import visitor.Visitable;
import visitor.Visitor;

public abstract class SDLElement implements Visitable{
	protected String name;
	
	public SDLElement(String name) {
		this.name = name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}

	@Override
	abstract public void accept(Visitor visitor);
}
