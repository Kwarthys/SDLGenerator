package model.elements;

import model.SDLElement;
import visitor.Visitor;

public class SDLProcess extends SDLElement {

	public SDLProcess(String name) {
		super(name);
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visitSDLProcess(this);		
	}

}
