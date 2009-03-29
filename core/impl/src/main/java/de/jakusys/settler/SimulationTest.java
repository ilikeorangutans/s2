package de.jakusys.settler;

import de.jakusys.settler.game.Game;
import de.jakusys.settler.game.GameData;
import de.jakusys.settler.game.GameDataImpl;
import de.jakusys.settler.game.GameImpl;
import de.jakusys.settler.game.Simulation;
import de.jakusys.settler.game.SimulationImpl;
import de.jakusys.settler.model.map.FlagImpl;
import de.jakusys.settler.model.structure.BuildingFactory;
import de.jakusys.settler.model.structure.BuildingFactoryImpl;
import de.jakusys.settler.model.structure.WellBuildingType;

public class SimulationTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BuildingFactory bf = new BuildingFactoryImpl();
		Simulation s = new SimulationImpl();
		GameData gameData = new GameDataImpl();
		gameData.addSimulatable(bf.createConstructionSite(new FlagImpl(),
				new WellBuildingType()));
		s.setData(gameData);
		Game g = new GameImpl(s, gameData, 200);
		g.start();

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		g.stop();
	}
}
