package de.jakusys.settler.model.map;

import java.util.List;

import de.jakusys.settler.model.structure.Structure;
import de.jakusys.settler.model.ware.Ware;

public interface Flag {

	static final int MAX_NUMBER_OF_GOODS = 9;

	void addPath(Path path);

	/**
	 * Connects this flag with the given flag with a path.
	 * 
	 * @param otherFlag
	 * @return
	 */
	Path connect(Flag otherFlag);

	Hexagon getHexagon();

	/**
	 * Returns all paths that are connected to that flag.
	 * 
	 * @return
	 */
	List<Path> getPaths();

	Structure getStructure();

	void setHexagon(Hexagon hexagon);

	List<Ware> getWares();

	/**
	 * Adds the given ware to the flag.
	 * 
	 * @param ware
	 */
	void addWare(Ware ware);

	/**
	 * Returns true if this flag cannot take any more wares.
	 * 
	 * @return
	 */
	boolean isFull();
}
