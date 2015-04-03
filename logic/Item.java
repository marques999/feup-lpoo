package lpoo.logic;

import java.io.*;

public abstract class Item implements Serializable
{
	private static final long serialVersionUID = -6596044906701645940L;
	
	protected Point pos;

	/**
	 * default constructor for 'Item' class
	 *
	 * @param pos initial coordinates for Item's position
	 */
	protected Item(Point pos)
	{
		this.pos = pos;
	}

	/**
	 * @return Item's current position x coordinate
	 */
	public final int getX()
	{
		return pos.x;
	}

	/**
	 * @return Item's current position y coordinate
	 */
	public final int getY()
	{
		return pos.y;
	}

	/**
	 * @return Item's current position
	 */
	public final Point getPosition()
	{
		return pos;
	}

	/**
	 * draws Item at its corresponding position
	 *
	 * @param maze an instance of 'Maze' class
	 */
	protected abstract void draw(Maze maze);
}