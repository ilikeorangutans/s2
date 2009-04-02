package de.jakusys.settler.ui.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.apache.log4j.Logger;

import de.jakusys.settler.model.map.FlagImpl;
import de.jakusys.settler.model.map.Hexagon;
import de.jakusys.settler.pathfinder.PassableStrategy;
import de.jakusys.settler.ui.event.HexagonEvent;
import de.jakusys.settler.ui.event.HexagonEventListener;
import de.jakusys.settler.ui.viewport.MapPanel;

public class BuildFlagAction extends AbstractAction implements
		HexagonEventListener {

	private static final Logger log = Logger.getLogger(BuildFlagAction.class);

	private final MapPanel mapPanel;

	private boolean active = false;

	private final PassableStrategy passableStrategy = new PassableStrategy.FlagBuildingPassableStrategy();

	public BuildFlagAction(MapPanel mapPanel) {
		super("Set Flag");
		this.mapPanel = mapPanel;
		this.mapPanel.addHexagonEventListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		setEnabled(active);
		active = !active;
	}

	public void hexagonHovered(HexagonEvent hexagonEvent) {
	}

	public void hexagonSelected(HexagonEvent hexagonEvent) {
		if (active) {
			Hexagon h = hexagonEvent.getHexagon();
			if (h != null && !h.hasFlag() && passableStrategy.isPassable(h)) {
				log.debug("Setting new flag at " + h);

				h.addFlag(new FlagImpl());

				active = false;
				setEnabled(true);
				mapPanel.repaint();
			} else {
				log.debug("Cannot build flag here.");
			}
		}
	}
}
