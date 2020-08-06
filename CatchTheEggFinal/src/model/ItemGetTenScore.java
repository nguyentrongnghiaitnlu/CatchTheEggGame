package model;

import javax.swing.ImageIcon;

public class ItemGetTenScore extends ItemsOfGame {
	public ItemGetTenScore() {
		description = "gettenscore";
		getCompetitorScore = new GetTenScore();
		imgIcon_Item = new ImageIcon("images/get10.png");
	}
}
