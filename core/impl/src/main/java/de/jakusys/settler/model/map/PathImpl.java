package de.jakusys.settler.model.map;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class PathImpl implements Path {

	private Flag end;

	private Flag start;

	private List<Hexagon> hexagons = new LinkedList<Hexagon>();

	public Flag getEnd() {
		return end;
	}

	public List<Hexagon> getHexagons() {
		return hexagons;
	}

	public Flag getStart() {
		return start;
	}

	public Path addHexagon(Hexagon hexagon) {
		if (!hexagons.contains(hexagon)) {
			hexagons.add(hexagon);

			if (hexagon.hasFlag()) {
				hexagon.getFlag().addPath(this);
			}
		}

		return this;
	}

	public Path fromHexagons(Collection<Hexagon> hexagons) {
		this.hexagons.clear();

		for (Iterator<Hexagon> i = hexagons.iterator(); i.hasNext();) {
			addHexagon(i.next());
		}
		return this;
	}

	@Override
	public String toString() {
		return getClass() + " from " + start + " over XXX to " + end;
	}
}
