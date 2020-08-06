package model;

public class AddFiveSecond implements TimeAdditionStrategy {

	@Override
	public int timeAddition(int timeOfGame) {
		return timeOfGame + 5;

	}

}
