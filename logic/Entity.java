package lpoo.logic;

import java.io.*;

public abstract class Entity implements Serializable
{
	private static final long serialVersionUID = -2963466833758950373L;
	
	protected Point pos;
	private int health;

	/**
	 * default constructor for class 'Entity'
	 *
	 * @param pos initial coordinates for Entity's position
	 * @param health initial Entity's health
	 */
	protected Entity(Point pos, int health)
	{
		this.pos = pos;
		this.health = health;
	}

	/**
	 * @return Entity's current position x coordinate
	 */
	public final int getX()
	{
		return pos.x;
	}

	/**
	 * @return Entity's current position y coordinate
	 */
	public final int getY()
	{
		return pos.y;
	}

	/**
	 * @return Entity's current health
	 */
	public final int getHealth()
	{
		return health;
	}

	/**
	 * changes Entity's current health
	 *
	 * @param health new value for entity's health
	 */
	protected void setHealth(int health)
	{
		this.health = health;
	}

	/**
	 * @return Entity's current position
	 */
	public final Point getPosition()
	{
		return pos;
	}

	/**
	 * draws the Entity at its corresponding position
	 *
	 * @param maze
	 */
	protected abstract void draw(Maze maze);
	protected abstract void move(Maze maze, Direction direction);
}