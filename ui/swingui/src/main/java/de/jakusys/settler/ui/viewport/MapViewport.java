package de.jakusys.settler.ui.viewport;

import java.awt.Rectangle;

import de.jakusys.settler.model.map.Hexagon;
import de.jakusys.settler.model.map.Map;

/**
 * 
 * @author Jakob Külzer
 * 
 */
public interface MapViewport {

	Map getMap();

	/**
	 * Returns a rectangle describing the visible hexagons on this map.
	 * 
	 * @return
	 */
	Rectangle getVisibleArea();

	/**
	 * Returns true if the given hexagon is visible in this viewport.
	 * 
	 * @param hexagon
	 * @return
	 */
	boolean isVisible(Hexagon hexagon);

}
