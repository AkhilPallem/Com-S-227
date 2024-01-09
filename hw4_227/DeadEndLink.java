package hw4;

import api.Crossable;
import api.Point;
import api.PositionVector;

/**
 * Models a link that connects a single path to nothing. getConnectedPoint()
 * returns null and shiftPoints() does nothing.
 * 
 * @author akhilpallem
 *
 */
public class DeadEndLink extends AbstractLink implements Crossable {

	public DeadEndLink() {
		super(null);
	}

	@Override
	public Point getConnectedPoint(Point point) {
		return null;
	}

	@Override
	public int getNumPaths() {
		return 1;
	}

	@Override
	public void shiftPoints(PositionVector positionVector) {

	}

}