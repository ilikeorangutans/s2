package de.jakusys.settler.model.structure;

import de.jakusys.settler.model.map.Flag;

public interface BuildingFactory {

	Building createConstructionSite(Flag flag, BuildingType buildingType);

}
