package com.apeelingtech.game.display.gamestates;

import java.awt.Color;
import java.awt.Graphics2D;

import com.apeelingtech.game.Game;
import com.apeelingtech.game.display.Display;
import com.apeelingtech.game.display.gui.GUI;
import com.apeelingtech.game.events.EventListener;
import com.apeelingtech.game.events.Event;
import com.apeelingtech.game.layers.Layer;
import java.util.ArrayList;
import java.util.List;

public abstract class GameState implements EventListener {
	
	protected Game game;
	private Color bgcolor;
	protected Display display;
	protected List<Layer> layerList = new ArrayList<>();
	
	public GameState(Game game, Color bgcolor, Display display) {
		this.game = game;
		this.bgcolor = bgcolor;
		this.display = display;
	}
	
	public GameState(Game game, Display display) {
		this.game = game;
		this.display = display;
	}
	
	public void tick() {
		for (Layer layer : layerList) {
			//layer.tick();
		}
	}
	
	public void render(Graphics2D g) {
		for (Layer layer : layerList) {
			layer.render(g);
		}
	}
	
	public void onEvent(Event event) {
		for (int i = layerList.size() - 1; i >= 0; i--) {
            layerList.get(i).onEvent(event);
        }
	}
	
	public Color getBgcolor() {
		return bgcolor;
	}
	
	public void setBgcolor(Color bgcolor) {
		this.bgcolor = bgcolor;
	}
	
	public Game getGame() {
		return game;
	}
	
	public Display getDisplay() {
		return display;
	}
	
	public final void addLayer(Layer layer) {
		layerList.add(layer);
	}
	
}
