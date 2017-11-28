package launcher;

import java.io.File;

import model.elements.SDLSystem;
import parser.ParseManager;
import visitor.Visitor;

public class Launcher {

	public static void main(String[] args) {
		SDLSystem s = ParseManager.parse(new File("TestXML.xml"));
		
		Visitor v = new Visitor();
		
		s.accept(v);
	}

}
