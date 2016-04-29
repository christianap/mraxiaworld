package com.apeelingtech.game.display.gamestates;

import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.JOptionPane;

import com.apeelingtech.game.Game;
import com.apeelingtech.game.layers.Layer;
import com.apeelingtech.game.display.Display;
import com.apeelingtech.game.entities.Player;
import com.apeelingtech.game.entities.PlayerMP;
import com.apeelingtech.game.level.Level;
import com.apeelingtech.game.net.GameClient;
import com.apeelingtech.game.net.GameServer;
import com.apeelingtech.game.net.packets.Packet00Login;

public class Gamescreen extends GameState {
	
	public Player player;
	public Level level;
	
	public GameClient socketClient;
	public GameServer socketServer;
	
	private SetupState.CharacterColor shirtColor = SetupState.CharacterColor.DEFAULT;
	private SetupState.CharacterSColor skinColor = SetupState.CharacterSColor.DEFAULT;
	private String username;
	private byte characterType = 1;
	
	public Gamescreen(Game game, Display display, SetupState.CharacterColor shirtColor, SetupState.CharacterSColor skinColor, byte characterType, String username) {
		super(game, Color.GREEN, display);
		this.shirtColor = shirtColor;
		this.skinColor = skinColor;
		this.characterType = characterType;
		this.username = username;
		init();
	}
	
	public void init() {
		if (!game.isApplet) { // TODO
			if (JOptionPane.showConfirmDialog(game, "Do you want to run the server?") == 0) {
				socketServer = new GameServer(this);
				socketServer.start();
				
				socketClient = new GameClient(this, "localhost");
			} else {
				if (JOptionPane.showConfirmDialog(game, "Do you want to connect to a server?") == 0) {
					socketClient = new GameClient(this, JOptionPane.showInputDialog("What IP do you want to connect to?", "localhost"));
				} else {
					socketClient = new GameClient(this, "localhost");
				}
			}
			socketClient.start();
		}
		
		level = new Level("/levels/water_test_level.png");
		
		player = new PlayerMP(this, 100, 100, username, null, -1, shirtColor.getColor(), skinColor.getSkinColor(), characterType);
		level.addEntity(player);
		if (!game.isApplet) {
			Packet00Login loginPacket = new Packet00Login(player.getUsername(), player.x, player.y, player.getShirtColor(), player.getSkinColor(), characterType);
			if (socketServer != null) {
				socketServer.addConnection((PlayerMP) player, loginPacket);
			}
			loginPacket.writeData(socketClient);
		}
		
		layerList.add(new Layer((g) -> {
			int xOffset = player.x - (game.screen.width / 2);
			int yOffset = player.y - (game.screen.height / 2);
			
			level.renderTiles(game.screen, xOffset, yOffset);
			level.renderEntities(game.screen);
			
			for (int y = 0; y < game.screen.height; y++) {
				for (int x = 0; x < game.screen.width; x++) {
					int colourCode = game.screen.pixels[x + y * game.screen.width];
					if (colourCode < 255) game.pixels[x + y * Game.WIDTH] = game.colours[colourCode];
				}
			}
		}));
	}
	
	@Override
	public void tick() {
		level.tick();
	}
}
