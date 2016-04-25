package com.apeelingtech.game.display.gui;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class GUIImage extends GUIElement {
	
	private URL path;
	private BufferedImage pic;
	
	public GUIImage(int x, int y, int width, int height, URL path) {
		super(x, y, width, height);
		this.path = path;
		loadImage();
	}
	
	public GUIImage(int x, int y, int width, int height, String path) {
		super(x, y, width, height);
		this.path = GUIImage.class.getResource(path);
		loadImage();
	}
	
	public GUIImage(int x, int y, URL path) {
		super(x, y, 0, 0);
		this.path = path;
		loadImage2();
	}
	
	public GUIImage(int x, int y, String path) {
		super(x, y, 0, 0);
		this.path = GUIImage.class.getResource(path);
		loadImage2();
	}
	
	private void loadImage() {
		try {
			pic = ImageIO.read(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void loadImage2() {
		try {
			pic = ImageIO.read(path);
			setWidth(image.getWidth());
			setHeight(image.getHeight());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setImage(String path) {
		this.path = GUIImage.class.getResource(path);
		loadImage();
	}
	
	public void setImage(URL path) {
		this.path = path;
		loadImage();
	}
	
	@Override
	public void render(Graphics2D g) {
		g.drawImage(pic, getX(), getY(), getWidth(), getHeight(), null);
	}
	
}
