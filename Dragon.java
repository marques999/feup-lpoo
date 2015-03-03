/*!
 * \file Dragon.java
 *
 * LPOO_2014-2015_P1
 * \author Diogo Marques
 * \author Pedro Melo
 *
 * \date March 2015
 *
 */

package lpoo;

public class Dragon
{
	private Point pos;
	private int health;

	private final int DRAGON_HEALTH = 100;

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
		this.pos = new Point(x, y);
		this.health = DRAGON_HEALTH;
	}

	/**
	 * @brief returns the dragon's current position x coordinate
	 */
	public final int getX()
	{
		return pos.x;
	}
	
	/**
	 * @brief returns the dragon's current position y coordinate
	 */
	public final int getY()
	{
		return pos.y;
	}

	/**
	 * @brief changes the dragon's current position x coordinate
	 * @param x new value for x coordinate
	 */
	public void setX(int x)
	{
		this.pos.x = x;
	}

	/**
	 * @brief changes the dragon's current position y coordinate
	 * @param y new value for y coordinate
	 */
	public void setY(int y)
	{
		this.pos.y = y;
	}

	/**
	 * @return returns the dragon's current position
	 */
	public final Point getPosition()
	{
		return this.pos;
	}

	/**
	 * @brief changes the dragon's current position
	 * @param pos new coordinates for dragon position
	 */
	public void setPosition(Point pos)
	{
		this.pos = pos;
	}

	/**
	 * @return returns the dragon's current health
	 */
	public final int getHealth()
	{
		return this.health;
	}

	/**
	 * @brief changes the dragon's current health
	 * @param health new value for dragon's health
	 */
	public void setHealth(int health)
	{
		this.health = health;
	}

	/**
	 * @brief tells if the dragon move is valid
	 * @param direction the direction to move
	 */
	public final boolean validMove(Direction direction)
	{
		// checks if the dragon is still alive
		if (health <= 0)
		{
			return false;
		}

		// checks if the dragon can go north
		if (direction == Direction.UP && Board.m[pos.y - 1][pos.x] != 'X')
		{
			return true;
		}

		// checks if the dragon can go south
		if (direction == Direction.DOWN && Board.m[pos.y + 1][pos.x] != 'X')
		{
			return true;
		}

		// checks if the dragon can move to the left
		if (direction == Direction.LEFT && Board.m[pos.y][pos.x - 1] != 'X')
		{
			return true;
		}

		// checks if the dragon can move to the right
		if (direction == Direction.RIGHT && Board.m[pos.y][pos.x + 1] != 'X')
		{
			return true;
		}

		return false;
	}

	/**
	 * @brief move the dragon
	 * @param direction the direction to move
	 */
	public void move(Direction direction)
	{
		if (direction == Direction.NONE)
		{
			return;
		}

		Board.clearSymbol(pos.x, pos.y);

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

	/**
	 * @brief attacks the 'Hero'
	 * @param player
	 *            the player entity
	 */
	private final boolean canAttack(Hero player)
	{
		if (health <= 0)
		{
			return false;
		}

		if (player.getHealth() <= 0 || player.hasSword())
		{
			return false;
		}

		int playerX = player.getX();
		int playerY = player.getY();

		if (pos.x <= playerX + 1 && pos.x >= playerX - 1 && pos.y == playerY)
		{
			return true;
		}

		if (pos.y <= playerY + 1 && pos.y >= playerY - 1 && pos.x == playerX)
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
		if (health <= 0)
		{
			return;
		}

		if (pos.x >= 0 && pos.x < Board.NUM_COLUNAS && pos.y >= 0
				&& pos.y < Board.NUM_LINHAS)
		{
			if (Board.m[pos.y][pos.x] == 'E')
			{
				Board.m[pos.y][pos.x] = 'F';
			} else
			{
				Board.m[pos.y][pos.x] = 'D';
			}
		}
	}
}