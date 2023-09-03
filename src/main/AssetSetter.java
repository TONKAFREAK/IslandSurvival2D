package main;

import object.Chest;

public class AssetSetter {
	
	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
			
		this.gp = gp;
			
	}
	
	public void setObject() {
		
		gp.obj[0] = new Chest();
		gp.obj[0].worldX = 15 * gp.tileSize;
		gp.obj[0].worldY = 8 * gp.tileSize;
	}
}
