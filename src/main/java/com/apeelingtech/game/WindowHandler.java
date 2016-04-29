package com.apeelingtech.game;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import com.apeelingtech.game.display.gamestates.Gamescreen;
import com.apeelingtech.game.net.packets.Packet01Disconnect;

public class WindowHandler implements WindowListener {
	
	private final Game game;
	
	public WindowHandler(Game game) {
		this.game = game;
		this.game.frame.addWindowListener(this);
	}
	
	@Override
	public void windowActivated(WindowEvent event) {
	}
	
	@Override
	public void windowClosed(WindowEvent event) {
	}
	
	@Override
	public void windowClosing(WindowEvent event) {
		try {
			if (this.game.display.gameStates.get(2) != null) {
				Packet01Disconnect packet = new Packet01Disconnect(((Gamescreen) this.game.display.gameStates.get(2)).player.getUsername());
				packet.writeData(((Gamescreen) this.game.display.gameStates.get(2)).socketClient);
			}
		} catch (IndexOutOfBoundsException e) {
		} catch (NullPointerException n) {
		}
	}
	
	@Override
	public void windowDeactivated(WindowEvent event) {
	}
	
	@Override
	public void windowDeiconified(WindowEvent event) {
	}
	
	@Override
	public void windowIconified(WindowEvent event) {
	}
	
	@Override
	public void windowOpened(WindowEvent event) {
	}
	
}
