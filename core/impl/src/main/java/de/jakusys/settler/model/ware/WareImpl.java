package de.jakusys.settler.model.ware;

import de.jakusys.settler.model.map.Hexagon;

public class WareImpl implements Ware {

	private Hexagon hexagon;

	private final String name;

	public WareImpl(String name) {
		this.name = name;
	}

	public Hexagon getLocation() {
		return hexagon;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return getName() + " at " + getLocation();
	}
}
