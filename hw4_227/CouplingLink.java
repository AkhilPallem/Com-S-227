package hw4;

import api.Point;
import api.PointPair;

/**
 * Models a link between exactly two paths.
 * 
 * @author akhilpallem
 *
 */
public class CouplingLink extends AbstractLink {

	/*
	 * Creates a new CouplingLink that connects the two given endpoints.
	 * 
	 * @param both endpoints
	 */
	public CouplingLink(Point endpoint1, Point endpoint2) {
		super(new PointPair[] { new PointPair(endpoint1, endpoint2), new PointPair(endpoint2, endpoint1) });
	}

}
