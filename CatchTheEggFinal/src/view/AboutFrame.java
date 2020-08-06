package view;

import javax.swing.JFrame;

import controller.AboutGame;

public class AboutFrame extends JFrame {

	public AboutFrame() {
		add(new AboutGame());

		setTitle("About");
		setSize(800, 700);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
