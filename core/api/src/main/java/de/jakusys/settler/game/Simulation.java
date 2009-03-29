package de.jakusys.settler.game;

import de.jakusys.settler.model.map.Map;

public interface Simulation {

	void setData(GameData gameData);

	Map getMap();

	/**
	 * Performs a single step in the simulation.
	 */
	void step();
}
