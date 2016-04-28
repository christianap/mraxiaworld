package com.apeelingtech.game.display;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.List;

import com.apeelingtech.game.Game;
import com.apeelingtech.game.display.gamestates.GameState;
import com.apeelingtech.game.display.gamestates.Gamescreen;
import com.apeelingtech.game.display.gamestates.Startstate;
import com.apeelingtech.game.events.types.*; // Expand this
import com.apeelingtech.game.events.Event;
import com.apeelingtech.game.display.gui.GUI;

public class Display {
	
	public List<GameState> gameStates = new ArrayList<>();
	private Game game;
	public GameState currentGameState;
	private Startstate startState;
	public int keys[]; // TODO
	
	/**
	 * Makes a new Display that is used to render Game States. This also creates a StartState and sets that as the current GameState to be shown when the game starts.
	 * 
	 * @param game
	 *            - The current instance of Game!
	 */
	public Display(Game game) {
		this.game = game;
		startState = new Startstate(game, this);
		add(startState);
		currentGameState = gameStates.get(0);
		
		game.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                MousePressedEvent event = new MousePressedEvent(e.getButton(), e.getX(), e.getY());
                onEvent(event);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                MouseReleasedEvent event = new MouseReleasedEvent(e.getButton(), e.getX(), e.getY());
                onEvent(event);
            }
        });

        game.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                MouseMovedEvent event = new MouseMovedEvent(e.getX(), e.getY(), true);
                onEvent(event);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                MouseMovedEvent event = new MouseMovedEvent(e.getX(), e.getY(), false);
                onEvent(event);
            }
        });

        game.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                KeyPressedEvent event = new KeyPressedEvent(e.getKeyCode());
                onEvent(event);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                KeyReleasedEvent event = new KeyReleasedEvent(e.getKeyCode());
                onEvent(event);
            }
        });
        
        game.addMouseWheelListener(new MouseWheelListener() {
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				MouseWheelMovedEvent event = new MouseWheelMovedEvent(e.getWheelRotation());
				onEvent(event);
			}
        });
	}
	
	public void add(GameState gs) {
		gameStates.add(gs);
	}
	
	public void tick() {
		currentGameState.tick();
	}
	
	public void onEvent(Event event) {
		// call onEvent for currentState
		currentGameState.onEvent(event);
	}
	
	public void render(Graphics2D g) {
		if (currentGameState.getBgcolor() != null) {
			g.setColor(currentGameState.getBgcolor());
			g.fillRect(0, 0, Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE);
		}
		currentGameState.render(g);
		if (getCurrentGameState() instanceof Gamescreen) {
			g.drawImage(game.image, 0, 0, game.getWidth(), game.getHeight(), null);
		}
		//currentGameState.getGUI().render(g); Replace with something else???
	}
	
	public GameState getCurrentGameState() {
		return currentGameState;
	}
	
	public void setCurrentGameState(int index) {
		currentGameState = gameStates.get(index);
	}
	
	public void changeCurrentGameState(int index) {
		setCurrentGameState(index);
	}
	
	public void addMouseListener(MouseListener listener) {
		game.addMouseListener(listener);
	}
	
	public void addMouseMotionListener(MouseMotionListener listener) {
		game.addMouseMotionListener(listener);
	}
	
	public void addMouseWheelListener(MouseWheelListener listener) {
		game.addMouseWheelListener(listener);
	}
	
	public void addKeyListener(KeyListener listener) {
		game.addKeyListener(listener);
	}
	
}
