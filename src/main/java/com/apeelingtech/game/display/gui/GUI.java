package com.apeelingtech.game.display.gui;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.List;

import com.apeelingtech.game.display.Display;
import com.apeelingtech.game.display.IKeyable;
import com.apeelingtech.game.display.gamestates.GameState;

public class GUI implements MouseListener, MouseMotionListener, MouseWheelListener, KeyListener {
	
	protected GameState gameState;
	protected Display display;
	protected List<GUIElement> elements = new ArrayList<GUIElement>();
	protected Point prevPoint = new Point(0, 0);
	
	public boolean isMouseLeft;
	public boolean isMouseRight;
	public boolean isShift = false;
	public boolean isMainClose = false;
	protected int key;
	
	public GUI(Display display) {
		this.display = display;
	}
	
	public void addGameState(GameState gameState) {
		this.gameState = gameState;
	}
	
	public void add(GUIElement element) {
		elements.add(element);
	}
	
	public void remove(GUIElement element) {
		elements.remove(element);
	}
	
	public void remove(int index) {
		elements.remove(index);
	}
	
	// public void tick() {
	// for (GUIElement element : elements) {
	// element.tick();
	// }
	// }
	
	public void render(Graphics2D g) {
		for (GUIElement element : elements) {
			if (element.isHidden()) continue;
			element.render(g);
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		for (GUIElement ge : elements) {
			if (ge.isHidden()) continue;
			if (ge instanceof IClickable) {
				if (ge.bounds.contains(e.getPoint())) {
					((IClickable) ge).click(e);
				}
			}
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
	}
	
	public ArrayList<GUIElement> getElements() {
		return (ArrayList<GUIElement>) elements;
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		for (GUIElement ge : elements) {
			if (ge.isHidden()) continue;
			if (ge instanceof IClickable) {
				if (ge.bounds.contains(e.getPoint()) && !(ge.bounds.contains(prevPoint))) {
					((IClickable) ge).mouseEnter(e);
				} else if (!(ge.bounds.contains(e.getPoint())) && ge.bounds.contains(prevPoint)) {
					((IClickable) ge).mouseExit(e);
				}
			}
		}
		prevPoint = e.getPoint();
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		for (GUIElement ge : elements) {
			if (ge.isHidden()) continue;
			if (ge instanceof IKeyable) {
				((IKeyable) ge).keyTyped(e);
			}
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		for (GUIElement ge : elements) {
			if (ge.isHidden()) continue;
			if (ge instanceof IKeyable) {
				((IKeyable) ge).keyPressed(e);
			}
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		for (GUIElement ge : elements) {
			if (ge.isHidden()) continue;
			if (ge instanceof IKeyable) {
				((IKeyable) ge).keyReleased(e);
			}
		}
	}
	
}
