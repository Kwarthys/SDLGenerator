package visitor;

import model.elements.SDLChannel;
import model.elements.SDLProcess;
import model.elements.SDLSystem;

public class Visitor {
	
	public static final String indent = "    ";
	
	public void visitSDLProcess(SDLProcess process)
	{
		System.out.println(indent + "Process " + process.getName());
	}
	
	public void visitSDLSystem(SDLSystem system)
	{
		System.out.println("System " + system.getName());
		System.out.println("{");
		
		for(SDLProcess p : system.getProcesses())
		{
			p.accept(this);
		}
		
		for(SDLChannel c : system.getChannels())
		{
			c.accept(this);
		}

		System.out.println("}");
	}

	public void visitSDLChannel(SDLChannel channel) {
		System.out.println(indent + "Chanel " + channel.getName());
	}
}
