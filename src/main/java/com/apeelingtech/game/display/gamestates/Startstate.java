package com.apeelingtech.game.display.gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import com.apeelingtech.game.Game;
import com.apeelingtech.game.display.Display;
import com.apeelingtech.game.display.gui.ActionAdapter;
import com.apeelingtech.game.display.gui.GUIButton;
import com.apeelingtech.game.events.Event;
import com.apeelingtech.game.events.types.MouseMovedEvent;
import com.apeelingtech.game.events.types.MousePressedEvent;
import com.apeelingtech.game.net.packets.Packet01Disconnect;
import com.apeelingtech.game.layers.Layer;

public class Startstate extends GameState {
	
	private GUIButton playButton;
	public boolean changePlayButton = false; // TODO: Why is this here?
	private GUIButton exitButton;
	
	private int buttonSpace = 5;
	
	private GameState setupState;
	
	public Startstate(Game game, Display display) {
		super(game, Color.black, display);
		init();
	}
	
	public void init() {
		playButton = new GUIButton("Play!", "Times New Roman", Font.PLAIN, false, (Game.WIDTH * Game.SCALE - 50), (Game.HEIGHT * Game.SCALE / 2) - ((25 / 2) + buttonSpace), 90, 25);
		playButton.setX((Game.WIDTH * Game.SCALE - 50) - playButton.getWidth());
		playButton.setColors(new Color(0, 220, 0), new Color(0, 180, 0), new Color(0, 200, 0),
								new Color(0, 250, 0), new Color(0, 200, 0), new Color(0, 250, 0));
		playButton.setOutline(0);
		playButton.setRounded(15, 15);
		playButton.addActionAdapter(new ActionAdapter() {
			@Override
			public boolean mousePressed(MousePressedEvent event) {
				if (setupState == null) {
					setupState = new SetupState(getGame(), display);
					display.add(setupState);
					display.changeCurrentGameState(1);
				} else {
					if (((SetupState) setupState).gameScreen != null) {
						display.changeCurrentGameState(2);
					} else {
						display.changeCurrentGameState(1);
					}
				}
				return true;
			}

			@Override
			public boolean mouseEntered(MouseMovedEvent event) {
				playButton.label = playButton.label + "  <--";
				return true;
			}
			
			@Override
			public boolean mouseExited(MouseMovedEvent event) {
				if (playButton.label == "Continue Game  <--") {
					playButton.label = "Continue Game";
				} else {
					playButton.label = "Play!";
				}
				return true;
			}
		});
		
		exitButton = new GUIButton("Exit", Game.WIDTH * Game.SCALE - 50, (Game.HEIGHT * Game.SCALE / 2) + ((25 / 2) + buttonSpace), playButton);
		exitButton.setX((Game.WIDTH * Game.SCALE - 50) - exitButton.getWidth());
		exitButton.setColors(new Color(220, 0, 0), new Color(180, 0, 0), new Color(200, 0, 0),
								new Color(250, 0, 0), new Color(200, 0, 0), new Color(250, 0, 0));
		exitButton.addActionAdapter(new ActionAdapter() {
			@Override
			public boolean mousePressed(MousePressedEvent event) {
				try {
					if (display.gameStates.get(2) != null) {
						Packet01Disconnect packet = new Packet01Disconnect(((Gamescreen) display.gameStates.get(2)).player.getUsername());
						packet.writeData(((Gamescreen) display.gameStates.get(2)).socketClient);
					}
				} catch (IndexOutOfBoundsException e2) {
					//e2.printStackTrace();
				}
				
				getGame().frame.setVisible(false);
				getGame().running = false;
				getGame().frame.dispose();
				System.exit(0);
				return true;
			}
			
			@Override
			public boolean mouseEntered(MouseMovedEvent event) {
				exitButton.label = exitButton.label + "   <--";
				return true;
			}
			
			@Override
			public boolean mouseExited(MouseMovedEvent event) {
				exitButton.label = "Exit";
				return true;
			}
		});
		
		layerList.add(new Layer((g) -> {
			// Render stuff here!
		}, playButton, exitButton));
	}
	
	@Override
	public void tick() {
		if (setupState != null) {
			playButton.setText("Continue Game");
			// TODO: Not working!
			playButton.setX((Game.WIDTH * Game.SCALE - 50) - playButton.getWidth());
		}
	}
	
	/*@Override
	public void render(Graphics2D g) {
	}*/
	
}
