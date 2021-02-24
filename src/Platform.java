import java.awt.Color;
import java.awt.Graphics;

public class Platform extends GameObject{
	String plat1;
	String plat2;
	String plat3;
	String plat4;

	Platform(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(x, y, width, height);
	}
}
