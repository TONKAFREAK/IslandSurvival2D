package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Entity;
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
	public final int screenHeight = tileSize * maxScreenRow - 20; // 972 px 
	
	// World settings 
	
	public final int maxWorldCol = 30;  //change depending on the world map size.
	public final int maxWorldRow = 16;
	
	TileManager tm = new TileManager(this);
	
	KeyHandler keyH = new KeyHandler(this);
	MouseHandler mouseH = new MouseHandler(this);
	
	public CollisionDetection cd = new CollisionDetection(this);
	public AssetSetter as = new AssetSetter(this);
	
	Sound music = new Sound();
	Sound SE = new Sound();
	
	public UI ui = new UI(this);
	
	Thread gameThread;
	
	public Player player = new Player(this, keyH, mouseH);
	public SuperObject obj[] = new SuperObject[10];
	public Entity npc[] = new Entity[10];
	
	public enum STATE {
		
		Pause,
		Game
		
	}
	
	public STATE gameState;
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.addMouseListener(mouseH);
		this.addMouseMotionListener(mouseH);
		this.setFocusable(true);
		
		
	}
	
	public void setupGame() { 
		
		as.setNPC();
		
		playMusic(0); 
		
		gameState = STATE.Game;
		
		
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
		
		if (gameState == STATE.Game) {
			
			player.update();
			
			for(int i = 0; i < npc.length; i++) {
				if(npc[i] != null) {
					npc[i].update();
				}
			}
			
		}
		if (gameState == STATE.Pause) {
			
			
			
		}
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		long drawStart = 0;
		drawStart = System.nanoTime();
		
		tm.draw(g2);
		
		// objects
		
		for(int i = 0; i < obj.length; i++) {
			if(obj[i] != null) {
				obj[i].draw(g2, this);
			}
		}
		// NPC 
		
		for(int i = 0; i < npc.length; i++) {
			if(npc[i] != null) {
				npc[i].draw(g2);
			}
			
		}
		
		player.draw(g2);
		
		ui.draw(g2);;
		
//		long drawEnd = System.nanoTime();
//		long passed = drawEnd - drawStart;   DEBUG
//		System.out.println(passed);
		
//		int h = mouseH.mouseX;
//		System.out.println(h);  DEBUG
		
		g2.dispose();
	}
	
	public void playMusic(int i) {
		
		music.setFile(i);
		music.play();
		music.loop();
		
	}
	
	public void stopMusic() {
		
		music.stop();
		
	}
	
	public void playSE(int i) {
		
		SE.setFile(i);
		SE.play();
		
	}
	
}

