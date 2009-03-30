package de.jakusys.settler.game;

public interface Game {

	void setStepTime(int millis);

	void start();

	void pause();

	boolean isRunning();

	boolean isPaused();

	void stop();

	GameData getData();

	Simulation getSimulation();

}
