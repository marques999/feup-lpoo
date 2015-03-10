package lpoo.logic;

public final class Hero extends Entity
{
	private Item sword;
	private Item dart;
	private Item shield;
	
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
	 * @return returns 'true' if the player has the sword; 'false' otherwise
	 */
	protected final boolean hasSword()
	{
		return (this.sword != null);
	}

	/**
	 * @return returns 'true' if the player has darts; 'false' otherwise
	 */
	protected final boolean hasDarts()
	{
		return (this.dart != null);
	}
	
	/**
	 * @return returns 'true' if player has the shield; 'false' otherwise
	 */
	protected final boolean hasShield()
	{
		return (this.shield != null);
	}

	protected boolean attackDarts(Maze maze, Direction direction)
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
			Dragon target = GameState.dragonAt(positionX, positionY);

			if (target != null)
			{
				maze.placeSymbol(positionX, positionY, '*');
				target.setHealth(0);
				dart = null;
				
				return true;
			}
		}

		dart = null;
		
		return false;
	}

	/**
	 * @brief checks if player can move in specified direction
	 * @param direction
	 * @param board
	 * @return returns 'true' if player is still alive and can move to the
	 *         specified direction, 'false' otherwise
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

		if (direction == Direction.DOWN && maze.symbolAt(pos.x, pos.y + 1) != 'X')
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

	protected void pickItem(Maze maze, Point pos)
	{
		Item itemPicked = null;
		
		if (maze.symbolAt(pos.x, pos.y) != ' ')
		{
			itemPicked = GameState.itemAt(pos);
		}

		if (itemPicked != null)
		{
			switch (itemPicked.type())
			{
			case 'E': // SWORD
				
				if (!hasSword())
				{
					itemPicked.setOwner(this);
					sword = itemPicked;
				}

				break;
				
			case '*': // DARTS
				
				if (!hasDarts())
				{	
					itemPicked.setOwner(this);
					dart = itemPicked;
				}
				
				break;
				
			case 'V': // SHEILD
				
				if (!hasShield())
				{
					itemPicked.setOwner(this);
					shield = itemPicked;
				}
				
				break;
			}
		}
	}
	
	protected void move(Maze maze, Direction direction)
	{
		Point newPosition = new Point();

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

			if (maze.symbolAt(newPosition.x, newPosition.y) == 'S' && !hasSword())
			{
				return;
			}

			pickItem(maze, newPosition);
			maze.clearSymbol(pos.x, pos.y);
			pos = newPosition;
		}
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

		if (!hasSword() && !hasDarts())
		{
			return false;
		}

		if (pos.equals(dragon.getPosition()))
		{
			return true;
		}

		int dragonX = dragon.getX();
		int dragonY = dragon.getY();

		if ((pos.x == dragonX + 1 || pos.x == dragonX - 1) && pos.y == dragonY)
		{
			return true;
		}

		if ((pos.y == dragonY + 1 || pos.y == dragonY - 1) && pos.x == dragonX)
		{
			return true;
		}

		return false;
	}

	protected final void attackSword(Maze maze, Dragon dragon)
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
		if (getHealth() > 0)
		{
			if (hasShield())
			{
				maze.placeSymbol(pos.x, pos.y, hasSword() ? 'A' : 'H');
			}
			else
			{
				maze.placeSymbol(pos.x, pos.y, hasSword() ? 'A' : 'h');
			}
		}
	}
}