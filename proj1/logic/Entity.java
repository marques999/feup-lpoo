package lpoo.logic;

import java.io.*;

public abstract class Entity implements Serializable
{
	private static final long serialVersionUID = -2963466833758950373L;

	protected Point pos;
	private int health;

	/**
	 * default constructor for class 'Entity'
	 * @param pos initial coordinates for entity's position
	 * @param health initial entity's health
	 */
	protected Entity(Point pos, int health)
	{
		this.pos = pos;
		this.health = health;
	}

	/**
	 * gets entity's current position x coordinate
	 * @return returns entity's current position x coordinate
	 */
	public final int getX()
	{
		return pos.x;
	}

	/**
	 * gets entity's current position y coordinate
	 * @return returns entity's current position y coordinate
	 */
	public final int getY()
	{
		return pos.y;
	}

	/**
	 * gets entity's current position
	 * @return returns entity's current position
	 */
	public final Point getPosition()
	{
		return pos;
	}

	/**
	 * gets entity's current health
	 * @return returns entity's current health
	 */
	public final int getHealth()
	{
		return health;
	}

	/**
	 * sets or changes entity's current health
	 * @param hp new value for entity's health
	 */
	protected void setHealth(int hp)
	{
		health = hp;
	}

	/**
	 * draws the entity at its corresponding position on the game board
	 * @param maze an object containing the game board
	 */
	protected abstract void draw(Maze maze);

	/**
	 * moves the entity around the maze in a given direction
	 * @param direction the direction given by the user
	 * @param maze an object containing the game board
	 */
	protected abstract void move(Maze maze, Direction direction);
}