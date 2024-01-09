package hw4;

import api.Point;
import api.PointPair;

/**
 * 
 * Models a fixed link with three paths. 
 * 
 * @author akhilpallem
 *
 */
public class StraightLink extends AbstractLink {
	/*
	 * Creates a new StraightLink.
	 * @param
	 * endpointA -
	 * endpointB -
	 * endpointC -
	 */
	public StraightLink(Point endpointA, Point endpointB, Point endpointC) {
		super(new PointPair[] { new PointPair(endpointA, endpointB), new PointPair(endpointB, endpointA),
				new PointPair(endpointC, endpointA), });
	}

	/*
	 * Gets the number of paths
	 */
	public int getNumPaths() {
		return 3;
	}

}
