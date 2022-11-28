package Robo;

import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class terminal extends Robo{
	public terminal(GamePanel gp) {
		
		super(gp);	
		direction = "down";
		speed = 1;
		terminalImage();
	}
	public void terminalImage() {
		try {
			down1 = ImageIO.read(getClass().getResource("/objects/terminal.png"));
			up1 = ImageIO.read(getClass().getResource("/objects/terminalBlink.png"));
			left1 = ImageIO.read(getClass().getResource("/objects/terminal.png"));
			right1 = ImageIO.read(getClass().getResource("/objects/terminal.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void setAction() {
		
		animationLock ++;
		if(animationLock == 120) {
		
		Random random = new Random();
		int i = random.nextInt(100)+1;
		if(i <= 25) {
			direction = "down";
		}
		if(i > 25 && i <= 50){
			direction = "up";
		}
		if(i > 50 && i <= 75 ) {
			direction = "right";
		}
		if(i > 75 && i <= 100) {
			direction = "left";
		}
	}
		animationLock = 0;
	}

	}

