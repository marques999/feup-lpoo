package lpoo.logic;

import java.io.Serializable;
import java.util.Random;

public class Maze implements Serializable
{
	private final int mazeWidth;
	private final int mazeHeight;
	private final Random mazeRandom;
	private Point mazeExit;
	private char[][] mazeMatrix;

	public Maze(int w, int h)
	{
		mazeMatrix = new char[h][w];
                mazeRandom = new Random();
		mazeWidth = w;
		mazeHeight = h;
	}

	protected void setExitPosition(Point e)
	{
		mazeExit = e;
	}

	public char[][] getMatrix()
	{
		return mazeMatrix;
	}

	public void setMatrix(char[][] m)
	{
		mazeMatrix = m;
	}

	public Point getExitPosition()
	{
		return mazeExit;
	}

	public int getWidth()
	{
		return mazeWidth;
	}

	public int getHeight()
	{
		return mazeHeight;
	}

	public final boolean isWall(int x, int y)
	{
		return x == 0 || x == mazeWidth - 1 || y == 0 || y == mazeHeight - 1;
	}

	public final boolean isCorner(int x, int y)
	{
		return (x == 0 && y == 0) || (x == 0 && y == mazeHeight - 1) || (x == mazeWidth - 1 && y == 0) || (x == mazeWidth - 1 && y == mazeHeight - 1);
	}

	protected final void printMaze()
	{
		for (int y = 0; y < mazeHeight; y++)
		{
			for (int x = 0; x < mazeWidth; x++)
			{
				System.out.print(mazeMatrix[y][x] + " ");
			}

			System.out.println("");
		}
	}

	public final char symbolAt(int x, int y)
	{
		return mazeMatrix[y][x];
	}

	protected void clearSymbol(int x, int y)
	{
		if (x >= 0 && x < mazeWidth && y >= 0 && y < mazeHeight)
		{
			mazeMatrix[y][x] = ' ';
		}
	}

	public void placeSymbol(int x, int y, char s)
	{
		if (x >= 0 && x < mazeWidth && y >= 0 && y < mazeHeight)
		{
			mazeMatrix[y][x] = s;
		}
	}
        
        protected Point findSymbol(char s)
        {
            for (int y = 0; y < mazeHeight; y++)
            {
                for (int x = 0; x < mazeWidth; x++)
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

	protected final Point placeEntity(char symbol)
	{
		int initialX = 0;
		int initialY = 0;

		while (mazeMatrix[initialY][initialX] != ' ')
		{
			initialX = 1 + mazeRandom.nextInt(mazeWidth - 2);
			initialY = 1 + mazeRandom.nextInt(mazeHeight - 2);
		}

		placeSymbol(initialX, initialY, symbol);

		return new Point(initialX, initialY);
	}
}