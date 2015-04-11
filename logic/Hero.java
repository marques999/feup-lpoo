package lpoo.logic;

public final class Hero extends Entity
{
	private static final long serialVersionUID = -24716147576324592L;

	private int numberDarts = 0;
	private Item sword = null;
	private Item shield = null;

	/**
	 * default constructor for class 'Hero'
	 * @param pos initial coordinates for Player's position
	 */
	protected Hero(Point pos)
	{
		super(pos, 100);
	}

	/**
	 * checks if the player has picked up the sword
	 * @return 'true' if player has the sword; 'false' otherwise
	 */
	public final boolean hasSword()
	{
		return sword != null;
	}

	/**
	 * checks if the player has picked up darts
	 * @return returns 'true' if player has darts; 'false' otherwise
	 */
	public final boolean hasDarts()
	{
		return numberDarts > 0;
	}

	/**
	 * gets the number of darts owned by the player
	 * @return returns the number of darts owned by the player
	 */
	protected final int getNumberDarts()
	{
		return numberDarts;
	}

	/**
	 * checks if the player has picked up the shield
	 * @return 'true' if player has the shield; 'false' otherwise
	 */
	public final boolean hasShield()
	{
		return shield != null;
	}

	/**
	 * attacks an enemy dragon with darts (long-ranged weapon)
	 * @param direction the direction given by the user
	 * @param maze an object containing the game board
	 * @return returns 'true' if the target was in player's range and the attack was successful, 'false' otherwise
	 */
	public boolean attackDarts(Maze maze, Direction direction)
	{
		if (!hasDarts())
		{
			return false;
		}

		int incrementX = 0;
		int incrementY = 0;
		int positionX = pos.x;
		int positionY = pos.y;

		switch (direction)
		{
		case UP:
			incrementX = 0;
			incrementY = -1;
			break;
		case DOWN:
			incrementX = 0;
			incrementY = 1;
			break;
		case LEFT:
			incrementX = -1;
			incrementY = 0;
			break;
		case RIGHT:
			incrementX = 1;
			incrementY = 0;
			break;
		case NONE:
			return false;
		}

		for (; maze.symbolAt(positionX, positionY) != 'X'; positionX += incrementX, positionY += incrementY)
		{
			final Dragon target = GameState.dragonAt(new Point(positionX, positionY));

			if (target != null)
			{
				maze.placeSymbol(positionX, positionY, 'x');
				target.setHealth(0);
				numberDarts--;

				return true;
			}
		}

		numberDarts--;

		return false;
	}

	/**
	 * checks if the player can move in a given direction
	 * @param direction the direction given by the user
	 * @param maze an object containing the game board
	 * @return returns 'true' if player is still alive and can move to the specified direction, 'false' otherwise
	 */
	public final boolean validMove(Maze maze, Direction direction)
	{
		if (getHealth() <= 0)
		{
			return false;
		}

		final Point newPosition = new Point();

		switch (direction)
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

		return maze.symbolAt(newPosition.x, newPosition.y) != 'X';
	}

	/**
	 * checks if there is an item that can be picked up at a given position
	 * @param maze an object containing the game board
	 * @param pos coordinates for the possible location of an item
	 */
	public void pickItem(Maze maze, Point pos)
	{
		Item itemPicked = null;

		if (maze.symbolAt(pos.x, pos.y) != ' ')
		{
			itemPicked = GameState.itemAt(pos);
		}

		if (itemPicked != null)
		{
			if (itemPicked instanceof Sword)
			{
				if (!hasSword())
				{
					GameState.removeItem(itemPicked);
					sword = itemPicked;
				}
			}
			else if (itemPicked instanceof Shield)
			{
				if (!hasShield())
				{
					GameState.removeItem(itemPicked);
					shield = itemPicked;
				}
			}
			else if (itemPicked instanceof Dart)
			{
				GameState.removeItem(itemPicked);
				numberDarts++;
			}
		}
	}

	/**
	 * moves the player around the maze given a direction
	 * @param direction the direction given by the user
	 * @param maze an object containing the game board
	 */
	@Override
	public void move(Maze maze, Direction direction)
	{
		final Point newPosition = new Point();

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

			if (maze.symbolAt(newPosition.x, newPosition.y) == 'S' && !GameState.canExit())
			{
				return;
			}

			pickItem(maze, newPosition);
			maze.clearSymbol(pos.x, pos.y);
			pos = newPosition;
		}
	}

	/**
	 * checks if the player is close enough to attack an enemy dragon
	 * @param dragon an object that represents the enemy dragon
	 * @return 'true' if player has the sword and can attack the dragon; 'false' otherwise
	 */
	public final boolean canAttack(Dragon dragon)
	{
		if (getHealth() <= 0 || dragon.getHealth() <= 0 || !hasSword())
		{
			return false;
		}

		final Point dpos = dragon.getPosition();

		if (pos.x <= dpos.x + 1 && pos.x >= dpos.x - 1 && pos.y == dpos.y)
		{
			return true;
		}

		return pos.y <= dpos.y + 1 && pos.y >= dpos.y - 1 && pos.x == dpos.x;
	}

	/**
	 * attacks an enemy dragon with the sword (close-ranged weapon)
	 * @param maze an object containing the game board
	 * @param dragon an object that represents the enemy dragon
	 */
	public final void attackSword(Maze maze, Dragon dragon)
	{
		if (canAttack(dragon))
		{
			maze.placeSymbol(dragon.getX(), dragon.getY(), 'x');
			dragon.setHealth(0);
		}
	}

	/**
	 * draws the player at its corresponding position on the game board
	 * @param maze an object containing the game board
	 */
	@Override
	protected void draw(Maze maze)
	{
		if (getHealth() > 0)
		{
			if (hasShield())
			{
				maze.placeSymbol(pos.x, pos.y, hasSword() ? 'A' : 'H');
			}
			else
			{
				maze.placeSymbol(pos.x, pos.y, hasSword() ? 'a' : 'h');
			}
		}
	}
}