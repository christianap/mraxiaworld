package com.apeelingtech.game.display;

import java.awt.event.KeyEvent;

public interface IKeyable {
	
	public void keyTyped(KeyEvent e);
	
	public void keyPressed(KeyEvent e);
	
	public void keyReleased(KeyEvent e);
	
}
