package de.jakusys.settler.game;

import java.util.List;

public interface GameData {

	List<Simulatable> getSimulatables();

	void addSimulatable(Simulatable simulatable);
	
}
