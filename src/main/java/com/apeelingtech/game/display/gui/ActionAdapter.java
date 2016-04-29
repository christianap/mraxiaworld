package com.apeelingtech.game.display.gui;

import com.apeelingtech.game.events.types.*;

public class ActionAdapter {
	
	public boolean mousePressed(MousePressedEvent event) {
		return false;
    }

    public boolean mouseReleased(MouseReleasedEvent event) {
    	return false;
    }

    public boolean mouseMoved(MouseMovedEvent event) {
        return false;
    }

    public boolean mouseEntered(MouseMovedEvent event) {
    	return false;
    }

    public boolean mouseExited(MouseMovedEvent event) {
    	return false;
    }

    public boolean mouseWheelMoved(MouseWheelMovedEvent event) {
    	return false;
    }

    public boolean keyPressed(KeyPressedEvent event) {
    	return false;
    }

    public boolean keyReleased(KeyReleasedEvent event) {
    	return false;
    }

	
}
