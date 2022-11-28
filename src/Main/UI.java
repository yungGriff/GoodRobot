package Main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import object.OBJ_Disk;

public class UI {
	GamePanel gp;
	Graphics2D g2;
	Font tnRoman_40;
	Font tnRoman_45;
//	BufferedImage diskImage;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	
	public UI(GamePanel gp) {
		this.gp = gp;
		
		tnRoman_40 = new Font("Times New Roman", Font.PLAIN, 25);
		tnRoman_45 = new Font("Times New Roman", Font.PLAIN, 45);
//		OBJ_Disk disk = new OBJ_Disk();
//		diskImage = disk.image;
	}
	public void showMessage(String text) {
		
		message = text;
		messageOn = true;
	}
	
	public void draw(Graphics2D g2) {
		this.g2 = g2;
		
		g2.setFont(tnRoman_40);
		g2.setColor(Color.white);
//		g2.drawImage(diskImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
//		g2.drawString("x "+ gp.player.hasDisk, 75, 57);
		
		// pause unpause
		if(gp.gameState == gp.playState) {
			
		}
		if(gp.gameState == gp.pauseState) {
			drawPauseScreen();
		}
		
		// at Terminal
		if(gp.gameState == gp.atTerminal) {
			drawTerminalScreen();
		}
		
		if(messageOn == true) {
			
			g2.setFont(g2.getFont().deriveFont(30F));
			g2.drawString(message,gp.tileSize * 6, gp.tileSize * 8);
			
			messageCounter++;
			if(messageCounter > 180) {
				messageCounter = 0;
				messageOn = false;
			}
			
		}
	}
	public void drawPauseScreen() {
		String text = "PAUSED";
		
		int x = getXforCenteredText(text);
		int y = gp.screenHeight/2;
		
		g2.drawString(text, x, y);
	}
	public void drawTerminalScreen() {
		// how big is the monitor
		int x = gp.tileSize;
		int y = gp.tileSize;
		int width = gp.screenWidth - (gp.tileSize*2);
		int height = gp.screenHeight - (gp.tileSize*2);
		
		drawTerminalWindow(x, y, width, height);
	}
	public void drawTerminalWindow(int x,int y, int width, int height) {
		
		Color color = new Color(0,0,0);
		g2.setColor(color);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		
		
		color = new Color(255, 246, 181);
		g2.setColor(color);
		g2.setStroke(new BasicStroke(8));
		g2.drawRoundRect(x+5, y+5, width-5, height-10, 25, 25);
	
	
	
	
	}
	public int getXforCenteredText(String text) {
		int length = (int)g2.getFontMetrics().getStringBounds(text,  g2).getWidth();
		int x = gp.screenWidth/2 - length/2;
		return x;
	}

}
