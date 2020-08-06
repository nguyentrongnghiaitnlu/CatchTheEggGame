package model;

import javax.swing.ImageIcon;

public class ItemAddFiveSecond extends ItemsOfGame {
	public ItemAddFiveSecond() {
		description = "addfivesecond";
		timeAddition = new AddFiveSecond();
		eggScoreIncrease = new DontIncreaseEggScore();
		imgIcon_Item = new ImageIcon("images/time.png");
	}
}
