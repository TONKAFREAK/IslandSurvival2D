package entity;

import java.awt.Rectangle;
import java.util.Random;

import main.GamePanel;

public class NPC_Start extends Entity {

	public NPC_Start(GamePanel gp) {
		super(gp);
		
		direction = "idle";
		speed = 4;
		
		solidArea = new Rectangle();
		
		solidArea.x = 22;
		solidArea.y = 30;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 18;
		solidArea.height = 30;
		
		getNPCImage();
	}
	
	public void getNPCImage() {
		
		idle1 = setup("/npc/idleNPC1");
		idle2 = setup("/npc/idleNPC2");
	}
	
	public void setAction() {
		
		actionLockCounter++;
		if(actionLockCounter == 120) {
			Random random = new Random();
			int i = random.nextInt(100)+1;  // random num from 1 to 100
			
			if (0 < i && i < 101) {
				
				direction = "idle";
				
			}
			
			actionLockCounter = 0;
		}
		
		
		
	}
	

}
