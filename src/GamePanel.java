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
	Timer fps;
	int frameCount = 0;
	int startFrameCount = 0;

	ObjectManager manager;
	public static BufferedImage bgimage;
	public static BufferedImage menuimage;
	public static BufferedImage endimage;
	public static boolean needImage = true;
	public static boolean gotImage = false;

	GamePanel() {
		frameDraw = new Timer(1000 / 100, this);
		frameDraw.start();
		wonka = new WillyWonka(WonkaWorld.WIDTH / 2, 400, 378 / 3, 720 / 3);
		fps = new Timer(1000 / 60, this);
		fps.start();
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
		// for tester
	}

	void updateEndState() {
	}

	void drawMenu(Graphics g) {
		g.setColor(Color.PINK);
		g.fillRect(0, 0, WonkaWorld.WIDTH, WonkaWorld.HEIGHT);

		titleFont = new Font("Calibri", Font.PLAIN, 58);
		g.setFont(titleFont);
		g.setColor(Color.WHITE);
		g.drawString("WONKA WORLD", 220, 150);
		titleFont = new Font("Calibri", Font.PLAIN, 30);
		g.setFont(titleFont);
		g.setColor(Color.WHITE);
		g.drawString("welcome to wonka world!", 280, 200);
		titleFont = new Font("Calibri", Font.PLAIN, 25);
		g.setFont(titleFont);
		g.drawString("press ENTER to start", 320, 400);
		titleFont = new Font("Calibri", Font.PLAIN, 25);
		g.setFont(titleFont);
		g.drawString("press SPACE for instructions", 287, 600);
	}

	void drawMap(Graphics g) {
		g.setColor(Color.PINK);
		g.fillRect(0, 0, WonkaWorld.WIDTH, WonkaWorld.HEIGHT);
		g.drawImage(bgimage, 0, 0, WonkaWorld.WIDTH, WonkaWorld.HEIGHT, null);
		g.setColor(Color.GREEN);
		g.fillRect(400, 0, 100, 50);
		manager.draw(g);
	}

	void drawIntro(Graphics g) {
		g.setColor(Color.ORANGE);
		g.fillRect(0, 0, WonkaWorld.WIDTH, WonkaWorld.HEIGHT);
		titleFont = new Font("Arial", Font.PLAIN, 20);
		g.setFont(titleFont);
		g.setColor(Color.WHITE);
		g.drawString("Uh Oh! Willy Wonka spilled all of his candy!", 270, 150);
		g.drawString(" Help him clean the mess up by", 310, 180);
		g.drawString(" collecting all of the candy.", 320, 210);
		g.drawString(" To jump press the up arrow. And to move side", 260, 240);
		g.drawString("to side use the left and right arrows.", 300, 270);
		g.drawString("press space to go back to main menu", 290, 400);
	}

	void drawGameover(Graphics g) {
		g.setColor(Color.ORANGE);
		g.fillRect(0, 0, WonkaWorld.WIDTH, WonkaWorld.HEIGHT);

		titleFont = new Font("Arial", Font.PLAIN, 30);
		g.setFont(titleFont);
		g.setColor(Color.WHITE);
		g.drawString("press ENTER to play again", 280, 350);
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

		// wonka.update(frameCount);
		wonka.draw(g);
	}

	void startGame() {
		manager.addCandy(30);
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

		frameCount++;
		repaint();
	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			if (currentState == MENU) {
				currentState = INTRO;
			} else if (currentState == INTRO) {
				currentState = MENU;
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (currentState == MENU) {
				currentState = MAP;
				wonka = new WillyWonka(250, 700, 50, 70);
				manager = new ObjectManager(wonka);

				startGame();
			} else if (currentState == MAP) {
				currentState = GAMEOVER;
			} else if (currentState == INTRO) {
				currentState = MENU;
			} else if (currentState == GAMEOVER) {
				currentState = MENU;
			} else {
				currentState++;
			}
			// System.out.println(currentState);

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
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		int keyPressed = e.getKeyCode();

		if (keyPressed == KeyEvent.VK_LEFT || (keyPressed == KeyEvent.VK_RIGHT)) {
			wonka.speedx = 0;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
