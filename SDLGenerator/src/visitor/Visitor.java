package visitor;

import model.elements.SDLProcess;
import model.elements.SDLSystem;

public class Visitor {
	
	public static final String indent = "    ";
	
	public void visitSDLProcess(SDLProcess process)
	{
		System.out.println(indent + "VISITOR - Process " + process.getName());
	}
	
	public void visitSDLSystem(SDLSystem system)
	{
		System.out.println("VISITOR - System " + system.getName());
		System.out.println("{");
		for(SDLProcess p : system.getProcesses())
		{
			p.accept(this);
		}

		System.out.println("}");
	}
}
