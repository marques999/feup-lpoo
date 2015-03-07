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

package lpoo.logic;

public class Dragon extends Entity
{
	private int health;
	private boolean sleeping;
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
		super(x, y);
		this.sleeping = false;
		this.health = DRAGON_HEALTH;
	}

	/**
	 * @return returns the dragon's current health
	 */
	public final int getHealth()
	{
		return this.health;
	}

	public final boolean isSleeping()
	{
		return this.sleeping;
	}

	public void toggleSleep()
	{
		this.sleeping = !sleeping;
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
	public final boolean validMove(Maze maze, Direction direction)
	{
		// checks if the dragon is still alive
		if (health <= 0)
		{
			return false;
		}

		// checks if the dragon can go north
		if (direction == Direction.UP && maze.m[pos.y - 1][pos.x] != 'X')
		{
			return true;
		}

		// checks if the dragon can go south
		if (direction == Direction.DOWN && maze.m[pos.y + 1][pos.x] != 'X')
		{
			return true;
		}

		// checks if the dragon can move to the left
		if (direction == Direction.LEFT && maze.m[pos.y][pos.x - 1] != 'X')
		{
			return true;
		}

		// checks if the dragon can move to the right
		if (direction == Direction.RIGHT && maze.m[pos.y][pos.x + 1] != 'X')
		{
			return true;
		}

		return false;
	}

	/**
	 * @brief move the dragon
	 * @param direction the direction to move
	 */

	public void move(Maze maze, Direction direction)
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
		
		if (sleeping)
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

	public final void attackPlayer(Maze maze, Hero player)
	{
		if (canAttack(player))
		{
			maze.clearSymbol(player.getX(), player.getY());
			player.setHealth(0);
		}
	}

	/**
	 * @brief draws the dragon on the game board
	 */
	public final void draw(Maze maze)
	{
		if (health <= 0)
		{
			return;
		}

		if (pos.x >= 0 && pos.x < maze.NUM_COLUNAS && pos.y >= 0 && pos.y < maze.NUM_LINHAS)
		{
			if (sleeping)
			{
				maze.m[pos.y][pos.x] = 'd';
			}
			else
			{
				maze.m[pos.y][pos.x] = 'D';
			}
		}
	}
}