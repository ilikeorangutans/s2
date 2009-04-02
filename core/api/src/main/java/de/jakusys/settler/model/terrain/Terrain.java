package de.jakusys.settler.model.terrain;

/**
 * Describes terrain.
 * 
 * @author Jakob Külzer
 * 
 */
public interface Terrain {

	boolean allowsRoads();

	String getName();

	/**
	 * Returns true if this type of terrain is passable by peasants.
	 * 
	 * @return
	 */
	boolean isPassable();

}
