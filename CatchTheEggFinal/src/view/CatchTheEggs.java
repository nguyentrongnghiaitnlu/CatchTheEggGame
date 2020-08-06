package view;

import javax.swing.JFrame;

public class CatchTheEggs extends JFrame {
	private MenuGame menuGame;

	public CatchTheEggs() {
		menuGame = new MenuGame(this);
		add(menuGame);

		setTitle("Catch the eggs");
		setSize(1200, 730);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static void main(String[] args) {
		new CatchTheEggs();
	}
}
