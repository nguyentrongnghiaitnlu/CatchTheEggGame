package model;

import javax.swing.ImageIcon;

public class ItemResumeCompetitorGame extends ItemsOfGame {
	public ItemResumeCompetitorGame() {
		description = "resumecompetiorgame";
		resumeCompetitorGame = new ResumeGame();
		imgIcon_Item = new ImageIcon("images/ice.png");
	}
}
