package hw3;

import api.Tile;

/**
 * Represents the game's grid.
 */
public class Grid {
	// width of the grid 
	private int gridWidth;
	
	//height of the grid
	private int gridHeight;
	
	//gridTiles that stores the grid 
	private Tile[][] gridtiles;

	/**
	 * Creates a new grid.
	 * 
	 * @param width  number of columns
	 * @param height number of rows
	 */
	public Grid(int width, int height) {
		gridWidth = width;
		gridHeight = height;
		gridtiles = new Tile[gridHeight][gridWidth];
	}

	/**
	 * Get the grid's width.
	 * 
	 * @return width
	 */
	public int getWidth() {
		return gridWidth;
	}

	/**
	 * Get the grid's height.
	 * 
	 * @return height
	 */
	public int getHeight() {
		return gridHeight;
	}

	/**
	 * Gets the tile for the given column and row.
	 * 
	 * @param x the column
	 * @param y the row
	 * @return
	 */
	public Tile getTile(int x, int y) {
		return gridtiles[y][x];
	}

	/**
	 * Sets the tile for the given column and row. Calls tile.setLocation().
	 * 
	 * @param tile the tile to set
	 * @param x    the column
	 * @param y    the row
	 */
	public void setTile(Tile tile, int x, int y) {
		tile.setLocation(y, x);
		gridtiles[y][x] = tile;
	}

	@Override
	public String toString() {
		String str = " ";
		for (int y = 0; y < getHeight(); y++) {
			if (y > 0) {
				str += "\n";
			}
			str += "[";
			for (int x = 0; x < getWidth(); x++) {
				if (x > 0) {
					str += ",";
				}
				str += getTile(x, y);
			}
			str += "]";
		}
		return str;
	}
}
