package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.MouseHandler;
import main.UtilityTool;

public class Player extends Entity{

	KeyHandler keyH;
	MouseHandler mouseH;
	
	BufferedImage moveL1, moveL2, moveL3, moveL4, idleL1, idleL2;
	
	public final int screenX, screenY;
	
	public Player(GamePanel gp, KeyHandler keyH, MouseHandler mouseH) {
		
		super(gp);
		this.keyH = keyH;
		this.mouseH = mouseH;
		
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		
		solidArea.x = 22;
		solidArea.y = 30;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 18;
		solidArea.height = 30;
		
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
		
			move1 = setup("/player/walk1");
			move2 = setup("/player/walk2");
			move3 = setup("/player/walk3");
			move4 = setup("/player/walk4");
			
			moveL1 = setup("/player/walkL1");
			moveL2 = setup("/player/walkL2");
			moveL3 = setup("/player/walkL3");
			moveL4 = setup("/player/walkL4");
			
			idle1 = setup("/player/idle1");
			idle2 = setup("/player/idle2");
			
			idleL1 = setup("/player/idleL1");
			idleL2 = setup("/player/idleL2");

			
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
		objectInteraction(objectIndex);
		
		
		//System.out.println(objectIndex);
		//System.out.println(worldX/64+", "+worldY/64);
		
		// check npc collision 
		
		int npcIndex = gp.cd.checkEntity(this, gp.npc);
		npcIntercation(npcIndex);
		
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
	
	private void npcIntercation(int i) {
		
		if(i != -1) {
			
		}
	}

	public void objectInteraction(int i) {
		
		if(i != -1) {
			
			String objectName = gp.obj[i].name;
			
			switch(objectName) {
			case "chest":
				
				break;
				
			}
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
			if (mouseH.getMouseX() >= worldX) {
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
			} 
			else {
				
				if(spriteNum == 1) {
					image = moveL1;
				}
				if(spriteNum == 2) {
					image = moveL2;
				}
				if(spriteNum == 3) {
					image = moveL3;
				}
				if(spriteNum == 4) {
					image = moveL4;
				}
				
				break;
			}
			
		case "idle":
			if (mouseH.getMouseX() >= worldX+6) {
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
			else {
				if(spriteNum == 1) {
					image = idleL1;
				}
				if(spriteNum == 2) {
					image = idleL2;
				}
				if(spriteNum == 3) {
					image = idleL1;
				}
				if(spriteNum == 4) {
					image = idleL2;
				}
				break;
			}
		}
		g2.drawImage(image,screenX,screenY,null);
		
		
		
	}
}
