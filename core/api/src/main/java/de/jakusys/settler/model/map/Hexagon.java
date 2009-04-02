package de.jakusys.settler.model.map;

import java.util.Map;

import de.jakusys.settler.model.terrain.Terrain;

public interface Hexagon {

	/**
	 * Adds a flag to this hexagon.
	 * 
	 * @param flag
	 */
	void addFlag(Flag flag);

	void addNeighbour(HexagonAdjancency adjacency, Hexagon hexagon);

	/**
	 * Returns the flag on this hexagon.
	 * 
	 * @return
	 */
	Flag getFlag();

	int getHeight();

	Hexagon getNeighbour(HexagonAdjancency direction);

	Map<HexagonAdjancency, Hexagon> getNeighbours();

	Road getRoad();

	Terrain getTerrain();

	int getX();

	int getY();

	/**
	 * Retuns true if this hexagon has a flag.
	 * 
	 * @return
	 */
	boolean hasFlag();

	void setRoad(Road road);
}
