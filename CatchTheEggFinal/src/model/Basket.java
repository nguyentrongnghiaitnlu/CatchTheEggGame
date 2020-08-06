package model;

import java.awt.Image;


public abstract class Basket {
	protected int x_Basket;
	protected final int Y_BASKET = 540;
	protected Image img_Basket;

	public Basket(int x_Basket) {
		this.x_Basket = x_Basket;
	}

	public Basket() {

	}

	public void moveLeft() {
		setX_BasketWhenOutScreen();
		this.x_Basket = x_Basket - 40;
	}

	public void moveRight() {
		setX_BasketWhenOutScreen();
		this.x_Basket = x_Basket + 40;
	}

	// xem lai
	public void setX_BasketWhenOutScreen() {
		if (x_Basket <= 38)
			x_Basket = 38;
		if (x_Basket >= 1048)
			x_Basket = 1048;
	}

	public int getX_Basket() {
		return x_Basket;
	}

	public void setX_Basket(int x_Basket) {
		this.x_Basket = x_Basket;
	}

	public int getY_Basket() {
		return Y_BASKET;
	}

	public Image getImg_Basket() {
		return img_Basket;
	}

}
