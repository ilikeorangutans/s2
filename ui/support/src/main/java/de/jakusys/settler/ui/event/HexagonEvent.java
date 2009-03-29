package de.jakusys.settler.ui.event;

import java.util.EventObject;

import de.jakusys.settler.model.map.Hexagon;

public class HexagonEvent extends EventObject {

	public static enum TYPE {
		CLICK, HOVER
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final Hexagon hexagon;

	private final TYPE type;

	public HexagonEvent(Object source, Hexagon hexagon, TYPE type) {
		super(source);
		this.hexagon = hexagon;
		this.type = type;
	}

	public Hexagon getHexagon() {
		return hexagon;
	}

	public TYPE getType() {
		return type;
	}
}
