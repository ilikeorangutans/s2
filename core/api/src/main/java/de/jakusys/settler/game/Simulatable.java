package de.jakusys.settler.game;

/**
 * Interface that must be implemented by everything in the game that is part of
 * the simulation.
 * 
 * @author Jakob Külzer
 * 
 */
public interface Simulatable {

	/**
	 * Performs one step in the simulation and updates the internal state of the
	 * object.
	 */
	void step();

	void setSimulation(Simulation simulation);

	/**
	 * Returns true if this instance has to be removed from the simulation.
	 */
	void isDisposable();
}
