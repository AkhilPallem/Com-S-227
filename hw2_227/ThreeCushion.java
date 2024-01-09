package hw2;

import api.PlayerPosition;
import api.BallType;
import static api.PlayerPosition.*;
import static api.BallType.*;

/**
 * Class that models the game of three-cushion billiards.
 * 
 * @author Akhil Pallem                      
 */
public class ThreeCushion { 

	// TODO: EVERTHING ELSE GOES HERE
	// Note that this code will not compile until you have put in stubs for all
	// the required methods.

	// The method below is provided for you and you should not modify it.
	// The compile errors will go away after you have written stubs for the
	// rest of the API methods.
	// Initalizes playerAScore
	private int playerAScore;
	
	// Initalizes playerBScore
	private int playerBScore;
	
	// Initalizes the current inning
	private int currentInning;
	
	// Initalizes gameOver to false
	private boolean gameOver = false;
	
	// Initalizes if the shot has been started to false 
	private boolean shotStarted = false;
	
	// Initalizes cueBall to the ballType
	private BallType cueBall;
	
	// Initalizes breakShot to true 
	private boolean breakShot = true;
	
	// Initalizes inningPlay to the players position 
	private PlayerPosition inningPlay;
	
	// Initalizes bankshot 
	private boolean bankShot;
	
	// Initalizes innning start to where it starts 
	private boolean inningStart;
	
	// Initalizes end shot to false
	private boolean endShot = false;
	
	// Initalizes points win to pointsToWin in the constructor 
	private int pointsWin;
	
	// Initalizes red to false as the cue ball
	private boolean red = false;
	
	// Initalizes other cue to the false cue 
	private boolean otherCue = false;
	
	// Initalizes cushion to the points  
	private int cushion = 0;
	
	/**
	 * the constructor 
	 * @author akhilpallem
	 */
	public ThreeCushion(PlayerPosition lagWinner, int pointsToWin) {
		currentInning = 1;
		cueBall = BallType.WHITE;
		inningPlay = PlayerPosition.PLAYER_A;
		playerAScore = 0;
		playerBScore = 0;
		pointsWin = pointsToWin;
		breakShot=true;
	}
	/**
	 * Starts the game by deciding who wants to go first and plays out the "lag"
	 * @author Akhil Pallem
	 * 
	 * 
	 */
	public void lagWinnerChooses(boolean selfBreak, BallType cueBall) {
		if (selfBreak == true && breakShot == true) {
			this.cueBall = cueBall;
		} else if (selfBreak == false && breakShot == true) {
			if (inningPlay == PlayerPosition.PLAYER_A) {
				inningPlay = PlayerPosition.PLAYER_B;
			} else {
				inningPlay = PlayerPosition.PLAYER_A;
			}
			if (cueBall == BallType.WHITE) {
				cueBall = BallType.YELLOW;
			} else {
				cueBall = BallType.WHITE;
			}
		}
	}
	/**
	 * checks if it has been broken and then can move on to the shot and the end to start the inning 
	 * @author akhilpallem
	 */
	public void cueStickStrike(BallType ball) {
		breakShot = false;
		if (breakShot == false && gameOver == false) {
			shotStarted = true;
			inningStart = true;
			if (endShot == true || ball != cueBall) {
				foul();
			}
		}

	}
	
	/**
	 * if a bankshot is made it gets a point and checks if the shot starts 
	 * @author akhilpallem
	 */
	public void cueBallImpactCushion() {
		if(shotStarted) {
			cushion++;
		}
		if(red == false && otherCue == false && cushion >= 3) {
			bankShot = true;
		}
		if(breakShot == true && red == false) {
			foul();
		}

	}
	
	/**
	 * cue ball strike which checks which balls were hit 
	 * @author akhilpallem
	 */

	public void cueBallStrike(BallType ball) {
		if(ball == BallType.RED) {
			red = true;
		}
		else if (ball == BallType.YELLOW || ball == BallType.WHITE) {
			otherCue = true;
		}
	}
	
	/**
	 * checks if the shot end is completed and then switches turns and the cue ball 
	 * @author akhilpallem
	 */

