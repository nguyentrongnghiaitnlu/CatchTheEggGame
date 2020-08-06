package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class HelpGame extends JLabel {
	private final String BACKGROUNDHELP = "images/helpframe.png";

	public HelpGame() {
		createBackgroundHelp();
	}

	private void createBackgroundHelp() {
		BufferedImage image;
		try {
			// create background img
			image = ImageIO.read(new File(BACKGROUNDHELP));
			ImageIcon icon = new ImageIcon(image.getScaledInstance(800, 700, image.SCALE_SMOOTH));
			this.setIcon(icon);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
