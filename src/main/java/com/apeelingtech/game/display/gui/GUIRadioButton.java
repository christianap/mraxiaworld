package com.apeelingtech.game.display.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class GUIRadioButton extends GUIElement implements IClickable {
	
	public GUIText label;
	
	private boolean enabled = false;
	private final int PADDING = 5;
	private final int radioSize = 20;
	public ButtonAction buttonAction;
	
	public GUIRadioButton(String text, Color color, int x, int y) {
		super(x, y, 20, 20);
		label = new GUIText(text, color, x + radioSize + PADDING, y + radioSize, 18);
		setWidth(radioSize + PADDING + (int) label.getStringBounds().getWidth());
	}
	
	public GUIRadioButton(String text, Color color, int x, int y, int fontSize) {
		super(x, y, 20, 20);
		label = new GUIText(text, color, x + radioSize + PADDING, y + radioSize, fontSize);
		setWidth(radioSize + PADDING + (int) label.getStringBounds().getWidth());
	}
	
	public GUIRadioButton(String text, String font, int fontStyle, Color color, int x, int y) {
		super(x, y, 20, 20);
		label = new GUIText(text, font, fontStyle, color, x + radioSize + PADDING, y + radioSize, 18);
		setWidth(radioSize + PADDING + (int) label.getStringBounds().getWidth());
	}
	
	public GUIRadioButton(String text, String font, int fontStyle, Color color, int x, int y, int fontSize) {
		super(x, y, 20, 20);
		label = new GUIText(text, font, fontStyle, color, x + radioSize + PADDING, y + radioSize, fontSize);
		setWidth(radioSize + PADDING + (int) label.getStringBounds().getWidth());
	}
	
	public void enable() {
		enabled = true;
	}
	
	public void disable() {
		enabled = false;
	}
	
	@Override
	public void addAction(ButtonAction buttonAction) {
		this.buttonAction = buttonAction;
	}
	
	@Override
	public void click(MouseEvent e) {
		enabled = true;
		if (buttonAction != null) {
			this.buttonAction.action(e);
		}
	}
	
	@Override
	public void mouseEnter(MouseEvent e) {
		if (buttonAction != null) {
			this.buttonAction.mouseEnter(e);
		}
	}
	
	@Override
	public void mouseExit(MouseEvent e) {
		if (buttonAction != null) {
			this.buttonAction.mouseExit(e);
		}
	}
	
	@Override
	public void render(Graphics2D g) {
		label.render(g);
		
		g.setColor(Color.WHITE);
		g.fillRoundRect(getX(), getY(), radioSize, radioSize, 20, 20);
		if (enabled) {
			g.setColor(Color.BLUE);
			g.fillRoundRect(getX() + 2, getY() + 2, radioSize - (2 * 2), radioSize - (2 * 2), 20, 20);
		}
	}
	
}