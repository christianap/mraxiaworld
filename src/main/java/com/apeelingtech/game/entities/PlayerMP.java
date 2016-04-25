package com.apeelingtech.game.entities;

import java.net.InetAddress;

import com.apeelingtech.game.display.gui.GameGUI;
import com.apeelingtech.game.level.Level;

public class PlayerMP extends Player {
	
	public InetAddress ipAddress;
	public int port;
	
	public PlayerMP(Level level, int x, int y, GameGUI input, String username, InetAddress ipAddress, int port, int shirtColor, int skinColor, byte characterType) {
		super(level, x, y, input, username, shirtColor, skinColor, characterType);
		this.ipAddress = ipAddress;
		this.port = port;
	}
	
	public PlayerMP(Level level, int x, int y, String username, InetAddress ipAddress, int port, int shirtColor, int skinColor, byte characterType) {
		super(level, x, y, null, username, shirtColor, skinColor, characterType);
		this.ipAddress = ipAddress;
		this.port = port;
	}
	
	@Override
	public void tick() {
		super.tick();
	}
}
