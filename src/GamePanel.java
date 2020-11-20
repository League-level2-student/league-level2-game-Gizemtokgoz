import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.Timer;

public class GamePanel {
	final int MENU = 0;
	final int MAP = 1;
	final int GAMEOVER = 2;
	int currentState = MENU;
	Timer candySpawn;
	Timer frameDraw;
	WillyWonka wonka = new WillyWonka(250, 700, 50, 50);
	ObjectManager manager = new ObjectManager(wonka);

	GamePanel() {
		
	}
	
	void updateMenuState() {
	}

	void updateGameState() {
		
	}

	void updateEndState() {
	}

	void drawMenu(Graphics g) {
		
	}

	void drawMap(Graphics g) {
		
	}

	void drawGameover(Graphics g) {
		
	}
	
	public void paintComponent(Graphics g) {
		if (currentState == MENU) {
			drawMenu(g);
		} else if (currentState == MAP) {
			drawMap(g);
		} else if (currentState == GAMEOVER) {
			drawGameover(g);
		}
	}
	
	void startGame() {
		candySpawn = new Timer(1000, manager);
		candySpawn.start();
	}
	
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (currentState == MENU) {
			updateMenuState();
		} else if (currentState == MAP) {
			updateGameState();
		} else if (currentState == GAMEOVER) {
			updateEndState();
		}
	}
	
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (currentState == GAMEOVER) {
				currentState = MENU;
				wonka = new WillyWonka(250, 700, 50, 50);
				manager = new ObjectManager(wonka);
			}

			else if (currentState == MENU) {
				currentState = MAP;
				startGame();
			} 
			else if (currentState == MAP) {
				currentState = GAMEOVER;
			}
			else {
				currentState++;
			}
			System.out.println(currentState);
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			wonka.up();
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			wonka.down();
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			wonka.right();
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			wonka.left();
		}
	}
}
