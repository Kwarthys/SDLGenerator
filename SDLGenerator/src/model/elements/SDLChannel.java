package model.elements;

import model.SDLElement;
import visitor.Visitor;

public class SDLChannel extends SDLElement {

	public SDLChannel(String name) {
		super(name);
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visitSDLChannel(this);
	}

}
