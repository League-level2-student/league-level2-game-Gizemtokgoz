import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener {
	WillyWonka wonka;
	ArrayList<Candy> candy;
	ArrayList<Platform> platform;
	Random rand = new Random();
	int score = 0;
	Font titleFont;
	Stopwatch sw;
	boolean playing = true;

	int getScore() {
		return score;
	}

	ObjectManager(WillyWonka wonka) {
		this.wonka = wonka;
		candy = new ArrayList<Candy>();
		platform = new ArrayList<Platform>();

		platform.add(new Platform(0, 525, 500, 20));
		platform.add(new Platform(400, 400, 500, 20));
		platform.add(new Platform(0, 250, 500, 20));
		platform.add(new Platform(400, 100, 500, 20));

		sw = new Stopwatch();
		sw.Init(15);
	}

	void addCandy(int numCandy) {
		for (int i = 0; i < numCandy; i++) {
			candy.add(new Candy(rand.nextInt(WonkaWorld.WIDTH), rand.nextInt(WonkaWorld.HEIGHT), 50, 50));
		}
	}

	void update() {
		if (playing == false) {
			return;
		}
		
		for (int i = 0; i < candy.size(); i++) {
			candy.get(i).update();
		}

		wonka.update(platform);

		checkCollision();
		purgeObjects();

	}

	void draw(Graphics g) {
		wonka.draw(g);
		for (int i = 0; i < candy.size(); i++) {
			candy.get(i).draw(g);
		}

		for (int i = 0; i < platform.size(); i++) {
			platform.get(i).draw(g);
		}

		titleFont = new Font("Calibri", Font.PLAIN, 50);
		g.setFont(titleFont);
		g.setColor(Color.RED);
		g.drawString(String.valueOf(sw.countdown), 425, 40);

		if (candy.size() == 0) {
			titleFont = new Font("Calibri", Font.PLAIN, 58);
			g.setFont(titleFont);
			g.setColor(Color.RED);
			g.drawString("YOU WON!", 350, 300);
			sw.Stop();
			playing = false;
		}

		if (sw.countdown == 0) {
			titleFont = new Font("Calibri", Font.PLAIN, 58);
			g.setFont(titleFont);
			g.setColor(Color.RED);
			g.drawString("YOU LOST", 350, 300);
			sw.Stop();
			playing = false;

		} else {

		}
	}

	void purgeObjects() {
		for (int i = 0; i < candy.size(); i++) {
			if (!candy.get(i).isActive) {
				candy.remove(i);
			}
		}
	}

	void checkCollision() {
		for (int i = 0; i < candy.size(); i++) {
			if (wonka.collisionBox.intersects(candy.get(i).collisionBox)) {
				// wonka.isActive = false;
				candy.get(i).isActive = false;
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		// addCandy();
	}
}
