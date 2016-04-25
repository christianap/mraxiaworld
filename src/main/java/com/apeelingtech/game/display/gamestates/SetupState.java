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
import com.apeelingtech.game.display.gui.GUIField;
import com.apeelingtech.game.display.gui.GUIImage;
import com.apeelingtech.game.display.gui.GUIText;
import com.apeelingtech.game.display.gui.GameGUI;

public class SetupState extends GameState {
	
	public static enum CharacterColor {
		DEFAULT("Default", 145), RED("Red", 500), ORANGE("Orange", 530), GREEN("Green", 151), YELLOW("Yellow", 550);
		
		private String name = "Default";
		private int shirtColor = 145;
		
		CharacterColor(String name, int shirtColor) {
			this.name = name;
			this.shirtColor = shirtColor;
		}
		
		public String getName() {
			return name;
		}
		
		public int getColor() {
			return shirtColor;
		}
	}
	
	public static enum CharacterSColor {
		DEFAULT("", 543), DARK("D", 432);
		
		private String name = "";
		private int skinColor = 543;
		
		CharacterSColor(String name, int skinColor) {
			this.name = name;
			this.skinColor = skinColor;
		}
		
		public String getName() {
			return name;
		}
		
		public int getSkinColor() {
			return skinColor;
		}
	}
	
	public GameState gameScreen;
	private GameState pauseState;
	private GameState deathState;
	
	private GUIImage currentCharacter;
	private GUIText title;
	private GUIButton prevChar;
	private GUIButton nextChar;
	private GUIButton playButton;
	private GUIButton backButton;
	private GUIText shirtColorText;
	private GUIButton prevShirtColor;
	private GUIButton nextShirtColor;
	private GUIText skinColorText;
	private GUIButton prevSkinColor;
	private GUIButton nextSkinColor;
	
	private GUIField username;
	
	private byte characterType = 1;
	private CharacterColor shirtColor = CharacterColor.DEFAULT;
	private CharacterSColor skinColor = CharacterSColor.DEFAULT;
	
	public SetupState(Game game, GUI gui, Display display) {
		super(game, Color.black, gui, display);
		init();
	}
	
