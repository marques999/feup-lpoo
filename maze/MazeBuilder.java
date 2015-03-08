package lpoo.maze;

import java.util.Random;
import java.util.Stack;

import lpoo.logic.Direction;
import lpoo.logic.Point;

public final class MazeBuilder
{
	private Random rand;

	/**
	 * Maze Builder Parameters
	 */
	private char[][] m_matrix;
	private int m_size;
	private int m_type;

	/**
	 * Random Maze Generation
	 */
	private Point m_guide;
	private Stack<Point> m_stack;

	private char[][] visitedCells;
	private int visitedCellsDimension;

	protected MazeBuilder()
	{
		this.rand = new Random();
	}

	public char[][] getMatrix()
	{
		return this.m_matrix;
	}

	protected void setType(int type)
	{
		this.m_type = type;
	}

	protected void setSize(int size)
	{
		this.m_size = size;
	}

	protected void generateMaze()
	{
		// static maze
		if (m_type == 0)
		{
			generateStaticMaze();
		}

		// random maze
		if (m_type == 1)
		{
			// random maze dimensions must be EVEN!
			if (m_size % 2 != 0)
			{
				generateRandomMaze();
			}
		}
	}

	private void generateStaticMaze()
	{
		m_matrix = new char[][] 
		{
			{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
			{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
			{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
			{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
			{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', 'X', ' ', 'S' },
			{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
			{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
			{ 'X', ' ', 'X', 'X', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' }, 
		};
	}

	private void generateRandomMaze()
	{
		this.m_matrix = new char[m_size][m_size];
		this.visitedCellsDimension = (m_size - 1) / 2;
		this.visitedCells = new char[visitedCellsDimension][visitedCellsDimension];
		this.m_stack = new Stack<Point>();
		this.m_guide = new Point(0, 0);

		initializeMatrix();
		initializeGuide();
		initializeVisitedCells();
		generatePath();

		while (!m_stack.empty())
		{
			generatePath();
		}
	}

	private void initializeMatrix()
	{
		for (int y = 0; y < m_size; ++y)
		{
			for (int x = 0; x < m_size; ++x)
			{
				if (x % 2 != 0 && y % 2 != 0)
				{
					m_matrix[y][x] = ' ';
				}
				else
				{
					m_matrix[y][x] = 'X';
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

			while (m_matrix[initialY][initialX] != ' ')
			{
				initialX = 1 + rand.nextInt(m_size - 2);
				initialY = 1 + rand.nextInt(m_size - 2);
			}

			if (isWall(initialX + 1, initialY))
			{
				m_matrix[initialY][initialX + 1] = 'S';
				exitPlaced = true;
			}
			else if (isWall(initialX, initialY + 1))
			{
				m_matrix[initialY + 1][initialX] = 'S';
				exitPlaced = true;
			} 
			else if (isWall(initialX - 1, initialY))
			{
				m_matrix[initialY][initialX - 1] = 'S';
				exitPlaced = true;
			} 
			else if (isWall(initialX, initialY - 1))
			{
				m_matrix[initialY - 1][initialX] = 'S';
				exitPlaced = true;
			}
		}

		m_guide.x = initialX;
		m_guide.y = initialY;
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

	private final boolean isWall(int x, int y)
	{
		return (x == 0 || x == m_size - 1 || y == 0 || y == m_size - 1);
	}

	private final boolean isStuck()
	{
		Point convertedGuide = convertCoordinates(m_guide);

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

	private static final Point convertCoordinates(Point point)
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
			middle.x = m_guide.x;
			middle.y = m_guide.y - 1;
			destination.x = middle.x;
			destination.y = middle.y - 1;
			break;
		case DOWN:
			middle.x = m_guide.x;
			middle.y = m_guide.y + 1;
			destination.x = middle.x;
			destination.y = middle.y + 1;
			break;
		case LEFT:
			middle.x = m_guide.x - 1;
			middle.y = m_guide.y;
			destination.x = middle.x - 1;
			destination.y = middle.y;
			break;
		case RIGHT:
			middle.x = m_guide.x + 1;
			middle.y = m_guide.y;
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

		m_matrix[m_guide.y][m_guide.x] = ' ';
		m_matrix[middle.y][middle.x] = ' ';
		m_guide.y = destination.y;
		m_guide.x = destination.x;
		visitedCells[newGuide.y][newGuide.x] = '+';
		m_stack.push(destination);

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

		if (!m_stack.empty())
		{
			m_guide = m_stack.pop();
		}
	}
}