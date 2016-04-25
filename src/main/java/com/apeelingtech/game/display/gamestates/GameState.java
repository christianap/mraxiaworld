package com.apeelingtech.game.display.gamestates;

import java.awt.Color;
import java.awt.Graphics2D;

import com.apeelingtech.game.Game;
import com.apeelingtech.game.display.Display;
import com.apeelingtech.game.display.gui.GUI;

public abstract class GameState {
	
	protected Game game;
	private Color bgcolor;
	protected GUI gui;
	protected Display display;
	
	public GameState(Game game, Color bgcolor, GUI gui, Display display) {
		this.game = game;
		this.bgcolor = bgcolor;
		this.gui = gui;
		this.display = display;
		gui.addGameState(this);
		game.addMouseListener(gui);
		game.addMouseMotionListener(gui);
		game.addMouseWheelListener(gui);
		game.addKeyListener(gui);
	}
	
	public GameState(Game game, Color bgcolor, Display display) {
		this.game = game;
		this.bgcolor = bgcolor;
		this.display = display;
	}
	
	public GameState(Game game, GUI gui, Display display) {
		this.game = game;
		this.gui = gui;
		this.display = display;
	}
	
	public GameState(Game game, Display display) {
		this.game = game;
		this.display = display;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics2D g);
	
	public Color getBgcolor() {
		return bgcolor;
	}
	
	public void setBgcolor(Color bgcolor) {
		this.bgcolor = bgcolor;
	}
	
	public GUI getGUI() {
		return gui;
	}
	
	public Game getGame() {
		return game;
	}
	
	public Display getDisplay() {
		return display;
	}
	
}
