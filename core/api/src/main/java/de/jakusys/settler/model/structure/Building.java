package de.jakusys.settler.model.structure;

import de.jakusys.settler.game.Simulatable;

public interface Building extends Simulatable, Structure {

	BuildingType getBuildingType();

	void setBuildingType(BuildingType buildingType);

}
