package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class Entity {
	
	public int worldX;
	public int worldY;
	public int speed;
	public int actionLockCounter;
	
	public BufferedImage idle1,idle2, move1, move2, move3, move4;
	public String direction;
	public GamePanel gp;
	
	public int spriteCounter = 1;
	public int spriteNum = 1;
	
	public Rectangle solidArea = new Rectangle(0,0,64,64);;
	public int solidAreaDefaultX,solidAreaDefaultY;
	public boolean collisionOn = false;
	
	public Entity (GamePanel gp) {
		
		this.gp = gp;
		
	}
	
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null; 
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		
		// renders only the player shown tiles 
		if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
		   worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && 
		   worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && 
		   worldY - gp.tileSize < gp.player.worldY + gp.player.screenY ) {
		
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
			
			g2.drawImage(image, screenX, screenY, gp.tileSize,gp.tileSize,null);
		}
	}

	public void update() {
		
		setAction();
		
		collisionOn = false;
		gp.cd.checkTile(this);
		
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
	
	public void setAction() {
		
	}
	
	public BufferedImage setup(String imageName) {
		
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream(imageName + ".png"));
			image = uTool.scaleIamge(image, gp.tileSize, gp.tileSize);
		
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return image;
		
	}
	
}


