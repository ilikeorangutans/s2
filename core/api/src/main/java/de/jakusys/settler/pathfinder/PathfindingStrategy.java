package de.jakusys.settler.pathfinder;

import java.util.List;

import de.jakusys.settler.model.map.Hexagon;
import de.jakusys.settler.model.map.Map;

public interface PathfindingStrategy {

	List<Hexagon> findPath(Map map, Hexagon start, Hexagon dest);

	PathfindingStrategy setPassableStrategy(PassableStrategy passableStrategy);

}
