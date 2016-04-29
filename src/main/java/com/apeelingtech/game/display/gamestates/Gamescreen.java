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
	
	public enum GamePlayType {
		LOCAL,
		LOCALSERVER,
		CONNECT
	}
	
	public Player player;
	public Level level;
	
	public GameClient socketClient;
	public GameServer socketServer;
	
	private SetupState.CharacterColor shirtColor = SetupState.CharacterColor.DEFAULT;
	private SetupState.CharacterSColor skinColor = SetupState.CharacterSColor.DEFAULT;
	private String username;
	private byte characterType = 1;
	private String ipAddress;
	private GamePlayType type;
	private String levelString;
	
	public Gamescreen(Game game, Display display, SetupState.CharacterColor shirtColor, SetupState.CharacterSColor skinColor, byte characterType, String username, GamePlayType type, String ipAddress, String levelString) {
		super(game, Color.GREEN, display);
		this.shirtColor = shirtColor;
		this.skinColor = skinColor;
		this.characterType = characterType;
		this.username = username;
		System.out.println("Usernmame: " + username);
		this.type = type;
		this.ipAddress = ipAddress;
		this.levelString = levelString;
		init();
	}
	
	public void init() {
		if (!game.isApplet) {
			if (type == GamePlayType.LOCALSERVER) { // Want's to run a server
				socketServer = new GameServer(this);
				socketServer.start();
				socketClient = new GameClient(this, "localhost");
				System.out.println("Created SERVER");
			} else if (type == GamePlayType.CONNECT) { // Wants to connect to server
				if (ipAddress == null || ipAddress == "" || ipAddress == "IP Address") {
					ipAddress = "localhost";
				}
				socketClient = new GameClient(this, ipAddress);
				System.out.println("Connected to server");
			} else if (type == GamePlayType.LOCAL) {
				socketClient = new GameClient(this, "localhost");
				System.out.println("Local");
			}
			socketClient.start();
		}
		
		level = new Level(levelString);
		
		if (username == null || username == "") {
			username = JOptionPane.showInputDialog("Please enter a username that is not blank", "");
		}
		player = new PlayerMP(this, true, 100, 100, username, null, -1, shirtColor.getColor(), skinColor.getSkinColor(), characterType); // Local player
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
