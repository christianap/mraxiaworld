package com.apeelingtech.game.display.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.apeelingtech.game.display.Display;
import com.apeelingtech.game.display.gamestates.Gamescreen;
import com.apeelingtech.game.display.gamestates.Pausestate;

public class GameGUI extends GUI implements KeyListener {
	
	public Gamescreen gameScreen;
	
	public GameGUI(Display display) {
		super(display);
	}
	
	public void addGameScreen(Gamescreen gameScreen) {
		this.gameScreen = gameScreen;
	}
	
	public class Key {
		private int numTimesPressed = 0;
		private boolean pressed = false;
		
		public int getNumTimesPressed() {
			return numTimesPressed;
		}
		
		public boolean isPressed() {
			return pressed;
		}
		
		public void toggle(boolean isPressed) {
			pressed = isPressed;
			if (isPressed)
				numTimesPressed++;
		}
	}
	
	public Key up = new Key();
	public Key down = new Key();
	public Key left = new Key();
	public Key right = new Key();
	
	public void keyPressed(KeyEvent e) {
		toggleKey(e.getKeyCode(), true);
	}
	
	public void keyReleased(KeyEvent e) {
		toggleKey(e.getKeyCode(), false);
	}
	
	public void keyTyped(KeyEvent e) {
	}
	
	public void toggleKey(int keyCode, boolean isPressed) {
		if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) {
			up.toggle(isPressed);
		}
		if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN) {
			down.toggle(isPressed);
		}
		if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT) {
			left.toggle(isPressed);
		}
		if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT) {
			right.toggle(isPressed);
		}
		
		if (isPressed) {
			switch (keyCode) {
				case KeyEvent.VK_ESCAPE:
					if (display.getCurrentGameState() instanceof Gamescreen) {
						display.setCurrentGameState(3);
					} else if (display.getCurrentGameState() instanceof Pausestate) {
						display.setCurrentGameState(2);
					}
					break;
			}
		} else {
			switch (keyCode) {
			
			}
		}
	}
	
}
