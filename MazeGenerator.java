/*!
 * \file MazeGenerator.java
 *
 * LPOO_2014-2015_P1
 * \author Diogo Marques
 * \author Pedro Melo
 *
 * \date March 2015
 *
 */

package lpoo;

import java.util.Random;

public class MazeGenerator
{
	private Random rand;
	private Point guide;

	private char[][] maze;
	private int sizeX;
	private int sizeY;

	public char[][] getMatrix()
	{
		return this.maze;
	}

	public final int getNumberRows()
	{
		return this.sizeY;
	}

	public final int getNumberColumns()
	{
		return this.sizeX;
	}

	public MazeGenerator(int dimension)
	{
		if (dimension % 2 == 0)
		{
			return;
		}

		this.sizeX = dimension;
		this.sizeY = dimension;
		this.maze = new char[sizeX][sizeY];
		this.rand = new Random();
		this.guide = new Point(0, 0);

		initializeMatrix();
		initializeGuide();
	}

	private void initializeMatrix()
	{
		for (int y = 0; y < sizeY; y++)
		{
			for (int x = 0; x < sizeX; x++)
			{
				if (x % 2 != 0 && y % 2 != 0)
				{
					maze[y][x] = ' ';
				} else
				{
					maze[y][x] = 'X';
				}
			}
		}
	}

	private void initializeGuide()
	{
		int initialX = 0;
		int initialY = 0;

		boolean exitPlaced = false;

		while (!exitPlaced)
		{
			initialX = 0;
			initialY = 0;

			while (maze[initialY][initialX] != ' ')
			{
				initialX = 1 + rand.nextInt(sizeX - 2);
				initialY = 1 + rand.nextInt(sizeY - 2);
			}

			if (isWall(initialX + 1, initialY))
			{
				maze[initialY][initialX + 1] = 'S';
				exitPlaced = true;
			} 
			else if (isWall(initialX, initialY + 1))
			{
				maze[initialY + 1][initialX] = 'S';
				exitPlaced = true;
			} 
			else if (isWall(initialX - 1, initialY))
			{
				maze[initialY][initialX - 1] = 'S';
				exitPlaced = true;
			} 
			else if (isWall(initialX, initialY - 1))
			{
				maze[initialY - 1][initialX] = 'S';
				exitPlaced = true;
			}
		}

		maze[initialY][initialX] = '+';

		guide.setX(initialX);
		guide.setY(initialY);
	}

	private boolean isWall(int x, int y)
	{
		return (x == 0 || x == sizeX - 1 || y == 0 || y == sizeY - 1);
	}
}