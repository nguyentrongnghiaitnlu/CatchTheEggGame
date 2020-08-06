package view;

import javax.swing.JFrame;

import controller.GameOverTwoPlayerGame;
import controller.TwoPlayers;

public class GameOverTwoPlayerFrame extends JFrame {

	public GameOverTwoPlayerFrame(TwoPlayers twoPlayers) {

		add(new GameOverTwoPlayerGame(twoPlayers, this));

		setTitle("Catch the eggs");
		setSize(1200, 730);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
	}
}
