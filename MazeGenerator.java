package lpoo;

import java.util.Random;

public class MazeGenerator
{
	private Random rand = new Random();
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

	private void initializeGuide()
	{
		int initialX = 2;
		int initialY = 2;

		boolean exitPlaced = false;

		while (!exitPlaced)
		{
			while (maze[initialY][initialX] != ' ')
			{
				initialX = generateRandom(1, sizeX - 2);
				initialY = generateRandom(1, sizeY - 2);
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
		guideX = initialX;
		guideY = initialY;
	}

	private void placeEntity(char symbol)
	{
		int initialX = 1;
		int initialY = 1;
		
		while(maze[initialY][initialX] != ' ')
		{
			initialX = 1 + rand.nextInt(sizeX - 2);
			initialY = 1 + rand.nextInt(sizeY - 2);
		}
		
		maze[initialY][initialX] = symbol;
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