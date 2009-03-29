package de.jakusys.settler.model.structure;

import de.jakusys.settler.game.Simulation;

public abstract class AbstractBuildingType implements BuildingType {

	protected Building building;

	protected final String name;

	protected Simulation simulation;

	public AbstractBuildingType(String name) {
		this.name = name;
	}

	public Building getBuilding() {
		return building;
	}

	public String getName() {
		return name;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

	public void setSimulation(Simulation simulation) {
		this.simulation = simulation;
	}
}
