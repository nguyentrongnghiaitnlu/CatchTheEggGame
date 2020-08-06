package model;

import javax.swing.ImageIcon;

public class ItemAddTenSecond extends ItemsOfGame {
	public ItemAddTenSecond() {
		description = "addtensecond";
		timeAddition = new AddTenSecond();
		eggScoreIncrease = new DontIncreaseEggScore();
		imgIcon_Item = new ImageIcon("images/time.png");
	}
}
