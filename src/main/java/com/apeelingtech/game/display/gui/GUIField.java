package com.apeelingtech.game.display.gui;

import com.apeelingtech.game.events.types.MousePressedEvent;
import com.apeelingtech.game.events.types.MouseMovedEvent;
import com.apeelingtech.game.events.types.KeyPressedEvent;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

public class GUIField extends GUIElement {
	
	private boolean focus = false;
	private int time;
	private boolean drawLine = false;
	private String text;
	private int textLocation = 0;
	public int charHeight;
	public int charWidth;
	
	private Font font = new Font("Courier", Font.PLAIN, 25);
	public Rectangle2D stringBounds;
	
	public GUIField(String text, int x, int y, int width, int height) {
		super(x, y, width, height);
		this.text = text;
		initFont(text);
	}
	
	private void initFont(String string) {
		Graphics2D g2 = image.createGraphics();
		FontMetrics fm = g2.getFontMetrics(font);
		
		stringBounds = fm.getStringBounds(string, g2);
		stringBounds.setRect(stringBounds.getX(), stringBounds.getY(), stringBounds.getWidth() + 1, stringBounds.getHeight());
		charWidth = fm.charWidth(' ');
		charHeight = fm.getAscent();
		
		g2.dispose();
	}
	
	@Override
	public void render(Graphics2D g) {
		time++;
		if (time % 25 == 0 && focus) {
			drawLine = !drawLine;
		}
		g.setColor(new Color(240, 240, 240));
		g.fillRect(getX(), getY(), getWidth(), getHeight());
		g.setColor(new Color(50, 50, 50));
		g.drawRect(getX() + 1, getY() + 1, getWidth() - 2, getHeight() - 2);
		g.setFont(font);
		g.drawString(text.toLowerCase(), getX() + 5, getY() + getHeight() - 6);
		if (drawLine && focus) {
			g.setColor(new Color(30, 30, 30));
			g.drawLine(getX() + 5 + (charWidth * textLocation), getY() + 4, getX() + 5 + (charWidth * textLocation), getY() + getHeight() - 4);
		}
	}
	
	public String getText() {
		return text.toLowerCase();
	}
	
	@Override
	public boolean mousePressed(MousePressedEvent e) {
		focus = true;
		if (action != null) {
			action.mousePressed(e);
		}
		return true;
	}
	
	@Override
	public boolean mouseEntered(MouseMovedEvent e) {
		focus = true;
		if (action != null) {
			action.mouseEntered(e);
		}
		return true;
	}
	
	@Override
	public boolean mouseExited(MouseMovedEvent e) {
		focus = false;
		if (action != null) {
			action.mouseExited(e);
		}
		return true;
	}
	
	@Override
	public boolean keyPressed(KeyPressedEvent e) {
		if (focus) {
			switch (e.getKeyCode()) {
				case KeyEvent.VK_BACK_SPACE:
					if (textLocation != 0) {
						String newText = "";
						for (int i = 0; i < text.length(); i++) {
							if (i != textLocation - 1) {
								newText += text.charAt(i);
							}
						}
						text = newText;
						initFont(text);
						textLocation--;
					}
					break;
				case KeyEvent.VK_DELETE:
					String newText2 = "";
					for (int i = 0; i < text.length(); i++) {
						if (i != textLocation) {
							newText2 += text.charAt(i);
						}
					}
					text = newText2;
					initFont(text);
					break;
				case KeyEvent.VK_RIGHT:
					if (textLocation != text.length()) {
						textLocation++;
					}
					break;
				case KeyEvent.VK_LEFT:
					if (textLocation != 0) {
						textLocation--;
					}
					break;
				case KeyEvent.VK_SHIFT:
					break;
				case KeyEvent.VK_CAPS_LOCK:
					break;
				case KeyEvent.VK_UP:
					break;
				case KeyEvent.VK_DOWN:
					break;
				case KeyEvent.VK_END:
					break;
				case KeyEvent.VK_HOME:
					break;
				case KeyEvent.VK_INSERT:
					break;
				case KeyEvent.VK_PAGE_UP:
					break;
				case KeyEvent.VK_PAGE_DOWN:
					break;
				case KeyEvent.VK_ALT:
					break;
				case KeyEvent.VK_CONTROL:
					break;
				case KeyEvent.VK_WINDOWS:
					break;
				case KeyEvent.VK_PAUSE:
					break;
				case KeyEvent.VK_NUM_LOCK:
					break;
				case KeyEvent.VK_CONTEXT_MENU:
					break;
				case KeyEvent.VK_ESCAPE:
					break;
				case KeyEvent.VK_ENTER:
					break;
				default:
					String newText3 = "";
					char character = (char) e.getKeyCode();
					if (text.length() == 0) {
						text = character + "";
						textLocation++;
						break;
					}
					if (textLocation == text.length()) {
						text = text + character;
						textLocation++;
						break;
					}
					if (text.length() == 2) {
						newText3 += text.charAt(0);
						newText3 += character + "";
						newText3 += text.charAt(1);
					} else {
						newText3 = text.substring(0, textLocation) + character + "" + text.substring(textLocation);
					}
					text = newText3;
					textLocation++;
					break;
			}
		}

		return true;
	}
}
