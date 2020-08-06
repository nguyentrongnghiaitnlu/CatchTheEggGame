package model;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class GoldenEgg extends Eggs {

	public GoldenEgg() {
		img_Egg = new ImageIcon("images/goldenegg.png").getImage();
		score_Egg = 20;
	}
}
