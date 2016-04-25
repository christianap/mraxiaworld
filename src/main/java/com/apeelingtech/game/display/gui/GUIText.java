package com.apeelingtech.game.display.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import com.apeelingtech.game.Game;

public class GUIText extends GUIElement {
	
	private String text = "";
	private Font font;
	private Color color = Color.GREEN;
	private Rectangle2D stringBounds;
	@SuppressWarnings("unused")
	private int charHeight, charWidth, fontSize;
	
	public GUIText(String text, Color color, int x, int y, int fontSize) {
		super(x, y, 0, 0);
		this.text = text;
		this.color = color;
		this.fontSize = fontSize;
		this.font = new Font("Ariel", Font.PLAIN, fontSize);
		init();
	}
	
	public GUIText(String text, String font, int fontStyle, Color color, int x, int y, int fontSize) {
		super(x, y, 0, 0);
		this.text = text;
		this.color = color;
		this.fontSize = fontSize;
		this.font = new Font(font, fontStyle, fontSize);
		init();
	}
	
	private void init() {
		Graphics2D g2 = image.createGraphics();
		FontMetrics fm = g2.getFontMetrics(font);
		
		charHeight = fm.getAscent();
		charWidth = fm.charWidth(' ');
		stringBounds = fm.getStringBounds(text, g2);
		
		g2.dispose();
	}
	
	@Override
	public void render(Graphics2D g) {
		g.setColor(color);
		g.setFont(font);
		g.drawString(text, getX(), getY());
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public Font getFont() {
		return font;
	}
	
	public void setFont(String font, int fontStyle) {
		this.font = new Font(font, fontStyle, fontSize);
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	@Override
	public void setHorizontalCenter() {
		int x = ((Game.WIDTH * Game.SCALE / 2) - (int) (stringBounds.getWidth() / 2));
		setX(x);
	}
	
	@Override
	public int getHorizontalCenter() {
		return ((Game.WIDTH * Game.SCALE / 2) - (int) (stringBounds.getWidth() / 2));
	}
	
	@Override
	public void setVerticalCenter() {
		int y = ((Game.HEIGHT * Game.SCALE / 2) - (int) (stringBounds.getHeight() / 2));
		setY(y);
	}
	
	@Override
	public int getVerticalCenter() {
		return ((Game.HEIGHT * Game.SCALE / 2) - (int) (stringBounds.getHeight() / 2));
	}
	
	@Override
	public void setCenter() {
		setHorizontalCenter();
		setVerticalCenter();
	}
	
	@Override
	public int[] getCenter() {
		int[] result = { getHorizontalCenter(), getVerticalCenter() };
		return result;
	}
	
}
