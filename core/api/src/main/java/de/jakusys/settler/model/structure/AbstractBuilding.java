package de.jakusys.settler.model.structure;

import de.jakusys.settler.game.Simulation;
import de.jakusys.settler.model.map.Flag;

/**
 * Abstract base class for buildings.
 * 
 * @author jakob
 * 
 */
public abstract class AbstractBuilding implements Building {

	protected BuildingType buildingType;

	protected boolean disposable;

	protected final Flag flag;

	protected Simulation simulation;

	public AbstractBuilding(Flag flag, BuildingType buildingType) {
		this.flag = flag;
		this.buildingType = buildingType;
		buildingType.setBuilding(this);
	}

	public BuildingType getBuildingType() {
		return buildingType;
	}

	public Flag getFlag() {
		return flag;
	}

	public String getName() {
		return null;
	}

	public boolean isDisposable() {
		return disposable;
	}

	public void setBuildingType(BuildingType buildingType) {
		this.buildingType = buildingType;
	}

	public void setSimulation(Simulation simulation) {
		this.simulation = simulation;
	}
}
