package Main;

import java.awt.*;
import java.util.*;

import javax.swing.JPanel;

import Robo.Player;
import Robo.Robo;
import Robo.terminal;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
	
	// setting the screen size
	final int originalTileSize = 16; // 16X16 Title
	final int scale = 3;
	
	public final int tileSize = originalTileSize * scale;
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenHeight = tileSize * maxScreenRow;
	
	//make world move
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;
	
	// stop start
	public int gameState;
	public final int titleScreen = 0;
	public final int playState = 1;
	public final int pauseState = 2; 
	public final int atTerminal = 3;
	public final int listCommands = 4;
	public final int numJumpState = 5;
	public final int LabInvestState = 6;
	public final int runDoomState = 7;
	
	
	//FPS
	int fps = 60;
	
	TileManager tileM = new TileManager(this);
	Movement mvm = new Movement(this);
	Thread gameThread;
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetPlacer aPlacer = new AssetPlacer(this);
	public UI ui = new UI(this);
	public Player player = new Player(this, mvm);
	public SuperObject obj[] = new SuperObject[10];
	public Robo terminal[] = new Robo[10];
	
	
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
	
	public void setupGame() {
		
		aPlacer.setTerminal();
		aPlacer.setObject();
		gameState = titleScreen;
	}
	
	
	public void startGameThread() {
		//instantiate a thread calls run method
		gameThread = new Thread(this);
		gameThread.start();
	}
	@Override
	public void run(){
		
		
		double drawInterval = 1000000000/fps;
		double nextDrawTime = System.nanoTime() + drawInterval;
		//long timer = 0;
		//int drawCount = 0;
		
		// while game thread exists run the game loop
		while(gameThread != null) {
			//System.out.println("Game Loop is running.");
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
		
		if(gameState == playState) {
			//player
			player.update();
			//terminal
			for(int i = 0;  i < terminal.length; i++) {
				if(terminal[i] != null) {
					terminal[i].update();
				}
			}
		}
		if(gameState == pauseState) {
			//the game doesnt allow you to move the sprite
		}
		
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		long drawStart = 0;
		if(mvm.checkDrawTime == true) {
		drawStart = System.nanoTime();
		}
		
		//user log on
		if(gameState == titleScreen) {
			ui.draw(g2);
		}
		else {
		
		//needs to be before the player
		tileM.draw(g2);
		
		// objects
		for(int i = 0; i < obj.length; i++) {
			if(obj[i] != null) {
				obj[i].draw(g2, this);
			}
		}
		//terminals
		for(int i = 0; i < terminal.length; i++) {
			if(terminal[i] != null) {
				terminal[i].draw(g2);
			}
		}
		
		player.draw(g2);
		
		//ui 
		ui.draw(g2);
		if(mvm.checkDrawTime == true) {
		long drawEnd = System.nanoTime();
		long passed = drawEnd - drawStart;
		g2.setColor(getBackground());
		g2.drawString("Draw Time: " + passed, 10, 400);
		System.out.println("Draw Time: " + passed);
		}
		
		g2.dispose();
		
		
	}
	}
}