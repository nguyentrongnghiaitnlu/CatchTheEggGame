package model;

import javax.swing.ImageIcon;

public class ItemGetTwentyScore extends ItemsOfGame {
	public ItemGetTwentyScore() {
		description = "gettwentyscore";
		getCompetitorScore = new GetTwentyScore();
		imgIcon_Item = new ImageIcon("images/get20.png");
	}
}
