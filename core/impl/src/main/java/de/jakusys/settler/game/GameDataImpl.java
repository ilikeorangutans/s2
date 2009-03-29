package de.jakusys.settler.game;

import java.util.ArrayList;
import java.util.List;

public class GameDataImpl implements GameData {

	private List<Simulatable> simulatables;

	public GameDataImpl() {
		simulatables = new ArrayList<Simulatable>();
	}

	public void addSimulatable(Simulatable simulatable) {
		simulatables.add(simulatable);
	}

	public List<Simulatable> getSimulatables() {
		return simulatables;
	}

}
