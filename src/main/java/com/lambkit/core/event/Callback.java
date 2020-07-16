package com.lambkit.core.event;

public interface Callback {
	public void execute(Event event);
	public void error(Event event);
}
