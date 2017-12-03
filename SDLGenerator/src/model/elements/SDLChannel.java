package model.elements;

import model.SDLElement;
import visitor.Visitor;

public class SDLChannel extends SDLElement {

	protected String signal;
	protected String endChannel;
	protected String from;
	protected String to;
	
	public String getFrom()
	{
		return this.from;
	}
	
	public String getTo()
	{
		return this.to;
	}
	
	public void setFromTo(String from, String to)
	{
		this.from = from;
		this.to = to;
	}

	public String getEndChannel() {
		return endChannel;
	}

	public void setEndChannel(String endChannel) {
		this.endChannel = endChannel;
	}

	public String getSignal() {
		return signal;
	}

	public void setSignal(String signal) {
		this.signal = signal;
	}

	public SDLChannel(String name) {
		super(name);
	}

	public SDLChannel(String name, String signal) {
		super(name);
		this.signal = signal;
	}

	public SDLChannel(String name, String signal, String endChannel) {
		this(name, signal);
		this.endChannel = endChannel;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visitSDLChannel(this);
	}

}
