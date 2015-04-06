package lpoo.logic;

public final class Dragon extends Entity
{
	private static final long serialVersionUID = -5875613832655200186L;
	private boolean sleeping;
	private Fireball fireball;

	/**
	 * default constructor for class 'Dragon'
	 *
	 * @param pos initial coordinates for Dragon's position
	 */
	protected Dragon(Point pos)
	{
		super(pos, 100);

		sleeping = false;
		fireball = null;
	}

	/**
	 * constructor with parameters for class 'Dragon'
	 *
	 * @param x initial X coordinate for dragon position
	 * @param y initial Y coordinate for dragon position
	 */
	protected Dragon(int x, int y)
	{
		this(new Point(x, y));
	}

	/**
	 * @return 'true' is dragon is asleep; 'false' otherwise
	 */
	public final boolean isSleeping()
	{
		return sleeping;
	}
	
	protected Fireball getFireball()
	{
		return fireball;
	}
	
	protected void setFireball(Fireball fireball)
	{
		this.fireball = fireball;
	}

	/**
	 * toggles dragon sleep status (puts him to sleep/wakes him up)
	 */
	protected void toggleSleep()
	{
		sleeping = !sleeping;
	}

	/**
	 * checks if the dragon can move in the given direction
	 *
	 * @return 'true' if the dragon is active and the destination cell is not a wall nor an exit, 'false', otherwise
	 * @param maze game board
	 * @param direction the direction to move
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

		final boolean isNotWall = maze.symbolAt(newPosition.x, newPosition.y) != 'X';
		final boolean isNotExit = maze.symbolAt(newPosition.x, newPosition.y) != 'S';

		return isNotWall && isNotExit;
	}

	/**
	 * makes a valid move in the given direction
	 *
	 * @param maze game board
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
	}

	private boolean isActive()
	{
		return getHealth() > 0 && !sleeping;
	}

	/**
	 * checks if the dragon can attack the 'Hero' with close range melee attack
	 *
	 * @param player the target player entity
	 * @return 'true' if the dragon is active and the player's position is adjacent to the dragon
	 */
	protected final boolean canAttack(Hero player)
	{
		if (!isActive() || player.getHealth() <= 0 || player.hasSword())
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
	 * checks if the dragon can attack the 'Hero' with fireballs
	 *
	 * @param maze
	 * @param player the target player entity
	 * @return 'true' is dragon is active and if player is in the line of sight, 'false' otherwise
	 */
//	protected final boolean canAttackFire(Maze maze, Hero player)
//	{
//		if (!isActive() || player.getHealth() <= 0 || player.hasShield())
//		{
//			return false;
//		}
//
//		int dragonX = pos.x;
//		int dragonY = pos.y;
//		int incrementX = 0;
//		int incrementY = 0;
//
//		if (player.pos.y == pos.y && Math.abs(player.pos.x - pos.x) <= 3)
//		{
//			incrementX = player.pos.x < pos.x ? -1 : 1;
//		}
//		else if (player.pos.x == pos.x && Math.abs(player.pos.y - pos.y) <= 3)
//		{
//			incrementY = player.pos.y < pos.y ? -1 : 1;
//		}
//		else
//		{
//			return false;
//		}
//
//		for (int i = 0; i <= 3 && maze.symbolAt(dragonX, dragonY) != 'X'; i++, dragonX += incrementX, dragonY += incrementY)
//		{
//			if (player.pos.x == dragonX && player.pos.y == dragonY)
//			{
//				return true;
//			}
//		}
//
//		return false;
//	}

	/**
	 * attacks the player with short-range melee
	 *
	 * @param maze game board
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
	 * attacks the player with long-ranged fireballs
	 *
	 * @param maze game board
	 * @param player the target player
	 */
//	protected final void attackFire(Maze maze, Hero player)
//	{
//		if (canAttackFire(maze, player))
//		{
//			maze.placeSymbol(player.getX(), player.getY(), 'O');
//			player.setHealth(0);
//		}
//	}

	/**
	 * draws the dragon at its corresponding position
	 *
	 * @param maze game board where the dragon is going to be placed
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