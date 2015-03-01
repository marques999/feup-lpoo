package lpoo;

public class Dragon
{
	private int x; // the x coordinate
	private int y; // the y coordinate
	private int health; // the dragon health

	private final int DRAGON_HEALTH = 100; // initial health

	/**
	 * @brief default constructor for class 'Dragon'
	 */
	public Dragon()
	{
		this(0, 0);
	}

	/**
	 * @brief constructor with parameters for class 'Dragon'
	 * @param x
	 * @param y
	 */
	public Dragon(int x, int y)
	{
		this.x = x;
		this.y = y;
		this.setHealth(DRAGON_HEALTH);
	}

	/**
	 * @brief return the dragon x coordinate
	 */
	public final int getX()
	{
		return x;
	}

	/**
	 * @brief changes the dragon x coordinate
	 * @param x
	 *            new x coordinate value
	 */
	public void setX(int x)
	{
		this.x = x;
	}

	/**
	 * @brief returns the dragon's position y coordinate
	 */
	public final int getY()
	{
		return y;
	}

	/**
	 * @brief changes the dragon's position y coordinate
	 * @param y
	 *            new y coordinate value
	 */
	public void setY(int y)
	{
		this.y = y;
	}

	/**
	 * @return returns current dragon health
	 */
	public final int getHealth()
	{
		return this.health;
	}

	/**
	 * @brief changes the dragon's current health
	 * @param health
	 *            new value for dragon's health
	 */
	public void setHealth(int health)
	{
		this.health = health;
	}

	/**
	 * @brief tells if the dragon move is valid
	 * @param direction
	 *            the direction to move
	 */
	public final boolean validMove(Direction direction)
	{
		// check if the dragon is still alive
		if (health <= 0)
		{
			return false;
		}

		// check if the dragon can move upwards
		if (direction == Direction.UP && Board.m[y - 1][x] != 'X')
		{
			return true;
		}

		// check if the dragon can move downwards
		if (direction == Direction.DOWN && Board.m[y + 1][x] != 'X')
		{
			return true;
		}

		// check if the dragon can move westwards
		if (direction == Direction.LEFT && Board.m[y][x - 1] != 'X')
		{
			return true;
		}

		// check if the dragon can move eastwards
		if (direction == Direction.RIGHT && Board.m[y][x + 1] != 'X')
		{
			return true;
		}

		return false;
	}

	/**
	 * @brief move the dragon
	 * @param direction
	 *            the direction to move
	 */
	public void move(Direction direction)
	{
		// move the dragon upwards
		if (direction == Direction.UP)
		{
			Board.clearSymbol(x, y);
			y--;
		}

		// move the dragon southwards
		else if (direction == Direction.DOWN)
		{
			Board.clearSymbol(x, y);
			y++;
		}

		// move the dragon westwards
		else if (direction == Direction.LEFT)
		{
			Board.clearSymbol(x, y);
			x--;
		}

		// move the dragon eastwards
		else if (direction == Direction.RIGHT)
		{
			Board.clearSymbol(x, y);
			x++;
		}

		// don't move the dragon!
		else
		{
			return;
		}

		this.draw();
	}

	/**
	 * @brief attacks the 'Hero'
	 * @param player
	 *            the player
	 */
	private final boolean canAttack(Hero player)
	{
		if (player.getHealth() <= 0)
		{
			return false;
		}

		if (health <= 0)
		{
			return false;
		}

		if (player.hasSword())
		{
			return false;
		}

		int playerX = player.getX();
		int playerY = player.getY();

		if (x <= playerX + 1 && x >= playerX - 1 && y == playerY)
		{
			return true;
		}

		if (y <= playerY + 1 && y >= playerY - 1 && x == playerX)
		{
			return true;
		}

		return false;
	}

	public final void attackPlayer(Hero player)
	{
		if (canAttack(player))
		{
			Board.clearSymbol(player.getX(), player.getY());
			player.setHealth(0);
		}
	}

	/**
	 * @brief draws the dragon on the game board
	 */
	public final void draw()
	{
		if (health > 0 && x >= 0 && x < Board.NUM_COLUNAS && y >= 0 && y < Board.NUM_LINHAS)
		{
			if (Board.m[y][x] == 'E')
			{
				Board.m[y][x] = 'F';
			} 
			else
			{
				Board.m[y][x] = 'D';
			}
		}
	}
}