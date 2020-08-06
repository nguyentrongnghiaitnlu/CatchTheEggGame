package model;

import javax.swing.ImageIcon;

public class ItemIncreaseDoubleEggScore extends ItemsOfGame {
	public ItemIncreaseDoubleEggScore() {
		description = "increasedoubleeggscore";
		eggScoreIncrease = new IncreaseDoubleEggScore();
		timeAddition = new DontAddTime();
		increaseEggScore = true;
		imgIcon_Item = new ImageIcon("images/x2.png");

	}
}
