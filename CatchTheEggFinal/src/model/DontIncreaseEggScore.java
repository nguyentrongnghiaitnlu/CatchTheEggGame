package model;

import java.util.ArrayList;

public class DontIncreaseEggScore implements EggScoreIncreaseStrategy {

	@Override
	public ArrayList<Eggs> eggScoreIncreas(ArrayList<Eggs> listKindsOfEgg) {
		return listKindsOfEgg;
	}

}
