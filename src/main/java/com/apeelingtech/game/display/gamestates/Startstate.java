package com.apeelingtech.game.display.gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import com.apeelingtech.game.Game;
import com.apeelingtech.game.display.Display;
import com.apeelingtech.game.display.gui.ButtonAction;
import com.apeelingtech.game.display.gui.GUI;
import com.apeelingtech.game.display.gui.GUIButton;
import com.apeelingtech.game.net.packets.Packet01Disconnect;

public class Startstate extends GameState {
	
	private GUIButton playButton;
	public boolean changePlayButton = false;
	private GUIButton exitButton;
	
	private int buttonSpace = 5;
	
	private GameState setupState;
	
	// private GUIRectButton playButton;
	// private GUIRectButton exitButton;
	
	public Startstate(Game game, Display display) {
		super(game, Color.black, display);
		init();
	}
	
	public Startstate(Game game, GUI gui, Display display) {
		super(game, Color.black, gui, display);
		init();
	}
	
	public void init() {
		
		playButton = new GUIButton("Play!", "Times New Roman", Font.PLAIN, false, Game.WIDTH * Game.SCALE - 150, (Game.HEIGHT * Game.SCALE / 2) - ((25 / 2) + buttonSpace), 90, 25);
		playButton.setColors(new Color(0, 220, 0), new Color(0, 180, 0), new Color(0, 200, 0));
		playButton.setOutline(0);
		playButton.setRounded(15, 15);
		playButton.addAction(new ButtonAction() {
			@Override
			public void action(MouseEvent e) {
				if (setupState == null) {
					setupState = new SetupState(getGame(), new GUI(display), display);
					display.add(setupState);
					display.changeCurrentGameState(1);
				} else {
					if (((SetupState) setupState).gameScreen != null) {
						display.changeCurrentGameState(2);
					} else {
						display.changeCurrentGameState(1);
					}
				}
			}
			
			@Override
			public void mouseEnter(MouseEvent e) {
				playButton.setColors(new Color(0, 250, 0), new Color(0, 200, 0), new Color(0, 250, 0));
				playButton.label = playButton.label + "  <--";
			}
			
			@Override
			public void mouseExit(MouseEvent e) {
				playButton.setColors(new Color(0, 220, 0), new Color(0, 180, 0), new Color(0, 200, 0));
				if (playButton.label == "Continue Game  <--") {
					playButton.label = "Continue Game";
				} else {
					playButton.label = "Play!";
				}
			}
		});
		gui.add(playButton);
		
		exitButton = new GUIButton("Exit", Game.WIDTH * Game.SCALE - 150, (Game.HEIGHT * Game.SCALE / 2) + ((25 / 2) + buttonSpace), playButton);
		exitButton.setColors(new Color(220, 0, 0), new Color(180, 0, 0), new Color(200, 0, 0));
		exitButton.addAction(new ButtonAction() {
			@Override
			public void action(MouseEvent e) {
				
				try {
					if (display.gameStates.get(2) != null) {
						Packet01Disconnect packet = new Packet01Disconnect(((Gamescreen) display.gameStates.get(2)).player.getUsername());
						packet.writeData(((Gamescreen) display.gameStates.get(2)).socketClient);
					}
				} catch (IndexOutOfBoundsException ioobe) {
				}
				
				getGame().frame.setVisible(false);
				getGame().running = false;
				getGame().frame.dispose();
				System.exit(0);
			}
			
			@Override
			public void mouseEnter(MouseEvent e) {
				exitButton.setColors(new Color(250, 0, 0), new Color(200, 0, 0), new Color(250, 0, 0));
				exitButton.label = exitButton.label + "   <--";
			}
			
			@Override
			public void mouseExit(MouseEvent e) {
				exitButton.setColors(new Color(220, 0, 0), new Color(180, 0, 0), new Color(200, 0, 0));
				exitButton.label = "Exit";
			}
		});
		gui.add(exitButton);
		
		// playButton = new GUIRectButton(Game.GAME_SIZE.width - 100, 400, 90, 30, Color.GREEN, "Play!", Color.BLACK, new Font("Times New Roman", Font.BOLD, 14), gui, new ButtonAction() {
		// @Override
		// public void action() {
		// game.display.setCurrentGameState(1);
		// }
		// });
		// gui.add(playButton);
		//
		// exitButton = new GUIRectButton(Game.GAME_SIZE.width - 100, 440, 90, 30, Color.RED, "Exit", Color.BLACK, new Font("Times New Roman", Font.BOLD, 14), gui, new ButtonAction() {
		// @Override
		// public void action() {
		// // Code to stop the Game
		// }
		// });
		// gui.add(exitButton);
	}
	
	@Override
	public void tick() {
		if (setupState != null) {
			playButton.setText("Continue Game");
		}
	}
	
	@Override
	public void render(Graphics2D g) {
	}
	
}
