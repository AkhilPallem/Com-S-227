package hw4;

import api.PointPair;

/**
 * Models a link with a minimum of 2 to a maximum of 6 paths.
 *
 * The connections are provided to the constructor as an array of 2 to 6 point
 * pairs. Each point pair describes the two endpoints where the train comes from
 * and goes to. The turn cannot be modified when the train is in the crossing.
 * The number of point pairs should be the same as the number of paths.
 * 
 * @author akhilpallem
 *
 */
public class MultiSwitchLink extends AbstractLink {
	/*
	 * Creates a new MultiSwitchLink.
	 * 
	 * @param Point Pair
	 */
	public MultiSwitchLink(PointPair[] connections) {
		super(connections);
	}

	/*
	 * Updates the connection point pair at the given index. The connection cannot
	 * be modified (method does nothing) when the train is currently in (entered but
	 * not exited) the crossing.
	 */
	public void switchConnection(PointPair pointPair, int index) {
		if (!crossing()) {
			Connect()[index] = pointPair;
		}
	}

}