	public void init() {
		title = new GUIText("Setup Character and World", "Times New Roman", Font.BOLD, Color.CYAN, 100, 23, 25);
		title.setHorizontalCenter();
		gui.add(title);
		currentCharacter = new GUIImage(100, 100, 64, 64, "/C1Default.png"); // Animate this picture??? TODO
		gui.add(currentCharacter);
		prevChar = new GUIButton("<", "Times New Roman", Font.BOLD, true, 50, 115, 30, 30);
		prevChar.setColors(new Color(0, 0, 220), new Color(0, 0, 120), Color.CYAN);
		prevChar.addAction(new ButtonAction() {
			@Override
			public void action(MouseEvent e) {
				if (characterType == 2) {
					characterType = 1;
				} else {
					characterType = 2;
				}
			}
			
			@Override
			public void mouseEnter(MouseEvent e) {
			}
			
			@Override
			public void mouseExit(MouseEvent e) {
			}
		});
		gui.add(prevChar);
		nextChar = new GUIButton(">", "Times New Roman", Font.BOLD, true, 184, 115, 30, 30);
		nextChar.setColors(new Color(0, 0, 220), new Color(0, 0, 120), Color.CYAN);
		nextChar.addAction(new ButtonAction() {
			@Override
			public void action(MouseEvent e) {
				if (characterType == 1) {
					characterType = 2;
				} else {
					characterType = 1;
				}
			}
			
			@Override
			public void mouseEnter(MouseEvent e) {
			}
			
			@Override
			public void mouseExit(MouseEvent e) {
			}
		});
		gui.add(nextChar);
		
		// 3 Radio Buttons in group for whether starting server, connecting to server, or local play
		
		
		// Textbox for username
		
		backButton = new GUIButton("Back", "Times New Roman", Font.BOLD, true, 10, Game.HEIGHT * Game.SCALE - 40, 80, 30);
		backButton.setColors(new Color(220, 0, 0), new Color(120, 0, 0), Color.CYAN);
		backButton.setRounded(5, 5);
		backButton.addAction(new ButtonAction() {
			
			@Override
			public void action(MouseEvent e) {
				display.changeCurrentGameState(0);
			}
			
			@Override
			public void mouseEnter(MouseEvent e) {
			}
			
			@Override
			public void mouseExit(MouseEvent e) {
			}
			
		});
		
		gui.add(backButton);
		
		playButton = new GUIButton("Play!", "Times New Roman", Font.BOLD, true, Game.WIDTH * Game.SCALE - 90, Game.HEIGHT * Game.SCALE - 40, 80, 30);
		playButton.setColors(new Color(0, 220, 0), new Color(0, 120, 0), Color.CYAN);
		playButton.setRounded(5, 5);
		playButton.addAction(new ButtonAction() {
			
			@Override
			public void action(MouseEvent e) {
				if (gameScreen == null) {
					gameScreen = new Gamescreen(getGame(), new GameGUI(display), display, shirtColor, skinColor, characterType);
					display.add(gameScreen);
					pauseState = new Pausestate(getGame(), new GUI(display), display);
					display.add(pauseState);
					deathState = new Deathstate(getGame(), new GUI(display), display);
					display.add(deathState);
				}
				display.changeCurrentGameState(2); // Change to Gamescreen state
			}
			
			@Override
			public void mouseEnter(MouseEvent e) {
			}
			
			@Override
			public void mouseExit(MouseEvent e) {
			}
			
		});
		gui.add(playButton);
		shirtColorText = new GUIText("Blue", "Times New Roman", Font.BOLD, Color.cyan, 112, 206, 22);
		gui.add(shirtColorText);
		
		prevShirtColor = new GUIButton("<", "Times New Roman", Font.BOLD, true, 50, 184, 30, 30);
		prevShirtColor.setColors(new Color(0, 0, 220), new Color(0, 0, 120), Color.CYAN);
		prevShirtColor.addAction(new ButtonAction() {
			@Override
			public void action(MouseEvent e) {
				if (shirtColor == CharacterColor.RED) {
					shirtColor = CharacterColor.DEFAULT;
				} else if (shirtColor == CharacterColor.ORANGE) {
					shirtColor = CharacterColor.RED;
				} else if (shirtColor == CharacterColor.YELLOW) {
					shirtColor = CharacterColor.ORANGE;
				} else if (shirtColor == CharacterColor.GREEN) {
					shirtColor = CharacterColor.YELLOW;
				}
			}
			
			@Override
			public void mouseEnter(MouseEvent e) {
			}
			
			@Override
			public void mouseExit(MouseEvent e) {
			}
		});
		gui.add(prevShirtColor);
		nextShirtColor = new GUIButton(">", "Times New Roman", Font.BOLD, true, 184, 184, 30, 30);
		nextShirtColor.setColors(new Color(0, 0, 220), new Color(0, 0, 120), Color.CYAN);
		nextShirtColor.addAction(new ButtonAction() {
			@Override
			public void action(MouseEvent e) {
				if (shirtColor == CharacterColor.DEFAULT) {
					shirtColor = CharacterColor.RED;
				} else if (shirtColor == CharacterColor.RED) {
					shirtColor = CharacterColor.ORANGE;
				} else if (shirtColor == CharacterColor.ORANGE) {
					shirtColor = CharacterColor.YELLOW;
				} else if (shirtColor == CharacterColor.YELLOW) {
					shirtColor = CharacterColor.GREEN;
				}
			}
			
			@Override
			public void mouseEnter(MouseEvent e) {
			}
			
			@Override
			public void mouseExit(MouseEvent e) {
			}
		});
		gui.add(nextShirtColor);
		
		skinColorText = new GUIText("White", "Times New Roman", Font.BOLD, Color.cyan, 107, 246, 22);
		gui.add(skinColorText);
		
		prevSkinColor = new GUIButton("<", "Times New Roman", Font.BOLD, true, 50, 224, 30, 30);
		prevSkinColor.setColors(new Color(0, 0, 220), new Color(0, 0, 120), Color.CYAN);
		prevSkinColor.addAction(new ButtonAction() {
			@Override
			public void action(MouseEvent e) {
				if (skinColor == CharacterSColor.DEFAULT) {
					skinColor = CharacterSColor.DARK;
				} else if (skinColor == CharacterSColor.DARK) {
					skinColor = CharacterSColor.DEFAULT;
				}
			}
			
			@Override
			public void mouseEnter(MouseEvent e) {
			}
			
			@Override
			public void mouseExit(MouseEvent e) {
			}
		});
		gui.add(prevSkinColor);
		nextSkinColor = new GUIButton(">", "Times New Roman", Font.BOLD, true, 184, 224, 30, 30);
		nextSkinColor.setColors(new Color(0, 0, 220), new Color(0, 0, 120), Color.CYAN);
		nextSkinColor.addAction(new ButtonAction() {
			@Override
			public void action(MouseEvent e) {
				if (skinColor == CharacterSColor.DEFAULT) {
					skinColor = CharacterSColor.DARK;
				} else if (skinColor == CharacterSColor.DARK) {
					skinColor = CharacterSColor.DEFAULT;
				}
			}
			
			@Override
			public void mouseEnter(MouseEvent e) {
			}
			
			@Override
			public void mouseExit(MouseEvent e) {
			}
		});
		gui.add(nextSkinColor);
		
		username = new GUIField(20, 20, 100, 30);
		gui.add(username);
	}
	
	@Override
	public void tick() {
		if (characterType == 1) {
			currentCharacter.setImage("/C1" + shirtColor.getName() + skinColor.getName() + ".png");
		} else {
			currentCharacter.setImage("/C2" + shirtColor.getName() + skinColor.getName() + ".png");
		}
		if (shirtColor != CharacterColor.DEFAULT) {
			shirtColorText.setText(shirtColor.getName());
		} else {
			shirtColorText.setText("Blue");
		}
		if (skinColor != CharacterSColor.DEFAULT) {
			skinColorText.setText("Dark");
		} else {
			skinColorText.setText("White");
		}
	}
	
	@Override
	public void render(Graphics2D g) {
	}
	
}
