package Robo;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Main.GamePanel;

public class Robo {
	
	GamePanel gp;
	public int worldX, worldY;
	public int speed;
	
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	public String direction;
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collisionOn = false;
	public int animationLock = 0;
	String termMessage[] = new String[20];
	
	
	public Robo(GamePanel gp) {
		this.gp = gp;
	}
	public void setAction() {}
	public void AIfeed() {
		
	}
	public void update() {
		
		setAction();
		collisionOn = false;
		
		gp.cChecker.checkTile(this);
	// to force player image to change
		if(collisionOn == false) {
			switch(direction) {
			case "up":
				
				break;
			case "down":
				
				break;
			case "left":
				//worldX -= speed;
				break;
			case "right":
				//worldX += speed;
				break;
			}
		}
	
		// to force player image to change
		spriteCounter++;
		if(spriteCounter > 10) {
			if(spriteNum == 1) {
				spriteNum = 2;
			}
			else if(spriteNum == 2) {
				spriteNum = 1;
		}
		spriteCounter = 0;
		}
		
	}
	
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		
		 int screenX = worldX - gp.player.worldX + gp.player.screenX;
		 int screenY = worldY - gp.player.worldY + gp.player.screenY;
		 
		 if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
			worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && 
			worldY + gp.tileSize> gp.player.worldY - gp.player.screenY && 
			worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
			 
				switch(direction) {
				case "down":
					if(spriteNum > 1) {
						image = down1;
					}
					
				case "up":
					if(spriteNum == 1) {
						image = up1;
					}
					break;
				}
			 g2.drawImage(image,screenX,screenY, gp.tileSize, gp.tileSize, null);
			 
		
		
		 }
	}
}


