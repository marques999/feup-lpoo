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

package lpoo.logic;

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
		this.pathHistory = new Stack<Point>();
		this.rand = new Random();
		this.guide = new Point(0, 0);

		initializeMatrix();
		initializeGuide();
		initializeVisitedCells();
		generatePath();

		while (!pathHistory.empty())
		{
			generatePath();
		}
	}

	private void initializeMatrix()
	{
		for (int y = 0; y < mazeDimension; ++y)
		{
			for (int x = 0; x < mazeDimension; ++x)
			{
				if (x % 2 != 0 && y % 2 != 0)
				{
					maze[y][x] = ' ';
				} 
				else
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

<<<<<<< HEAD:logic/MazeGenerator.java
	//	maze[initialY][initialX] = '+';
=======
		maze[initialY][initialX] = '+';

>>>>>>> origin/master:MazeGenerator.java
		guide.x = initialX;
		guide.y = initialY;
	}
	
	private void initializeVisitedCells()
	{
		for (int y = 0; y < visitedCellsDimension; ++y)
		{
			for (int x = 0; x < visitedCellsDimension; ++x)
			{
				visitedCells[x][y] = '.';
			}
		}
	}

	private boolean isWall(int x, int y)
	{
		return (x == 0 || x == mazeDimension - 1 || y == 0 || y == mazeDimension - 1);
	}

	private boolean isStuck()
	{
		Point convertedGuide = convertCoordinates(guide);

		int x = convertedGuide.x;
		int y = convertedGuide.y;

		boolean canMoveLeft = false;
		boolean canMoveRight = false;
		boolean canMoveUp = false;
		boolean canMoveDown = false;

		if (x > 0)
		{
			canMoveLeft = (visitedCells[y][x - 1] != '+');
		} 

		if (x < visitedCellsDimension - 1)
		{
			canMoveRight = (visitedCells[y][x + 1] != '+');
		} 

		if (y > 0)
		{
			canMoveUp = (visitedCells[y - 1][x] != '+');
		} 

		if (y < visitedCellsDimension - 1)
		{
			canMoveDown = (visitedCells[y + 1][x] != '+');
		} 

		return (!canMoveLeft && !canMoveRight && !canMoveUp && !canMoveDown);
	}
	
	private static Point convertCoordinates(Point point)
	{
		return new Point((point.x - 1) / 2, (point.y - 1) / 2);
	}

	private boolean moveGuide(Direction direction)
	{
		Point middle = new Point();
		Point destination = new Point();

		switch (direction)
		{
		case UP:
			middle.x = guide.x;
			middle.y = guide.y - 1;
			destination.x = guide.x;
			destination.y = middle.y - 1;
			break;
		case DOWN:
			middle.x = guide.x;
			middle.y = guide.y + 1;
			destination.x = middle.x;
			destination.y = middle.y + 1;
			break;
		case LEFT:
			middle.x = guide.x - 1;
			middle.y = guide.y;
			destination.x = middle.x - 1;
			destination.y = middle.y;
			break;
		case RIGHT:
			middle.x = guide.x + 1;
			middle.y = guide.y;
			destination.x = middle.x + 1;
			destination.y = middle.y;
			break;
		case NONE:
			return false;
		}

		Point newGuide = convertCoordinates(destination);

		if (isWall(middle.x, middle.y))
		{
			return false;
		}

		if (visitedCells[newGuide.y][newGuide.x] == '+')
		{
			return false;
		}

		maze[guide.y][guide.x] = ' ';
		maze[middle.y][middle.x] = ' ';
<<<<<<< HEAD:logic/MazeGenerator.java
		guide.y = destination.y;
		guide.x = destination.x;
=======

		guide.y = destination.y;
		guide.x = destination.x;

>>>>>>> origin/master:MazeGenerator.java
		visitedCells[newGuide.y][newGuide.x] = '+';
		pathHistory.push(destination);

		return true;
	}

	private void generatePath()
	{
		while (!isStuck())
		{
			int randomMove = rand.nextInt(4);

			switch (randomMove)
			{
			case 0:
				moveGuide(Direction.UP);
				break;
			case 1:
				moveGuide(Direction.DOWN);
				break;
			case 2:
				moveGuide(Direction.LEFT);
				break;
			case 3:
				moveGuide(Direction.RIGHT);
				break;
			}
		}

		if (!pathHistory.empty())
		{
			guide = pathHistory.pop();
		}
	}
}