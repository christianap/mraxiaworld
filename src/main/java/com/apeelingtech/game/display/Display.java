package com.apeelingtech.game.display;

import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.List;

import com.apeelingtech.game.Game;
import com.apeelingtech.game.display.gamestates.GameState;
import com.apeelingtech.game.display.gamestates.Gamescreen;
import com.apeelingtech.game.display.gamestates.Startstate;
import com.apeelingtech.game.display.gui.GUI;

public class Display {
	
	public List<GameState> gameStates = new ArrayList<GameState>();
	private Game game;
	public GameState currentGameState;
	private Startstate startState;
	
	/**
	 * Makes a new Display that is used to render Game States. This also creates a StartState and sets that as the current GameState to be shown when the game starts.
	 * 
	 * @param game
	 *            - The current instance of Game!
	 */
	public Display(Game game) {
		this.game = game;
		startState = new Startstate(game, new GUI(this), this);
		add(startState);
		currentGameState = gameStates.get(0);
	}
	
	public void add(GameState gs) {
		gameStates.add(gs);
	}
	
	public void tick() {
		currentGameState.tick();
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
		currentGameState.getGUI().render(g);
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
