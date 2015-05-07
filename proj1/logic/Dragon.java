package lpoo.logic;

public final class Dragon extends Entity
{
	private static final long serialVersionUID = -5875613832655200186L;
	private boolean sleeping;
	private Fireball fireball;

	/**
	 * default constructor for class 'Dragon'
	 * @param pos initial coordinates for dragon's position
	 */
	protected Dragon(Point pos)
	{
		super(pos, 100);

		sleeping = false;
		fireball = null;
	}

	/**
	 * checks if the dragon is sleeping
	 * @return 'true' is dragon is asleep; 'false' otherwise
	 */
	public final boolean isSleeping()
	{
		return sleeping;
	}

	/**
	 * checks if the fireball thrown by the dragon is still moving
	 * @return returns 'true' if the fireball exists
	 */
	public final boolean hasFireball()
	{
		return fireball != null;
	}

	/**
	 * toggles dragon sleep status (puts him to sleep/wakes him up)
	 */
	protected void toggleSleep()
	{
		sleeping = !sleeping;
	}

	/**
	 * checks if the dragon can move in a given direction
	 * @return 'true' if the dragon is active and the destination cell is not a wall nor an exit, 'false', otherwise
	 * @param maze an object containing the game board
	 * @param direction the direction to move the dragon
	 */
	protected final boolean validMove(Maze maze, Direction direction)
	{
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

		final char mazeSymbol = maze.symbolAt(newPosition.x, newPosition.y);

		return mazeSymbol != 'X' && mazeSymbol != 'S';
	}

	/**
	 * makes a valid move in a given direction
	 * @param maze an object containing the game board
	 * @param direction the direction to move the dragon
	 */
	@Override
	protected void move(Maze maze, Direction direction)
	{
		if (direction != Direction.NONE)
		{
			maze.clearSymbol(pos.x, pos.y);

			if (direction == Direction.UP)
			{
				if (maze.symbolAt(pos.x, pos.y - 1) != 'D' && maze.symbolAt(pos.x, pos.y - 1) != 'd')
				{
					pos.y--;
				}
			}
			else if (direction == Direction.DOWN)
			{
				if (maze.symbolAt(pos.x, pos.y + 1) != 'D' && maze.symbolAt(pos.x, pos.y + 1) != 'd')
				{
					pos.y++;
				}
			}
			else if (direction == Direction.LEFT)
			{
				if (maze.symbolAt(pos.x - 1, pos.y) != 'D' && maze.symbolAt(pos.x - 1, pos.y) != 'd')
				{
					pos.x--;
				}
			}
			else if (direction == Direction.RIGHT)
			{
				if (maze.symbolAt(pos.x + 1, pos.y) != 'D' && maze.symbolAt(pos.x + 1, pos.y) != 'd')
				{
					pos.x++;
				}
			}
		}
	}

	/**
	 * checks if the dragon is active and ready to attack
	 * @return returns 'true' if the dragon is still alive and is not sleeping
	 */
	private boolean isActive()
	{
		return getHealth() > 0 && !isSleeping();
	}

	/**
	 * checks if the dragon can attack the player with close range attack
	 * @param player the target player entity
	 * @return 'true' if the dragon is active and the player's position is adjacent to the dragon
	 */
	protected final boolean canAttack(Hero player)
	{
		if (!isActive() || player == null || player.getHealth() <= 0 || player.hasSword())
		{
			return false;
		}

		final Point hero = player.getPosition();

		if (pos.x <= hero.x + 1 && pos.x >= hero.x - 1 && pos.y == hero.y)
		{
			return true;
		}

		return pos.y <= hero.y + 1 && pos.y >= hero.y - 1 && pos.x == hero.x;
	}

	/**
	 * checks if the dragon can throw a fireball at the player
	 * @param maze an object containing the game board
	 * @param player an entity representing the player
	 * @return 'true' is dragon is active and player is in sight, 'false' otherwise
	 */
	protected final boolean throwFireball(Maze maze, Hero player)
	{
		if (hasFireball())
		{
			return false;
		}

		if (!isActive() || player == null || player.getHealth() <= 0)
		{
			return false;
		}

		final Point fireballPosition = new Point(pos.x, pos.y);

		fireball = new Fireball(fireballPosition);

		if (player.pos.y == pos.y && Math.abs(player.pos.x - pos.x) <= 3)
		{
			fireball.setDirection(player.pos.x < pos.x ? Direction.LEFT : Direction.RIGHT);
		}
		else if (player.pos.x == pos.x && Math.abs(player.pos.y - pos.y) <= 3)
		{
			fireball.setDirection(player.pos.x < pos.x ? Direction.UP : Direction.DOWN);
		}
		else
		{
			return false;
		}

		return true;
	}

	protected void updateFireball(Maze maze, Hero player)
	{
		if (!hasFireball())
		{
			return;
		}

		if (fireball.move(maze))
		{
			if (fireball.getPosition().equals(player.getPosition()) && !player.hasShield())
			{
				maze.placeSymbol(player.getX(), player.getY(), 'O');
				player.setHealth(0);
			}
		}
		else
		{
			maze.clearSymbol(fireball.getX(), fireball.getY());
			fireball = null;
		}
	}

	/**
	 * attacks the player with short-range attack (claws)
	 * @param maze an object containing the game board
	 * @param player the target player
	 */
	protected final void attack(Maze maze, Hero player)
	{
		if (canAttack(player))
		{
			maze.placeSymbol(player.getX(), player.getY(), 'x');
			player.setHealth(0);
		}
	}

	/**
	 * draws the dragon at its corresponding position on the game board
	 * @param maze an object containing the game board
	 */
	@Override
	protected void draw(Maze maze)
	{
		if (getHealth() > 0)
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
		else
		{
			maze.placeSymbol(pos.x, pos.y, 'x');
		}
	}
}