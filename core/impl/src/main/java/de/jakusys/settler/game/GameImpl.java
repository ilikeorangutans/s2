/**
 * 
 */
package de.jakusys.settler.game;

import org.apache.log4j.Logger;

/**
 * @author jakob
 * 
 */
public class GameImpl implements Game, Runnable {

	private static Logger log = Logger.getLogger(GameImpl.class);

	private Simulation simulation;

	private int stepTime;

	private boolean running;

	private boolean paused;

	private Thread thread;

	private GameData gameData;

	public GameImpl(Simulation simulation, GameData gameData, int stepTime) {
		this.simulation = simulation;
		this.stepTime = stepTime;
		this.gameData = gameData;

		thread = new Thread(this, "simulation");
		log.info("Game initialized, step time " + stepTime
				+ " ms, using thread " + thread);
	}

	public Simulation getSimulation() {
		return simulation;
	}

	public void pause() {
	}

	public void setStepTime(int millis) {
	}

	public void start() {
		log.debug("Starting game...");
		running = true;
		thread.start();
	}

	private void step() {
		simulation.step();
	}

	public boolean isRunning() {
		return running;
	}

	public boolean isPaused() {
		return paused;
	}

	public void stop() {
		log.debug("Stopping simulation thread...");
		running = false;
	}

	public void run() {
		log.debug("Simulation thread started.");
		while (running) {
			step();

			try {
				Thread.sleep(stepTime);
			} catch (InterruptedException e) {
				log.error(e, e);
			}
		}
		log.debug("Simulation thread stopped.");
	}

	public GameData getData() {
		return gameData;
	}
}
