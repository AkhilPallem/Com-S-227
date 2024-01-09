package hw4;

import api.Crossable;
import api.PointPair;

/**
 * Models a link with a minimum of 2 to a maximum of 6 paths. 
 *
 * The connections are provided to the constructor as an array of 2 to 6 point
 * pairs. Each point pair indicates the two endpoints where the train comes from
 * and goes to. The number of point pairs should be the same as the number of
 * paths.
 * 
 * @author akhilpallem
 *
 */
public class MultiFixedLink extends AbstractLink implements Crossable {

	/*
	 * Creates a new MultiFixedLink.
	 * 
	 * @param PointPair
	 */
	public MultiFixedLink(PointPair[] connection) {
		super(connection);
	}
}