	public void endShot() {
		breakShot = false;
		if(shotStarted == true && gameOver == false) 
		{
			shotStarted = false;
			if (red == true && otherCue == true && cushion >= 3 &&(playerAScore < pointsWin || playerBScore < pointsWin)) 
			{
				if (inningPlay == PlayerPosition.PLAYER_A) {
					playerAScore++;
					red = false;
					otherCue = false;
					cushion = 0;
				}
				else if (inningPlay == PlayerPosition.PLAYER_B) {
					playerBScore ++;
					red = false;
					otherCue = false;
					cushion = 0;
				}
				
			}
			else 
			{
				currentInning ++;
				inningStart = false;
				red = false;
				otherCue = false;
				cushion = 0;
				bankShot = false;
				if(inningPlay == PlayerPosition.PLAYER_A) {
					inningPlay = PlayerPosition.PLAYER_B;
				}
				else 
				{
					inningPlay = PlayerPosition.PLAYER_B;
				}
				if (cueBall == BallType.WHITE) {
					this.cueBall = BallType.YELLOW;
				}
				else {
					this.cueBall = BallType.WHITE;
				}
			}
		if (playerAScore >= pointsWin || playerBScore >= pointsWin) {
			gameOver = true;
		}
	}
				
	}
	/**
	 * checks if a foul has been committed on the shot 
	 * @author akhilpallem
	 */
	public void foul() {
		shotStarted = false;
		inningStart = false;
		if (inningPlay == PlayerPosition.PLAYER_A) {
			inningPlay = PlayerPosition.PLAYER_B;
		} else {
			inningPlay = PlayerPosition.PLAYER_A;
		}
		if (cueBall == BallType.WHITE) {
			cueBall = BallType.YELLOW;
		} else {
			cueBall = BallType.WHITE;
		}
		currentInning++;

	}
	
	/**
	 * retuns playerAScore 
	 * @return
	 */
	public int getPlayerAScore() {
		return playerAScore;

	}
	
	/**
	 * retuns Player B Score 
	 * @return
	 */
	public int getPlayerBScore() {
		return playerBScore;

	}
	/**
	 * retuns current inning 
	 * @return
	 */

	public int getInning() {
		return currentInning;
	}

	/**
	 * retuns ball type 
	 * @return
	 */
	
	public BallType getCueBall() {
		return cueBall;

	}
	
	/**
	 * retuns inning player
	 * @return
	 */

	public PlayerPosition getInningPlayer() {
		return inningPlay;
	}
	
	/**
	 * retuns breakShot 
	 * @return
	 */

	public boolean isBreakShot() {
		return breakShot;

	}
	
	/**
	 * retuns bank shot 
	 * @return
	 */

	public boolean isBankShot() {
		return bankShot;
	}
	
	/**
	 * retuns the shot being started 
	 * @return
	 */
	public boolean isShotStarted() {
		return shotStarted;

	}
	
	/**
	 * retuns inning started
	 * @return
	 */

	public boolean isInningStarted() {
		return inningStart;

	}
/**
 * Game Over method 
 * @param
 */
	public boolean isGameOver() {
		if (gameOver == true) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Returns a one-line string representation of the current game state. The
	 * format is:
	 * <p>
	 * <tt>Player A*: X Player B: Y, Inning: Z</tt>
	 * <p>
	 * The asterisks next to the player's name indicates which player is at the
	 * table this inning. The number after the player's name is their score. Z is
	 * the inning number. Other messages will appear at the end of the string.
	 * 
	 * @return one-line string representation of the game state
	 */
	public String toString() {
		String fmt = "Player A%s: %d, Player B%s: %d, Inning: %d %s%s";
		String playerATurn = "";
		String playerBTurn = "";
		String inningStatus = "";
		String gameStatus = "";
		if (getInningPlayer() == PLAYER_A) {
			playerATurn = "*";
		} else if (getInningPlayer() == PLAYER_B) {
			playerBTurn = "*";
		}
		if (isInningStarted()) {
			inningStatus = "started";
		} else {
			inningStatus = "not started";
		}
		if (isGameOver()) {
			gameStatus = ", game result final";
		}
		return String.format(fmt, playerATurn, getPlayerAScore(), playerBTurn, getPlayerBScore(), getInning(),
				inningStatus, gameStatus);
	}
}