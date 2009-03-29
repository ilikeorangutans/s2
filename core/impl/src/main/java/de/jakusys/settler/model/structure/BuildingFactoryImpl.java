package de.jakusys.settler.model.structure;

import de.jakusys.settler.model.map.Flag;

public class BuildingFactoryImpl implements BuildingFactory {

	public Building createConstructionSite(Flag flag, BuildingType buildingType) {
		Building b = new BuildingImpl(flag, new ConstructionSiteBuildingType(
				buildingType));
		return b;
	}
}
