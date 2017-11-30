package visitor;

import model.elements.SDLChannel;
import model.elements.SDLProcess;
import model.elements.SDLSystem;

public class Visitor {
	
	public static final String indent = "    ";
	
	protected StringBuffer result = new StringBuffer();
	
	public String getResult()
	{
		return result.toString();
	}
	
	public void visitSDLProcess(SDLProcess process)
	{
		result.append("process " + process.getName() + "\n");
		result.append("endprocess " + process.getName() + "\n" + "\n");
	}
	
	public void visitSDLSystem(SDLSystem system)
	{
		result.append("System " + system.getName() + "\n");

		result.append(createSignalDefinition(system) + "\n");
		
		for(SDLChannel c : system.getChannels())
		{
			c.accept(this);
		}
		
		result.append(createProcessDefinition(system) + "\n");

		result.append("endsystem " + system.getName() + "\n\n");
		
		for(SDLProcess p : system.getProcesses())
		{
			p.accept(this);
		}
	}

	public void visitSDLChannel(SDLChannel channel)
	{
		result.append(indent + "channel " + channel.getName() + " nodelay from X to Y with " + channel.getSignal() + ";\n");
		result.append(indent + "endchannel " + channel.getEndChannel() + ";\n\n");
	}

	protected String createProcessDefinition(SDLSystem s)
	{		
		StringBuffer code = new StringBuffer();
		
		if(!s.getProcesses().isEmpty())
		{
			
			for(int i = 0; i < s.getProcesses().size(); i++)
			{
				code.append(indent + "process ");
				SDLProcess p = s.getProcesses().get(i);
				code.append(p.getName() + " referenced;\n");
			}
			
		}
		
		return code.toString();
	}
	
	
	protected String createSignalDefinition(SDLSystem s)
	{		
		StringBuffer code = new StringBuffer();
		
		if(!s.getChannels().isEmpty())
		{
			code.append(indent + "signal ");
			
			for(int i = 0; i < s.getChannels().size(); i++)
			{
				SDLChannel c = s.getChannels().get(i);
				code.append(c.getSignal());
				if(i < s.getChannels().size()-1)code.append(",");
			}
			
			code.append(";\n");
		}
		
		return code.toString();
	}
}
