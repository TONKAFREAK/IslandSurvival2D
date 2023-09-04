package main;

import entity.NPC_Start;
import object.Chest;

public class AssetSetter {
	
	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
			
		this.gp = gp;
			
	}
	
	public void setObject() {
		
		gp.obj[0] = new Chest(gp);
		gp.obj[0].worldX = 15 * gp.tileSize;
		gp.obj[0].worldY = 8 * gp.tileSize;
	}
	
	public void setNPC() {
		
		gp.npc[0] = new NPC_Start(gp);
		gp.npc[0].worldX = 15 * gp.tileSize;
		gp.npc[0].worldY = 8 * gp.tileSize;
	}
}
