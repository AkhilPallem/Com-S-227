package hw3;

import java.util.ArrayList;

import java.util.Random;

import api.ScoreUpdateListener;
import api.ShowDialogListener;
import api.Tile;

/**
 * Class that models a game.
 * @author AkhilPallem
 */
public class ConnectGame {
	// used for notifications of the max a min tile
	private ShowDialogListener dialogListener;

	// used for displaying live score
	private ScoreUpdateListener scoreListener;

	// width of the grid
	private int width;

	// height of he grid
	private int height;

	// the tile minimum
	private int tileMin;

	// the tile maximum
	private int tileMax;

	// random variable for random object
	private int random;

	// the grid
	private Grid grid;

	// variable keeping track of score
	private long score;

	// creates an array list of selected tiles
	private ArrayList<Tile> selectedTiles;

	// Calls random and stores it in the rand variable
	private Random rand = new Random();

	// stores the last selected tile
	private Tile lastSelected;

	// stores the first selected tile
	private Tile startTile;

	// initialized select to false that can be changed to keep track later on
	private boolean select = false;

	/**
	 * Constructs a new ConnectGame object with given grid dimensions and minimum
	 * and maximum tile levels.
	 * 
	 * @param width  grid width
	 * @param height grid height
	 * @param min    minimum tile level
	 * @param max    maximum tile level
	 * @param rand   random number generator
	 */
	public ConnectGame(int width, int height, int min, int max, Random rand) {
		this.grid = new Grid(width, height);
		this.selectedTiles = new ArrayList<>();
		this.width = width;
		this.height = height;
		this.tileMin = min;
		this.tileMax = max;
		this.rand = rand;
		score = 0;

	}

	/**
	 * Gets a random tile with level between minimum tile level inclusive and
	 * maximum tile level exclusive. For example, if minimum is 1 and maximum is 4,
	 * the random tile can be either 1, 2, or 3.
	 * <p>
	 * DO NOT RETURN TILES WITH MAXIMUM LEVEL
	 * 
	 * @return a tile with random level between minimum inclusive and maximum
	 *         exclusive
	 */
	public Tile getRandomTile() {
		random = rand.nextInt(tileMax - tileMin) + tileMin;
		Tile new_tile = new Tile(random);
		return new_tile;
	}

