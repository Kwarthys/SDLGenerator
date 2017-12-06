package visitor;

import model.elements.SDLBlock;
import model.elements.SDLChannel;
import model.elements.SDLProcess;
import model.elements.SDLSystem;
import model.elements.processitems.SDLCommand;
import model.elements.processitems.SDLState;

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
		
		if(!process.getStates().isEmpty())
		{
			result.append(indent + "start;\n");
			result.append(indent + "nextState " + process.getStates().get(0).getName() + ";\n\n");
		
			for(SDLState state : process.getStates())
			{
				state.accept(this);
			}
		}		
		result.append("endprocess " + process.getName() + "\n" + "\n");
	}
	
	public void visitSDLSystem(SDLSystem system)
	{
		result.append("System " + system.getName() + "\n");

		result.append(createBlockDefinition(system) + "\n");

		result.append(createSignalDefinition(system) + "\n");
		
		for(SDLChannel c : system.getChannels())
		{
			c.accept(this);
		}

		result.append("endsystem " + system.getName() + "\n\n");
		
		for(SDLBlock b : system.getBlocks())
		{
			b.accept(this);
		}
	}

	public void visitSDLBlock(SDLBlock block)
	{
		result.append("block " + block.getName() + "\n");
		
		for(SDLChannel c : block.getChannels())
		{
			c.accept(this);
		}
		
		result.append(createProcessDefinition(block) + "\n");
		
		for(String connect : block.getConnections())
		{
			result.append(indent + "connect " + connect + ";\n");
		}
		
		result.append("\n");

		result.append("endblock " + block.getName() + "\n\n");
		
		for(SDLProcess p : block.getProcesses())
		{
			p.accept(this);
		}
	}

	public void visitSDLChannel(SDLChannel channel)
	{
		result.append(indent + "channel " + channel.getName() + " nodelay from " + channel.getFrom() + " to " + channel.getTo() + " with " + channel.getSignal() + ";\n");
		result.append(indent + "endchannel " + channel.getEndChannel() + ";\n\n");
	}

	public void visitSDLState(SDLState state)
	{
		result.append(indent + "state " + state.getName() + ";\n");
		
		for(SDLCommand c : state.getCommands())
		{
			c.accept(this);
		}

		result.append(indent + indent + "nextstate " + state.getNextStateName() + ";\n");
		
		result.append(indent + "endstate " + state.getName() + ";\n\n");
	}

	public void visitSDLCommand(SDLCommand cmd)
	{
		result.append(indent + indent + cmd.getName() + ";\n");
	}
	
	protected String createBlockDefinition(SDLSystem system)
	{
		StringBuffer code = new StringBuffer();
		
		if(!system.getBlocks().isEmpty())
		{			
			for(int i = 0; i < system.getBlocks().size(); i++)
			{
				code.append(indent + "block ");
				SDLBlock block = system.getBlocks().get(i);
				code.append(block.getName() + " referenced;\n");
			}			
		}
		
		return code.toString();
	}

	protected String createProcessDefinition(SDLBlock b)
	{		
		StringBuffer code = new StringBuffer();
		
		if(!b.getProcesses().isEmpty())
		{
			
			for(int i = 0; i < b.getProcesses().size(); i++)
			{
				code.append(indent + "process ");
				SDLProcess p = b.getProcesses().get(i);
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
