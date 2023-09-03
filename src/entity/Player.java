package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{

	GamePanel gp;
	KeyHandler keyH;
	
	public final int screenX, screenY;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		
		this.gp = gp;
		this.keyH = keyH;
		
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		
		solidArea = new Rectangle();
		
		solidArea.x = 16;
		solidArea.y = 32;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 32;
		solidArea.height = 32;
		
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		
		worldX = gp.tileSize * 15;
		worldY = gp.tileSize * 9 ;
		speed = 4;
		direction = "idle";
		
	}
	
	public void getPlayerImage() {
		
		try {
			move1 = ImageIO.read(getClass().getResourceAsStream("/player/walk1.png"));
			move2 = ImageIO.read(getClass().getResourceAsStream("/player/walk2.png"));
			move3 = ImageIO.read(getClass().getResourceAsStream("/player/walk3.png"));
			move4 = ImageIO.read(getClass().getResourceAsStream("/player/walk4.png"));
			
			idle1 = ImageIO.read(getClass().getResourceAsStream("/player/idle1.png"));
			idle2 = ImageIO.read(getClass().getResourceAsStream("/player/idle2.png"));
		} catch(IOException e){
			 e.printStackTrace();
		 }
	}
	
	public void update() {
		
		if (keyH.upP == true || keyH.downP == true || keyH.leftP == true || keyH.rightP == true) {
			
			if(keyH.upP == true) {
				direction = "up";
				
			}
			else if(keyH.downP == true) {
				direction = "down";
				
			}
			else if(keyH.leftP == true) {
				direction = "left";
				
			}
			else if(keyH.rightP == true) {
				direction = "right";
				
			}
			
		} else {
			
			direction = "idle";
		}
		
		// CHECK TILE COLLISION 
		collisionOn = false;
		gp.cd.checkTile(this);
		
		int objectIndex = gp.cd.checkObject(this, true);
		
		// IF COLLISION IS FALSE, PLAYER CAN MOVE
		
		if (collisionOn == false ) {
			
			switch(direction) {
			case"up":
				worldY -= speed;
				break;
			case"down":
				worldY += speed;
				break;
			case"left":
				worldX -= speed;
				break;
			case"right":
				worldX += speed;
			 break;
			
			}
			
		}
		
		spriteCounter++;
		if(spriteCounter > 14) {
			
			if(spriteNum == 1) {
				spriteNum = 2;
			}
			else if(spriteNum == 2) {
				spriteNum = 3;
			}
			else if(spriteNum == 3) {
				spriteNum = 4;
			}
			else if(spriteNum == 4) {
				spriteNum = 1;
			}
			spriteCounter = 0;
			
		}
	}
	
	public void draw(Graphics2D g2) {
		
//        g2.setColor(Color.WHITE);
//		  g2.fillRect(x, y, gp.tileSize, gp.tileSize);
		
		BufferedImage image = null; 
		
		switch(direction) {
		case "right" :
		case "up" :
		case "down" :
		case "left" :
			if(spriteNum == 1) {
				image = move1;
			}
			if(spriteNum == 2) {
				image = move2;
			}
			if(spriteNum == 3) {
				image = move3;
			}
			if(spriteNum == 4) {
				image = move4;
			}
			
			break;
		case "idle":
			if(spriteNum == 1) {
				image = idle1;
			}
			if(spriteNum == 2) {
				image = idle2;
			}
			if(spriteNum == 3) {
				image = idle1;
			}
			if(spriteNum == 4) {
				image = idle2;
			}
			break;
		}
		g2.drawImage(image,screenX,screenY,gp.tileSize,gp.tileSize,null);
		
		
	}
}
