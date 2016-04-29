package com.apeelingtech.game.events.types;

import com.apeelingtech.game.events.Event;

public class MouseWheelMovedEvent extends Event {

	protected int wheelRotation;
	protected int x, y; // TODO: Mouse Positions probably wont have to
	
	public MouseWheelMovedEvent(int wheelRotation) {
		super(Type.MOUSE_WHEEL_MOVED);
	}
	
	public int getWheelRotation() {
		return wheelRotation;
	}
	
}
