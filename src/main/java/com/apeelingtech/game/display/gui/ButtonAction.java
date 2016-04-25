package com.apeelingtech.game.display.gui;

import java.awt.event.MouseEvent;

public abstract class ButtonAction {
	
	public abstract void action(MouseEvent e);
	
	public abstract void mouseEnter(MouseEvent e);
	
	public abstract void mouseExit(MouseEvent e);
	
}
