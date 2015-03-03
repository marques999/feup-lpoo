/*!
 * \file Hero.java
 *
 * LPOO_2014-2015_P1
 * \author Diogo Marques
 * \author Pedro Melo
 *
 * \date March 2015
 *
 */

package lpoo;

public class Hero
{
	private final int PLAYER_HEALTH = 100;

	private Point pos;
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
	 * @param x initial x position for player
	 * @param y initial y position for player
	 */
	public Hero(int x, int y)
	{
		this.pos = new Point(x, y);
		this.health = PLAYER_HEALTH;
		this.done = false;
	}

	/**
	 * @return returns the player's current position
	 */
	public final Point getPosition()
	{
		return this.pos;
	}

	/**
	 * @brief changes the player's current position
	 * @param pos new coordinates for player position
	 */
	public void setPosition(Point pos)
	{
		this.pos = pos;
	}
	
	/**
	 * @return returns player location's X coordinate
	 */
	public final int getX()
	{
		return this.pos.x;
	}

	/**
	 * @return returns player location's Y coordinate
	 */
	public final int getY()
	{
		return this.pos.y;
	}
	
	/**
	 * @brief changes player location
	 * @param x new X coordinate for player location
	 */
	public void setX(int x)
	{
		this.pos.x = x;
	}

	/**
	 * @brief changes player location
	 * @param y new Y coordinate for player location
	 */
	public void setY(int y)
	{
		this.pos.y = y;
	}

	/**
	 * @return returns player's current health
	 */
	public final int getHealth()
	{
		return this.health;
	}

	/**
	 * @brief changes player's current health
	 * @param health new value for player's health
	 */
	public void setHealth(int health)
	{
		this.health = health;
	}

	/**
	 * @brief checks if the hero has escaped from the maze
	 * @return returns 'true' if the hero has reached the exit; 'false' if game is still in progress
	 */
	public final boolean hasEscaped()
	{
		return this.done;
	}

	/**
	 * @brief equips the player with a sword
	 */
	public void takeSword()
	{
		sword = true;
	}

	/**
	 * @brief removes the sword from the player
	 */
	public void removeSword()
	{
		sword = false;
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
	 * @return returns 'true' if player is still alive and can move to the specified direction, 'false' otherwise
	 */
	private final boolean validMove(Direction direction)
	{
		if (health <= 0)
		{
			return false;
		}

		if (direction == Direction.UP && Board.m[pos.y - 1][pos.x] != 'X')
		{
			return true;
		}

		if (direction == Direction.DOWN && Board.m[pos.y + 1][pos.x] != 'X')
		{
			return true;
		}

		if (direction == Direction.LEFT && Board.m[pos.y][pos.x - 1] != 'X')
		{
			return true;
		}

		if (direction == Direction.RIGHT && Board.m[pos.y][pos.x + 1] != 'X')
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

	public final void attackDragon(Dragon dragon)
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
		if (health > 0)
		{
			if (pos.x >= 0 && pos.x < Board.NUM_COLUNAS && pos.y >= 0 && pos.y < Board.NUM_LINHAS)
			{
				Board.placeSymbol(pos.x, pos.y, hasSword() ? 'A' : 'H');
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
		Point newPosition = new Point();
		
		if (validMove(direction))
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

			itemPicked = Board.m[newPosition.y][newPosition.x];
			Board.clearSymbol(pos.x, pos.y);
			pos = newPosition;

			if (itemPicked != ' ')
			{
				if (itemPicked == 'E')
				{
					sword = true;
				}

				if (itemPicked == 'S' && hasSword())
				{
					done = true;
				}
			}
		}
	}
}