package de.jakusys.settler.model.map;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import de.jakusys.settler.model.structure.Structure;
import de.jakusys.settler.model.ware.Ware;

/**
 * @author jakob
 * 
 */
public class FlagImpl implements Flag {

	private final Path[] paths = new Path[6];

	private Hexagon hexagon;

	private LinkedList<Ware> wares = new LinkedList<Ware>();

	public Path connect(Flag otherFlag) {
		return null;
	}

	public Hexagon getHexagon() {
		return hexagon;
	}

	public List<Path> getPaths() {
		return Arrays.asList(paths);
	}

	public Structure getStructure() {
		return null;
	}

	public void setHexagon(Hexagon hexagon) {
		this.hexagon = hexagon;
	}

	public void addPath(Path path) {
		int freeSlot = 0;
		for (int i = 0; i < paths.length; i++) {
			if (paths[i] != null && paths[i] == path) {
				return;
			} else if (paths[i] == null) {
				freeSlot = i;
			}
		}

		paths[freeSlot] = path;
	}

	@Override
	public String toString() {
		return getClass() + " at " + hexagon.getX() + "/" + hexagon.getY();
	}

	public void addWare(Ware ware) {
		wares.add(ware);
	}

	public List<Ware> getWares() {
		return wares;
	}

	public boolean isFull() {
		return wares.size() >= MAX_NUMBER_OF_GOODS;
	}
}
