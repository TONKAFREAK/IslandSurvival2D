package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
	
	public int worldX;
	public int worldY;
	public int speed;
	
	public BufferedImage idle1,idle2, move1, move2, move3, move4;
	public String direction;
	
	public int spriteCounter = 1;
	public int spriteNum = 1;
	
	public Rectangle solidArea;
	public int solidAreaDefaultX,solidAreaDefaultY;
	public boolean collisionOn = false;
}

