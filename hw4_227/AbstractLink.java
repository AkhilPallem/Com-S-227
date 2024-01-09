package hw4;

import api.Crossable;
import api.Path;
import api.Point;
import api.PointPair;
import api.PositionVector;

/**
 * 
 * Models a link between paths. This class implements Crossable which extends
 * Traversable.
 * 
 * @author Akhil Pallem
 */
public abstract class AbstractLink implements Crossable {
	// Variable point pair array named length
	private PointPair[] length;

	// determines if the train is crossing or not
	private boolean trainEnter = false;

	protected AbstractLink(PointPair[] connections) {
		length = connections;
	}

	@Override
	public void shiftPoints(PositionVector positionVector) {
		Point newPath = getConnectedPoint(positionVector.getPointB());
		positionVector.setPointA(newPath);
		Path otherPath1 = newPath.getPath();
		if (newPath.getPointIndex() == 0) {
			positionVector.setPointB(otherPath1.getPointByIndex(1));
		} else {
			positionVector.setPointB(otherPath1.getPointByIndex(otherPath1.getNumPoints() - 2));
		}

	}

	@Override
	/**
	 * Gets the point that is connected to the given point by the link. Returns null
	 * if no point is connected to the given point.
	 */
	public Point getConnectedPoint(Point point) throws NullPointerException {

		for (PointPair pairing : length) {
			if (pairing.getPointA() == point) {
				return pairing.getPointB();
			}
		}
		return null;
	}

	@Override
	/*
	 * This method is called by the simulation to indicate a train has entered the
	 * crossing.
	 */
	public void trainEnteredCrossing() {
		trainEnter = true;
	}

	protected boolean crossing() {
		return trainEnter;
	}
	
	@Override
	/**
	 * This method is called by the simulation to indicate a train has exited the
	 * crossing.
	 */
	public void trainExitedCrossing() {
		trainEnter = false;
	}


	protected PointPair[] Connect() {
		return length;
	}

	@Override
	/**
	 * Gets the total number of paths connected by the link.
	 */
	public int getNumPaths() {
		return length.length;
	}

}
