package de.jakusys.settler.pathfinder;

import de.jakusys.settler.model.map.Hexagon;

public interface PassableStrategy {

	/**
	 * Simple strategy, basically just returns true for everything.
	 * 
	 * @author jakob
	 * 
	 */
	public final class DefaultPassableStrategy implements PassableStrategy {
		public boolean isPassable(Hexagon hexagon) {
			return true;
		}
	}

	/**
	 * Strategy aware of terrain.
	 * 
	 * @author jakob
	 * 
	 */
	public class TerrainAwarePassableStrategy implements PassableStrategy {

		public boolean isPassable(Hexagon hexagon) {
			if (hexagon.getTerrain() == null) {
				// This shouldn't happen.
				return false;
			} else {
				return hexagon.getTerrain().isPassable();
			}
		}

	}

	public class RoadBuildingPassableStrategy extends
			TerrainAwarePassableStrategy {

		@Override
		public boolean isPassable(Hexagon h) {
			if (super.isPassable(h)) {
				return h.getTerrain().allowsRoads();
			} else {
				return false;
			}
		}
	}

	boolean isPassable(Hexagon hexagon);

}
