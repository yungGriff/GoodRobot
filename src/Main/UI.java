package Main;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

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
	public int commandNum = 0;
	public int listCommands = 0;  // listCommands prompts to Help listCommands 1 lists the commands
	
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
		
		
		// title Screen
		if(gp.gameState == gp.titleScreen) {
			drawTitleScreen();
		}
		
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
		if(gp.gameState == gp.numJumpState){
		  drawnumJumpScreen();
		  }
		if(gp.gameState == gp.LabInvestState){
		  drawLabInvestState();
		  }
		if(gp.gameState == gp.runDoomState){
		  runDoom();
		 }
		
		
		if(messageOn == true) {
			
			g2.setFont(g2.getFont().deriveFont(30F));
			g2.drawString(message,gp.tileSize * 6, gp.tileSize * 8);
			
			messageCounter++;
			if(messageCounter > 60) {
				messageCounter = 0;
				messageOn = false;
			}
			
		}
	}
	public void runDoom() {
		BufferedImage doom = null;
		try {
			doom = ImageIO.read(getClass().getResource("/tiles/doom1.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Color color = new Color(255, 246, 181);
		g2.setColor(color);
		g2.fillRect(0,0, gp.screenWidth, gp.screenHeight);
		g2.drawImage(doom, 10, -5, null);
	}
	public void drawLabInvestState() {
		BufferedImage LabInvestOptions = null;
		try {
			LabInvestOptions = ImageIO.read(getClass().getResource("/tiles/labInvestGeneric.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Color color = new Color(255, 246, 181);
		g2.setColor(color);
		g2.fillRect(0,0, gp.screenWidth, gp.screenHeight);
		g2.drawImage(LabInvestOptions, 14, 15, null);
		
	}
	public void drawnumJumpScreen() {
		int x = gp.tileSize;
		int y = gp.tileSize;
		int width = gp.screenWidth - (gp.tileSize*2);
		int height = gp.screenHeight - (gp.tileSize*2);
		Color color = new Color(255, 255, 255);
		drawTerminalWindow(x, y, width, height);
		
		
	}
	
	public void drawTitleScreen() {
		// background color for title screen
		g2.setColor(new Color(0, 204, 204));
		g2.fillRect(0,0, gp.screenWidth, gp.screenHeight);
		
		
		// Good Robot !
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,72F));
		String text = "Good Robot![NNYF]";
		int x = getXforCenteredText(text);
		int y = gp.tileSize * 3;
		
		// 3d effect
		g2.setColor(Color.gray);
		g2.drawString(text, x+2, y+2);
		//write text
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		
		// the robot !
		x = gp.screenWidth/2 - (gp.tileSize*2)/2;
		y += gp.tileSize*2;
		g2.drawImage(gp.player.down1, x, y, gp.tileSize*2, gp.tileSize*2, null);
		
		// Start a game
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,32F));
		text = "START GAME";
		x = getXforCenteredText(text);
		y += gp.tileSize *4;
		g2.drawString(text, x, y);
		if(commandNum == 0) {
			g2.drawString(">", x-gp.tileSize, y);
		}
		
		// EXIT GAME
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,32F));
		text = "EXIT GAME";
		x = getXforCenteredText(text);
		y += gp.tileSize;
		g2.drawString(text, x, y);
		if(commandNum == 1) {
			g2.drawString(">", x-gp.tileSize, y);
		}
		
		
		
	}
	public void drawPauseScreen() {
		// The user hits B to pause and unpaused the game. Screen will read PAUSED
		String text = "PAUSED";
		
		int x = getXforCenteredText(text);
		int y = gp.screenHeight/2;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,32F));
		g2.drawString(text, x, y);
	}
	public void drawTerminalScreen() {
		// how big is the monitor
		if(listCommands == 0) {
		int x = gp.tileSize;
		int y = gp.tileSize;
		int width = gp.screenWidth - (gp.tileSize*2);
		int height = gp.screenHeight - (gp.tileSize*2);
		Color color = new Color(255, 255, 255);
		String helpText = "";
		
		drawTerminalWindow(x, y, width, height);
		
		x += gp.tileSize;
		y += gp.tileSize;
		
		
		g2.setColor(color);
		g2.drawString(message, x, y);
		
		helpText = "!H for HELP COMMAND-NAME";
		g2.drawString(helpText, x, y + gp.tileSize);
		}
		else if(listCommands == 1) {
			
			String commands = "";
			int x = gp.tileSize;
			int y = gp.tileSize;
			int width = gp.screenWidth - (gp.tileSize*2);
			int height = gp.screenHeight - (gp.tileSize*2);
			
			
		drawTerminalWindow(x, y, width, height);
		
		Color color = new Color(255, 255, 255);
		g2.setColor(color);
		
		commands = "C:\\Users\\robot> ";
		g2.drawString(commands, x + gp.tileSize, y + gp.tileSize * 4);
		
		commands = "	1. -run numJump protocol";
		g2.drawString(commands, x + gp.tileSize, y + gp.tileSize * 6);
		
		 commands = "	2. -run labInvest protocol";
		g2.drawString(commands, x + gp.tileSize, y + gp.tileSize * 7);
		
		commands = "	3. -run Doom.exe";
		g2.drawString(commands, x + gp.tileSize, y + gp.tileSize * 8);
		
		commands = "	0. EXIT";
		g2.drawString(commands, x + gp.tileSize, y + gp.tileSize * 9);
		
		}
		
	}
	public void drawTerminalWindow(int x,int y, int width, int height) {
		
		//terminal screenface
		Color color = new Color(0,0,0);
		g2.setColor(color);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		
		// Terminal border
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
