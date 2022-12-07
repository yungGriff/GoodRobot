package Main;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class Movement implements KeyListener{
	
	
	GamePanel gp;
	public boolean upPressed, downPressed, leftPressed, rightPressed;
	Scanner scanner = new Scanner(System.in);
	boolean checkDrawTime = false;
	
	public Movement(GamePanel gp) {
	this.gp = gp;
	} 
	

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();
		// on launch
		
		//0 is start game
		// 1 is exit game
		if(gp.gameState == gp.titleScreen) {
			if(code == KeyEvent.VK_W) {
				gp.ui.commandNum--;
				if(gp.ui.commandNum < 0) {
					gp.ui.commandNum = 1;
				}
			}
			if(code == KeyEvent.VK_S) {
				gp.ui.commandNum++;
				if(gp.ui.commandNum > 1) {
					gp.ui.commandNum = 0;
				}
			}
			if(code == KeyEvent.VK_ENTER) {
				if(gp.ui.commandNum == 0) {
					gp.gameState = gp.playState;
				}
				if(gp.ui.commandNum == 1) {
					System.exit(0);
				}
			}
		}
		
		
		// play the game
		
		if(gp.gameState == gp.playState ) {
		if(code == KeyEvent.VK_W) {
			upPressed = true;
		}
		if(code == KeyEvent.VK_S) {
			downPressed = true;
		}
		if(code == KeyEvent.VK_A) {
			leftPressed = true;
		}
		if(code == KeyEvent.VK_D) {
			rightPressed = true;
		}
		if(code == KeyEvent.VK_B) {			
				gp.gameState = gp.pauseState;
		}
			
		if(code == KeyEvent.VK_T){
			if(checkDrawTime == false) {
				checkDrawTime = true;
			}else if(checkDrawTime == true) {
				checkDrawTime = false;
			}
		}
		}
		
		//if paused
		else if(gp.gameState == gp.pauseState) {
		if(code == KeyEvent.VK_B) {
			gp.gameState = gp.playState;
		}
		}
		
	
	//if at terminal
	//else
		if(gp.gameState == gp.atTerminal || gp.gameState == gp.numJumpState || gp.gameState == gp.LabInvestState || gp.gameState == gp.runDoomState) {
			gp.ui.listCommands = 0;
			if(code == KeyEvent.VK_H) {
				gp.ui.listCommands = 1;
				}
			if(code == KeyEvent.VK_S) {
			gp.gameState = gp.playState;
			//scanner.close();
			}
		
			if(code == KeyEvent.VK_0) {
				gp.gameState = gp.playState;
			}
		}

			 //This code will allow you to launch the different games from the terminal
		
			if(code == KeyEvent.VK_1) {
				gp.gameState = gp.numJumpState;
				System.out.println("numJump BEEP BOOOP");
					
				
				}
			
			if(code == KeyEvent.VK_2) {
				gp.gameState = gp.LabInvestState;
				System.out.println("Lab Investments");
			}
			if(code == KeyEvent.VK_3) {
				gp.gameState = gp.runDoomState;
						//gp.gameState = gp.atTerminal;
					
			/*	try {
					Runtime.getRuntime().exec("E:\\Program Files (x86)\\Notepad++", null, new File("E:\\Program Files (x86)\\Notepad++"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
			}
			*/
				System.out.println("DOOOOOM!");
			}
		}
		
	//}
	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		
			
		if(code == KeyEvent.VK_W) {
			upPressed = false;
		}
		if(code == KeyEvent.VK_S) {
			downPressed = false;
		}
		if(code == KeyEvent.VK_A) {
			leftPressed = false;
}
		if(code == KeyEvent.VK_D) {
			rightPressed = false;
}
	}
}
