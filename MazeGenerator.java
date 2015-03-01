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
import java.util.Stack;

public class MazeGenerator
{
	private Random rand;
	private Point guide;
	private Stack<Point> pathHistory;
	
	private char[][] visitedCells;
	private char[][] maze;
	
	private int mazeDimension;
	private int visitedCellsDimension;

	public char[][] getMatrix()
	{
		return this.maze;
	}

	public MazeGenerator(int dimension)
	{
		if (dimension % 2 == 0)
		{
			return;
		}

		this.mazeDimension = dimension;
		this.maze = new char[mazeDimension][mazeDimension];
		this.visitedCellsDimension = (mazeDimension - 1) / 2;
		this.visitedCells = new char[visitedCellsDimension][visitedCellsDimension];
		this.rand = new Random();
		this.guide = new Point(0, 0);

		initializeMatrix();
		initializeGuide();
		generatePaths();
	}

	private void initializeMatrix()
	{
		for (int y = 0; y < mazeDimension; y++)
		{
			for (int x = 0; x < mazeDimension; x++)
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
				initialX = 1 + rand.nextInt(mazeDimension - 2);
				initialY = 1 + rand.nextInt(mazeDimension - 2);
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
	
	private void generatePaths()
	{
		Point x0 = new Point();
		
		pathHistory.push(x0);
		visitedCells[0][1] = 'X';
	}

	private boolean isWall(int x, int y)
	{
		return (x == 0 || x == mazeDimension - 1 || y == 0 || y == mazeDimension - 1);
	}
}