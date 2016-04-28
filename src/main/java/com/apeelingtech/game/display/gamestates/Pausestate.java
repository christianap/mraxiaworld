package com.apeelingtech.game.display.gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import com.apeelingtech.game.Game;
import com.apeelingtech.game.layers.Layer;
import com.apeelingtech.game.display.Display;
import com.apeelingtech.game.display.gui.ButtonAction;
import com.apeelingtech.game.display.gui.GUIButton;

public class Pausestate extends GameState {
	
	private GUIButton continueBtn;
	private GUIButton menuBtn;
	
	public Pausestate(Game game, Display display) {
		super(game, Color.GREEN, display);
		init();
	}
	
	private void init() {
		continueBtn = new GUIButton("Continue to Game", "Times New Roman", Font.PLAIN, true, 80, 150, 150, 25);
		continueBtn.setColors(new Color(0, 0, 220), new Color(0, 0, 180), new Color(30, 30, 30));
		continueBtn.setOutline(0);
		continueBtn.setRounded(15, 15);
		continueBtn.addAction(new ButtonAction() {
			@Override
			public void action(MouseEvent e) {
				display.changeCurrentGameState(2);
			}
			
			@Override
			public void mouseEnter(MouseEvent e) {
				continueBtn.setColors(new Color(0, 0, 250), new Color(0, 0, 200), new Color(25, 25, 25));
			}
			
			@Override
			public void mouseExit(MouseEvent e) {
				continueBtn.setColors(new Color(0, 0, 220), new Color(0, 0, 180), new Color(30, 30, 30));
			}
		});
		menuBtn = new GUIButton("Back to Menu", "Times New Roman", Font.PLAIN, true, 80, 200, 150, 25);
		menuBtn.setColors(new Color(220, 0, 0), new Color(180, 0, 0), new Color(30, 30, 30));
		menuBtn.setOutline(0);
		menuBtn.setRounded(15, 15);
		menuBtn.addAction(new ButtonAction() {
			
			@Override
			public void action(MouseEvent e) {
				display.changeCurrentGameState(0);
			}
			
			@Override
			public void mouseEnter(MouseEvent e) {
				menuBtn.setColors(new Color(250, 0, 0), new Color(200, 0, 0), new Color(25, 25, 25));
			}
			
			@Override
			public void mouseExit(MouseEvent e) {
				menuBtn.setColors(new Color(220, 0, 0), new Color(180, 0, 0), new Color(30, 30, 30));
			}
			
		});
		
		layerList.add(new Layer((g) -> {
		}, continueBtn, menuBtn));
	}
	
	@Override
	public void tick() {
	}
	
	/*@Override
	public void render(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.setFont(new Font("Times New Roman", Font.BOLD, 24));
		g.drawString("Paused", 105, 120);
	}*/
	
}
