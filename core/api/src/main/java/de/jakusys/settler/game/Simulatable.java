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
	 * Returns true if this instance has to be removed from the simulation. If
	 * true is returned, this object will be removed from the the running
	 * {@link Simulation}s list of {@link Simulatable}s.
	 */
	boolean isDisposable();

	void setSimulation(Simulation simulation);

	/**
	 * Performs one step in the simulation and updates the internal state of the
	 * object.
	 */
	void step();
}
