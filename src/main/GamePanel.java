package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
	
	// SCREEN Settings 
	public final int originalTileSize = 32;
	final int scale = 2;
	
	public final int tileSize = originalTileSize * scale; //64x64 tile
	
	public final int maxScreenCol = 30;
	public final int maxScreenRow = 16;
	public final int screenWidth = tileSize * maxScreenCol;  // 1980 px
	public final int screenHeight = tileSize * maxScreenRow; // 992 px 
	
	// World settings 
	
	public final int maxWorldCol = 30;
	public final int maxWorldRow = 16;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;
	
	
	TileManager tm = new TileManager(this);
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	public CollisionDetection cd = new CollisionDetection(this);
	public AssetSetter as = new AssetSetter(this);
	public Player player = new Player(this, keyH);
	public SuperObject obj[] = new SuperObject[10];
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		
		
	}
	
	public void setupGame() {
		
		as.setObject();
	}
	
	public void startGameThread() {
		
		gameThread = new Thread(this);
		gameThread.start();
		
	}
	
	@Override
	public void run() {
		long lastime = System.nanoTime();
		double AmountOfTicks = 60;
		double ns = 1000000000 / AmountOfTicks;
		double delta = 0;
		int frames = 0;
		double time = System.currentTimeMillis();
		
		while(gameThread != null) {
			long now = System.nanoTime();
			delta += (now - lastime) / ns;
			lastime = now;
			
			if(delta >= 1) {
				update();
				repaint();
				frames++;
				delta--;
				if(System.currentTimeMillis() - time >= 1000) {
					System.out.println("fps:" + frames);
					time += 1000;
					frames = 0;
				}
			}
		}
	}
	
	public void update() {
		
		player.update();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		tm.draw(g2);
		
		for(int i = 0; i < obj.length; i++) {
			if(obj[i] != null) {
				obj[i].draw(g2, this);
			}
		}
		
		player.draw(g2);
		
		g2.dispose();
	}
	
}

