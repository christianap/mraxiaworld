package com.apeelingtech.game.display.gui;

import java.awt.Color;
import java.awt.Graphics2D;

import com.apeelingtech.game.events.Event;

public class GUIRect extends GUIElement {
	
	private Color color;
	private boolean outline = false;
	
	public GUIRect(int x, int y, int width, int height, Color color, boolean outline) {
		super(x, y, width, height);
		this.color = color;
		this.outline = outline;
	}
	
	public GUIRect(int x, int y, int width, int height, Color color) {
		super(x, y, width, height);
		this.color = color;
		outline = false;
	}
	
	@Override
	public void onEvent(Event event) {
	}
	
	@Override
	public void render(Graphics2D g) {
		g.setColor(color);
		if (!outline) {
			g.fillRect(getX(), getY(), getWidth(), getHeight());
		} else {
			g.drawRect(getX(), getY(), getWidth(), getHeight());
		}
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
}
