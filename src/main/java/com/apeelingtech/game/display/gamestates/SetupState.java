package com.apeelingtech.game.display.gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import com.apeelingtech.game.Game;
import com.apeelingtech.game.display.Display;
import com.apeelingtech.game.layers.Layer;
import com.apeelingtech.game.display.gui.ActionAdapter;
import com.apeelingtech.game.display.gui.RadioActionAdapter;
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
	
	private GUIImage currentLevel;
	private String currentLevelFile = "/levels/water_test_level.png";
	private GUIButton prevLevel;
	private GUIButton nextLevel;
	private GUIText levelTitle;
	
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

		currentCharacter = new GUIImage(100, 100, 64, 64, "/C1Default.png"); // Animate this picture??? TODO
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

		shirtColorText = new GUIText("Blue", "Times New Roman", Font.BOLD, Color.cyan, 112, 206, 22);
		
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
		
		skinColorText = new GUIText("White", "Times New Roman", Font.BOLD, Color.cyan, 107, 246, 22);
		
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
		
		// 3 Radio Buttons in group for whether starting server, connecting to server, or local play
		localRadioButton = new GUIRadioButton("Local Gameplay", Color.CYAN, 300, 127);
		serverRadioButton = new GUIRadioButton("Create Server", Color.CYAN, 300, 152);
		connectRadioButton = new GUIRadioButton("Connect to server", Color.CYAN, 300, 177);
		GUIField ipAddress = new GUIField("IP Address", 300, 207, connectRadioButton.getWidth(), 30);
		ipAddress.hide();
		connectRadioButton.addActionAdapter(new RadioActionAdapter() {
			@Override
			public boolean mousePressed(MousePressedEvent event) {
				ipAddress.show();
				currentLevel.hide();
				levelTitle.hide();
				prevLevel.hide();
				nextLevel.hide();
				//currentLevelFile = "/levels/water_test_level.png";
				return true;
			}
			
			@Override
			public void onEnable() {
				ipAddress.show();
				currentLevel.hide();
				levelTitle.hide();
				prevLevel.hide();
				nextLevel.hide();
				//currentLevelFile = "/levels/water_test_level.png";
			}
			
			@Override
			public void onDisable() {
				ipAddress.hide();
				currentLevel.show();
				levelTitle.show();
				prevLevel.show();
				nextLevel.show();
			}
		});
		gameplayTypeGroup = new GUIGroup("Gameplay Type", Color.CYAN, 22, 300, 100, connectRadioButton.getWidth(), 100, localRadioButton, serverRadioButton, connectRadioButton);
		gameplayTypeGroup.enable(0);

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
		
		playButton = new GUIButton("Play!", "Times New Roman", Font.BOLD, true, Game.WIDTH * Game.SCALE - 90, Game.HEIGHT * Game.SCALE - 40, 80, 30);
		playButton.setColors(new Color(0, 220, 0), new Color(0, 120, 0), Color.CYAN,
							new Color(0, 250, 0), new Color(0, 140, 0), Color.WHITE);
		playButton.setRounded(5, 5);
		playButton.addActionAdapter(new ActionAdapter() {
			@Override
			public boolean mousePressed(MousePressedEvent event) {
				if (gameScreen == null) {
					Gamescreen.GamePlayType type;
					if (gameplayTypeGroup.getCurrentEnabled() == 0) {
						type = Gamescreen.GamePlayType.LOCAL;
					} else if (gameplayTypeGroup.getCurrentEnabled() == 1) {
						type = Gamescreen.GamePlayType.LOCALSERVER;
					} else if (gameplayTypeGroup.getCurrentEnabled() == 2) {
						type = Gamescreen.GamePlayType.CONNECT;
					} else {
						type = Gamescreen.GamePlayType.LOCAL;
					}
					gameScreen = new Gamescreen(getGame(), display, shirtColor, skinColor, characterType, username.getText(), type, ipAddress.getText(), currentLevelFile);
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

		// Level Settings
		levelTitle = new GUIText("Choose a level", Color.CYAN, 300, 230, 22);
		currentLevel = new GUIImage(335, 235, 75, 75, "/levels/water_test_level.png");
		prevLevel = new GUIButton("<", "Times New Roman", Font.BOLD, true, 300, 240, 30, 30);
		prevLevel.setColors(new Color(0, 0, 220), new Color(0, 0, 120), Color.CYAN,
							new Color(0, 0, 250), new Color(0, 0, 140), Color.WHITE);
		prevLevel.addActionAdapter(new ActionAdapter() {
			@Override
			public boolean mousePressed(MousePressedEvent event) {
				if (currentLevelFile == "/levels/water_test_level.png") {
					currentLevelFile = "/levels/small_level.png";
				} else {
					currentLevelFile = "/levels/water_test_level.png";
				}
				currentLevel.setImage(currentLevelFile);
				return true;
			}
		});

		nextLevel = new GUIButton(">", "Times New Roman", Font.BOLD, true, 335 + 80, 240, 30, 30);
		nextLevel.setColors(new Color(0, 0, 220), new Color(0, 0, 120), Color.CYAN,
							new Color(0, 0, 250), new Color(0, 0, 140), Color.WHITE);
		nextLevel.addActionAdapter(new ActionAdapter() {
			@Override
			public boolean mousePressed(MousePressedEvent event) {
				if (currentLevelFile == "/levels/water_test_level.png") {
					currentLevelFile = "/levels/small_level.png";
				} else {
					currentLevelFile = "/levels/water_test_level.png";
				}
				currentLevel.setImage(currentLevelFile);
				return true;
			}
		});

		layerList.add(new Layer((g) -> {
			
		}, currentCharacter, title, prevChar, nextChar, gameplayTypeGroup,
		backButton, playButton, shirtColorText, prevShirtColor, nextShirtColor,
		skinColorText, prevSkinColor, nextSkinColor, username, ipAddress, levelTitle, currentLevel, prevLevel, nextLevel));
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
	
}
