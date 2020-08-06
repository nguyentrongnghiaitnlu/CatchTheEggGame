package model;

public class DontAddTime implements TimeAdditionStrategy {

	@Override
	public int timeAddition(int timeOfGame) {
		return timeOfGame;
	}

}
