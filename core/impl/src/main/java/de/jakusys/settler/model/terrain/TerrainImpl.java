package de.jakusys.settler.model.terrain;

public class TerrainImpl implements Terrain {

	public static final Terrain SEA = new TerrainImpl("Sea", false, false);

	public static final Terrain GRASS = new TerrainImpl("Grass");

	private final boolean passable;

	private final String name;

	private final boolean roads;

	public TerrainImpl(String name) {
		this(name, true, true);
	}

	public TerrainImpl(String name, boolean passable, boolean roads) {
		this.passable = passable;
		this.name = name;
		this.roads = roads;
	}

	public boolean allowsRoads() {
		return roads;
	}

	public String getName() {
		return name;
	}

	public boolean isPassable() {
		return passable;
	}

}
