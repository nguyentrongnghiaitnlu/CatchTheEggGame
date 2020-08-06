package model;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Chicken {
	private int x_Chicken;
	private final int Y_CHICKEN = 0;
	private Image img_Chicken;
	private Image imgBeforeHaveEgg;
	private Image imgAfterHaveEgg;

	public Chicken(int x_Chicken) {
		this.x_Chicken = x_Chicken;
		imgBeforeHaveEgg = new ImageIcon("images/chickenbefore.png").getImage();
		imgAfterHaveEgg = new ImageIcon("images/chickenafter.png").getImage();
		img_Chicken = imgBeforeHaveEgg;

	}

	public void beforeHaveEgg() {
		img_Chicken = imgBeforeHaveEgg;
	}

	public void afterHaveEgg() {
		img_Chicken = imgAfterHaveEgg;
	}

	public int getX_Chicken() {
		return x_Chicken;
	}

	public void setX_Chicken(int x_Chicken) {
		this.x_Chicken = x_Chicken;
	}

	public int getY_Chicken() {
		return Y_CHICKEN;
	}

	public Image getImg_Chicken() {
		return img_Chicken;
	}

}
