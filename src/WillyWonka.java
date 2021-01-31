import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class WillyWonka extends GameObject {
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	boolean inair = false;
	int gravity = 2;
	int startingGravity = 2;
	int wonkaYVelocity;

	WillyWonka(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed = 20;
		if (needImage) {
			loadImage("Willy-Wonka.png");
		}
	}

	void loadImage(String imageFile) {
		if (needImage) {
			try {
				image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
				gotImage = true;
			} catch (Exception e) {

			}
			needImage = false;
		}
	}

	void draw(Graphics g) {
		if (gotImage) {
			g.setColor(Color.green);
			g.fillRect(x, y, width, height);
			g.drawImage(image, x, y, width, height, null);
		} else {
			g.setColor(Color.green);
			g.fillRect(x, y, width, height);
		}
	}

	public void right() {
		speedx = 6;
	}

	public void left() {
		speedx = -6;
	}

	public void up() {
		if (!inair) {
			speedy = -6;
		}
	}

	public void down() {
		speedy = 6;
	}

	public void gravity() {

	}

	void update(ArrayList<Platform> platform) {
		inair = true;
		
		if (y + height + gravity < 800) {
			y += gravity;

			/*
			 * for (int i = 0; i < platform.size(); i++) { if
			 * (this.collisionBox.intersects(platform.get(i).collisionBox)) { y -= gravity;
			 * break; } }
			 */
		}

		x += speedx;
		y += speedy;

		for (int i = 0; i < platform.size(); i++) {
			if (this.collisionBox.intersects(platform.get(i).collisionBox)) {
				if (platform.get(i).y > this.y) {
					y -= gravity;
				} else if (platform.get(i).y < this.y) {
					y -= speedy;
				}
			}
			System.out.println(y + height);
			System.out.println(platform.get(i).y);
			if (y+height == platform.get(i).y+2) {
				inair = false;
			}
		}
		if (y+height == 798) {
			inair = false;
		}

		
		super.update();
	}
}
