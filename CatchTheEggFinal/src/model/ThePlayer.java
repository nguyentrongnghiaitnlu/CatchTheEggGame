package model;

import java.io.Serializable;

public class ThePlayer implements Serializable {
	private String name;
	private int score;

	public ThePlayer() {

	}

	public ThePlayer(String name, int score) {
		this.name = name;
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String toString() {
		return this.name + "\t" + this.score;
	}

}
