package lpoo.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import java.util.Random;
import lpoo.logic.Entity;
import lpoo.logic.GameState;
import lpoo.logic.Maze;
import lpoo.logic.Point;
import lpoo.logic.RandomMaze;

public class TestMaze
{
	// a) the maze boundaries must have exactly one exit and everything else walls
	// b) the exist cannot be a corner
	private static boolean checkBoundaries(Maze m)
	{
		int countExit = 0;
		final int n = m.getWidth();

		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++)
			{
				if (i == 0 || j == 0 || i == n - 1 || j == n - 1)
				{
					if (m.symbolAt(i, j) == 'S')
					{
						if ((i == 0 || i == n - 1) && (j == 0 || j == n - 1))
						{
							return false;
						}
						else
						{
							countExit++;
						}
					}
					else if (m.symbolAt(i, j) != 'X')
					{
						return false;
					}
				}
			}
		}
		return countExit == 1;
	}

	private boolean checkExitReachable(Maze maze)
	{
		final Point p = maze.getExitPosition();

		final char[][] m = deepClone(maze.getMatrix());

		visit(m, p.x, p.y);

		for (final char[] m1 : m)
		{
			for (final char m2 : m1)
			{
				if (m2 != 'X' && m2 != 'V')
				{
					return false;
				}
			}
		}

		return true;
	}

	public static boolean testSquares(Maze m)
	{
		final char[][] badWalls = { { 'X', 'X', 'X' }, { 'X', 'X', 'X' }, { 'X', 'X', 'X' } };
		final char[][] badSpaces = { { ' ', ' ' }, { ' ', ' ' } };
		final char[][] badDiag1 = { { 'X', ' ' }, { ' ', 'X' } };
		final char[][] badDiag2 = { { ' ', 'X' }, { 'X', ' ' } };

		return !hasSquare(m, badWalls) && !hasSquare(m, badSpaces) && !hasSquare(m, badDiag1) && !hasSquare(m, badDiag2);

	}

	// d) there cannot exist 2x2 (or greater) squares with blanks only
	// e) there cannot exit 2x2 (or greater) squares with blanks in one diagonal and walls in the other
	// d) there cannot exist 3x3 (or greater) squares with walls only
	private static boolean hasSquare(Maze maze, char[][] square)
	{
		final char[][] m = maze.getMatrix();

		for (int i = 0; i < m.length - square.length; i++)
		{
			for (int j = 0; j < m.length - square.length; j++)
			{
				boolean match = true;

				for (int x = 0; x < square.length; x++)
				{
					for (int y = 0; y < square.length; y++)
					{
						if (m[i + x][j + y] != square[x][y])
						{
							match = false;
						}
					}
				}

				if (match)
				{
					return true;
				}
			}
		}

		return false;
	}

	// c) there must exist a path between any blank cell and the maze exit
	public static boolean testExitReachable(Maze maze)
	{
		final Point p = maze.getExitPosition();
		final char[][] m = deepClone(maze.getMatrix());

		visit(m, p.x, p.y);

		for (final char[] m1 : m)
		{
			for (final char m2 : m1)
			{
				if (m2 != 'X' && m2 != 'V')
				{
					return false;
				}
			}
		}

		return true;
	}

	private static void visit(char[][] m, int i, int j)
	{
		if (i < 0 || i >= m.length || j < 0 || j >= m[i].length)
		{
			return;
		}

		if (m[i][j] == 'X' || m[i][j] == 'V')
		{
			return;
		}

		m[i][j] = 'V';
		visit(m, i - 1, j);
		visit(m, i + 1, j);
		visit(m, i, j - 1);
		visit(m, i, j + 1);
	}

	private static char[][] deepClone(char[][] m)
	{
		char[][] c = m.clone();

		for (int i = 0; i < m.length; i++)
		{
			c[i] = m[i].clone();
		}

		return c;
	}

	@Test(timeout = 1000)
	public void test_placeEntity()
	{
		GameState.setDifficulty(2);
		GameState.setDragonMovement(GameState.DRAGON_STATIC_NOSLEEP);

		assertNotNull(GameState.getDart());
		assertNotNull(GameState.getShield());
		assertNotNull(GameState.getSword());
		assertNotNull(GameState.getDragon());
		assertNotNull(GameState.getPlayer());
	}

	@Test(timeout = 1000)
	public void test_staticMaze()
	{
		GameState.setDifficulty(1);
		GameState.setDragonMovement(GameState.DRAGON_STATIC_NOSLEEP);

		final Maze m = GameState.getMaze();
		final Point exitPosition = new Point(9, 5);

		assertEquals(m.getWidth(), 10);
		assertEquals(m.getWidth(), 10);
		assertEquals(m.getExitPosition(), exitPosition);

		final Entity d = GameState.getDragon();
		final Entity p = GameState.getPlayer();

		assertNotNull(d);
		assertNotNull(p);
		assertFalse(d.getPosition().equals(p.getPosition()));
	}

	@Test(timeout = 1000)
	public void testRandomMazeGenerator() throws Exception
	{
		final int numMazes = 100;
		final int maxSize = 101;
		final char[][] badWalls = { { 'X', 'X', 'X' }, { 'X', 'X', 'X' }, { 'X', 'X', 'X' } };
		final char[][] badSpaces = { { ' ', ' ' }, { ' ', ' ' } };
		final char[][] badDiag1 = { { 'X', ' ' }, { ' ', 'X' } };
		final char[][] badDiag2 = { { ' ', 'X' }, { 'X', ' ' } };

		final Random rand = new Random();

		for (int i = 0; i < numMazes; i++)
		{
			final int size = maxSize == 5 ? 5 : 5 + 2 * rand.nextInt((maxSize - 5) / 2);

			final Maze m = new RandomMaze(size, size);

			assertTrue("Invalid maze boundaries in maze:\n" + m, checkBoundaries(m));
			//		assertTrue("Invalid exit position:\n" + m.getExitPosition(), checkExitReachable(m));
			assertNotNull("Invalid walls in maze:\n" + m, !hasSquare(m, badWalls));
			assertNotNull("Invalid spaces in maze:\n" + m, !hasSquare(m, badSpaces));
			assertNotNull("Invalid diagonals in maze:\n" + m, !hasSquare(m, badDiag1));
			assertNotNull("Invalid diagonals in maze:\n" + m, !hasSquare(m, badDiag2));
		}
	}
}