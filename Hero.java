package lpoo;

public class Hero
{
	private final int PLAYER_HEALTH = 100;

	private int x; // the x coordinate
	private int y; // the y coordinate
	private int health; // player health (default: 100)
	private boolean sword; // tells if the player has the sword
	private boolean done; // tells if hero escaped
	private char itemPicked; // symbol representing if the hero took some item

	/**
	 * @brief default constructor for class 'Hero'
	 */
	public Hero()
	{
		this(0, 0);
	}

	/**
	 * @brief constructor with parameters for class 'Hero'
	 * @param x
	 *            initial x position for player
	 * @param y
	 *            initial y position for player
	 */
	public Hero(int x, int y)
	{
		this.x = x;
		this.y = y;
		this.health = PLAYER_HEALTH;
		this.done = false;
	}

	/**
	 * @return returns the X coordinate (player location)
	 */
	public final int getX()
	{
		return this.x;
	}

	/**
	 * @brief changes player location
	 * @param x
	 *            new X coordinate for player location
	 */
	public void setX(int x)
	{
		this.x = x;
	}

	/**
	 * @return returns the Y coordinate (player location)
	 */
	public final int getY()
	{
		return this.y;
	}

	/**
	 * @brief changes player location
	 * @param y
	 *            new Y coordinate for player location
	 */
	public void setY(int y)
	{
		this.y = y;
	}

	/**
	 * @return returns the current player health
	 */
	public final int getHealth()
	{
		return this.health;
	}

	/**
	 * @brief changes player health
	 * @param health
	 *            new value for player's health
	 */
	public void setHealth(int health)
	{
		this.health = health;
	}

	// tells if the hero has escaped
	public final boolean hasFinished()
	{
		return this.done;
	}

	/**
	 * @brief equips the player with a sword
	 * @return returns 'true' if player got the sword, 'false' if player already
	 *         had the sword
	 */
	public boolean takeSword()
	{
		if (sword == true)
		{
			return false;
		}

		sword = true;

		return true;
	}

	/**
	 * @brief removes the sword from the player
	 * @return returns 'true' if sword removed successfully; 'false' if player
	 *         doesn't have a sword
	 */
	public boolean removeSword()
	{

		if (sword == false)
		{
			return false;
		}

		sword = false;

		return true;
	}

	/**
	 * @return returns 'true' if the player has the sword; 'false' otherwise
	 */
	public final boolean hasSword()
	{
		return this.sword;
	}

	/**
	 * @brief checks if player can move in specified direction
	 * @param direction
	 * @param board
	 * @return returns 'true' if player is still alive and can move to the
	 *         specified direction, 'false' otherwise
	 */
	public final boolean validMove(Direction direction)
	{
		if (health <= 0)
		{
			return false;
		}

		if (direction == Direction.UP && Board.m[y - 1][x] != 'X')
		{
			return true;
		}

		if (direction == Direction.DOWN && Board.m[y + 1][x] != 'X')
		{
			return true;
		}

		if (direction == Direction.LEFT && Board.m[y][x - 1] != 'X')
		{
			return true;
		}

		if (direction == Direction.RIGHT && Board.m[y][x + 1] != 'X')
		{
			return true;
		}

		return false;
	}

	/**
	 * @brief checks if player is close enough to attack an enemy dragon
	 * @param dragon
	 * @param board
	 * @return returns 'true' if player has the sword and can attack the dragon;
	 *         'false' otherwise
	 */
	public final boolean canAttack(Dragon dragon)
	{
		if (health <= 0)
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

		if (x <= dragonX + 1 && x >= dragonX - 1 && y == dragonY)
		{
			return true;
		}

		if (y <= dragonY + 1 && y >= dragonY - 1 && x == dragonX)
		{
			return true;
		}

		return false;
	}

	public void attackDragon(Dragon dragon)
	{
		if (canAttack(dragon))
		{
			Board.clearSymbol(dragon.getX(), dragon.getY());
			dragon.setHealth(0);
		}
	}

	/**
	 * @brief draws a symbol on the screen representing the player
	 * @param board
	 */
	public final void draw()
	{
		if (x >= 0 && x < Board.NUM_COLUNAS && y >= 0 && y < Board.NUM_LINHAS)
		{
			if (health > 0)
			{
				Board.placeSymbol(x, y, hasSword() ? 'A' : 'H');
			}
		}
	}

	/**
	 * @brief moves the player in a specified direction
	 * @param board
	 * @param direction
	 */
	public void makeMove(Direction direction)
	{
		if (!validMove(direction))
		{
			return;
		}

		boolean pickedUp = true;

		if (direction == Direction.UP)
		{
			itemPicked = Board.m[y - 1][x];

			if (itemPicked == 'S' && !hasSword())
			{
				itemPicked = ' ';
				return;
			}

			if (itemPicked != ' ')
			{
				pickedUp = true;
			}

			Board.clearSymbol(x, y);

			y--;
		}

		else if (direction == Direction.DOWN)
		{
			itemPicked = Board.m[y + 1][x];

			if (itemPicked == 'S' && !hasSword())
			{
				itemPicked = ' ';

				return;
			}

			if (itemPicked != ' ')
			{
				pickedUp = true;
			}

			Board.clearSymbol(x, y);

			y++;
		}

		else if (direction == Direction.LEFT)
		{
			itemPicked = Board.m[y][x - 1];

			if (itemPicked == 'S' && !hasSword())
			{
				itemPicked = ' ';
				return;
			}

			if (itemPicked != ' ')
			{
				pickedUp = true;
			}

			Board.clearSymbol(x, y);

			x--;
		}

		else if (direction == Direction.RIGHT)
		{
			itemPicked = Board.m[y][x + 1];

			if (itemPicked == 'S' && !hasSword())
			{
				itemPicked = ' ';
				return;
			}

			if (itemPicked != ' ')
			{
				pickedUp = true;
			}

			Board.clearSymbol(x, y);

			x++;
		}

		if (pickedUp)
		{
			if (itemPicked == 'E')
			{
				takeSword();
			}

			if (itemPicked == 'S')
			{
				done = true;
			}
		}
	}
}
