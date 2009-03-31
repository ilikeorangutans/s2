/**
 * 
 */
package de.jakusys.settler.model.structure;

import java.util.ArrayList;
import java.util.List;

import de.jakusys.settler.model.map.Hexagon;

/**
 * Abstract base class for {@link Structure}.
 * 
 * @author Jakob Külzer
 * 
 */
public abstract class AbstractStructure implements Structure {

	protected Hexagon baseHexagon;

	protected String name;

	protected List<Hexagon> occupiedHexagons;

	public AbstractStructure() {
		occupiedHexagons = new ArrayList<Hexagon>();
	}

	public Hexagon getBaseHexagon() {
		return baseHexagon;
	}

	public String getName() {
		return name;
	}

	public List<Hexagon> getOccupiedHexagons() {
		return occupiedHexagons;
	}

	public void setBaseHexagon(Hexagon hexagon) {
		if (hexagon == null) {
			throw new NullPointerException("Cannot set base hexagon to null!");
		}
		baseHexagon = hexagon;
		updateOccupiedHexagons();
	}

	/**
	 * Simple default implementation. Will set the occupied hexagons to the base
	 * hexagon.
	 */
	public void updateOccupiedHexagons() {
		occupiedHexagons.clear();
		occupiedHexagons.add(baseHexagon);
	}
}
