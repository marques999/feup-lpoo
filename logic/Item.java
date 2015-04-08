package lpoo.logic;

import java.io.*;

public abstract class Item implements Serializable
{
	private static final long serialVersionUID = -6596044906701645940L;

	protected Point pos;

	/**
	 * default constructor for 'Item' class
	 * @param pos initial coordinates for Item's position
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
	 * draws item at its corresponding position on the board matrix
	 * @param maze an object containing the board matrix
	 */
	protected abstract void draw(Maze maze);
}