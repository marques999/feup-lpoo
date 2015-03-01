package lpoo;

import java.util.Random;
import java.util.Stack;

public class MazeGenerator
{
	
	private class Point
	{
		private int x;	// the x coordinate
		private int y;	// the y coordinate
		
		// default class constructor
		public Point()
		{
			this(0,0);
		}
		
		// class constructor
		public Point(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
		
		public final int getX(){return x;}
		public final int getY(){return y;}
		public void setX(int x){this.x = x;}
		public void setY(int y){this.y = y;}
	}
	
	private Random rand = new Random();
	private Stack pathHistory;
	private char[][] maze;
	private int guideX;
	private int guideY;
	private int sizeX;
	private int sizeY;

	public MazeGenerator(int dimension)
	{
		if (dimension % 2 == 0)
		{
			return;
		}
		
		this.sizeX = dimension;
		this.sizeY = dimension;
		this.maze = new char[sizeX][sizeY];

		initializeMatrix();
		initializeGuide();
		
		placeEntity('E');
		placeEntity('D');
		placeEntity('H');
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
				}
				else 
				{
					maze[y][x] = 'X';
				}
			}
		}
	}

	// JÁ UTILIZA Point
	private void initializeGuide()
	{
		Point initialPoint = new Point();	// (0,0)
		
		boolean exitPlaced = false;
		
		while (!exitPlaced)
		{	
			while (maze[initialPoint.getY()][initialPoint.getX()] != ' ')
			{
				initialPoint.setX(generateRandom(1, sizeX - 2));
				initialPoint.setY(generateRandom(1, sizeY - 2));
			}

			if (isWall(initialPoint.getX() + 1, initialPoint.getY()))
			{
				maze[initialPoint.getY()][initialPoint.getX() + 1] = 'S';
				exitPlaced = true;
			} 
			else if (isWall(initialPoint.getX(), initialPoint.getY() + 1))
			{
				maze[initialPoint.getY() + 1][initialPoint.getX()] = 'S';
				exitPlaced = true;
			} 
			else if (isWall(initialPoint.getX() - 1, initialPoint.getY()))
			{
				maze[initialPoint.getY()][initialPoint.getX() - 1] = 'S';
				exitPlaced = true;
			} 
			else if (isWall(initialPoint.getX(), initialPoint.getY() - 1))
			{
				maze[initialPoint.getY() - 1][initialPoint.getX()] = 'S';
				exitPlaced = true;
			}
		}
		
		maze[initialPoint.getY()][initialPoint.getX()] = '+';
		guideX = initialPoint.getX();
		guideY = initialPoint.getY();
	}

	//JÁ UTILIZA Point
	private void placeEntity(char symbol)
	{
		Point initialPoint = new Point(1,1);
		
		while(maze[initialPoint.getY()][initialPoint.getX()] != ' ')
		{
			initialPoint.setX(1 + rand.nextInt(sizeX - 2));
			initialPoint.setY(1 + rand.nextInt(sizeY - 2));
		}
		
		maze[initialPoint.getY()][initialPoint.getX()] = symbol;
	}
	
	
	private boolean isWall(int x, int y)
	{
		return (x == 0 || x == sizeX - 1 || y == 0 || y == sizeY - 1);
	}

	private int generateRandom(int lowerLimit, int upperLimit)
	{
		return (Math.abs(rand.nextInt()) % (upperLimit - lowerLimit) + 1);
	}

	public final void printMaze()
	{
		for (int y = 0; y < sizeY; y++)
		{
			for (int x = 0; x < sizeX; x++)
			{
				System.out.print(maze[y][x] + " ");
			}

			System.out.println("");
		}
	}
}