package Main;

import Robo.Robo;

public class CollisionChecker {
	
	GamePanel gp;
	
	
	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}
	
	public void checkTile(Robo robo) {
		
		int entityLeftWorldX  = robo.worldX + robo.solidArea.x;
		int entityRightWorldX = robo.worldX + robo.solidArea.x + robo.solidArea.width;
		int entityTopWorldY = robo.worldY + robo.solidArea.y;
		int entityBottomWorldY = robo.worldY + robo.solidArea.y + robo.solidArea.height;
		
		int entityLeftCol = entityLeftWorldX / gp.tileSize;
		int entityRightCol = entityRightWorldX / gp.tileSize;
		int entityTopRow = entityTopWorldY / gp.tileSize;
		int entityBottomRow = entityBottomWorldY / gp.tileSize;
		
		int tileNum1, tileNum2;
		
		switch(robo.direction) {
		case "up":
			entityTopRow = (entityTopWorldY - robo.speed)/ gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				robo.collisionOn = true;
			}
				break;
		case "down":
			entityBottomRow = (entityBottomWorldY - robo.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				robo.collisionOn = true;
			}
				break;
		case "left":
			entityLeftCol = (entityLeftWorldX - robo.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				robo.collisionOn = true;
			}
				break;
		case "right":
			entityRightCol = (entityRightWorldX - robo.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				robo.collisionOn = true;
			}
				break;
		}
		
		
		
		
	}

}
