package lpoo.gui;

import lpoo.logic.Maze;
import lpoo.logic.Point;

public class GUIValidation
{
	protected static boolean checkBoundaries(Maze m)
	{
		int mazeWidth = m.getWidth();
		int mazeHeight = m.getHeight();
		boolean foundExit = false;

		for (int x = 0; x < mazeWidth; x++)
		{
			if (m.symbolAt(x, 0) != 'X' && m.symbolAt(x, 0) != 'S')
			{
				return false;
			}

			if (m.symbolAt(x, mazeHeight - 1) != 'X' && m.symbolAt(x, mazeHeight - 1) != 'S')
			{
				return false;
			}

			if (m.symbolAt(x, 0) == 'S' || m.symbolAt(x, mazeHeight - 1) == 'S')
			{
				foundExit = true;
			}
		}

		for (int y = 0; y < mazeHeight; y++)
		{
			if (m.symbolAt(0, y) != 'X' && m.symbolAt(0, y) != 'S')
			{
				return false;
			}

			if (m.symbolAt(mazeWidth - 1, y) != 'X' && m.symbolAt(mazeWidth - 1, y) != 'S')
			{
				return false;
			}

			if (m.symbolAt(0, y) == 'S' || m.symbolAt(mazeWidth - 1, y) == 'S')
			{
				foundExit = true;
			}
		}

		return foundExit;
	}

	protected static boolean checkExit(Maze maze)
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
		final char[][] c = m.clone();

		for (int i = 0; i < m.length; i++)
		{
			c[i] = m[i].clone();
		}

		return c;
	}
}