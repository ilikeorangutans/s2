package de.jakusys.settler.model.structure;

import java.util.List;

import de.jakusys.settler.model.ware.Ware;

public class WellBuildingType extends AbstractBuildingType {

	private static final String NAME = "Well";

	public WellBuildingType() {
		super(NAME);
	}

	public void step() {
	}

	public List<Ware> getBuildMaterials() {
		return null;
	}
}
