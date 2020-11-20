import java.awt.Color;
import java.awt.Graphics;

public class WillyWonka extends GameObject {

	WillyWonka(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed = 20;
	}

	void draw(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);
	}
}
