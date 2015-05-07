package lpoo.logic;

import java.io.*;

public abstract class Item implements Serializable
{
	private static final long serialVersionUID = -6596044906701645940L;

	protected Point pos;

	/**
	 * default constructor for class 'Item'
	 * @param pos initial coordinates for item's position
	 */
	protected Item(Point pos)
	{
		this.pos = pos;
	}

	/**
	 * gets item's current position x coordinate
	 * @return item's current position x coordinate
	 */
	public final int getX()
	{
		return pos.x;
	}

	/**
	 * gets item's current position y coordinate
	 * @return item's current position y coordinate
	 */
	public final int getY()
	{
		return pos.y;
	}

	/**
	 * gets item's current position
	 * @return item's current position
	 */
	public final Point getPosition()
	{
		return pos;
	}

	/**
	 * draws an item at its corresponding position on the game board
	 * @param maze an object containing the game board
	 */
	protected abstract void draw(Maze maze);
}