package view;

import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.HelpGame;

public class HelpFrame extends JFrame {
	private JLabel helpPanel;

	public HelpFrame() {
		helpPanel = new HelpGame();
		add(helpPanel);

		setTitle("Help");
		setSize(800, 700);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
