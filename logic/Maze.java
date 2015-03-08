/*!
 * \file Maze.java
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

public abstract class Maze
{
	private Random m_rand;
	
	protected int m_size;
	protected char[][] m;

	protected Maze(int n)
	{
		m_rand = new Random();
		m_size = n;
	}

	protected final void printMaze()
	{
		for (int y = 0; y < m_size; y++)
		{
			for (int x = 0; x < m_size; x++)
			{
				System.out.print(m[y][x] + " ");
			}

			System.out.println("");
		}
	}

	// ------------------------------
	// | SYMBOL METHODS |
	// ------------------------------

	protected void clearSymbol(int x, int y)
	{
		if (x >= 0 && x < m_size && y >= 0 && y < m_size)
		{
			m[y][x] = ' ';
		}
	}

	protected void placeSymbol(int x, int y, char s)
	{
		if (x >= 0 && x < m_size && y >= 0 && y < m_size)
		{
			m[y][x] = s;
		}
	}

	// ------------------------------
	// | MAZE METHODS |
	// ------------------------------

	protected final Point placeEntity(char symbol)
	{
		int initialX = 0;
		int initialY = 0;

		while (m[initialY][initialX] != ' ')
		{
			initialX = 1 + m_rand.nextInt(m_size - 2);
			initialY = 1 + m_rand.nextInt(m_size - 2);
		}

		return new Point(initialX, initialY);
	}
}