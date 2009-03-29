package de.jakusys.settler.ui;


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import de.jakusys.settler.model.map.Flag;
import de.jakusys.settler.model.map.FlagImpl;
import de.jakusys.settler.model.map.Hexagon;
import de.jakusys.settler.model.map.Map;
import de.jakusys.settler.model.map.MapImpl;
import de.jakusys.settler.model.map.Path;
import de.jakusys.settler.model.map.PathImpl;
import de.jakusys.settler.pathfinder.DijkstraPathfindingStrategy;
import de.jakusys.settler.pathfinder.PassableStrategy;
import de.jakusys.settler.pathfinder.PathfindingStrategy;
import de.jakusys.settler.ui.event.HexagonEvent;
import de.jakusys.settler.ui.event.HexagonEventListener;
import de.jakusys.settler.ui.renderer.TerrainRenderer;
import de.jakusys.settler.ui.viewport.MapPanel;

public class Main extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Main m = new Main();
		m.init();
	}

	private JLabel selectedCoordinatesLabel;

	private JLabel hoverHexagon;

	private JToggleButton pathfindingButton;

	private Map map;

	private MapPanel mapPanel;

	private JLabel zoomLabel = new JLabel(Integer
			.toString(TerrainRenderer.BASE_HEXAGON_WIDTH));

	private void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		map = new MapImpl("test", 10, 10);

		PathfindingStrategy pf = new DijkstraPathfindingStrategy();
		pf
				.setPassableStrategy(new PassableStrategy.RoadBuildingPassableStrategy());

		Flag f1 = new FlagImpl();
		map.getHexagon(1, 1).addFlag(f1);
		Flag f2 = new FlagImpl();
		map.getHexagon(8, 8).addFlag(f2);
		Flag f3 = new FlagImpl();
		map.getHexagon(7, 2).addFlag(f3);
		Flag f4 = new FlagImpl();
		map.getHexagon(2, 5).addFlag(f4);

		Path p = new PathImpl();
		p.fromHexagons(pf.findPath(map, f1.getHexagon(), f2.getHexagon()));
		new PathImpl().fromHexagons(pf.findPath(map, f1.getHexagon(), f3
				.getHexagon()));
		new PathImpl().fromHexagons(pf.findPath(map, f4.getHexagon(), f2
				.getHexagon()));
		new PathImpl().fromHexagons(pf.findPath(map, f3.getHexagon(), f2
				.getHexagon()));

		pf.findPath(map, map.getHexagon(1, 1), map.getHexagon(0, 0));

		mapPanel = new MapPanel(map);
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		c.add(mapPanel, BorderLayout.CENTER);

		mapPanel.addHexagonEventListener(new HexagonEventListener() {

			public void hexagonSelected(HexagonEvent hexagonSelectedEvent) {
				final Hexagon h = hexagonSelectedEvent.getHexagon();
				selectedCoordinatesLabel.setText("clicked: " + h.getX() + "/"
						+ h.getY() + ", has flag: " + h.hasFlag()
						+ ", terrain: " + h.getTerrain().getName());

				pathfindingButton.setEnabled(true);
				pathfindingButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (pathfindingButton.isSelected()) {
							final PathfindingStrategy pf = new DijkstraPathfindingStrategy();
							pf
									.setPassableStrategy(new PassableStrategy.RoadBuildingPassableStrategy());
							mapPanel
									.addHexagonEventListener(new HexagonEventListener() {

										public void hexagonHovered(
												HexagonEvent hexagonEvent) {
											System.out.println(pf.findPath(map,
													h, hexagonEvent
															.getHexagon()));
										}

										public void hexagonSelected(
												HexagonEvent hexagonEvent) {
										}
									});
						} else {

						}
					}
				});
			}

			public void hexagonHovered(HexagonEvent hexagonEvent) {
				Hexagon h = hexagonEvent.getHexagon();
				if (h == null) {
					hoverHexagon.setText("hovering: OUT OF MAP!");
				} else {
					hoverHexagon.setText("hovering: " + h.getX() + "/"
							+ h.getY() + ", has flag: " + h.hasFlag()
							+ ", terrain: " + h.getTerrain().getName());
				}
			}
		});

		JPanel zoomPanel = new JPanel();
		zoomPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory
				.createTitledBorder("Zoom"), BorderFactory.createEmptyBorder(
				11, 11, 11, 11)));
		zoomPanel.add(new JLabel("Size: "));
		final JSlider slider = new JSlider(2, 128, 32);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				zoomLabel.setText(Integer.toString(slider.getValue()));
				mapPanel.setHexagonSide(slider.getValue());
			}
		});
		zoomPanel.add(slider);
		zoomPanel.add(zoomLabel);

		c.add(zoomPanel, BorderLayout.SOUTH);

		JPanel detailsPanel = new JPanel();
		detailsPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory
				.createTitledBorder("Selected Hexagon"), BorderFactory
				.createEmptyBorder(11, 11, 11, 11)));
		detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));

		selectedCoordinatesLabel = new JLabel();
		detailsPanel.add(selectedCoordinatesLabel);
		hoverHexagon = new JLabel();
		detailsPanel.add(hoverHexagon);
		pathfindingButton = new JToggleButton("Pathfinding");
		pathfindingButton.setEnabled(false);
		detailsPanel.add(pathfindingButton);
		c.add(detailsPanel, BorderLayout.NORTH);

		setSize(600, 600);
		setVisible(true);
	}
}
