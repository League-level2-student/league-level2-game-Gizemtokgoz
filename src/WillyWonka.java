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
	public static final int JUMP_VELOCITY = 30;
	int x;
	int y;
	int yold;
	int width;
	int height;
	int gravityIncrement = 1;
	int startGravity = 3;
	int gravity = startGravity;
	int jumpDecayVelocity = 0;
	int currentFrame = 0;
	int startJumpFrame = 0;
	boolean jumping = false;
	BufferedImage wonkaStandImage;
	BufferedImage wonkaJump1Image;
	BufferedImage wonkaJump2Image;

	WillyWonka(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		try {
			wonkaStandImage = ImageIO.read(this.getClass().getResourceAsStream("wonka_stand.png"));
			wonkaJump1Image = ImageIO.read(this.getClass().getResourceAsStream("wonka_jump1.png"));
			wonkaJump2Image = ImageIO.read(this.getClass().getResourceAsStream("wonka_jump2.png"));
		} catch (Exception e) {
			System.out.println("ERROR: Unable to load images!");
		}
	}

	public void jump() {
		if (!jumping) {
			speedy = WillyWonka.JUMP_VELOCITY;
			gravity = startGravity;
			startJumpFrame = this.currentFrame;
			jumping = true;
		}
	}

	void draw(Graphics g) {
		if (jumping) {
			if (speedy > JUMP_VELOCITY / 2) {
				g.drawImage(wonkaJump1Image, x, y, width, height, null);
			} else {
				g.drawImage(wonkaJump2Image, x, y, width, height, null);
			}
		} else {
			g.drawImage(wonkaStandImage, x, y, width, height, null);
		}
	}

	public void right() {
		speedx = 6;
	}

	public void left() {
		speedx = -6;
	}

	public void up() {
		jump();
	}

	public void down() {
		//speedy = 6;
	}

	public void gravity() {

	}

	void update(ArrayList<Platform> platform) {
		this.currentFrame = currentFrame;
		x += speedx;
		if (x > WonkaWorld.WIDTH - width) {
			x = WonkaWorld.WIDTH - width;
		}
		if (x < 0) {
			x = 0;
		}

		if (jumping) {
			gravity += gravityIncrement;

			if ((currentFrame - startJumpFrame) % 10 == 0) {
				speedy -= jumpDecayVelocity;
				// System.out.println("speedy adjusted=" + speedy);
			}
		}

		yold = y;
		y += gravity - speedy;

		// System.out.println("y=" + y + "yold=" + yold);

		// For falling off platform
		if (!jumping) {
			gravity = startGravity;
			speedy = 0;
		}

		// Going up
		if (y < yold) {
			for (int i = 0; i < platform.size(); i++) {
				for (int j = yold; j >= y; j = j - 1) {
					// System.out.println(j);
					this.collisionBox.setBounds(x, j, width, height);
					if (this.collisionBox.intersects(platform.get(i).collisionBox)) {
						// Hits head at platform, falling back
						// System.out.println("Hit head, j=" + j);
						y = platform.get(i).y + platform.get(i).height;
						gravity = startGravity;
						speedy = 0;
						break;
					}
				}
			}
		}
		// Going down
		else {
			for (int i = 0; i < platform.size(); i++) {
				for (int j = yold; j <= y; j = j + 1) {
					this.collisionBox.setBounds(x, j, width, height);
					if (this.collisionBox.intersects(platform.get(i).collisionBox)) {
						// Landed on platform. Stop jump
						// System.out.println("Landed on platform, j=" + j);
						y = platform.get(i).y - height;
						jumping = false;
						// gravity = 0;
						speedy = 0;
						break;
					}
				}
			}
		}

		// Stop at top
		if (y <= 0) {
			y = 0;
			gravity = startGravity;
			speedy = 0;
		}

		// Stop at bottom
		if (y > WonkaWorld.HEIGHT - height) {
			y = WonkaWorld.HEIGHT - height;
			jumping = false;
			//gravity = 0;
			speedy = 0;
		}

		// super.update();
		// System.out.println("Wonka");
		this.collisionBox.setBounds(x, y, width, height);

	}
}
