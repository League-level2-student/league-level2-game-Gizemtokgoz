import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements KeyListener, ActionListener {
	final int MENU = 0;
	final int INTRO = 3;
	final int MAP = 1;
	final int GAMEOVER = 2;
	int currentState = MENU;
	Font titleFont;
	Timer candySpawn;
	Timer frameDraw;
	WillyWonka wonka = new WillyWonka(250, 700, 50, 50);
	ObjectManager manager = new ObjectManager(wonka);
	public static BufferedImage bgimage;
	public static BufferedImage menuimage;
	public static BufferedImage endimage;
	public static boolean needImage = true;
	public static boolean gotImage = false;

	GamePanel() {
		frameDraw = new Timer(1000 / 100, this);
		frameDraw.start();
		titleFont = new Font("Arial", Font.PLAIN, 48);
		this.setPreferredSize(new Dimension(WonkaWorld.WIDTH, WonkaWorld.HEIGHT));

		try {
			bgimage = ImageIO.read(this.getClass().getResourceAsStream("clouds.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void updateMenuState() {
	}

	void updateGameState() {
		wonka.update();
		manager.update();
		if (wonka.isActive == false) {
			currentState = GAMEOVER;
		}
	}

	void updateIntroState() {
		//for tester
	}

	void updateEndState() {
	}

	void drawMenu(Graphics g) {
		g.setColor(Color.PINK);
		g.fillRect(0, 0, WonkaWorld.WIDTH, WonkaWorld.HEIGHT);

		titleFont = new Font("Arial", Font.PLAIN, 58);
		g.setFont(titleFont);
		g.setColor(Color.WHITE);
		g.drawString("WONKA WORLD", 200, 150);
		titleFont = new Font("Arial", Font.PLAIN, 30);
		g.setFont(titleFont);
		g.setColor(Color.WHITE);
		g.drawString("welcome to wonka world!", 260, 200);
		titleFont = new Font("Arial", Font.PLAIN, 25);
		g.setFont(titleFont);
		g.drawString("press ENTER to start", 300, 400);
		titleFont = new Font("Arial", Font.PLAIN, 25);
		g.setFont(titleFont);
		g.drawString("press SPACE for instructions", 267, 600);
	}

	void drawMap(Graphics g) {
		g.setColor(Color.PINK);
		g.fillRect(0, 0, WonkaWorld.WIDTH, WonkaWorld.HEIGHT);
		g.drawImage(bgimage, 0, 0, WonkaWorld.WIDTH, WonkaWorld.HEIGHT, null);
		manager.draw(g);
	}

	void drawIntro(Graphics g) {
		g.setColor(Color.ORANGE);
		g.fillRect(0, 0, WonkaWorld.WIDTH, WonkaWorld.HEIGHT);
		manager.draw(g);
	}

	void drawGameover(Graphics g) {
		g.setColor(Color.ORANGE);
		g.fillRect(0, 0, WonkaWorld.WIDTH, WonkaWorld.HEIGHT);

		titleFont = new Font("Arial", Font.PLAIN, 30);
		g.setFont(titleFont);
		g.setColor(Color.WHITE);
		g.drawString("oh no you lost but you can press ENTER to play again", 70, 350);
	}

	public void paintComponent(Graphics g) {
		if (currentState == MENU) {
			drawMenu(g);
		} else if (currentState == MAP) {
			drawMap(g);
		} else if (currentState == INTRO) {
			drawIntro(g);
		} else if (currentState == GAMEOVER) {
			drawGameover(g);
		}
	}

	void startGame() {
		manager.addCandy(10);
	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (currentState == MENU) {
			updateMenuState();
		} else if (currentState == MAP) {
			updateGameState();
		} else if (currentState == INTRO) {
			updateIntroState();
		} else if (currentState == GAMEOVER) {
			updateEndState();
		}

		repaint();
	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (currentState == GAMEOVER) {
				currentState = MENU;
				wonka = new WillyWonka(250, 700, 50, 50);
				manager = new ObjectManager(wonka);
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			if (currentState == MENU) {
				currentState = INTRO;
				wonka = new WillyWonka(250, 700, 50, 50);
				manager = new ObjectManager(wonka);
			}

			else if (currentState == MENU) {
				currentState = MAP;
				startGame();
			} else if (currentState == MAP) {
				currentState = GAMEOVER;
			} else {
				currentState++;
			}
			System.out.println(currentState);
		} else if (e.getKeyCode() == KeyEvent.VK_UP)

		{
			wonka.up();
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			wonka.down();
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			wonka.right();
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			wonka.left();
		}

		repaint();

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		wonka.speedx = 0;
		wonka.speedy = 0;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
