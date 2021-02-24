import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Candy extends GameObject {
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;

	Candy(int x, int y, int width, int height) {
		super(x, y, width, height);
		if (needImage) {
			loadImage("candy.png");
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

	void update() {
		super.update();
	}

	void draw(Graphics g) {
		if (gotImage) {
			//g.setColor(Color.ORANGE);
			//g.fillRect(x, y, width, height);
			g.drawImage(image, x, y, width, height, null);
		} else {
			//g.setColor(Color.ORANGE);
			//g.fillRect(x, y, width, height);
		}
	}
}
