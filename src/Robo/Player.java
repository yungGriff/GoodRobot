package Robo;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;
import Main.Movement;

public class Player extends Robo{
	GamePanel gp;
	Movement mvm;
	
	public final int screenX;
	public final int screenY;
	
	public Player(GamePanel gp, Movement mvm){
		this.gp = gp;
		this.mvm = mvm;
		
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		
		solidArea = new Rectangle(0, 0, gp.tileSize, gp.tileSize);
		
		solidArea.x = 8;
		solidArea.y = 16;
		solidArea.width = 32;
		solidArea.height = 32;
		
		setDefaultValues();
		getPlayerImage();
	}
	public void setDefaultValues() {
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;
		speed = 4;
		direction = "up";
		
	}
	public void getPlayerImage() {
		try {
			
			up1 = ImageIO.read(getClass().getResource("/player/up1.png"));
			up2 = ImageIO.read(getClass().getResource("/player/up2.png"));
			down1 = ImageIO.read(getClass().getResource("/player/down1.png"));
			down2 = ImageIO.read(getClass().getResource("/player/down2.png"));
			left1 = ImageIO.read(getClass().getResource("/player/left1.png"));
			left2 = ImageIO.read(getClass().getResource("/player/left2.png"));
			right1 = ImageIO.read(getClass().getResource("/player/right1.png"));
			right2 = ImageIO.read(getClass().getResource("/player/right2.png"));
			
			
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		
	}
	public void update() {
	if(mvm.upPressed == true || mvm.downPressed == true || mvm.leftPressed == true || mvm.rightPressed == true) {
		if(mvm.upPressed == true) {
			direction = "up";
			
		}
		if(mvm.downPressed == true) {
			direction = "down";
			
		}
		if(mvm.leftPressed == true) {
			direction = "left";
			
		}
		if(mvm.rightPressed == true) {
			direction = "right";
			
		}
		
		
		//Add solids Check TILE COLLISION
		collisionOn = false;
		gp.cChecker.checkTile(this);
		
		// if collision is false player can move through | 
		if(collisionOn == false) {
			switch(direction) {
			case "up":
				worldY -= speed;
				break;
			case "down":
				worldY += speed;
				break;
			case "left":
				worldX -= speed;
				break;
			case "right":
				worldX += speed;
				break;
			}
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
	
		//g2.setColor(Color.white);
		//g2.fillRect(x, y, gp.tileSize, gp.tileSize);
		BufferedImage image = null;
		switch(direction) {
		case "up":
			if(spriteNum == 1) {
				image = up1;
			}
			if(spriteNum == 2) {
				image = up2;
			}
			break;
		case "down":
			if(spriteNum == 1) {
				image = down1;
			}
			if(spriteNum == 2) {
				image = down2;
			}
			break;
		case "left":
			if(spriteNum == 1) {
				image = left1;
			}
			if(spriteNum == 2) {
				image = left2;
			}			
			break;
		case "right":
			if(spriteNum == 1) {
			image = right1;
			}
			if(spriteNum == 2) {
				image = right2;
			}
			break;
		}
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
	}
}
