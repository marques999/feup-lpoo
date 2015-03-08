package lpoo.logic;

public final class Hero extends Entity
{
	private boolean sword; // tells if the player has the sword

	/**
	 * @brief default constructor for class 'Hero'
	 */
	protected Hero()
	{
		this(0, 0);
	}

	/**
	 * @brief constructor with parameters for class 'Hero'
	 * @param x initial x position for player
	 * @param y initial y position for player
	 */
	protected Hero(int x, int y)
	{
		super(x, y, 100);
	}

	/**
	 * @brief equips the player with a sword
	 */
	protected void takeSword()
	{
		sword = true;
	}

	/**
	 * @brief removes the sword from the player
	 */
	protected void removeSword()
	{
		sword = false;
	}

	/**
	 * @return returns 'true' if the player has the sword; 'false' otherwise
	 */
	protected final boolean hasSword()
	{
		return this.sword;
	}

	/**
	 * @brief checks if player can move in specified direction
	 * @param direction
	 * @param board
	 * @return returns 'true' if player is still alive and can move to the specified direction, 'false' otherwise
	 */
	private final boolean validMove(Maze maze, Direction direction)
	{
		if (this.getHealth() <= 0)
		{
			return false;
		}

		if (direction == Direction.UP && maze.symbolAt(pos.x, pos.y - 1) != 'X')
		{
			return true;
		}

		if (direction == Direction.DOWN
				&& maze.symbolAt(pos.x, pos.y + 1) != 'X')
		{
			return true;
		}

		if (direction == Direction.LEFT && maze.symbolAt(pos.x - 1, pos.y) != 'X')
		{
			return true;
		}

		if (direction == Direction.RIGHT && maze.symbolAt(pos.x + 1, pos.y) != 'X')
		{
			return true;
		}

		return false;
	}

	/**
	 * @brief checks if player is close enough to attack an enemy dragon
	 * @param dragon
	 * @param board
	 * @return returns 'true' if player has the sword and can attack the dragon; 'false' otherwise
	 */
	private final boolean canAttack(Dragon dragon)
	{
		if (this.getHealth() <= 0)
		{
			return false;
		}

		if (dragon.getHealth() <= 0)
		{
			return false;
		}

		if (!this.sword)
		{
			return false;
		}

		int dragonX = dragon.getX();
		int dragonY = dragon.getY();

		if (pos.x == dragonX && pos.y == dragonY)
		{
			return true;
		}

		if (pos.x <= dragonX + 1 && pos.x >= dragonX - 1 && pos.y == dragonY)
		{
			return true;
		}

		if (pos.y <= dragonY + 1 && pos.y >= dragonY - 1 && pos.x == dragonX)
		{
			return true;
		}

		return false;
	}

	protected final void attackDragon(Maze maze, Dragon dragon)
	{
		if (canAttack(dragon))
		{
			maze.clearSymbol(dragon.getX(), dragon.getY());
			dragon.setHealth(0);
		}
	}

	/**
	 * @brief draws a symbol on the screen representing the player
	 * @param board
	 */
	protected final void draw(Maze maze)
	{
		if (this.getHealth() > 0)
		{
			maze.placeSymbol(pos.x, pos.y, hasSword() ? 'A' : 'H');
		}
	}

	/**
	 * @brief moves the player in a specified direction
	 * @param board
	 * @param direction
	 */
	protected void move(Maze maze, Direction direction)
	{
		Point newPosition = new Point();

		char itemPicked;
		
		if (validMove(maze, direction))
		{
			switch (direction)
			{
			case UP:
				newPosition.y = pos.y - 1;
				newPosition.x = pos.x;
				break;
			case DOWN:
				newPosition.y = pos.y + 1;
				newPosition.x = pos.x;
				break;
			case LEFT:
				newPosition.y = pos.y;
				newPosition.x = pos.x - 1;
				break;
			case RIGHT:
				newPosition.y = pos.y;
				newPosition.x = pos.x + 1;
				break;
			case NONE:
				return;
			}

			itemPicked = maze.symbolAt(newPosition.x, newPosition.y);
			
			if (itemPicked == 'S' && !hasSword())
			{
				return;
			}

			switch (itemPicked)
			{
			case 'E':
				sword = true;
				break;
			}
			
			maze.clearSymbol(pos.x, pos.y);
			pos = newPosition;
		}
	}
}