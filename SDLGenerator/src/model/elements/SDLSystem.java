package model.elements;

import java.util.ArrayList;

import model.SDLElement;
import visitor.Visitor;

public class SDLSystem extends SDLElement {

	protected ArrayList<SDLChannel> channels = new ArrayList<>();
	protected ArrayList<SDLBlock> blocks = new ArrayList<>();

	public SDLSystem(String name)
	{
		super(name);
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visitSDLSystem(this);
	}

	public void addChannel(SDLChannel c)
	{
		if(!channels.contains(c))
		{
			channels.add(c);
		}		
	}

	public ArrayList<SDLChannel> getChannels() {
		return channels;
	}

	public void addBlock(SDLBlock b)
	{
		if(!blocks.contains(b))
		{
			blocks.add(b);
		}		
	}

	public ArrayList<SDLBlock> getBlocks() {
		return blocks;
	}
}
