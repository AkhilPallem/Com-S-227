package hw4;

import api.Point;
import api.PointPair;

/**
 * Models a switchable link with three paths. A boolean turn determines which
 * path trains take. By default turn is set to false. 
 * 
 * @author akhilpallem
 *
 */
public class SwitchLink extends AbstractLink {

	/**
	 * endpointB variable
	 */
	private Point endpoint1;

	/**
	 * endpointC variable
	 */
	private Point endpoint2;

	/*
	 * The given endpoints correspond to the paths as labeled below.
	 * @param
	 * endpointA -
	 * endpointB -
	 * endpointC -
	 */
	public SwitchLink(Point endpointA, Point endpointB, Point endpointC) {
		super(new PointPair[] { new PointPair(endpointA, endpointB), new PointPair(endpointB, endpointA),
				new PointPair(endpointC, endpointA), });
		endpoint1 = endpointB;
		endpoint2 = endpointC;
	}

	/*
	 * Gets the number of paths
	 */
	public int getNumPaths() {
		return 3;
	}

	/*
	 * Updates the link to turn or not turn. The turn cannot be modified (do
	 * nothing) when the train is currently in (entered but not exited) the
	 * crossing.
	 * 
	 * @param turn - true for turn and false for not turn
	 */
	public void setTurn(boolean turn) {
		if (!crossing()) {
			PointPair[] pairing = Connect();
			if (turn) {
				pairing[0].setPointB(endpoint2);
			} else {
				pairing[0].setPointB(endpoint1);
			}
		}
	}

}
