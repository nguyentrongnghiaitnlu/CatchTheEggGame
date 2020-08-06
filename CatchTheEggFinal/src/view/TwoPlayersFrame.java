package view;

import javax.swing.JFrame;

import controller.TwoPlayers;

public class TwoPlayersFrame extends JFrame {
	public TwoPlayersFrame(TwoPlayersSettingFrame game) {
		add(new TwoPlayers(game, this));

		setTitle("Catch the eggs");
		setSize(1200, 730);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
