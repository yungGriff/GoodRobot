package Main;

import java.awt.*;
import java.util.*;

import javax.swing.JPanel;

import Robo.Player;

public class GamePanel extends JPanel implements Runnable{
	final int originalTileSize = 16; // 16X16 Title
	final int scale = 3;
	
	public final int tileSize = originalTileSize * scale;
	final int maxScreenCol = 16;
	final int maxScreenRow = 12;
	final int screenWidth = tileSize * maxScreenCol;
	final int screenHeight = tileSize * maxScreenRow;
	
	//FPS
	int fps = 60;
	
	Movement mvm = new Movement();
	Thread gameThread;
	Player player = new Player(this, mvm);
	
	//where is the players
	int playersX = 100;
	int playersY = 100;
	int playerSpeed = 4;
	
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(mvm);
		this.setFocusable(true);
		
	}
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	@Override
	public void run(){
		
		
		double drawInterval = 1000000000/fps;
		double nextDrawTime = System.nanoTime() + drawInterval;
		//long timer = 0;
		//int drawCount = 0;
		
		while(gameThread != null) {
			System.out.println("Game Loop is running.");
			update();  
			
			//drawCount++;
			
			
			repaint();
			
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime/1000000;
				if(remainingTime < 0) {
					remainingTime = 0;
				}
				
				Thread.sleep((long) remainingTime);
				
				nextDrawTime += drawInterval;
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void update() {
		
		player.update();
		
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		player.draw(g2);
		
		g2.dispose();
		
		
	}
}