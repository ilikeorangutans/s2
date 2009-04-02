package de.jakusys.settler.model.map;

import java.util.List;

/**
 * Describes a path between two flags.
 * 
 * @author Jakob Külzer
 * 
 */
public interface Road {

	Flag getEnd();

	/**
	 * Returns a list of {@link Hexagon}s this path runs through.
	 * 
	 * @return
	 */
	List<Hexagon> getHexagons();

	/**
	 * Returns the flag at the other end of the given flag.
	 * 
	 * @param comingFrom
	 * @return
	 */
	Flag getOtherEnd(Flag comingFrom);

	Flag getStart();

}