	/**
	 * Regenerates the grid with all random tiles produced by getRandomTile().
	 */
	public void radomizeTiles() {
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				Tile new_Tile = getRandomTile();
				grid.setTile(new_Tile, col, row);
			}
		}
	}

	/**
	 * Determines if two tiles are adjacent to each other. The may be next to each
	 * other horizontally, vertically, or diagonally.
	 * 
	 * @param t1 one of the two tiles
	 * @param t2 one of the two tiles
	 * @return true if they are next to each other horizontally, vertically, or
	 *         diagonally on the grid, false otherwise
	 */
	public boolean isAdjacent(Tile t1, Tile t2) {
		int x1 = t1.getX();
		int x2 = t2.getX();
		int y1 = t1.getY();
		int y2 = t2.getY();
		int xDifference = Math.abs(x1 - x2);
		int yDifference = Math.abs(y1 - y2);
		if (xDifference + yDifference == 1 || xDifference + yDifference == 2) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Indicates the user is trying to select (clicked on) a tile to start a new
	 * selection of tiles.
	 * <p>
	 * If a selection of tiles is already in progress, the method should do nothing
	 * and return false.
	 * <p>
	 * If a selection is not already in progress (this is the first tile selected),
	 * then start a new selection of tiles and return true.
	 * 
	 * @param x the column of the tile selected
	 * @param y the row of the tile selected
	 * @return true if this is the first tile selected, otherwise false
	 */
	public boolean tryFirstSelect(int x, int y) {
		Tile selectedTile = grid.getTile(x, y);
		if ((selectedTiles.size() > 0)) {
			return false;
		} else {
			startTile = grid.getTile(x, y);
			lastSelected = startTile;
			selectedTiles.add(selectedTile);
			selectedTile.setSelect(true);
			select = true;
			return true;
		}
	}

	/**
	 * Indicates the user is trying to select (mouse over) a tile to add to the
	 * selected sequence of tiles. The rules of a sequence of tiles are:
	 * 
	 * <pre>
	 * 1. The first two tiles must have the same level.
	 * 2. After the first two, each tile must have the same level or one greater than the level of the previous tile.
	 * </pre>
	 * 
	 * For example, given the sequence: 1, 1, 2, 2, 2, 3. The next selected tile
	 * could be a 3 or a 4. If the use tries to select an invalid tile, the method
	 * should do nothing. If the user selects a valid tile, the tile should be added
	 * to the list of selected tiles.
	 * 
	 * @param x the column of the tile selected
	 * @param y the row of the tile selected
	 */
	public void tryContinueSelect(int x, int y) {
		if (select) {
			Tile Tile1 = selectedTiles.get(selectedTiles.size() - 1);
			Tile Tile2 = grid.getTile(x, y);
			if (isAdjacent(Tile2, Tile1)) {
				if (selectedTiles.size() == 1) {
					if (Tile2.getLevel() == selectedTiles.get(0).getLevel()) {
						Tile2.setSelect(true);
						selectedTiles.add(Tile2);
						lastSelected = Tile2;
					}
				} else {
					if (Tile2 == selectedTiles.get(selectedTiles.size() - 2)) {
						unselect(Tile1.getY(), Tile1.getX());
					} else if (Tile2.getLevel() - Tile1.getLevel() == 1 || Tile2.getLevel() == Tile1.getLevel()) {
						Tile2.setSelect(true);
						selectedTiles.add(Tile2);
						lastSelected = Tile2;
					}
				}
			}
		}

	}

	/**
	 * Indicates the user is trying to finish selecting (click on) a sequence of
	 * tiles. If the method is not called for the last selected tile, it should do
	 * nothing and return false. Otherwise it should do the following:
	 * 
	 * <pre>
	 * 1. When the selection contains only 1 tile reset the selection and make sure all tiles selected is set to false.
	 * 2. When the selection contains more than one block:
	 *     a. Upgrade the last selected tiles with upgradeLastSelectedTile().
	 *     b. Drop all other selected tiles with dropSelected().
	 *     c. Reset the selection and make sure all tiles selected is set to false.
	 * </pre>
	 * 
	 * @param x the column of the tile selected
	 * @param y the row of the tile selected
	 * @return return false if the tile was not selected, otherwise return true
	 */
	public boolean tryFinishSelection(int x, int y) {
		int add_Score = 0;
		boolean ifLastTileSelected = false;
		lastSelected.setLocation(x, y);
		if (lastSelected.isSelected()) {
			if (lastSelected.getX() == startTile.getX() && lastSelected.getY() == startTile.getY()) {
				startTile.setSelect(false);
				lastSelected.setSelect(false);
				startTile = null;
				lastSelected = null;
				selectedTiles.clear();
				ifLastTileSelected = true;
				select = false;
			} else {
				for (Tile selectedTile : selectedTiles) {
					add_Score += Math.pow(2, selectedTile.getLevel());
					selectedTile.setSelect(false);
				}
				upgradeLastSelectedTile();
				dropSelected();
				ifLastTileSelected = true;
				score += add_Score;
				setScore(score);
				selectedTiles.clear();
				startTile = null;
				lastSelected = null;
				select = false;
			}
		}
		return ifLastTileSelected;
	}

	/**
	 * Increases the level of the last selected tile by 1 and removes that tile from
	 * the list of selected tiles. The tile itself should be set to unselected.
	 * <p>
	 * If the upgrade results in a tile that is greater than the current maximum
	 * tile level, both the minimum and maximum tile level are increased by 1. A
	 * message dialog should also be displayed with the message "New block 32,
	 * removing blocks 2". Not that the message shows tile values and not levels.
	 * Display a message is performed with dialogListener.showDialog("Hello,
	 * World!");
	 */
	public void upgradeLastSelectedTile() {
		lastSelected.setLevel(lastSelected.getLevel() + 1);
		unselect(lastSelected.getY(), lastSelected.getX());
		int level = lastSelected.getLevel();
		lastSelected.setSelect(false);
		if (level > tileMax) {
			tileMax++;
			int highestValue = (int) Math.pow(2, tileMax);
			int lowestValue = (int) Math.pow(2, tileMin);
			tileMin++;
			dialogListener.showDialog("New block " + highestValue + ", removing blocks " + lowestValue);

		}

	}

	/**
	 * Gets the selected tiles in the form of an array. This does not mean selected
	 * tiles must be stored in this class as a array.
	 * 
	 * @return the selected tiles in the form of an array
	 */
	public Tile[] getSelectedAsArray() {
		return selectedTiles.toArray(new Tile[selectedTiles.size()]);
	}

	/**
	 * Removes all tiles of a particular level from the grid. When a tile is
	 * removed, the tiles above it drop down one spot and a new random tile is
	 * placed at the top of the grid.
	 * 
	 * @param level the level of tile to remove
	 */
	public void dropLevel(int level) {
		int width = grid.getWidth();
		int height = grid.getHeight();
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				Tile tile = grid.getTile(j, i);
				if (tile.getLevel() == level) {
					for (int k = i - 1; k >= 0; k--) {
						Tile toReplace = grid.getTile(j, k);
						grid.setTile(toReplace, j, k + 1);
					}
					grid.setTile(getRandomTile(), j, 0);
				}
			}
		}
	}

	/**
	 * Removes all selected tiles from the grid. When a tile is removed, the tiles
	 * above it drop down one spot and a new random tile is placed at the top of the
	 * grid.
	 */
	public void dropSelected() {
		for (int i = 0; i < grid.getWidth(); i++) {
			for (int j = 0; j < grid.getHeight(); j++) {
				if (selectedTiles.contains(grid.getTile(i, j))) {
					grid.setTile(getRandomTile(), i, j);
					if (j == 0) {
					} else if (j >= 1) {
						grid.setTile(grid.getTile(i, j - 1), i, j);
					}
				}
			}
		}
	}

	/**
	 * Remove the tile from the selected tiles.
	 * 
	 * @param x column of the tile
	 * @param y row of the tile
	 */
	public void unselect(int x, int y) {
		grid.getTile(x, y).setSelect(false);
		selectedTiles.remove(grid.getTile(x, y));
	}

	/**
	 * Gets the player's score.
	 * 
	 * @return the score
	 */
	public long getScore() {
		return score;
	}

	/**
	 * Gets the game grid.
	 * 
	 * @return the grid
	 */
	public Grid getGrid() {
		return grid;
	}

	/**
	 * Gets the minimum tile level.
	 * 
	 * @return the minimum tile level
	 */
	public int getMinTileLevel() {
		return tileMin;
	}

	/**
	 * Gets the maximum tile level.
	 * 
	 * @return the maximum tile level
	 */
	public int getMaxTileLevel() {
		return tileMax;
	}

	/**
	 * Sets the player's score.
	 * 
	 * @param score number of points
	 */
	public void setScore(long score) {
		this.score = score;
		try {
			scoreListener.updateScore(score);
		} catch (NullPointerException e) {
	}
}

	/**
	 * Sets the game's grid.
	 * 
	 * @param grid game's grid
	 */
	public void setGrid(Grid grid) {
		this.grid = grid;
	}

	/**
	 * Sets the minimum tile level.
	 * 
	 * @param tileMinLevel the lowest level tile
	 */
	public void setMinTileLevel(int tileMinLevel) {
		tileMin = tileMinLevel;
	}

	/**
	 * Sets the maximum tile level.
	 * 
	 * @param tileMaxLevel the highest level tile
	 */
	public void setMaxTileLevel(int tileMaxLevel) {
		tileMax = tileMaxLevel;
	}

	/**
	 * Sets callback listeners for game events.
	 * 
	 * @param dialogListener listener for creating a user dialog
	 * @param scoreListener  listener for updating the player's score
	 */
	public void setListeners(ShowDialogListener dialogListener, ScoreUpdateListener scoreListener) {
		this.dialogListener = dialogListener;
		this.scoreListener = scoreListener;
	}

	/**
	 * Save the game to the given file path.
	 * 
	 * @param filePath location of file to save
	 */
	public void save(String filePath) {
		GameFileUtil.save(filePath, this);
	}

	/**
	 * Load the game from the given file path
	 * 
	 * @param filePath location of file to load
	 */
	public void load(String filePath) {
		try {
			GameFileUtil.load(filePath, this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
