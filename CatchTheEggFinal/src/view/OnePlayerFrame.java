package view;

import javax.swing.JFrame;

import controller.OnePlayer;

public class OnePlayerFrame extends JFrame {

	public OnePlayerFrame() {
		OnePlayer onePlayer = new OnePlayer(this);
		add(onePlayer);

		setTitle("Catch the eggs");
		setSize(1200, 730);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
	}
}
