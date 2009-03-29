package de.jakusys.settler.model.structure;

import java.util.List;

import de.jakusys.settler.model.map.Flag;
import de.jakusys.settler.model.map.Hexagon;

public class BuildingImpl extends AbstractBuilding {

	public BuildingImpl(Flag flag, BuildingType buildingType) {
		super(flag, buildingType);
	}

	public void step() {
		buildingType.step();
	}

	public List<Hexagon> getOccupiedHexagons() {
		return null;
	}

}
