package com.apeelingtech.game.display.gui;

import com.apeelingtech.game.events.types.MouseMovedEvent;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class GUIButton extends GUIElement {
	public String label;
	
	@SuppressWarnings("unused")
	private int charHeight, charWidth, fontSize, arcWidth, arcHeight, outlineWidth = 2;
	private boolean isFramed;
	private boolean isRounded;
	private boolean isHovered;
	private Font font;
	private Color[] colors = { Color.gray, Color.lightGray, new Color(240, 240, 240), // Regular colors
								Color.lightGray, Color.white, new Color(255, 255, 255)}; // Hover colors
	public Rectangle2D stringBounds;
	
	// TODO: Change button width to match width of text plus padding
	public GUIButton(String label, boolean isFramed, int x, int y, int width, int height) {
		super(x, y, width, height);
		this.label = label;
		this.isFramed = isFramed;
		fontSize = height - (height / 4);
		
		this.font = new Font("Ariel", Font.PLAIN, fontSize);
		init();
	}
	
	public GUIButton(String label, String font, int fontStyle, boolean isFramed, int x, int y, int width, int height) {
		super(x, y, width, height);
		this.label = label;
		this.isFramed = isFramed;
		fontSize = height - (height / 4);
		
		this.font = new Font(font, fontStyle, fontSize);
		init();
	}
	
	public GUIButton(String label, int x, int y, GUIButton button) {
		super(x, y, button.getWidth(), button.getHeight());
		this.label = label;
		this.isFramed = button.isFramed;
		fontSize = button.getHeight() - (button.getHeight() / 4);
		
		outlineWidth = button.outlineWidth;
		isRounded = button.isRounded;
		arcWidth = button.arcWidth;
		arcHeight = button.arcHeight;
		
		this.font = button.font;
		init();
	}
	
	private void init() {
		Graphics2D g2 = image.createGraphics();
		FontMetrics fm = g2.getFontMetrics(font);
		
		charHeight = fm.getAscent();
		charWidth = fm.charWidth(' ');
		stringBounds = fm.getStringBounds(label, g2);
		
		g2.dispose();
	}

	@Override
	public boolean mouseEntered(MouseMovedEvent event) {
		isHovered = true;
		if (action != null) {
			action.mouseEntered(event);
		}
		return true; // TODO: false?
	}

	@Override
	public boolean mouseExited(MouseMovedEvent event) {
		isHovered = false;
		if (action != null) {
			action.mouseExited(event);
		}
		return false;
	}

	@Override
	public void render(Graphics2D g) { // TODO: when isHover == true, apply the hovering colors
		if (isRounded) {
			if (isFramed) {
				if (outlineWidth > 0) {
					g.setColor(colors[1]);
					if (isHovered) {
						g.setColor(colors[4]);
					}
					g.fillRoundRect(bounds.x, bounds.y, bounds.width, bounds.height, arcWidth, arcHeight);
				}
				g.setColor(colors[0]);
				if (isHovered) {
					g.setColor(colors[3]);
				}
				g.fillRoundRect(bounds.x + outlineWidth, bounds.y + outlineWidth, bounds.width - (outlineWidth * 2), bounds.height - (outlineWidth * 2), arcWidth, arcHeight);
			}
		} else {
			if (isFramed) {
				if (outlineWidth > 0) {
					g.setColor(colors[1]);
					if (isHovered) {
						g.setColor(colors[4]);
					}
					g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
				}
				g.setColor(colors[0]);
				if (isHovered) {
					g.setColor(colors[3]);
				}
				g.fillRect(bounds.x + outlineWidth, bounds.y + outlineWidth, bounds.width - (outlineWidth * 2), bounds.height - (outlineWidth * 2));
			}
		}
		g.setColor(colors[2]);
		if (isHovered) {
			g.setColor(colors[5]);
		}
		g.setFont(font);
		int x = bounds.x + ((bounds.width / 2) - (int) (stringBounds.getWidth() / 2));
		int y = bounds.y + ((bounds.height / 2) + (int) (stringBounds.getHeight() / 3));
		g.drawString(label, x, y);
	}
	
	public void setColors(Color bgColor, Color fgColor, Color txtColor,
							Color hoverBGColor, Color hoverFGColor, Color hoverTxtColor) {
		colors[0] = bgColor;
		colors[1] = fgColor;
		colors[2] = txtColor;
		colors[3] = hoverBGColor;
		colors[4] = hoverFGColor;
		colors[5] = hoverTxtColor;
	}
	
	public void setOutline(int outlineWidth) {
		this.outlineWidth = outlineWidth;
	}
	
	public void setRounded(int arcWidth, int arcHeight) {
		isRounded = true;
		this.arcWidth = arcWidth;
		this.arcHeight = arcHeight;
	}
	
	public void setText(String text) {
		label = text;
	}
	
	public void setPosition(int x, int y) {
		bounds.setBounds(x, y, bounds.width, bounds.height);
	}
	
}
