package com.apeelingtech.game.events.types;

import com.apeelingtech.game.events.Event;

public class MouseWheelMovedEvent extends Event {

	protected int wheelRotation;
	
	public MouseWheelMovedEvent(int wheelRotation) {
		super(Type.MOUSE_WHEEL_MOVED);
	}
	
	public int getWheelRotation() {
		return wheelRotation;
	}
	
}
