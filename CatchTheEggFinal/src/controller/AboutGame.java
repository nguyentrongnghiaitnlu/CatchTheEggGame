package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class AboutGame extends JLabel {
	private final String BACKGROUNDABOUT = "images/aboutframe.png";

	public AboutGame() {
		createBackgroundAbout();
	}

	private void createBackgroundAbout() {
		BufferedImage image;
		try {
			// create background img
			image = ImageIO.read(new File(BACKGROUNDABOUT));
			ImageIcon icon = new ImageIcon(image.getScaledInstance(800, 700, image.SCALE_SMOOTH));
			this.setIcon(icon);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
