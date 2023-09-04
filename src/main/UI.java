package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel.STATE;

public class UI {
	
	GamePanel gp;
	Graphics2D g2;
	Font arial_40 = new Font("Arial", Font.PLAIN, 40);
	
	BufferedImage avatar, menuBar;
	
	public boolean messageOn = false;
	public String message = "";
	
	public UI(GamePanel gp) {
		
		this.gp = gp;
		
		menuBar = setup("menuBar",224,224);
		avatar = setup("avatar");
	}
	
	public void showMassage(String text) {
		
		message = text;
		messageOn = true;
	}
	
	public void draw(Graphics2D g2) {
		
		this.g2 = g2;
		
		if(messageOn == true) {
				
			g2.drawString(message, 0, 0);
			
		}
		
		if(gp.gameState == STATE.Game) {
			
			g2.drawImage(avatar,4,4,null);    
			
			
			g2.setColor(Color.black);   // panel for healthBar + stamina
			g2.fillRect(68, 4, 128, 40); 
			
			g2.setColor(Color.red);
			g2.fillRect(68, 44, 128, 22); // exp bar 
			
		}
		if(gp.gameState == STATE.Pause) {
			
			drawPauseScreen(g2);
		}
		
		
		
		
		
	}
	
	public void drawPauseScreen(Graphics2D g2) {
		
		g2.drawImage(menuBar, (gp.screenWidth/2)-112, (gp.screenHeight/2)-112, null);
		
	}
	
	public BufferedImage setup(String imageName, int width, int height) {
		
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/ui/"+ imageName + ".png"));
			image = uTool.scaleIamge(image, width, height);
		
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return image;
		
	}
	
	public BufferedImage setup(String imageName) {
		
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/ui/"+ imageName + ".png"));
			image = uTool.scaleIamge(image, gp.tileSize, gp.tileSize);
		
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return image;
		
	}
}
