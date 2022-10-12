package Robo;
import java.awt.Color;
import java.awt.Graphics2D;

import Main.GamePanel;
import Main.Movement;

public class Player extends Robo{
	GamePanel gp;
	Movement mvm;
	
	public Player(GamePanel gp, Movement mvm){
		this.gp = gp;
		this.mvm = mvm;
		setDefaultValues();
	}
	public void setDefaultValues() {
		x = 100;
		y = 100;
		speed = 4;
		
	}
	public void update() {
		if(mvm.upPressed == true) {
			y -= speed;
		}
		if(mvm.downPressed == true) {
			y += speed;
		}
		if(mvm.leftPressed == true) {
			x -= speed;
		}
		if(mvm.rightPressed == true) {
			x += speed;
		}
	}
	public void draw(Graphics2D g2) {
	
		g2.setColor(Color.white);
		
		g2.fillRect(x, y, gp.tileSize, gp.tileSize);
	}
}
