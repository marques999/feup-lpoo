package lpoo.logic;

import java.io.Serializable;
import java.util.Random;

public class Maze implements Serializable
{
	private static final long serialVersionUID = 7550516997964463999L;

	private final int mazeWidth;
	private final int mazeHeight;
	private final Random mazeRandom;
	private Point mazeExit;
	private char[][] mazeMatrix;

	/**
	 * default constructor for 'Maze'
	 * 
	 * @param w initial maze width (number of columns)
	 * @param h initial maze height (number of rows)
	 */
	public Maze(int w, int h)
	{
		mazeMatrix = new char[h][w];
		mazeRandom = new Random();
		mazeWidth = w;
		mazeHeight = h;
                mazeExit = new Point(0, 0);
	}

	/**
	 * @param e cell coordinates for the exit
	 */
	public void setExitPosition(Point e)
	{
		mazeExit = e;
	}

	/**
	 * @return a two-dimensional matrix representing the maze
	 */
	public char[][] getMatrix()
	{
		return mazeMatrix;
	}

	/**
	 * replaces the current maze matrix with a given one
	 * 
	 * @param m new two-dimensional matrix representing the maze
	 */
	public void setMatrix(char[][] m)
	{
		mazeMatrix = m;
	}

	/**
	 * @return the current exit coordinates
	 */
	public Point getExitPosition()
	{
		return mazeExit;
	}

	/**
	 * @return maze width (number of columns)
	 */
	public int getWidth()
	{
		return mazeWidth;
	}

	/**
	 * @return maze height (number of rows)
	 */
	public int getHeight()
	{
		return mazeHeight;
	}

	/**
	 * checks if the given cell coordinates are on the maze border
	 * 
	 * @param x maze cell X coordinate
	 * @param y maze cell Y coordinate
	 * @return 'true' if given cell belongs to the maze border, 'false' otherwise
	 */
	public final boolean isWall(int x, int y)
	{
		return x == 0 || x == mazeWidth - 1 || y == 0 || y == mazeHeight - 1;
	}

	/**
	 * checks if the given cell coordinates are maze corners
	 * 
	 * @param x maze cell X coordinate
	 * @param y maze cell Y coordinate
	 * @return 'true' i given cell is a maze corner, 'false' otherwise
	 */
	public final boolean isCorner(int x, int y)
	{
		return (x == 0 && y == 0) || (x == 0 && y == mazeHeight - 1) || (x == mazeWidth - 1 && y == 0) || (x == mazeWidth - 1 && y == mazeHeight - 1);
	}

	/**
	 * prints a textual representation of the maze matrix on the screen
	 */
	public final void printMaze()
	{
		for (int y = 0; y < mazeHeight; ++y)
		{
			for (int x = 0; x < mazeWidth; ++x)
			{
				System.out.print(mazeMatrix[y][x] + " ");
			}

			System.out.println("");
		}
	}

	/**
	 * @param x maze cell X coordinate
	 * @param y maze cell Y coordinate
	 * @return
	 */
	public final char symbolAt(int x, int y)
	{
		return mazeMatrix[y][x];
	}

	/**
	 * removes the symbol
	 * 
	 * @param x maze cell X coordinate
	 * @param y maze cell Y coordinate
	 */
	protected void clearSymbol(int x, int y)
	{
		if (x >= 0 && x < mazeWidth && y >= 0 && y < mazeHeight)
		{
			mazeMatrix[y][x] = ' ';
		}
	}

	/**
	 * replaces the symbol
	 * 
	 * @param x maze cell X coordinate
	 * @param y maze cell Y coordinate
	 * @param s new symbol
	 * @see clearSymbol
	 */
	public void placeSymbol(int x, int y, char s)
	{
		if (x >= 0 && x < mazeWidth && y >= 0 && y < mazeHeight)
		{
			mazeMatrix[y][x] = s;
		}
	}

	/**
	 * given a symbol,
	 * 
	 * @param s
	 * @return coordinates of the first occurence in the maze, 'null' if symbol was not found
	 * @see placeEntity, placeSymbol
	 */
	protected Point findSymbol(char s)
	{
		for (int y = 0; y < mazeHeight; ++y)
		{
			for (int x = 0; x < mazeWidth; ++x)
			{
				if (mazeMatrix[y][x] == s)
				{
					clearSymbol(x, y);
					return new Point(x, y);
				}
			}
		}

		return null;
	}

	/**
	 * randomly places an entity (or item) on the maze matrix
	 * 
	 * @param s symbol representing the 'Entity' to be placed
	 * @return
	 */
	protected final Point placeEntity(char s)
	{
		int initialX = 0;
		int initialY = 0;

		while (mazeMatrix[initialY][initialX] != ' ')
		{
			initialX = 1 + mazeRandom.nextInt(mazeWidth - 2);
			initialY = 1 + mazeRandom.nextInt(mazeHeight - 2);
		}

		placeSymbol(initialX, initialY, s);

		return new Point(initialX, initialY);
	}
}