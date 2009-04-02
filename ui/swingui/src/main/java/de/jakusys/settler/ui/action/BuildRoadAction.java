package de.jakusys.settler.ui.action;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;

import org.apache.log4j.Logger;

import de.jakusys.settler.model.map.Flag;
import de.jakusys.settler.model.map.Hexagon;
import de.jakusys.settler.model.map.Path;
import de.jakusys.settler.model.map.PathImpl;
import de.jakusys.settler.pathfinder.DijkstraPathfindingStrategy;
import de.jakusys.settler.pathfinder.PassableStrategy;
import de.jakusys.settler.pathfinder.PathfindingStrategy;
import de.jakusys.settler.ui.event.HexagonEvent;
import de.jakusys.settler.ui.event.HexagonEventListener;
import de.jakusys.settler.ui.viewport.MapPanel;

public class BuildRoadAction extends AbstractAction implements
		HexagonEventListener {

	private static final Logger log = Logger.getLogger(BuildRoadAction.class);

	private boolean active = false;

	private Flag end;

	private final MapPanel mapPanel;

	private Flag start;

	private final PathfindingStrategy pathfindingStrategy = new DijkstraPathfindingStrategy();

	public BuildRoadAction(MapPanel mapPanel) {
		super("Build Road");
		this.mapPanel = mapPanel;
		this.mapPanel.addHexagonEventListener(this);
		pathfindingStrategy
				.setPassableStrategy(new PassableStrategy.RoadBuildingPassableStrategy());
	}

	public void actionPerformed(ActionEvent e) {
		setEnabled(active);
		active = !active;
		start = null;
		end = null;
	}

	public void hexagonHovered(HexagonEvent hexagonEvent) {
	}

	public void hexagonSelected(HexagonEvent hexagonEvent) {
		if (active) {
			if (hexagonEvent.getHexagon().hasFlag()) {
				if (start == null) {
					start = hexagonEvent.getHexagon().getFlag();
					log.debug("Starting road construction at " + start);
				} else {
					end = hexagonEvent.getHexagon().getFlag();
					log.debug("Road goes to " + end);

					List<Hexagon> path = pathfindingStrategy.findPath(mapPanel
							.getMap(), start.getHexagon(), end.getHexagon());

					Path p = new PathImpl();
					p.fromHexagons(path);

					start.addPath(p);
					end.addPath(p);

					active = false;
					start = null;
					end = null;
					
					setEnabled(true);
					
					mapPanel.repaint();
				}
			}
		}
	}

}
