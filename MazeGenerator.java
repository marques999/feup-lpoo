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

import java.util.Arrays;
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
		if (dimension < 3 || dimension % 2 == 0)
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
		
		while (!isFull())
		{
			generatePath();
		}
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
			} else if (isWall(initialX, initialY + 1))
			{
				maze[initialY + 1][initialX] = 'S';
				exitPlaced = true;
			} else if (isWall(initialX - 1, initialY))
			{
				maze[initialY][initialX - 1] = 'S';
				exitPlaced = true;
			} else if (isWall(initialX, initialY - 1))
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
		return (x == 0 || x == mazeDimension - 1 || y == 0 || y == mazeDimension - 1);
	}

	private boolean isWall(Point point)
	{
		int x = point.getX();
		int y = point.getY();

		return (x == 0 || x == mazeDimension - 1 || y == 0 || y == mazeDimension - 1);
	}

	// TUDO O QUE ESTÁ PARA BAIXO FOI O QUE FIZ AGORA

	private Point convertCoordinates(Point point)
	{
		return new Point((point.getX() - 1) / 2, (point.getY() - 1) / 2);
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

		Point initialGuide = convertCoordinates(guide);

		pathHistory.push(initialGuide);
		visitedCells[initialGuide.getX()][initialGuide.getY()] = '+';
	}

	private void printVisitedCells()
	{
		for (int y = 0; y < visitedCellsDimension; ++y)
		{
			for (int x = 0; x < visitedCellsDimension; x++)
			{
				System.out.print(visitedCells[y][x] + " ");
			}
			
			System.out.println("");
		}
	}
	// TELL IF VERIFIED CELLS IS FULL
	private boolean isFull()
	{
		for (int y = 0; y < visitedCellsDimension; ++y)
		{
			for (int x = 0; x < visitedCellsDimension; ++x)
			{
				if (visitedCells[y][x] == '.')
				{
					return false;
				}
			}
		}

		return true;
	}
	
	private boolean isStuck()
	{
		Point convertedGuide = convertCoordinates(guide);
	
		int x = convertedGuide.getX();
		int y = convertedGuide.getY();
	//	int df = 0;
		boolean canMoveLeft;
		boolean canMoveRight;
		boolean canMoveUp;
		boolean canMoveDown;
		
		// verify if movement is possible	
		if (x > 0)
		{
			canMoveLeft = (visitedCells[y][x-1] != '+');
		}
		else
		{
			canMoveLeft = false;
		}
		
		if (x < visitedCellsDimension - 1)
		{
			canMoveRight = (visitedCells[y][x+1] != '+');
		}
		else
		{
			canMoveRight = false;
		}
		
		if (y > 0)
		{
			canMoveUp = (visitedCells[y-1][x] != '+');
		}
		else 
		{
			canMoveUp = false;
		}
		
		if (y < visitedCellsDimension - 1)
		{
			canMoveDown = (visitedCells[y+1][x] != '+');
		}
		else
		{
			canMoveDown = false;
		}

		return (!canMoveLeft && !canMoveRight && !canMoveUp && !canMoveDown);
	}

	private boolean moveGuide(Direction direction)
	{
		Point middle = new Point();
		Point destination = new Point();

		switch (direction)
		{
		case UP:
			middle.setX(guide.getX());
			middle.setY(guide.getY() - 1);
			destination.setX(guide.getX());
			destination.setY(middle.getY() - 1);
			break;
		case DOWN:
			middle.setX(guide.getX());
			middle.setY(guide.getY() + 1);
			destination.setX(middle.getX());
			destination.setY(middle.getY() + 1);
			break;
		case LEFT:
			middle.setX(guide.getX() - 1);
			middle.setY(guide.getY());
			destination.setX(middle.getX() - 1);
			destination.setY(middle.getY());
			break;
		case RIGHT:
			middle.setX(guide.getX() + 1);
			middle.setY(guide.getY());
			destination.setX(middle.getX() + 1);
			destination.setY(middle.getY());
			break;
		case NONE:
			return false;
		}

		Point newGuide = convertCoordinates(destination);

		// verify if movement is possible
		if (isWall(middle.getX(), middle.getY()))
		{
			return false;
		}

		if (visitedCells[newGuide.getY()][newGuide.getX()] == '+')
		{
			return false;
		}

		maze[guide.getY()][guide.getX()] = ' ';
		maze[middle.getY()][middle.getX()] = ' ';

		guide.setY(destination.getY());
		guide.setX(destination.getX());
		
		visitedCells[newGuide.getY()][newGuide.getX()] = '+';
		pathHistory.push(destination);

		return true;
	}

	private void generatePath()
	{
		int randomMove;

		while (!isStuck())
		{
			randomMove = rand.nextInt(4);
			
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