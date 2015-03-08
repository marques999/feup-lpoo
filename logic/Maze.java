package lpoo.logic;

import java.util.Random;

public abstract class Maze
{
	private Random m_rand;
	private int m_size;
	private char[][] m_matrix;

	// -------------------------------
	// | 		MAZE METHODS		 |
	// -------------------------------
	
	protected Maze(int n)
	{
		m_rand = new Random();
		m_size = n;
	}

	protected void setMatrix(char[][] m_matrix)
	{
		this.m_matrix = m_matrix;
	}
	
	protected final void printMaze()
	{
		for (int y = 0; y < m_size; y++)
		{
			for (int x = 0; x < m_size; x++)
			{
				System.out.print(m_matrix[y][x] + " ");
			}

			System.out.println("");
		}
	}

	// -------------------------------
	// | 		SYMBOL METHODS		 |
	// -------------------------------

	protected final char symbolAt(int x, int y)
	{
		return m_matrix[y][x];
	}
	
	protected void clearSymbol(int x, int y)
	{
		if (x >= 0 && x < m_size && y >= 0 && y < m_size)
		{
			m_matrix[y][x] = ' ';
		}
	}

	protected void placeSymbol(int x, int y, char s)
	{
		if (x >= 0 && x < m_size && y >= 0 && y < m_size)
		{
			m_matrix[y][x] = s;
		}
	}

	// -------------------------------
	// | 		ENTITY METHODS		 |
	// -------------------------------

	protected final Point placeEntity(char symbol)
	{
		int initialX = 0;
		int initialY = 0;

		while (m_matrix[initialY][initialX] != ' ')
		{
			initialX = 1 + m_rand.nextInt(m_size - 2);
			initialY = 1 + m_rand.nextInt(m_size - 2);
		}

		return new Point(initialX, initialY);
	}
}