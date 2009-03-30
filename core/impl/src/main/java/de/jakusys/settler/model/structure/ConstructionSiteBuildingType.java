package de.jakusys.settler.model.structure;

import java.util.List;

import org.apache.log4j.Logger;

import de.jakusys.settler.model.ware.Ware;

/**
 * @author Jakob Külzer
 * 
 */
public class ConstructionSiteBuildingType extends AbstractBuildingType {

	private static final Logger log = Logger
			.getLogger(ConstructionSiteBuildingType.class);

	private static final String NAME = "Construction site: ";

	private float progress;

	private BuildingType target;

	public ConstructionSiteBuildingType(BuildingType target) {
		super(NAME + target.getName());
		this.target = target;
	}

	private void buildingComplete() {
		log.debug("Building " + target.getName() + " complete!");
		building.setBuildingType(target);
	}

	public List<Ware> getBuildMaterials() {
		return null;
	}

	public void step() {
		progress += 10;
		if (progress >= 100) {
			buildingComplete();
		}
		log.debug(getName() + " at " + progress + "%.");
	}

	@Override
	public String toString() {
		return getName();
	}

}
