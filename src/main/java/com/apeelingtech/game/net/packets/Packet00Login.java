package com.apeelingtech.game.net.packets;

import com.apeelingtech.game.net.GameClient;
import com.apeelingtech.game.net.GameServer;

public class Packet00Login extends Packet {
	
	private String username;
	private int x, y;
	private int shirtColor;
	private int skinColor;
	private byte characterType;
	
	public Packet00Login(byte[] data) {
		super(00);
		String[] dataArray = readData(data).split(",");
		this.username = dataArray[0];
		this.x = Integer.parseInt(dataArray[1]);
		this.y = Integer.parseInt(dataArray[2]);
		this.shirtColor = Integer.parseInt(dataArray[3]);
		this.skinColor = Integer.parseInt(dataArray[4]);
		this.characterType = Byte.parseByte(dataArray[5]);
	}
	
	public Packet00Login(String username, int x, int y, int shirtColor, int skinColor, byte characterType) {
		super(00);
		this.shirtColor = shirtColor;
		this.skinColor = skinColor;
		this.characterType = characterType;
		this.username = username;
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void writeData(GameClient client) {
		client.sendData(getData());
	}
	
	@Override
	public void writeData(GameServer server) {
		server.sendDataToAllClients(getData());
	}
	
	@Override
	public byte[] getData() {
		return ("00" + this.username + "," + getX() + "," + getY() + "," + this.shirtColor + "," + this.skinColor + "," + this.characterType).getBytes();
	}
	
	public String getUsername() {
		return username;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
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
