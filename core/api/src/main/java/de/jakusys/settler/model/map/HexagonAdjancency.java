package de.jakusys.settler.model.map;

public enum HexagonAdjancency {

	WEST, NORTH_EAST, SOUTH_EAST, EAST, SOUTH_WEST, NORTH_WEST;

	public HexagonAdjancency getOpposite() {
		switch (this) {
		case WEST:
			return EAST;
		case NORTH_EAST:
			return SOUTH_WEST;
		case SOUTH_EAST:
			return NORTH_WEST;
		case EAST:
			return WEST;
		case SOUTH_WEST:
			return NORTH_EAST;
		case NORTH_WEST:
			return SOUTH_EAST;
		default:
			throw new IllegalStateException("This should not happen.");
		}
	}

}
