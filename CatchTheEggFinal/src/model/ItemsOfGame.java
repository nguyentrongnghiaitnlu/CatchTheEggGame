package model;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public abstract class ItemsOfGame {
	protected ResumeCompetitorGameStrategy resumeCompetitorGame;
	protected GetCompetitorScoreStrategy getCompetitorScore;
	protected TimeAdditionStrategy timeAddition;
	protected EggScoreIncreaseStrategy eggScoreIncrease;
	protected boolean increaseEggScore = false;
	protected String description;
	protected int x_Item;
	protected int y_Item = 120;
	protected int speedDropItem = 2;
	protected ImageIcon imgIcon_Item;

	public int addTime(int timeOfGame) {
		return timeAddition.timeAddition(timeOfGame);
	}

	public ArrayList<Eggs> increaseEggScore(ArrayList<Eggs> listKindsOfEgg) {
		return eggScoreIncrease.eggScoreIncreas(listKindsOfEgg);
	}

	public int getScore() {
		return getCompetitorScore.getScore();
	}

	public boolean resumeGame() {
		return resumeCompetitorGame.resumeGame();
	}

	public int getX_Item() {
		return x_Item;
	}

	public void setX_Item(int x_Item) {
		this.x_Item = x_Item;
	}

	public int getY_Item() {
		return y_Item;
	}

	public void setY_Item(int y_Item) {
		this.y_Item = y_Item;
	}

	public int getSpeedDropItem() {
		return speedDropItem;
	}

	public void setSpeedDropItem(int speedDropItem) {
		this.speedDropItem = speedDropItem;
	}

	public ImageIcon getImgIcon_Item() {
		return imgIcon_Item;
	}

	public void setImgIcon_Item(ImageIcon imgIcon_Item) {
		this.imgIcon_Item = imgIcon_Item;
	}

	public void setTimeAddition(TimeAdditionStrategy timeAddition) {
		this.timeAddition = timeAddition;
	}

	public void setEggScoreIncrease(EggScoreIncreaseStrategy eggScoreIncrease) {
		this.eggScoreIncrease = eggScoreIncrease;
	}

	public boolean isIncreaseEggScore() {
		return increaseEggScore;
	}

	public void drop() {
		y_Item = y_Item + speedDropItem;
	}

	public String getDescription() {
		return description;
	}

}
