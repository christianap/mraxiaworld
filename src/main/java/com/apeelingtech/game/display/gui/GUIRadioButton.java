package com.apeelingtech.game.display.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import com.apeelingtech.game.events.Event;
import com.apeelingtech.game.events.EventDispatcher;
import com.apeelingtech.game.events.types.MousePressedEvent;

public class GUIRadioButton extends GUIElement {
	
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
	public void onEvent(Event event) {
		EventDispatcher eventDispatcher = new EventDispatcher(event);
		eventDispatcher.dispatch(Event.Type.MOUSE_PRESSED, (Event e) -> click((MousePressedEvent) e));
		//eventDispatcher.dispatch(Event.Type.MOUSE_PRESSED, );
	}
	
	public void addAction(ButtonAction buttonAction) {
		this.buttonAction = buttonAction;
	}
	
	public boolean click(MousePressedEvent e) {
		enabled = true;
		if (buttonAction != null) {
			buttonAction.action(e); // Not correct type
		}
	}
	
	/*public boolean mouseEnter(MouseMovedEvent e) {
		if (buttonAction != null) {
			this.buttonAction.mouseEnter(e);
		}
	}
	
	public boolean mouseExit(MouseEvent e) {
		if (buttonAction != null) {
			this.buttonAction.mouseExit(e);
		}
	}*/
	
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