package com.apeelingtech.game.display.gui;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.apeelingtech.game.Game;
import com.apeelingtech.game.events.Event;
import com.apeelingtech.game.events.types.*;

public abstract class GUIElement {
	
	public Rectangle bounds;
	protected BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_3BYTE_BGR);
	protected boolean hidden = false;
	protected boolean active = false;
	protected ActionAdapter action;

	public GUIElement(int x, int y, int width, int height) {
		bounds = new Rectangle(x, y, width, height);
	}

	public void addActionAdapter(ActionAdapter action) {
		this.action = action;
	}
	
	public boolean mousePressed(MousePressedEvent event) {
		if (action != null) {
			action.mousePressed(event);
		}
		return false;
    }

    public boolean mouseReleased(MouseReleasedEvent event) {
    	if (action != null) {
			action.mouseReleased(event);
		}
    	return false;
    }

    public boolean mouseMoved(MouseMovedEvent event) {
    	if (action != null) {
			action.mouseMoved(event);
		}
        return false;
    }

    public boolean mouseEntered(MouseMovedEvent event) {
    	if (action != null) {
			action.mouseEntered(event);
		}
    	return false;
    }

    public boolean mouseExited(MouseMovedEvent event) {
    	if (action != null) {
			action.mouseExited(event);
		}
    	return false;
    }

    public boolean mouseWheelMoved(MouseWheelMovedEvent event) {
    	if (action != null) {
			action.mouseWheelMoved(event);
		}
    	return false;
    }

    public boolean keyPressed(KeyPressedEvent event) {
    	if (action != null) {
			action.keyPressed(event);
		}
    	return false;
    }

    public boolean keyReleased(KeyReleasedEvent event) {
    	if (action != null) {
			action.keyReleased(event);
		}
    	return false;
    }

	// TODO: Add tick() method???
	
	public abstract void render(Graphics2D g);

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

	public void setActive() {
		active = true;
	}

	public void setNotActive() {
		active = false;
	}

	public boolean isActive() {
		return active;
	}
	
}
