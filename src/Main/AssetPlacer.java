package Main;

import Robo.terminal;
import object.OBJ_Disk;
import object.OBJ_Terminal;

public class AssetPlacer {
	
		GamePanel gp;
	
	public AssetPlacer(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		
		gp.obj[0] = new OBJ_Disk();
		gp.obj[0].worldX = 12 * gp.tileSize;
		gp.obj[0].worldY = 9 * gp.tileSize;
		
		
		gp.obj[1] = new OBJ_Disk();
		gp.obj[1].worldX = 22 * gp.tileSize;
		gp.obj[1].worldY = 10 * gp.tileSize;
		
	/*	
		gp.obj[2] = new OBJ_Disk();
		gp.obj[2].worldX = 25 * gp.tileSize;
		gp.obj[2].worldY = 25 * gp.tileSize;
	*/	
		
	//	gp.obj[2] = new OBJ_Terminal();
	//	gp.obj[2].worldX = 6 * gp.tileSize;
	//	gp.obj[2].worldY = 3 * gp.tileSize;
		
		
	}
	public void setTerminal() {
		
		//top terminal
		gp.terminal[0] = new terminal(gp);
		gp.terminal[0].worldX = 6 * gp.tileSize;
		gp.terminal[0].worldY = 3 *gp.tileSize;
		
		//test terminal
		/*
		gp.terminal[1] = new terminal(gp);
		gp.terminal[1].worldX = 20 * gp.tileSize;
		gp.terminal[1].worldY = 20 * gp.tileSize;
		*/
		//bot terminal
		
		gp.terminal[2] = new terminal(gp);
		gp.terminal[2].worldX = 5 * gp.tileSize;
		gp.terminal[2].worldY = 29 * gp.tileSize;
		
		
	}
}
