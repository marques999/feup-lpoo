package lpoo.logic;

import java.io.*;

public abstract class Entity implements Serializable
{
	private static final long serialVersionUID = -2963466833758950373L;

	protected Point pos;
	private int health;

	/**
	 * default constructor for 'Entity' class
	 * @param pos initial coordinates for Entity's position
	 * @param health initial Entity's health
	 */
	protected Entity(Point pos, int health)
	{
		this.pos = pos;
		this.health = health;
	}

	/**
	 * gets entity's current position x coordinate
	 * @return entity's current position x coordinate
	 */
	public final int getX()
	{
		return pos.x;
	}

	/**
	 * gets entity's current position y coordinate
	 * @return entity's current position y coordinate
	 */
	public final int getY()
	{
		return pos.y;
	}

	/**
	 * gets entity's current position
	 * @return entity's current position
	 */
	public final Point getPosition()
	{
		return pos;
	}

	/**
	 * gets entity's current health
	 * @return entity's current health
	 */
	public final int getHealth()
	{
		return health;
	}

	/**
	 * changes entity's current health
	 * @param health new value for entity's health
	 */
	protected void setHealth(int health)
	{
		this.health = health;
	}

	/**
	 * draws the Entity at its corresponding position on the board matrix
	 * @param maze an object containing the board matrix
	 */
	protected abstract void draw(Maze maze);

	/**
	 * moves the entity around the maze given a direction
	 * @param direction the direction given by the user
	 * @param maze an object containing the board matrix
	 */
	protected abstract void move(Maze maze, Direction direction);
}