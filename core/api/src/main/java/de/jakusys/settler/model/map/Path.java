package de.jakusys.settler.model.map;

import java.util.Collection;
import java.util.List;

public interface Path {

	Path addHexagon(Hexagon hexagon);

	Path fromHexagons(Collection<Hexagon> hexagons);

	Hexagon getEnd();

	List<Hexagon> getHexagons();

	Hexagon getStart();
}
