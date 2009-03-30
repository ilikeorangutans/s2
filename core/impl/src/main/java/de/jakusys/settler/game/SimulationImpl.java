package de.jakusys.settler.game;

import java.util.Iterator;

import org.apache.log4j.Logger;

import de.jakusys.settler.game.Simulation;
import de.jakusys.settler.model.map.Map;

public class SimulationImpl implements Simulation {

	private static Logger log = Logger.getLogger(SimulationImpl.class);

	private long steps;

	private Map map;

	private GameData data;

	public Map getMap() {
		return map;
	}

	public void step() {
		steps++;
		if (log.isDebugEnabled())
			log.debug("Step " + steps + " on thread " + Thread.currentThread());

		for (Iterator<Simulatable> i = data.getSimulatables().iterator(); i
				.hasNext();) {
			Simulatable s = i.next();
			if (s.isDisposable()) {
				if (log.isDebugEnabled())
					log.debug("Removing disposed simulatable: " + s);
			} else {
				s.step();
			}
		}
	}

	public void setData(GameData gameData) {
		this.data = gameData;
	}

}
