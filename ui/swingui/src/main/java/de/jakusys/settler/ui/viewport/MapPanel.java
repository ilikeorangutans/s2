package de.jakusys.settler.ui.viewport;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import de.jakusys.settler.model.map.Hexagon;
import de.jakusys.settler.model.map.Map;
import de.jakusys.settler.model.map.Path;
import de.jakusys.settler.model.terrain.TerrainImpl;
import de.jakusys.settler.ui.event.HexagonEvent;
import de.jakusys.settler.ui.event.HexagonEventEmitter;
import de.jakusys.settler.ui.event.HexagonEventListener;
import de.jakusys.settler.ui.event.HexagonEventSupport;
import de.jakusys.settler.ui.event.HexagonEvent.TYPE;
import de.jakusys.util.event.AbstractEventSupport;

public class MapPanel extends JPanel implements MouseInputListener,
		HexagonEventEmitter, MapViewport {

	private static final AbstractEventSupport<HexagonEvent, HexagonEventListener> hexagonEventHandling = new HexagonEventSupport();

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final double SIN_30_DEGREES = Math.sin(Math.toRadians(30));

	private static final double COS_30_DEGREES = Math.cos(Math.toRadians(30));

	private int shortAngle;

	private int longAngle;

	private int hexagonSide;

	private int hexagonWidth;

	private int hexagonHeight;

	private Map map;

	private Set<Path> pathsToRender = new LinkedHashSet<Path>();

	private Point currentHighlight = new Point();

	public MapPanel(Map map) {
		if (map == null) {
			throw new NullPointerException("Map is null.");
		}

		this.map = map;
		setHexagonSide(32);

		addMouseListener(this);
		addMouseMotionListener(this);
	}

	public void addHexagonEventListener(
			HexagonEventListener hexagonSelectedListener) {
		hexagonEventHandling.addListener(hexagonSelectedListener);
	}

	private void drawHexagon(Graphics2D graphics2D, Hexagon h, int x, int y) {
		graphics2D.setColor(Color.GRAY);

		if (h.getTerrain() == TerrainImpl.SEA) {
			graphics2D.setColor(new Color(0, 0, 255));
		} else {
			graphics2D.setColor(new Color(0, 128, 0));
		}

		graphics2D.fillPolygon(new int[] { x, x, x + longAngle,
				x + hexagonWidth, x + hexagonWidth, x + longAngle }, new int[] {
				y + shortAngle, y + shortAngle + hexagonSide,
				y + hexagonHeight, y + shortAngle + hexagonSide,
				y + shortAngle, y }, 6);

		if (h.getFlag() != null) {
			graphics2D.setColor(Color.green);
			int centerX = x + (hexagonWidth / 2);
			graphics2D.drawPolygon(new int[] { centerX, centerX,
					centerX + shortAngle, centerX + shortAngle, centerX },
					new int[] { y + hexagonSide + shortAngle, y + shortAngle,
							y + shortAngle, y + 2 * shortAngle,
							y + 2 * shortAngle }, 5);
		}
	}

	private void drawPaths(Graphics2D graphics2D) {
		graphics2D.setColor(Color.red);
		for (Iterator<Path> i = pathsToRender.iterator(); i.hasNext();) {
			Path path = i.next();
			if (path != null) {
				int[] xPoints = new int[path.getHexagons().size()];
				int[] yPoints = new int[path.getHexagons().size()];
				int nPoints = path.getHexagons().size();

				int counter = 0;
				for (Iterator<Hexagon> hi = path.getHexagons().iterator(); hi
						.hasNext();) {
					Hexagon h = hi.next();

					Point p = getHexagonScreenCoordinate(h.getX(), h.getY());
					xPoints[counter] = p.x + longAngle;
					yPoints[counter] = p.y + hexagonHeight / 2;
					counter++;
				}

				graphics2D.drawPolyline(xPoints, yPoints, nPoints);
			}
		}
		pathsToRender.clear();
	}

	public int getHexagonHeight() {
		return hexagonHeight;
	}

	public Point getHexagonScreenCoordinate(int x, int y) {
		int screenX = (x * 2 * longAngle) + (longAngle * (y % 2));
		int screenY = (shortAngle + hexagonSide) * y;

		return new Point(screenX, screenY);
	}

	public int getHexagonSide() {
		return hexagonSide;
	}

	public int getHexagonWidth() {
		return hexagonWidth;
	}

	public int getLongAngle() {
		return longAngle;
	}

	public Map getMap() {
		return map;
	}

	public int getShortAngle() {
		return shortAngle;
	}

	public Rectangle getVisibleArea() {
		return null;
	}

	public boolean isVisible(Hexagon hexagon) {
		return false;
	}

	public void mouseClicked(MouseEvent e) {
		Point p = screenToHexagon(e.getX(), e.getY());
		Hexagon h = map.getHexagon(p.x, p.y);
		if (h != null) {
			hexagonEventHandling.fire(new HexagonEvent(this, h, TYPE.CLICK));
		}
	}

	public void mouseDragged(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseMoved(MouseEvent e) {
		Point oldCurrent = currentHighlight;
		currentHighlight = screenToHexagon(e.getX(), e.getY());
		if (!currentHighlight.equals(oldCurrent)) {
			hexagonEventHandling.fire(new HexagonEvent(this, map.getHexagon(
					currentHighlight.x, currentHighlight.y), TYPE.HOVER));
		}
		repaint();
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		final Graphics2D g2d = (Graphics2D) g;

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		setBackground(Color.BLACK);

		int maxX = (getWidth() / hexagonWidth) + 1;
		int maxY = (getHeight() / (hexagonSide + shortAngle)) + 1;

		maxX = maxX > map.getWidth() ? map.getWidth() : maxX;
		maxY = maxY > map.getHeight() ? map.getHeight() : maxY;

		for (int x = 0; x < maxX; x++) {
			for (int y = 0; y < maxY; y++) {
				Hexagon hexagon = map.getHexagon(x, y);

				if (hexagon.hasFlag()
						&& hexagon.getFlag().getPaths().size() > 0) {
					pathsToRender.addAll(hexagon.getFlag().getPaths());
				}

				Point p = getHexagonScreenCoordinate(x, y);
				drawHexagon(g2d, hexagon, p.x, p.y);

				/*
				 * g2d.setColor(Color.red); g2d.drawLine(0, (int) p.getY(),
				 * getWidth(), (int) p.getY()); g2d.drawLine(0, (int) p.getY() +
				 * shortAngle, getWidth(), (int) p.getY() + shortAngle);
				 */
			}
		}

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_OFF);

		drawPaths(g2d);

		g2d.setColor(Color.red);
		Point p = getHexagonScreenCoordinate(currentHighlight.x,
				currentHighlight.y);
		g2d.drawOval(p.x, p.y + shortAngle / 2, hexagonWidth, hexagonHeight
				- shortAngle);

		g2d.dispose();
	}

	private Point screenToHexagon(int x, int y) {
		Point p = new Point(0, 0);

		boolean overSquareSection = y % (hexagonSide + shortAngle) > shortAngle;
		boolean even = y % (2 * (hexagonSide + shortAngle)) < (hexagonSide + shortAngle);

		if (even) {
			if (overSquareSection) {
				p.x = x / hexagonWidth;
				p.y = y / (shortAngle + hexagonSide);
			} else {
			}
		} else {
			if (overSquareSection) {
				p.x = (x - longAngle) / hexagonWidth;
				p.y = y / (shortAngle + hexagonSide);
			} else {
			}
		}

		return p;
	}

	public void setHexagonHeight(int hexagonHeight) {
		this.hexagonHeight = hexagonHeight;
	}

	public void setHexagonSide(int hexagonSide) {
		this.hexagonSide = hexagonSide;
		shortAngle = (int) Math.round(SIN_30_DEGREES * hexagonSide);
		longAngle = (int) Math.round(COS_30_DEGREES * hexagonSide);

		hexagonWidth = 2 * longAngle;
		hexagonHeight = 2 * shortAngle + hexagonSide;

		System.out.println("short: " + shortAngle + " long: " + longAngle);
		System.out.println("width: " + hexagonWidth + " height: " + hexagonHeight);

		repaint();
	}

	public void setHexagonWidth(int hexagonWidth) {
		this.hexagonWidth = hexagonWidth;
	}

	public void setLongAngle(int longAngle) {
		this.longAngle = longAngle;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public void setShortAngle(int shortAngle) {
		this.shortAngle = shortAngle;
	}

}
