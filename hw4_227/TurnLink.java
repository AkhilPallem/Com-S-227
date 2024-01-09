package hw4;

import api.Point;
import api.PointPair;

/**
 * Models a fixed link with three paths.
 * 
 * @author akhilpallem
 *
 */
public class TurnLink extends AbstractLink {

	/*
	 * Creates a new TurnLink.
	 * @param 
	 * endpointA -
	 * endpointB -
	 * endpointC -
	 */
	public TurnLink(Point endpointA, Point endpointB, Point endpointC) {
		super(new PointPair[] { new PointPair(endpointA, endpointC), new PointPair(endpointB, endpointA),
				new PointPair(endpointC, endpointA), });
	}

}
