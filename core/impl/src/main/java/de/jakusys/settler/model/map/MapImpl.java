package de.jakusys.settler.model.map;

import de.jakusys.settler.model.terrain.Terrain;
import de.jakusys.settler.model.terrain.TerrainImpl;

public class MapImpl implements Map {

	private final Hexagon[][] hexagons;

	private final int width;

	private final int height;

	private final String name;

	public MapImpl(String name, int width, int height) {
		this.width = width;
		this.height = height;
		this.name = name;

		hexagons = new Hexagon[width][height];

		createHexagons();
	}

	public void addHexagon(int x, int y, Hexagon hexagon) {
		setHexagon(x, y, hexagon);

		// First check hexagons on top; in even rows (0, 2, 4, ...) we find
		// same-column above hexagon on NORTH_EAST. In odd rows we find them in
		// NORTH_WEST.
		if (y % 2 == 0) {
			// Even row.
			hexagon.addNeighbour(HexagonAdjancency.NORTH_EAST, getHexagon(x,
					y - 1));
			hexagon.addNeighbour(HexagonAdjancency.NORTH_WEST, getHexagon(
					x - 1, y - 1));
		} else {
			// Odd row.
			hexagon.addNeighbour(HexagonAdjancency.NORTH_WEST, getHexagon(x,
					y - 1));
			hexagon.addNeighbour(HexagonAdjancency.NORTH_EAST, getHexagon(
					x + 1, y - 1));
		}

		hexagon.addNeighbour(HexagonAdjancency.WEST, getHexagon(x - 1, y));
	}

	private void createHexagons() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				Terrain t = TerrainImpl.SEA;
				if ((x > 0 && x < width - 1) && (y > 0 && y < height - 1)) {
					t = TerrainImpl.GRASS;
				}
				addHexagon(x, y, new HexagonImpl(x, y, x * 10 + y * 10, t));
			}
		}
	}

	public int getHeight() {
		return height;
	}

	public Hexagon getHexagon(int x, int y) {
		Hexagon h;
		if (x < 0 || x >= width || y < 0 || y >= height) {
			h = null;
		} else {
			h = hexagons[x][y];
		}

		return h;
	}

	public String getName() {
		return name;
	}

	public int getPlayers() {
		return 0;
	}

	public int getWidth() {
		return width;
	}

	public void setHexagon(int x, int y, Hexagon hexagon) {
		hexagons[x][y] = hexagon;
	}

	@Override
	public String toString() {
		return getClass() + " " + getWidth() + "x" + getHeight();
	}

	public int getDistance(Hexagon a, Hexagon b) {
		if (a == b) {
			return 0;
		}

		int ax = a.getX();
		int ay = a.getY();
		int bx = b.getX();
		int by = b.getY();

		int dx = ax - bx;
		int dy = ay - by;

		int dist = 0;

		if (Math.signum(dx) == Math.signum(dy)) {
			dist = Math.max(Math.abs(dx), Math.abs(dy));
		} else {
			dist = Math.abs(dx) + Math.abs(dy);
		}

		return dist;
	}
}
