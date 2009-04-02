package de.jakusys.settler.model.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import de.jakusys.settler.model.terrain.Terrain;
import de.jakusys.settler.model.terrain.TerrainImpl;

public class HexagonImpl implements Hexagon {

	private Flag flag;

	private int height;

	private final Map<HexagonAdjancency, Hexagon> neighbours = new HashMap<HexagonAdjancency, Hexagon>(
			10);

	private Road road;

	private final Terrain terrain;

	private final int x;

	private final int y;

	public HexagonImpl(int x, int y, int height) {
		this(x, y, height, TerrainImpl.SEA);
	}

	public HexagonImpl(int x, int y, int height, Terrain terrain) {
		this.height = height;
		this.x = x;
		this.y = y;
		this.terrain = terrain;
	}

	public void addFlag(Flag flag) {
		this.flag = flag;
		flag.setHexagon(this);
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

	public Flag getFlag() {
		return flag;
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

	public Road getRoad() {
		return road;
	}

	public Terrain getTerrain() {
		return terrain;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean hasFlag() {
		return flag != null;
	}

	public void setRoad(Road road) {
		this.road = road;
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
}
