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
			entityBottomRow = (entityBottomWorldY + robo.speed)/gp.tileSize;
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
			entityRightCol = (entityRightWorldX + robo.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				robo.collisionOn = true;
			}
				break;
		}
		
	}
	public int checkObject(Robo robo, boolean player) {
		
		int index = 999;
		
		for(int i = 0; i <gp.obj.length; i++) {
			
			if(gp.obj[i] != null) {
				
				robo.solidArea.x = robo.worldX + robo.solidArea.x;
				robo.solidArea.y = robo.worldY + robo.solidArea.y;
				// objects position
				gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
				gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;
				
				switch(robo.direction) {
				case "up":
					robo.solidArea.y -= robo.speed;
					if(robo.solidArea.intersects(gp.obj[i].solidArea)) {
						if(gp.obj[i].collision == true) {
							robo.collisionOn = true;
						}
						if(player == true) {
							index = i;
						}
					}
					break;
				case "down":
					robo.solidArea.y += robo.speed;
					if(robo.solidArea.intersects(gp.obj[i].solidArea)) {
						if(gp.obj[i].collision == true) {
							robo.collisionOn = true;
						}
						if(player == true) {
							index = i;
						}
					}
					break;
				case "left":
					robo.solidArea.x -= robo.speed;
					if(robo.solidArea.intersects(gp.obj[i].solidArea)) {
						if(gp.obj[i].collision == true) {
							robo.collisionOn = true;
						}
						if(player == true) {
							index = i;
						}
					}
					break;
				case "right":
					robo.solidArea.x += robo.speed;
					if(robo.solidArea.intersects(gp.obj[i].solidArea)) {
						if(gp.obj[i].collision == true) {
							robo.collisionOn = true;
						}
						if(player == true) {
							index = i;
						}
					}
					break;
				}
			
			robo.solidArea.x = robo.solidAreaDefaultX;
			robo.solidArea.y = robo.solidAreaDefaultY;
			gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
			gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
		}
		
	}
		return index;
}
// check terminal collision
		public int checkTerminal(Robo robo, Robo[] target) {
			int index = 999;
			
			for(int i = 0; i <target.length; i++) {
				
				if(target[i] != null) {
					
					robo.solidArea.x = robo.worldX + robo.solidArea.x;
					robo.solidArea.y = robo.worldY + robo.solidArea.y;
					// objects position
					target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
					target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;
					
					switch(robo.direction) {
					case "up":
						robo.solidArea.y -= robo.speed;
						if(robo.solidArea.intersects(target[i].solidArea)) {
								robo.collisionOn = true;
								index = i;	
						}
						break;
					case "down":
						robo.solidArea.y += robo.speed;
						if(robo.solidArea.intersects(target[i].solidArea)) {
								robo.collisionOn = true;
								index = i;
							
						}
						break;
					case "left":
						robo.solidArea.x -= robo.speed;
						if(robo.solidArea.intersects(target[i].solidArea)) {
								robo.collisionOn = true;
								index = i;
							
						}
						break;
					case "right":
						robo.solidArea.x += robo.speed;
						if(robo.solidArea.intersects(target[i].solidArea)) {
								robo.collisionOn = true;
								index = i;
							
						
						break;
					}
				
					}
				robo.solidArea.x = robo.solidAreaDefaultX;
				robo.solidArea.y = robo.solidAreaDefaultY;
				target[i].solidArea.x = target[i].solidAreaDefaultX;
				target[i].solidArea.y = target[i].solidAreaDefaultY;
			}
			
		}
			
			return index;
	}
}


