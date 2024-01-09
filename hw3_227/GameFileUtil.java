package hw3;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import api.Tile;

/**
 * Utility class with static methods for saving and loading game files.
 */
public class GameFileUtil {
	/**
	 * Saves the current game state to a file at the given file path.
	 * <p>
	 * The format of the file is one line of game data followed by multiple lines of
	 * game grid. The first line contains the: width, height, minimum tile level,
	 * maximum tile level, and score. The grid is represented by tile levels. The
	 * conversion to tile values is 2^level, for example, 1 is 2, 2 is 4, 3 is 8, 4
	 * is 16, etc. The following is an example:
	 * 
	 * <pre>
	 * 5 8 1 4 100
	 * 1 1 2 3 1
	 * 2 3 3 1 3
	 * 3 3 1 2 2
	 * 3 1 1 3 1
	 * 2 1 3 1 2
	 * 2 1 1 3 1
	 * 4 1 3 1 1
	 * 1 3 3 3 3
	 * </pre>
	 * 
	 * @param filePath the path of the file to save
	 * @param game     the game to save
	 */
	public static void save(String filePath, ConnectGame game) {
		try {
			BufferedWriter writer1 = new BufferedWriter(new FileWriter(filePath));
			Grid grid = game.getGrid();
			int width = grid.getWidth();
			int height = grid.getHeight();
			int minTileLevel = game.getMinTileLevel();
			int maxTileLevel = game.getMaxTileLevel();
			long score = game.getScore();
			writer1.write(width + " " + height + " " + minTileLevel + " " + maxTileLevel + " " + score);
			for (int col = 0; col < height; col++) {
				writer1.write('\n');
				for (int row = 0; row < width; row++) {
					Tile tiles = grid.getTile(row, col);
					if (tiles != null && row == width - 1) {
						writer1.write(tiles.getLevel() + "");
					} 
					else if(tiles != null) {
						writer1.write(tiles.getLevel() + " ");
					}
					else {
						writer1.write("0 ");
					}
				}
			}

			writer1.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void load(String filePath, ConnectGame game) {
		try {
			Scanner reader2 = new Scanner(new FileReader(filePath));
			int width = reader2.nextInt();
			int height = reader2.nextInt();
			int tileMinLevel = reader2.nextInt();
			int tileMaxLevel = reader2.nextInt();
			long score = reader2.nextLong();
			Grid grid = new Grid(width, height);
			for (int row = 0; row < height; row++) {
				for (int col = 0; col < width; col++) {
					int level1 = reader2.nextInt();
					if (level1 != 0) {
						Tile tile = new Tile(level1);
						grid.setTile(tile, col, row);
					}
				}
			}
			game.setGrid(grid);
			game.setMinTileLevel(tileMinLevel);
			game.setMaxTileLevel(tileMaxLevel);
			game.setScore(score);
			reader2.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

