package de.jakusys.settler.model.structure;

import de.jakusys.settler.model.map.Flag;

/**
 * Describes a building. A building is a container for a {@link BuildingType}.
 * By separating building and its type its easy to change the type and behaviour
 * of a building.
 * 
 * @author Jakob Külzer
 * 
 */
public interface Building extends Structure {

	BuildingType getBuildingType();

	/**
	 * Returns the flag associated with this building.
	 * 
	 * @return
	 */
	Flag getFlag();

	void setBuildingType(BuildingType buildingType);

}
