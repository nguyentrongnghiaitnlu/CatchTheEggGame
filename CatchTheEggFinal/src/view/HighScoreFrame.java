package view;

import javax.swing.JFrame;

import controller.HighScoreGame;

public class HighScoreFrame extends JFrame {

	public HighScoreFrame() {
		add(new HighScoreGame());

		setTitle("HighScore");
		setSize(800, 700);
		setResizable(true);
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
