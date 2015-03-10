package lpoo.logic;

public final class Dragon extends Entity
{
	private boolean sleeping;

	/**
	 * @brief default constructor for class 'Dragon'
	 */
	protected Dragon()
	{
		this(0, 0);
	}

	/**
	 * @brief constructor with parameters for class 'Dragon'
	 * @param x initial X coordinate for dragon position
	 * @param y initial Y coordinate for dragon position
	 */
	protected Dragon(int x, int y)
	{
		super(x, y, 100);

		this.sleeping = false;
	}

	/**
	 * @return returns 'true' is dragon is asleep/can't move; 'false' otherwise
	 */
	protected final boolean isSleeping()
	{
		return this.sleeping;
	}

	/**
	 * @brief toggles dragon sleep status
	 */
	protected void toggleSleep()
	{
		this.sleeping = !sleeping;
	}

	/**
	 * @brief tells if the dragon move is valid
	 * @param direction the direction to move
	 */
	protected final boolean validMove(Maze maze, Direction direction)
	{
		// checks if the dragon is still alive
		if (this.getHealth() <= 0)
		{
			return false;
		}

		// checks if the dragon can go north
		if (direction == Direction.UP && maze.symbolAt(pos.x, pos.y - 1) != 'X')
		{
			return true;
		}

		// checks if the dragon can go south
		if (direction == Direction.DOWN && maze.symbolAt(pos.x, pos.y + 1) != 'X')
		{
			return true;
		}

		// checks if the dragon can move to the left
		if (direction == Direction.LEFT && maze.symbolAt(pos.x - 1, pos.y) != 'X')
		{
			return true;
		}

		// checks if the dragon can move to the right
		if (direction == Direction.RIGHT && maze.symbolAt(pos.x + 1, pos.y) != 'X')
		{
			return true;
		}

		return false;
	}

	/**
	 * @brief move the dragon
	 * @param direction the direction to move
	 */
	protected void move(Maze maze, Direction direction)
	{
		if (direction == Direction.NONE)
		{
			return;
		}

		maze.clearSymbol(pos.x, pos.y);

		if (direction == Direction.UP)
		{
			pos.y--;
		}
		else if (direction == Direction.DOWN)
		{
			pos.y++;
		} 
		else if (direction == Direction.LEFT)
		{
			pos.x--;
		} 
		else if (direction == Direction.RIGHT)
		{
			pos.x++;
		}
	}


	private final boolean isActive()
	{
		if (this.getHealth() <= 0)
		{
			return false;
		}

		if (sleeping)
		{
			return false;
		}
		
		return true;
	}
	
	/**
	 * @brief attacks the 'Hero'
	 * @param player the player entity
	 */
	private final boolean canAttack(Hero player)
	{
		if (!isActive())
		{
			return false;
		}

		if (player.getHealth() <= 0 || player.hasSword())
		{
			return false;
		}

		int playerX = player.getX();
		int playerY = player.getY();

		if ((pos.x == playerX + 1 || pos.x == playerX - 1) && pos.y == playerY)
		{
			return true;
		}

		if ((pos.y == playerY + 1 || pos.y == playerY - 1) && pos.x == playerX)
		{
			return true;
		}

		return false;
	}
	
	private final boolean canAttackFire(Hero player)
	{
		if (!isActive())
		{
			return false;
		}
		
		if (player.getHealth() <= 0 || player.hasShield())
		{
			return false;
		}
		
		int playerX = player.getX();
		int playerY = player.getY();

		if (pos.x <= playerX + 3 && pos.x >= playerX - 3 && pos.y == playerY)
		{
			return true;
		}

		if (pos.y <= playerY + 3 && pos.y >= playerY - 3 && pos.x == playerX)
		{
			return true;
		}

		return false;
	}

	protected final void attackPlayer(Maze maze, Hero player)
	{
		if (canAttack(player) || canAttackFire(player))
		{
			maze.clearSymbol(player.getX(), player.getY());
			player.setHealth(0);
		}
	}
	
	/**
	 * @brief draws the dragon on the game board
	 */
	protected final void draw(Maze maze)
	{
		if (this.getHealth() > 0)
		{
			if (sleeping)
			{
				maze.placeSymbol(pos.x, pos.y, 'd');
			} 
			else
			{
				maze.placeSymbol(pos.x, pos.y, 'D');
			}
		}
	}
}