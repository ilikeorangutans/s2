package de.jakusys.settler.model.structure;

import java.util.List;

import de.jakusys.settler.game.Simulatable;
import de.jakusys.settler.model.map.Hexagon;

/**
 * Describes a structure that can be placed on a map.
 * 
 * @author Jakob Külzer
 * 
 */
public interface Structure extends Simulatable {

	Hexagon getBaseHexagon();

	String getName();

	/**
	 * Returns a list of {@link Hexagon}s occupied by this structure.
	 * 
	 * @return
	 */
	List<Hexagon> getOccupiedHexagons();

	/**
	 * Sets the structures "base" hexagon. This is used to calculate the
	 * remaining occupied hexagons returned by {@link #getOccupiedHexagons()}.
	 * 
	 * @param hexagon
	 */
	void setBaseHexagon(Hexagon hexagon);

	/**
	 * Updates the list of occupied {@link Hexagon}s.
	 */
	public void updateOccupiedHexagons();
}
