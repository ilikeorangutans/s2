package de.jakusys.settler.model.structure;

import java.util.List;

import de.jakusys.settler.game.Simulatable;
import de.jakusys.settler.model.map.Flag;
import de.jakusys.settler.model.map.Hexagon;

public interface Structure extends Simulatable {

	Flag getFlag();

	List<Hexagon> getOccupiedHexagons();

	String getName();

}
