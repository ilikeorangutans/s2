package de.jakusys.settler.pathfinder;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import de.jakusys.settler.model.map.Hexagon;
import de.jakusys.settler.model.map.Map;

/**
 * This is a very crude and very bad implementation of Dijkstra's algorithm. Not
 * even sure if it is Dijkstra's algorithm. But it works. Needs lots of
 * refactoring, but it is good enough for now.
 * 
 * @author jakob
 * 
 */
public class DijkstraPathfindingStrategy implements PathfindingStrategy {

	private class VisitHexagon {

		private int estimate;

		private Hexagon hexagon;

		private final boolean passable;

		public VisitHexagon(Hexagon hexagon) {
			this(hexagon, Integer.MAX_VALUE, true);
		}

		public VisitHexagon(Hexagon hexagon, int estimate) {
			this(hexagon, estimate, true);
		}

		public VisitHexagon(Hexagon hexagon, int estimate, boolean passable) {
			this.hexagon = hexagon;
			this.estimate = estimate;
			this.passable = passable;
		}

		@Override
		public boolean equals(Object obj) {
			System.out.println("equals called.");
			if (obj instanceof VisitHexagon) {
				VisitHexagon vh = (VisitHexagon) obj;
				return this == obj || this.hexagon.equals(vh.hexagon);
			} else {
				return false;
			}
		}

		@Override
		public int hashCode() {
			return hexagon.hashCode();
		}
	}

	private PassableStrategy passableStrategy = new PassableStrategy.DefaultPassableStrategy();

	private int estimate(Hexagon a, Hexagon b) {
		return (a.getX() - b.getX()) * (a.getX() - b.getX())
				+ ((a.getY() - b.getY()) * (a.getY() - b.getY()));
	}

	private final Comparator<VisitHexagon> comparator = new Comparator<VisitHexagon>() {
		public int compare(VisitHexagon o1, VisitHexagon o2) {
			return o1.estimate - o2.estimate;
		}
	};

	@SuppressWarnings("unchecked")
	public List<Hexagon> findPath(final Map map, final Hexagon start,
			final Hexagon dest) {

		if (start == null || dest == null) {
			throw new NullPointerException(
					"Both start and destination must not be null!");
		}

		LinkedList<VisitHexagon> toVisit = new LinkedList<VisitHexagon>();

		toVisit.add(new VisitHexagon(start, 0));
		List<Hexagon> visited = new LinkedList<Hexagon>();
		java.util.Map<Hexagon, Hexagon> predecessors = new HashMap<Hexagon, Hexagon>();

		Hexagon previous = null;
		VisitHexagon cheapest = null;
		while (!toVisit.isEmpty()) {
			Collections.sort(toVisit, comparator);
			System.out
					.println("------------------------------------------------");
			System.out.print("visited contains " + visited.size()
					+ " entries, toVisit contains " + toVisit.size()
					+ " entries: ");
			for (Iterator<VisitHexagon> i = toVisit.iterator(); i.hasNext();) {
				VisitHexagon vh = i.next();
				Hexagon h = vh.hexagon;
				System.out.print(h.getX() + "/" + h.getY() + "  ");
			}
			System.out.println();
			cheapest = toVisit.getFirst();
			toVisit.remove(cheapest);

			visited.add(cheapest.hexagon);

			if (!cheapest.passable) {
				System.out.println("Skipping impassable field at "
						+ cheapest.hexagon.getX() + "/"
						+ cheapest.hexagon.getY());
				continue;
			} else {
				System.out.println("Continuing with passable field at "
						+ cheapest.hexagon.getX() + "/"
						+ cheapest.hexagon.getY());
			}

			predecessors.put(cheapest.hexagon, previous);
			previous = cheapest.hexagon;

			if (dest.equals(cheapest.hexagon)) {
				break;
			}

			for (Iterator<Hexagon> i = cheapest.hexagon.getNeighbours()
					.values().iterator(); i.hasNext();) {
				Hexagon nextNeighbor = i.next();

				if (visited.contains(nextNeighbor)) {
					continue;
				}

				// Can we actually pass that hexagon?
				if (!passableStrategy.isPassable(nextNeighbor)) {
					// Add the hexagon to the toVisit list.
					toVisit.add(new VisitHexagon(nextNeighbor,
							cheapest.estimate + 1, false));
					System.out
							.println("Added non-passable field to toVisit list "
									+ nextNeighbor.getX()
									+ "/"
									+ nextNeighbor.getY());
					continue;
				}

				int tmpEstimate = estimate(nextNeighbor, dest);

				boolean alreadyLookedAtThis = false;
				VisitHexagon visitedHexagon = null;

				// Is this hexagon already in our toVisit list?
				for (Iterator<VisitHexagon> tmp = toVisit.iterator(); tmp
						.hasNext();) {
					VisitHexagon a = tmp.next();
					if (nextNeighbor.equals(a.hexagon)) {
						alreadyLookedAtThis = true;
						visitedHexagon = a;
						break;
					}
				}

				if (alreadyLookedAtThis) {
					System.out.println("Updating already looked at entry at "
							+ nextNeighbor.getX() + "/" + nextNeighbor.getY());
					// See if we can update the costs:
					if (tmpEstimate < visitedHexagon.estimate) {
						visitedHexagon.estimate = tmpEstimate;
					}
				} else {
					System.out
							.println("Adding new entry to toVisit list with estimate "
									+ tmpEstimate
									+ " at "
									+ nextNeighbor.getX()
									+ "/"
									+ nextNeighbor.getY());
					System.out.println(toVisit.add(new VisitHexagon(
							nextNeighbor, tmpEstimate)));
				}
				System.out.println("visited contains " + visited.size()
						+ " entries, toVisit has " + toVisit.size()
						+ " entries now. ");
			}
		}

		System.out.println(cheapest.estimate);

		if (dest.equals(cheapest.hexagon)) {
			System.out.println("Found path!");
			List<Hexagon> path = new LinkedList<Hexagon>();
			Hexagon cur = cheapest.hexagon;
			while (predecessors.get(cur) != null) {
				path.add(cur);
				cur = predecessors.get(cur);
			}

			path.add(cur);
			return path;
		} else {
			System.out.println("No path found.");
			return Collections.EMPTY_LIST;
		}
	}

	public PathfindingStrategy setPassableStrategy(
			PassableStrategy passableStrategy) {
		if (passableStrategy == null) {
			throw new NullPointerException(
					"Cannot set passableStrategy to null!");
		}
		this.passableStrategy = passableStrategy;
		return this;
	}
}
