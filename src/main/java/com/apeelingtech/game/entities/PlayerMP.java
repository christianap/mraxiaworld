package com.apeelingtech.game.entities;

import java.net.InetAddress;

import com.apeelingtech.game.display.gamestates.Gamescreen;
import com.apeelingtech.game.level.Level;

public class PlayerMP extends Player {
	
	public InetAddress ipAddress;
	public int port;
	
	public PlayerMP(Level level, Gamescreen gameScreen, int x, int y, String username, InetAddress ipAddress, int port, int shirtColor, int skinColor, byte characterType) {
		super(level, gameScreen, x, y, username, shirtColor, skinColor, characterType);
		this.ipAddress = ipAddress;
		this.port = port;
	}
	
	@Override
	public void tick() {
		super.tick();
	}
}
