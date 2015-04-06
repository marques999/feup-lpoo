package lpoo.logic;

public final class Fireball extends Entity
{
	private static final long serialVersionUID = -2177424718408922946L;

	private Direction direction;

	/**
	 * default constructor for class 'Dart'
	 * 
	 * @param pos initial coordinates for Dart's position
	 */
	protected Fireball(Point pos)
	{
		super(pos, 3);

		direction = Direction.NONE;
	}

	protected Direction getDirection()
	{
		return direction;
	}

	protected void setDirection(Direction d)
	{
		direction = d;
	}

	protected boolean validMove(Maze maze, Direction d)
	{
		final Point newPosition = new Point();

		switch (d)
		{
		case UP:
			newPosition.x = pos.x;
			newPosition.y = pos.y - 1;
			break;
		case DOWN:
			newPosition.x = pos.x;
			newPosition.y = pos.y + 1;
			break;
		case LEFT:
			newPosition.x = pos.x - 1;
			newPosition.y = pos.y;
			break;
		case RIGHT:
			newPosition.x = pos.x + 1;
			newPosition.y = pos.y;
			break;
		case NONE:
			return false;
		}

		final boolean isNotWall = maze.symbolAt(newPosition.x, newPosition.y) != 'X';
		final boolean isNotExit = maze.symbolAt(newPosition.x, newPosition.y) != 'S';

		return isNotWall && isNotExit;
	}

	/**
	 * draws the Dart at its corresponding position
	 * 
	 * @param maze an instance of 'Maze' class
	 */
	@Override
	protected final void draw(Maze maze)
	{
		if (getHealth() > 0)
		{
			if (maze.symbolAt(pos.x, pos.y) != 'D')
			{
				maze.placeSymbol(pos.x, pos.y, 'O');
			}
		}
	}

	private void reset()
	{
		setDirection(Direction.NONE);
		setHealth(0);
	}

	protected void move(Maze maze)
	{
		move(maze, Direction.NONE);
	}
	
	@Override
	protected void move(Maze maze, Direction d)
	{
		if (!validMove(maze, direction))
		{
			reset();
		}

		if (getHealth() <= 0)
		{
			return;
		}

		switch (direction)
		{
		case UP:
			pos.y--;
			break;
		case DOWN:
			pos.y++;
			break;
		case LEFT:
			pos.x--;
			break;
		case RIGHT:
			pos.x++;
			break;
		case NONE:
			return;
		}

		maze.clearSymbol(pos.x, pos.y);
		setHealth(getHealth() - 1);
	}
}