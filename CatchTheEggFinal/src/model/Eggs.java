package model;

import java.awt.Image;

import javax.swing.JLabel;

public abstract class Eggs extends JLabel {
	protected int x_Egg;
	protected int y_Egg;
	protected int score_Egg;
	protected int speedDropEgg = 1;
	protected Image img_Egg;

	public Eggs() {

	}

	public int getX_Egg() {
		return x_Egg;
	}

	public void setX_Egg(int x_Egg) {
		this.x_Egg = x_Egg;
	}

	public int getY_Egg() {
		return y_Egg;
	}

	public void setY_Egg(int y_Egg) {
		this.y_Egg = y_Egg;
	}

	public Image getImg_Egg() {
		return img_Egg;
	}

	public void setImg_Egg(Image img_Egg) {
		this.img_Egg = img_Egg;
	}

	public int getScore_Egg() {
		return score_Egg;
	}

	public void setScore_Egg(int score_Egg) {
		this.score_Egg = score_Egg;
	}

	public void increaseSpeedDropEgg() {
		if (speedDropEgg < 3)
			speedDropEgg++;
	}

	public void drop() {
		y_Egg = y_Egg + speedDropEgg;
	}

}
