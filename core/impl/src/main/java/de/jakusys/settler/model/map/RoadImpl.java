package de.jakusys.settler.model.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Simple implementation of {@link Road}.
 * 
 * @author Jakob Külzer
 * 
 */
public class RoadImpl implements Road {

	private final Flag end;

	private final List<Hexagon> hexagons = new ArrayList<Hexagon>(4);

	private final Flag start;

	public RoadImpl(Flag start, Flag end, List<Hexagon> hexagons) {
		this.start = start;
		this.end = end;
		this.hexagons.addAll(hexagons);
		updateHexagons();
	}

	/**
	 * Creates a road from a given list of {@link Hexagon}s. The hexagons should
	 * be ordered start -> end.
	 * 
	 * @param hexagons
	 */
	public RoadImpl(List<Hexagon> hexagons) {
		start = hexagons.get(0).getFlag();
		end = hexagons.get(hexagons.size() - 1).getFlag();
		updateHexagons();
	}

	public Flag getEnd() {
		return end;
	}

	public List<Hexagon> getHexagons() {
		return Collections.unmodifiableList(hexagons);
	}

	public Flag getOtherEnd(Flag comingFrom) {
		if (comingFrom == start) {
			return end;
		} else if (comingFrom == end) {
			return start;
		} else {
			// This should not happen!
			throw new IllegalArgumentException("Given flag " + comingFrom
					+ " is not connected to this road.");
		}
	}

	public Flag getStart() {
		return start;
	}

	private void updateHexagons() {
		for (Iterator<Hexagon> it = hexagons.iterator(); it.hasNext();) {
			Hexagon h = it.next();
			if (!(h == getStart().getHexagon() || h == getEnd().getHexagon())) {
				assert h.getRoad() != null : "Trying to set a road where should not be a road. Pathfinding correct?";
				h.setRoad(this);
			}
		}
	}
}
