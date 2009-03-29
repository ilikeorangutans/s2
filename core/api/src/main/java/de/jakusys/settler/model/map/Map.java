package de.jakusys.settler.model.map;

/**
 * @author jakob
 * 
 */
public interface Map {

	int getHeight();

	String getName();

	int getPlayers();

	int getWidth();

	void setHexagon(int x, int y, Hexagon hexagon);

	void addHexagon(int x, int y, Hexagon hexagon);

	Hexagon getHexagon(int x, int y);

	int getDistance(Hexagon hexagon1, Hexagon hexagon2);
}
