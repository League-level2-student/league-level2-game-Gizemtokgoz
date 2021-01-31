import java.awt.Dimension;

import javax.swing.JFrame;

public class WonkaWorld {
	static final int WIDTH = 900;
	static final int HEIGHT = 800;
	JFrame frame;
	GamePanel panel;

	public static void main(String[] args) {
		WonkaWorld world = new WonkaWorld();
		world.setup();

	}

	WonkaWorld() {
		frame = new JFrame();
		panel = new GamePanel();
	}

	void setup() {
		frame.add(panel);
		frame.addKeyListener(panel);
		frame.setVisible(true);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
