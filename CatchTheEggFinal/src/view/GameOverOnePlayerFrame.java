package view;

import javax.swing.JFrame;

import controller.GameOverOnePlayerGame;
import controller.OnePlayer;

public class GameOverOnePlayerFrame extends JFrame {

	public GameOverOnePlayerFrame(OnePlayer onePlayer) {

		add(new GameOverOnePlayerGame(onePlayer, this));

		setTitle("Catch the eggs");
		setSize(1200, 730);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
	}

}
