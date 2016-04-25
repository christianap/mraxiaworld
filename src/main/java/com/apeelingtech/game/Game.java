package com.apeelingtech.game;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.apeelingtech.game.display.Display;
import com.apeelingtech.game.gfx.Screen;
import com.apeelingtech.game.gfx.SpriteSheet;

public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 230;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE = 3;
	public static final String NAME = "Game";
	public static final Dimension DIMENSIONS = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
	public static Game game;
	
	public JFrame frame;
	public Display display;
	public WindowHandler windowHandler;
	
	private Thread thread;
	
	public boolean running = false;
	public int tickCount = 0;
	
	public BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	public int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	public int[] colours = new int[6 * 6 * 6];
	
	public Screen screen;
	
	public boolean debug = true;
	public boolean isApplet = false;
	
	public void init() {
		game = this;
		int index = 0;
		for (int r = 0; r < 6; r++) {
			for (int g = 0; g < 6; g++) {
				for (int b = 0; b < 6; b++) {
					int rr = (r * 255 / 5);
					int gg = (g * 255 / 5);
					int bb = (b * 255 / 5);
					
					colours[index++] = rr << 16 | gg << 8 | bb;
				}
			}
		}
		screen = new Screen(WIDTH, HEIGHT, new SpriteSheet("/sheets/sprite_sheet.png"));
		
		display = new Display(this);
		
	}
	
	public synchronized void start() {
		running = true;
		
		thread = new Thread(this, NAME + "_main");
		thread.start();
	}
	
	public synchronized void stop() {
		running = false;
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		requestFocus();
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D / 60D;
		
		int ticks = 0;
		int frames = 0;
		
		long lastTimer = System.currentTimeMillis();
		double delta = 0;
		
		init();
		
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;
			boolean shouldRender = true;
			
			while (delta >= 1) {
				ticks++;
				tick();
				delta -= 1;
				shouldRender = true;
			}
			
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if (shouldRender) {
				frames++;
				render();
			}
			
			if (System.currentTimeMillis() - lastTimer >= 1000) {
				lastTimer += 1000;
				debug(DebugLevel.INFO, ticks + " ticks, " + frames + " frames");
				frames = 0;
				ticks = 0;
			}
		}
	}
	
	public void tick() {
		tickCount++;
		display.tick();
	}
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics2D g = (Graphics2D) bs.getDrawGraphics();
		g.drawRect(0, 0, WIDTH, HEIGHT);
		display.render(g);
		g.dispose();
		bs.show();
	}
	
	public static long fact(int n) {
		if (n <= 1) {
			return 1;
		} else {
			return n * fact(n - 1);
		}
	}
	
	public void debug(DebugLevel level, String msg) {
		switch (level) {
			default:
			case INFO:
				if (debug) {
					System.out.println("[" + NAME + "] " + msg);
				}
				break;
			case WARNING:
				System.out.println("[" + NAME + "] [WARNING] " + msg);
				break;
			case SEVERE:
				System.out.println("[" + NAME + "] [SEVERE]" + msg);
				this.stop();
				break;
		}
	}
	
	public static enum DebugLevel {
		INFO, WARNING, SEVERE;
	}
}
