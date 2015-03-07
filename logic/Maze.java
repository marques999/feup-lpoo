/*!
 * \file Board.java
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
	protected int NUM_COLUNAS = 11;
	protected int NUM_LINHAS = 11;
	
	private Random randomGenerator;
	
	public char[][] m;
	
	public Maze(int n)
	{
		randomGenerator = new Random();
		NUM_COLUNAS = n;
		NUM_LINHAS = n;
	}

	public final int getNumberRows()
	{
		return NUM_LINHAS;
	}
	
	public final int getNumberColumns()
	{
		return NUM_COLUNAS;
	}
	
	public final void printMaze() 
	{
		for (int y = 0; y < NUM_COLUNAS; y++) 
		{
			for (int x = 0; x < NUM_LINHAS; x++) 
			{
				System.out.print(m[y][x] + " ");
			}

			System.out.println("");
		}
	}
	
	// ------------------------------
	// |	SYMBOL METHODS		 	|
	// ------------------------------
	
	public void clearSymbol(int x, int y) 
	{
		if (x >= 0 && x < NUM_COLUNAS && y >= 0 && y < NUM_LINHAS)
		{
			m[y][x] = ' ';
		}
	}
	
	public void placeSymbol(int x, int y, char s)
	{
		if (x >= 0 && x < NUM_COLUNAS && y >= 0 && y < NUM_LINHAS)
		{
			m[y][x] = s;
		}
	}
	
	// ------------------------------
	// |	   MAZE  METHODS	 	|
	// ------------------------------
	
	protected final Point placeEntity(char symbol)
	{
		int initialX = 0;
		int initialY = 0;
				
		while (m[initialY][initialX] != ' ')
		{
			initialX = 1 + randomGenerator.nextInt(NUM_COLUNAS - 2);
			initialY = 1 + randomGenerator.nextInt(NUM_LINHAS - 2);
		}
				
		return new Point(initialX, initialY);
	}
}
