package launcher;

import java.io.File;

import parser.ParseManager;

public class Launcher {

	public static void main(String[] args) {
		ParseManager.parse(new File("TestXML.xml"));
		System.out.println("perdu");
	}

}
