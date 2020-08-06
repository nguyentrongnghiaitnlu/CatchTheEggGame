package model;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class IncreaseDoubleEggScore implements EggScoreIncreaseStrategy {
	@Override
	public ArrayList<Eggs> eggScoreIncreas(ArrayList<Eggs> listKindsOfEgg) {
		for (int i = 0; i < listKindsOfEgg.size(); i++) {
			listKindsOfEgg.get(i).setScore_Egg((listKindsOfEgg.get(i).getScore_Egg()) * 2);
		}
		return listKindsOfEgg;
	}

}
