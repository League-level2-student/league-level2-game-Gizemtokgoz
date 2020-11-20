import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener {
	WillyWonka wonka;
	ArrayList<Candy> candy;
	Random rand = new Random();
	int score = 0;
	
	int getScore() {
		return score;
	}
	
	ObjectManager(WillyWonka wonka) {
		this.wonka = wonka;
		candy = new ArrayList<Candy>();
	}

	void addCandy() {
		candy.add(new Candy(rand.nextInt(LeagueInvaders.WIDTH), 0, 50, 50));
	}

	void update() {
		for (int i = 0; i < candy.size(); i++) {
			candy.get(i).update();
		}
		wonka.update();
		
		checkCollision();
		purgeObjects();

		if (wonka.isActive) {
			purgeObjects();
		}
		
		else {
			
		}
	}

	void draw(Graphics g) {
		wonka.draw(g);
		for (int i = 0; i < candy.size(); i++) {
			candy.get(i).draw(g);
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
				wonka.isActive = false;
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		addCandy();
	}
}

