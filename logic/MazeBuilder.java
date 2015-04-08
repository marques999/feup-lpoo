package lpoo.logic;

import java.util.Random;
import java.util.Stack;

public final class MazeBuilder
{
	protected static final int STATIC_MAZE = 0;
	protected static final int RANDOM_MAZE = 1;
	private final Random rand;

	/**
	 * Maze Builder Parameters
	 */
	private char[][] maze;
	private int mazeWidth;
	private int mazeHeight;
	private int mazeType;

	/**
	 * Random Maze Generation
	 */
	private Point m_guide;
	private Point m_exit;
	private Stack<Point> m_stack;

	private char[][] visitedCells;
	private int visitedCellsWidth;
	private int visitedCellsHeight;

	public MazeBuilder()
	{
		rand = new Random();
	}

	public char[][] getMatrix()
	{
		return maze;
	}

	public void setType(int type)
	{
		mazeType = type;
	}

	public void setWidth(int width)
	{
		mazeWidth = width;
	}

	public void setHeight(int height)
	{
		mazeHeight = height;
	}

	protected void generateMaze()
	{
		if (mazeWidth == 0 || mazeHeight == 0)
		{
			throw new IllegalArgumentException();
		}

		if (mazeType == STATIC_MAZE)
		{
			generateStaticMaze();
		}
		else if (mazeType == RANDOM_MAZE)
		{
			if (mazeWidth % 2 != 0 && mazeHeight % 2 != 0)
			{
				generateRandomMaze();
			}
			else
			{
				throw new IllegalArgumentException();
			}
		}
	}

	protected Point getExitPosition()
	{
		return m_exit;
	}

	private void generateStaticMaze()
	{
		maze = new char[][] 
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

		m_exit = new Point(9, 5);
	}

	private void generateRandomMaze()
	{
		maze = new char[mazeHeight][mazeWidth];
		visitedCellsWidth = (mazeWidth - 1) / 2;
		visitedCellsHeight = (mazeHeight - 1) / 2;
		visitedCells = new char[visitedCellsHeight][visitedCellsWidth];
		m_stack = new Stack<>();
		m_guide = new Point(0, 0);

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
		for (int y = 0; y < mazeHeight; ++y)
		{
			if (y % 2 == 0)
			{
				for (int x = 0; x < mazeWidth; ++x)
				{
					maze[y][x] = 'X';
				}
			}
			else
			{
				for (int x = 0; x < mazeWidth; ++x)
				{
					if (x % 2 != 0)
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
				initialX = 1 + rand.nextInt(mazeWidth - 2);
				initialY = 1 + rand.nextInt(mazeHeight - 2);
			}

			if (isWall(initialX + 1, initialY))
			{
				maze[initialY][initialX + 1] = 'S';
				m_exit = new Point(initialX + 1, initialY);
				exitPlaced = true;
			}
			else if (isWall(initialX, initialY + 1))
			{
				maze[initialY + 1][initialX] = 'S';
				m_exit = new Point(initialX, initialY + 1);
				exitPlaced = true;
			}
			else if (isWall(initialX - 1, initialY))
			{
				maze[initialY][initialX - 1] = 'S';
				m_exit = new Point(initialX - 1, initialY);
				exitPlaced = true;
			}
			else if (isWall(initialX, initialY - 1))
			{
				maze[initialY - 1][initialX] = 'S';
				m_exit = new Point(initialX, initialY - 1);
				exitPlaced = true;
			}
		}

		m_guide.x = initialX;
		m_guide.y = initialY;

	}

	private void initializeVisitedCells()
	{
		for (int y = 0; y < visitedCellsHeight; ++y)
		{
			for (int x = 0; x < visitedCellsWidth; ++x)
			{
				visitedCells[y][x] = '.';
			}
		}
	}

	private boolean isWall(int x, int y)
	{
		return x == 0 || x == mazeWidth - 1 || y == 0 || y == mazeHeight - 1;
	}

	private boolean isStuck()
	{
		final Point convertedGuide = convertCoordinates(m_guide);
		final int x = convertedGuide.x;
		final int y = convertedGuide.y;

		boolean canMoveLeft = false;
		boolean canMoveRight = false;
		boolean canMoveUp = false;
		boolean canMoveDown = false;

		if (x > 0)
		{
			canMoveLeft = visitedCells[y][x - 1] != '+';
		}

		if (x < visitedCellsWidth - 1)
		{
			canMoveRight = visitedCells[y][x + 1] != '+';
		}

		if (y > 0)
		{
			canMoveUp = visitedCells[y - 1][x] != '+';
		}

		if (y < visitedCellsHeight - 1)
		{
			canMoveDown = visitedCells[y + 1][x] != '+';
		}

		return !canMoveLeft && !canMoveRight && !canMoveUp && !canMoveDown;
	}

	private static Point convertCoordinates(Point point)
	{
		return new Point((point.x - 1) / 2, (point.y - 1) / 2);
	}

	private boolean moveGuide(Direction direction)
	{
		final Point middle = new Point();
		final Point destination = new Point();

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

		final Point newGuide = convertCoordinates(destination);

		if (isWall(middle.x, middle.y))
		{
			return false;
		}

		if (visitedCells[newGuide.y][newGuide.x] == '+')
		{
			return false;
		}

		maze[m_guide.y][m_guide.x] = ' ';
		maze[middle.y][middle.x] = ' ';
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
			final int randomMove = rand.nextInt(4);

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