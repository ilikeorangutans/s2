package de.jakusys.settler.model.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import de.jakusys.settler.model.terrain.Terrain;
import de.jakusys.settler.model.terrain.TerrainImpl;

public class HexagonImpl implements Hexagon {

	private int height;

	private final Map<HexagonAdjancency, Hexagon> neighbours = new HashMap<HexagonAdjancency, Hexagon>(
			10);

	private Flag flag;

	private final int x;

	private final int y;

	private final Terrain terrain;

	public HexagonImpl(int x, int y, int height) {
		this(x, y, height, TerrainImpl.SEA);
	}

	public HexagonImpl(int x, int y, int height, Terrain terrain) {
		this.height = height;
		this.x = x;
		this.y = y;
		this.terrain = terrain;
	}

	public void addNeighbour(HexagonAdjancency adjacency, Hexagon hexagon) {
		if (hexagon == null) {
			return;
		}
		neighbours.put(adjacency, hexagon);
		if (hexagon.getNeighbour(adjacency.getOpposite()) == null) {
			hexagon.addNeighbour(adjacency.getOpposite(), this);
		}
	}

	public int getHeight() {
		return height;
	}

	public Hexagon getNeighbour(HexagonAdjancency direction) {
		return neighbours.get(direction);
	}

	public Map<HexagonAdjancency, Hexagon> getNeighbours() {
		return neighbours;
	}

	@Override
	public String toString() {
		String m = getClass().getName() + " at " + x + "/" + y
				+ " with height " + height + " and " + neighbours.size()
				+ " neighbours at: ";

		for (Iterator<HexagonAdjancency> it = neighbours.keySet().iterator(); it
				.hasNext();) {
			HexagonAdjancency ha = it.next();
			m += ha.toString();
			m += ", ";
		}

		return m;
	}

	public void addFlag(Flag flag) {
		this.flag = flag;
		flag.setHexagon(this);
	}

	public Flag getFlag() {
		return flag;
	}

	public boolean hasFlag() {
		return flag != null;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Terrain getTerrain() {
		return terrain;
	}
}
