package com.apeelingtech.game.entities;

import com.apeelingtech.game.display.gamestates.SetupState;
import com.apeelingtech.game.display.gamestates.Gamescreen;
import com.apeelingtech.game.gfx.Colours;
import com.apeelingtech.game.gfx.Font;
import com.apeelingtech.game.gfx.Screen;
import com.apeelingtech.game.level.Level;
import com.apeelingtech.game.net.packets.Packet02Move;
import java.awt.event.KeyEvent;

public class Player extends Mob {
	
	// -1, 111, 145, 543 - Default
	// 550 - Yellow (3rd)
	//
	private int colour = Colours.get(-1, 111, 145, 543); // 432 for darker skin color
	private int scale = 1;
	protected boolean isSwimming = false;
	private int tickCount = 0;
	private String username;
	private int shirtColor = SetupState.CharacterColor.DEFAULT.getColor();
	private int skinColor = SetupState.CharacterSColor.DEFAULT.getSkinColor();
	private byte characterType = 1;
	private Gamescreen gameScreen; // Pass keys instead?
	
	public Player(Level level, Gamescreen gameScreen, int x, int y, String username, int shirtColor, int skinColor, byte characterType) { // TODO: Change gui and input
		super(level, "Player", x, y, 1);
		this.shirtColor = shirtColor;
		this.skinColor = skinColor;
		this.characterType = characterType;
		colour = Colours.get(-1, 111, shirtColor, skinColor);
		this.username = username;
		this.gameScreen = gameScreen;
	}
	
	public void tick() {
		int xa = 0;
		int ya = 0;

		if (gameScreen.getDisplay().keys[KeyEvent.VK_UP]) {
			ya--;
		} else if (gameScreen.getDisplay().keys[KeyEvent.VK_DOWN]) {
			ya++;
		}
		if (gameScreen.getDisplay().keys[KeyEvent.VK_LEFT]) {
			xa--;
		} else if (gameScreen.getDisplay().keys[KeyEvent.VK_RIGHT]) {
			xa++;
		}

		if (xa != 0 || ya != 0) {
			move(xa, ya);
			isMoving = true;
			
			if (!gameScreen.getGame().isApplet) {
				Packet02Move packet = new Packet02Move(this.getUsername(), this.x, this.y, this.numSteps, this.isMoving, this.movingDir);
				packet.writeData(gameScreen.socketClient);
			}
		} else {
			isMoving = false;
		}
		if (level.getTile(this.x >> 3, this.y >> 3).getId() == 3) {
			isSwimming = true;
		}
		if (isSwimming && level.getTile(this.x >> 3, this.y >> 3).getId() != 3) {
			isSwimming = false;
		}
		tickCount++;
	}
	
	public void render(Screen screen) {
		int xTile = 0;
		int yTile = 0;
		if (characterType == 1) {
			yTile = 28;
		} else {
			yTile = 26;
		}
		int walkingSpeed = 4;
		int flipTop = (numSteps >> walkingSpeed) & 1;
		int flipBottom = (numSteps >> walkingSpeed) & 1;
		
		if (movingDir == 1) {
			xTile += 2;
		} else if (movingDir > 1) {
			xTile += 4 + ((numSteps >> walkingSpeed) & 1) * 2;
			flipTop = (movingDir - 1) % 2;
		}
		
		int modifier = 8 * scale;
		int xOffset = x - modifier / 2;
		int yOffset = y - modifier / 2 - 4;
		if (isSwimming) {
			int waterColour = 0;
			yOffset += 4;
			if (tickCount % 60 < 15) {
				waterColour = Colours.get(-1, -1, 225, -1);
			} else if (15 <= tickCount % 60 && tickCount % 60 < 30) {
				yOffset -= 1;
				waterColour = Colours.get(-1, 225, 115, -1);
			} else if (30 <= tickCount % 60 && tickCount % 60 < 45) {
				waterColour = Colours.get(-1, 115, -1, 225);
			} else {
				yOffset -= 1;
				waterColour = Colours.get(-1, 225, 115, -1);
			}
			screen.render(xOffset, yOffset + 3, 0 + 25 * 32, waterColour, 0x00, 1);
			screen.render(xOffset + 8, yOffset + 3, 0 + 25 * 32, waterColour, 0x01, 1);
		}
		screen.render(xOffset + (modifier * flipTop), yOffset, xTile + yTile * 32, colour, flipTop, scale);
		screen.render(xOffset + modifier - (modifier * flipTop), yOffset, (xTile + 1) + yTile * 32, colour, flipTop, scale);
		
		if (!isSwimming) {
			screen.render(xOffset + (modifier * flipBottom), yOffset + modifier, xTile + (yTile + 1) * 32, colour, flipBottom, scale);
			screen.render(xOffset + modifier - (modifier * flipBottom), yOffset + modifier, (xTile + 1) + (yTile + 1) * 32, colour, flipBottom, scale);
		}
		if (username != null) {
			Font.render(username, screen, xOffset - ((username.length() - 1) / 2 * 8), yOffset - 10, Colours.get(-1, -1, -1, 555), 1);
		}
	}
	
	public boolean hasCollided(int xa, int ya) {
		int xMin = 0;
		int xMax = 7;
		int yMin = 3;
		int yMax = 7;
		for (int x = xMin; x < xMax; x++) {
			if (isSolidTile(xa, ya, x, yMin)) {
				return true;
			}
		}
		for (int x = xMin; x < xMax; x++) {
			if (isSolidTile(xa, ya, x, yMax)) {
				return true;
			}
		}
		for (int y = yMin; y < yMax; y++) {
			if (isSolidTile(xa, ya, xMin, y)) {
				return true;
			}
		}
		for (int y = yMin; y < yMax; y++) {
			if (isSolidTile(xa, ya, xMax, y)) {
				return true;
			}
		}
		return false;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public int getShirtColor() {
		return shirtColor;
	}
	
	public int getSkinColor() {
		return skinColor;
	}
	
	public byte getCharacterType() {
		return characterType;
	}
}
