package com.apeelingtech.game.display.gui;

import java.awt.event.MouseEvent;

public interface IClickable {
	
	public void addAction(ButtonAction buttonAction);
	
	public void click(MouseEvent e);
	
	public void mouseEnter(MouseEvent e);
	
	public void mouseExit(MouseEvent e);
	
}
