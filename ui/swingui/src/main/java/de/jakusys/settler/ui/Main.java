package de.jakusys.settler.ui;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import de.jakusys.settler.model.map.Hexagon;
import de.jakusys.settler.model.map.Map;
import de.jakusys.settler.model.map.MapImpl;
import de.jakusys.settler.ui.action.BuildFlagAction;
import de.jakusys.settler.ui.action.BuildRoadAction;
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

		mapPanel = new MapPanel(map);
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(mapPanel, BorderLayout.CENTER);

		final BuildRoadAction buildRoadAction = new BuildRoadAction(mapPanel);
		final BuildFlagAction buildFlagAction = new BuildFlagAction(mapPanel);

		JToolBar toolbar = new JToolBar("Tools", JToolBar.VERTICAL);
		toolbar.add(buildFlagAction);
		toolbar.add(buildRoadAction);
		contentPane.add(toolbar, BorderLayout.BEFORE_LINE_BEGINS);

		mapPanel.addHexagonEventListener(new HexagonEventListener() {

			public void hexagonSelected(HexagonEvent hexagonSelectedEvent) {
				final Hexagon h = hexagonSelectedEvent.getHexagon();
				selectedCoordinatesLabel.setText("clicked: " + h.getX() + "/"
						+ h.getY() + ", has flag: " + h.hasFlag()
						+ ", terrain: " + h.getTerrain().getName());
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

		contentPane.add(zoomPanel, BorderLayout.SOUTH);

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
		contentPane.add(detailsPanel, BorderLayout.NORTH);

		setSize(600, 600);
		setVisible(true);
	}
}
