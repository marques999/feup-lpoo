package lpoo.logic;

import java.io.Serializable;
import java.util.Random;

public class Maze implements Cloneable, Serializable
{
	private static final long serialVersionUID = 7550516997964463999L;

	private char[][] maze;
	private Point mazeExit;
	private final int mazeWidth;
	private final int mazeHeight;
	private final Random mazeRandom;

	/**
	 * default constructor for class 'Maze'
	 * @param w initial maze width (number of columns)
	 * @param h initial maze height (number of rows)
	 */
	public Maze(int w, int h)
	{
		maze = new char[h][w];
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
	 * @return returns a two-dimensional matrix representing a maze
	 */
	public char[][] getMatrix()
	{
		return maze;
	}

	/**
	 * replaces the current board matrix with a given one
	 * @param m new two-dimensional matrix representing a maze
	 */
	public void setMatrix(char[][] m)
	{
		maze = m;
	}

	/**
	 * @return returns position of the current exit
	 */
	public Point getExitPosition()
	{
		return mazeExit;
	}

	/**
	 * @return returns the maze width (number of columns)
	 */
	public int getWidth()
	{
		return mazeWidth;
	}

	/**
	 * @return returns the maze height (number of rows)
	 */
	public int getHeight()
	{
		return mazeHeight;
	}

	/**
	 * checks if position (x,y) is a maze border
	 * @param x maze cell X coordinate
	 * @param y maze cell Y coordinate
	 * @return 'true' if given positions belongs to the maze border, 'false' otherwise
	 */
	public final boolean isWall(int x, int y)
	{
		return x == 0 || x == mazeWidth - 1 || y == 0 || y == mazeHeight - 1;
	}

	/**
	 * checks if position (x,y) is a maze corner
	 * @param x maze cell X coordinate
	 * @param y maze cell Y coordinate
	 * @return 'true' if given position is a maze corner, 'false' otherwise
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
		for (char[] row : maze)
		{
			for (char column : row)
			{
				System.out.print(column + " ");
			}

			System.out.println("");
		}
	}

	/**
	 * gets the symbol at position (x,y)
	 * @param x maze cell X coordinate
	 * @param y maze cell Y coordinate
	 * @return returns the symbol found at position (x,y), ' ' character otherwise
	 */
	public final char symbolAt(int x, int y)
	{
		if (x >= 0 && x < mazeWidth && y >= 0 && y < mazeHeight)
		{
			return maze[y][x];
		}

		return ' ';
	}

	/**
	 * removes the symbol at position (x,y)
	 * @param x maze cell X coordinate
	 * @param y maze cell Y coordinate
	 */
	protected void clearSymbol(int x, int y)
	{
		if (x >= 0 && x < mazeWidth && y >= 0 && y < mazeHeight)
		{
			maze[y][x] = ' ';
		}
	}

	/**
	 * replaces the symbol at position (x,y) with a new symbol
	 * @param x maze cell X coordinate
	 * @param y maze cell Y coordinate
	 * @param s new symbol
	 */
	public void placeSymbol(int x, int y, char s)
	{
		if (x >= 0 && x < mazeWidth && y >= 0 && y < mazeHeight)
		{
			maze[y][x] = s;
		}
	}

	/**
	 * finds the first occurrence of an entity (or item) on the maze
	 * @param foundSymbol the symbol to be found
	 * @param updateMaze whether the maze should or not be updated - 'true' (finds and removes symbol from matrix), 'false' (finds only, matrix unchanged)
	 * @return coordinates of the first occurence in the maze, 'null' if symbol was not found
	 */
	public Point findSymbol(char foundSymbol, boolean updateMaze)
	{
		for (int y = 0; y < mazeHeight; ++y)
		{
			for (int x = 0; x < mazeWidth; ++x)
			{
				if (maze[y][x] == foundSymbol)
				{
					if (updateMaze)
					{
						clearSymbol(x, y);
					}

					return new Point(x, y);
				}
			}
		}

		return null;
	}

	/**
	 * randomly places an entity (or item) on the maze matrix
	 * @param mazeSymbol the symbol to be placed on the maze
	 * @param updateMaze whether the maze should or not be updated - 'true' (inserts symbol into matrix), 'false' (matrix unchanged)
	 * @return returns the coordinates where the entity was placed
	 */
	protected final Point placeEntity(char mazeSymbol, boolean updateMaze)
	{
		int initialX = 0;
		int initialY = 0;

		while (maze[initialY][initialX] != ' ')
		{
			initialX = 1 + mazeRandom.nextInt(mazeWidth - 2);
			initialY = 1 + mazeRandom.nextInt(mazeHeight - 2);
		}

		if (updateMaze)
		{
			placeSymbol(initialX, initialY, mazeSymbol);
		}

		return new Point(initialX, initialY);
	}

	/**
	 * creates a deep clone (copy) of the current maze object
	 * @throws CloneNotSupportedException if an object doesn't implement the clone method
	 */
	@Override
	public Maze clone() throws CloneNotSupportedException
	{
		super.clone();

		Maze newCopy = new Maze(mazeWidth, mazeHeight);
		newCopy.mazeExit = new Point(mazeExit.x, mazeExit.y);

		char[][] newMaze = maze.clone();

		for (int i = 0; i < maze.length; i++)
		{
			newMaze[i] = maze[i].clone();
		}

		newCopy.maze = newMaze;

		return newCopy;
	}
}