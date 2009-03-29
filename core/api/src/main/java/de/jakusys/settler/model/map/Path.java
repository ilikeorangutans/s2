package de.jakusys.settler.model.map;

import java.util.Collection;
import java.util.List;


public interface Path {

	Flag getStart();

	Flag getEnd();

	List<Hexagon> getHexagons();

	Path addHexagon(Hexagon hexagon);

	Path fromHexagons(Collection<Hexagon> hexagons);
}
