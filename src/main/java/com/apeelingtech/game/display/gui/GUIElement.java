package com.apeelingtech.game.display.gui;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.apeelingtech.game.Game;
import com.apeelingtech.game.events.EventListener;
import com.apeelingtech.game.events.Event;

public abstract class GUIElement implements EventListener {
	
	protected Rectangle bounds;
	protected BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_3BYTE_BGR);
	protected boolean hidden = false;
	
	public GUIElement(int x, int y, int width, int height) {
		bounds = new Rectangle(x, y, width, height);
	}
	
	// TODO: Add tick() method???
	
	public abstract void render(Graphics2D g);
	
	public void onEvent(Event event) {
	}
	
	public final boolean isHidden() {
		return hidden;
	}
	
	public final void hide() {
		hidden = true;
	}
	
	public final void show() {
		hidden = false;
	}
	
	public final int getX() {
		return bounds.x;
	}
	
	public final void setX(int x) {
		this.bounds.x = x;
	}
	
	public final int getY() {
		return bounds.y;
	}
	
	public final void setY(int y) {
		this.bounds.y = y;
	}
	
	public final int getWidth() {
		return bounds.width;
	}
	
	public final void setWidth(int width) {
		this.bounds.width = width;
	}
	
	public final int getHeight() {
		return bounds.height;
	}
	
	public final void setHeight(int height) {
		this.bounds.height = height;
	}
	
	public final Rectangle getBounds() {
		return bounds;
	}
	
	public final void setBounds(int x, int y, int width, int height) {
		bounds.setBounds(x, y, width, height);
	}
	
	public final void setBounds(Rectangle rect) {
		bounds.setBounds(rect);
	}
	
	public void setHorizontalCenter() {
		setX(Game.HEIGHT * Game.SCALE / 2 - getWidth() / 2);
	}
	
	public int getHorizontalCenter() {
		return Game.HEIGHT * Game.SCALE / 2 - getWidth() / 2;
	}
	
	public void setVerticalCenter() {
		setY(Game.HEIGHT * Game.SCALE / 2 - getHeight() / 2);
	}
	
	public int getVerticalCenter() {
		return Game.HEIGHT * Game.SCALE / 2 - getHeight() / 2;
	}
	
	public void setCenter() {
		setHorizontalCenter();
		setVerticalCenter();
	}
	
	public int[] getCenter() {
		int[] result = { getHorizontalCenter(), getVerticalCenter() };
		
		return result;
	}
	
}
