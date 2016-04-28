package com.apeelingtech.game.display.gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import com.apeelingtech.game.Game;
import com.apeelingtech.game.layers.Layer;
import com.apeelingtech.game.display.Display;
import com.apeelingtech.game.display.gui.GUIText;

public class Deathstate extends GameState {
	
	private GUIText text;
	
	public Deathstate(Game game, Display display) {
		super(game, Color.BLACK, display);
		init();
	}
	
	private void init() {
		text = new GUIText("You Have Died!", "Times New Roman", Font.BOLD, Color.RED, 70, 120, 24);
		
		layerList.add(new Layer((g) -> {
		}, text));
	}
	
}
