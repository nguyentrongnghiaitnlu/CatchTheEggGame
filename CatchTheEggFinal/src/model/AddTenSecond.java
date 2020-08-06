package model;

public class AddTenSecond implements TimeAdditionStrategy {

	@Override
	public int timeAddition(int timeOfGame) {
		return timeOfGame + 10;

	}

}
