package de.jakusys.settler.model.terrain;

public interface Terrain {

	String getName();

	boolean allowsRoads();

	/**
	 * Returns true if this type of terrain is passable by peasants.
	 * 
	 * @return
	 */
	boolean isPassable();

}
