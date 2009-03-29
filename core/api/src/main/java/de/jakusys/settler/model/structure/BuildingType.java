package de.jakusys.settler.model.structure;

import java.util.List;

import de.jakusys.settler.game.Simulatable;
import de.jakusys.settler.model.ware.Ware;

public interface BuildingType extends Simulatable {

	Building getBuilding();

	String getName();

	/**
	 * Returns a list of {@link Ware}s that are required to build this building
	 * type.
	 * 
	 * @return
	 */
	List<Ware> getBuildMaterials();

	void setBuilding(Building building);
}
