package de.jakusys.settler.pathfinder;

import java.util.Iterator;

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

	public class FlagBuildingPassableStrategy extends
			RoadBuildingPassableStrategy {

		@Override
		public boolean isPassable(Hexagon hexagon) {
			if (super.isPassable(hexagon)) {
				// Visit all neighbours and make sure they don't have a flag:
				for (Iterator<Hexagon> it = hexagon.getNeighbours().values()
						.iterator(); it.hasNext();) {
					if (it.next().hasFlag()) {
						return false;
					}
				}
				return true;
			} else {
				return false;
			}
		}
	}

	boolean isPassable(Hexagon hexagon);

}
