package com.apeelingtech.game.display.gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import com.apeelingtech.game.Game;
import com.apeelingtech.game.display.Display;
import com.apeelingtech.game.layers.Layer;
import com.apeelingtech.game.display.gui.ActionAdapter;
import com.apeelingtech.game.display.gui.GUIButton;
import com.apeelingtech.game.display.gui.GUIRadioButton;
import com.apeelingtech.game.display.gui.GUIField;
import com.apeelingtech.game.display.gui.GUIImage;
import com.apeelingtech.game.display.gui.GUIText;
import com.apeelingtech.game.display.gui.GUIGroup;
import com.apeelingtech.game.events.types.MousePressedEvent;

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
	private GUIRadioButton localRadioButton;
	private GUIRadioButton serverRadioButton;
	private GUIRadioButton connectRadioButton;
	private GUIGroup gameplayTypeGroup;
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
	
	public SetupState(Game game, Display display) {
		super(game, Color.black, display);
		init();
	}
	
	public void init() {
		title = new GUIText("Setup Character and World", "Times New Roman", Font.BOLD, Color.CYAN, 100, 23, 25);
		title.setHorizontalCenter();
		//gui.add(title);
		currentCharacter = new GUIImage(100, 100, 64, 64, "/C1Default.png"); // Animate this picture??? TODO
		//gui.add(currentCharacter);
		prevChar = new GUIButton("<", "Times New Roman", Font.BOLD, true, 50, 115, 30, 30);
		prevChar.setColors(new Color(0, 0, 220), new Color(0, 0, 120), Color.CYAN,
							new Color(0, 0, 250), new Color(0, 0, 140), Color.WHITE);
		prevChar.addActionAdapter(new ActionAdapter() {
			@Override
			public boolean mousePressed(MousePressedEvent event) {
				if (characterType == 2) {
					characterType = 1;
				} else {
					characterType = 2;
				}
				return true;
			}
		});
		//gui.add(prevChar);
		nextChar = new GUIButton(">", "Times New Roman", Font.BOLD, true, 184, 115, 30, 30);
		nextChar.setColors(new Color(0, 0, 220), new Color(0, 0, 120), Color.CYAN,
							new Color(0, 0, 250), new Color(0, 0, 140), Color.WHITE);
		nextChar.addActionAdapter(new ActionAdapter() {
			@Override
			public boolean mousePressed(MousePressedEvent event) {
				if (characterType == 1) {
					characterType = 2;
				} else {
					characterType = 1;
				}
				return true;
			}
		});
		//gui.add(nextChar);
		
		// 3 Radio Buttons in group for whether starting server, connecting to server, or local play
		localRadioButton = new GUIRadioButton("Local Gameplay", Color.CYAN, 300, 127);
		serverRadioButton = new GUIRadioButton("Create Server", Color.CYAN, 300, 152);
		connectRadioButton = new GUIRadioButton("Connect to server", Color.CYAN, 300, 177);
		GUIField ipAddress = new GUIField("IP Address", 300, 207, connectRadioButton.getWidth(), 30);
		ipAddress.hide();
		//gui.add(ipAddress); // TODO: Add ipAddress to group in future!
		connectRadioButton.addActionAdapter(new ActionAdapter() {
			@Override
			public boolean mousePressed(MousePressedEvent event) {
				ipAddress.show();
				return true;
			}
		});
		gameplayTypeGroup = new GUIGroup("Gameplay Type", Color.CYAN, 22, 300, 100, connectRadioButton.getWidth(), 100, localRadioButton, serverRadioButton, connectRadioButton);
		gameplayTypeGroup.enable(0);
		//gui.add(gameplayTypeGroup);
		
		backButton = new GUIButton("Back", "Times New Roman", Font.BOLD, true, 10, Game.HEIGHT * Game.SCALE - 40, 80, 30);
		backButton.setColors(new Color(220, 0, 0), new Color(120, 0, 0), Color.CYAN,
							new Color(250, 0, 0), new Color(140, 0, 9), Color.WHITE);
		backButton.setRounded(5, 5);
		backButton.addActionAdapter(new ActionAdapter() {
			@Override
			public boolean mousePressed(MousePressedEvent event) {
				display.changeCurrentGameState(0);
				return true;
			}
		});
		
		//gui.add(backButton);
		
		playButton = new GUIButton("Play!", "Times New Roman", Font.BOLD, true, Game.WIDTH * Game.SCALE - 90, Game.HEIGHT * Game.SCALE - 40, 80, 30);
		playButton.setColors(new Color(0, 220, 0), new Color(0, 120, 0), Color.CYAN,
							new Color(0, 250, 0), new Color(0, 140, 0), Color.WHITE);
		playButton.setRounded(5, 5);
		playButton.addActionAdapter(new ActionAdapter() {
			@Override
			public boolean mousePressed(MousePressedEvent event) {
				if (gameScreen == null) {
					gameScreen = new Gamescreen(getGame(), display, shirtColor, skinColor, characterType, username.getText());
					display.add(gameScreen);
					pauseState = new Pausestate(getGame(), display);
					display.add(pauseState);
					deathState = new Deathstate(getGame(), display);
					display.add(deathState);
				}
				display.changeCurrentGameState(2); // Change to Gamescreen state
				return true;
			}
		});
		//gui.add(playButton);
		shirtColorText = new GUIText("Blue", "Times New Roman", Font.BOLD, Color.cyan, 112, 206, 22);
		//gui.add(shirtColorText);
		
		prevShirtColor = new GUIButton("<", "Times New Roman", Font.BOLD, true, 50, 184, 30, 30);
		prevShirtColor.setColors(new Color(0, 0, 220), new Color(0, 0, 120), Color.CYAN,
									new Color(0, 0, 250), new Color(0, 0, 140), Color.WHITE);
		prevShirtColor.addActionAdapter(new ActionAdapter() {
			@Override
			public boolean mousePressed(MousePressedEvent event) {
				if (shirtColor == CharacterColor.RED) {
					shirtColor = CharacterColor.DEFAULT;
				} else if (shirtColor == CharacterColor.ORANGE) {
					shirtColor = CharacterColor.RED;
				} else if (shirtColor == CharacterColor.YELLOW) {
					shirtColor = CharacterColor.ORANGE;
				} else if (shirtColor == CharacterColor.GREEN) {
					shirtColor = CharacterColor.YELLOW;
				}
				return true;
			}
		});
		//gui.add(prevShirtColor);
		nextShirtColor = new GUIButton(">", "Times New Roman", Font.BOLD, true, 184, 184, 30, 30);
		nextShirtColor.setColors(new Color(0, 0, 220), new Color(0, 0, 120), Color.CYAN,
									new Color(0, 0, 250), new Color(0, 0, 140), Color.WHITE);
		nextShirtColor.addActionAdapter(new ActionAdapter() {
			@Override
			public boolean mousePressed(MousePressedEvent event) {
				if (shirtColor == CharacterColor.DEFAULT) {
					shirtColor = CharacterColor.RED;
				} else if (shirtColor == CharacterColor.RED) {
					shirtColor = CharacterColor.ORANGE;
				} else if (shirtColor == CharacterColor.ORANGE) {
					shirtColor = CharacterColor.YELLOW;
				} else if (shirtColor == CharacterColor.YELLOW) {
					shirtColor = CharacterColor.GREEN;
				}
				return true;
			}
		});
		//gui.add(nextShirtColor);
		
		skinColorText = new GUIText("White", "Times New Roman", Font.BOLD, Color.cyan, 107, 246, 22);
		//gui.add(skinColorText);
		
		prevSkinColor = new GUIButton("<", "Times New Roman", Font.BOLD, true, 50, 224, 30, 30);
		prevSkinColor.setColors(new Color(0, 0, 220), new Color(0, 0, 120), Color.CYAN,
									new Color(0, 0, 250), new Color(0, 0, 140), Color.WHITE);
		prevSkinColor.addActionAdapter(new ActionAdapter() {
			@Override
			public boolean mousePressed(MousePressedEvent event) {
				if (skinColor == CharacterSColor.DEFAULT) {
					skinColor = CharacterSColor.DARK;
				} else if (skinColor == CharacterSColor.DARK) {
					skinColor = CharacterSColor.DEFAULT;
				}
				return true;
			}
		});
		//gui.add(prevSkinColor);
		nextSkinColor = new GUIButton(">", "Times New Roman", Font.BOLD, true, 184, 224, 30, 30);
		nextSkinColor.setColors(new Color(0, 0, 220), new Color(0, 0, 120), Color.CYAN,
									new Color(0, 0, 250), new Color(0, 0, 140), Color.WHITE);
		nextSkinColor.addActionAdapter(new ActionAdapter() {
			@Override
			public boolean mousePressed(MousePressedEvent event) {
				if (skinColor == CharacterSColor.DEFAULT) {
					skinColor = CharacterSColor.DARK;
				} else if (skinColor == CharacterSColor.DARK) {
					skinColor = CharacterSColor.DEFAULT;
				}
				return true;
			}
		});
		
		username = new GUIField("username", 50, 264, 164, 30);
		
		layerList.add(new Layer((g) -> {
			
		}, currentCharacter, title, prevChar, nextChar, gameplayTypeGroup,
		backButton, playButton, shirtColorText, prevShirtColor, nextShirtColor,
		skinColorText, prevSkinColor, nextSkinColor, username, ipAddress));
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
	
	/*@Override
	public void render(Graphics2D g) {
	}*/
	
}
