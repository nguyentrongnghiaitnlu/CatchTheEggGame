package model;

public class BasketFactory {
	public Basket getBasket(String basket) {
		if (basket == null)
			return null;
		if (basket.equalsIgnoreCase("REDBASKET"))
			return new RedBasket();
		else if (basket.equalsIgnoreCase("BROWNBASKET"))
			return new BrownBasket();
		else if (basket.equalsIgnoreCase("BLUEBASKET"))
			return new BlueBasket();
		else if (basket.equalsIgnoreCase("GREENBASKET"))
			return new GreenBasket();

		return null;
	}
}
