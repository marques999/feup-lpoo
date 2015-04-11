package lpoo.logic;

public final class Fireball extends Entity
{
	private static final long serialVersionUID = -2177424718408922946L;
	private Direction direction;

	/**
	 * default constructor for class 'Fireball'
	 * @param pos initial coordinates for fireball's position
	 */
	protected Fireball(Point pos)
	{
		super(pos, 3);

		direction = Direction.NONE;
	}

	/**
	 * gets fireball's current direction
	 * @return returns fireball's current direction
	 */
	protected Direction getDirection()
	{
		return direction;
	}

	/**
	 * sets or changes the fireball's direction
	 * @param newDirection new direction
	 */
	protected void setDirection(Direction newDirection)
	{
		direction = newDirection;
	}

	private boolean validMove(Maze maze, Direction newDirection)
	{
		final Point newPosition = new Point();

		switch (newDirection)
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

		final char mazeSymbol = maze.symbolAt(newPosition.x, newPosition.y);

		return mazeSymbol != 'X' && mazeSymbol != 'd' && mazeSymbol != 'D' && mazeSymbol != 'S';
	}

	/**
	 * draws the Dart at its corresponding position on the game board
	 * @param maze an object containing the game board
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

	/**
	 * @param maze an object containing the game board
	 * @return returns 'true' if the last movement was successful, 'false' otherwise
	 */
	protected boolean move(Maze maze)
	{
		if (!validMove(maze, direction))
		{
			return false;
		}

		if (getHealth() <= 0)
		{
			return false;
		}

		move(maze, Direction.NONE);
		draw(maze);

		return true;
	}

	/**
	 * makes a valid move in a given direction
	 * @param maze an object containing the game board
	 * @param unused (unused)
	 */
	@Override
	protected void move(Maze maze, Direction unused)
	{
		maze.clearSymbol(pos.x, pos.y);

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

		setHealth(getHealth() - 1);
	}
}