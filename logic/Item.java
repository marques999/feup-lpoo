package lpoo.logic;

import java.io.*;

public abstract class Item implements Serializable
{
	private static final long serialVersionUID = -6596044906701645940L;
	protected Point pos;

	/**
	 * @brief default constructor for 'Item' class
	 * @param pos initial coordinates for Item's position
	 */
	protected Item(Point pos)
	{
		this.pos = pos;
	}

	/**
	 * @return returns the Item's current position x coordinate
	 */
	public final int getX()
	{
		return pos.x;
	}

	/**
	 * @return returns the Item's current position y coordinate
	 */
	public final int getY()
	{
		return pos.y;
	}

	/**
	 * @return returns the Item's current position
	 */
	public final Point getPosition()
	{
		return pos;
	}

	/**
	 * @brief reads Item's state from stream
	 * @param aInputStream file/object input stream to read from
	 * @throws IOException if stream not found or invalid
	 * @throws ClassNotFoundException
	 */
	private void readObject(ObjectInputStream aInputStream) throws IOException, ClassNotFoundException
	{
		pos = (Point) aInputStream.readObject();
	}

	/**
	 * @brief writes current Item's state to a stream
	 * @param aOutputStream file/object output stream to write to
	 * @throws IOException if stream not found or invalid
	 */
	private void writeObject(ObjectOutputStream aOutputStream) throws IOException
	{
		aOutputStream.writeObject(pos);
	}

	/**
	 * @brief draws Item at its corresponding position
	 * @param maze an instance of 'Maze' class
	 */
	protected abstract void draw(Maze maze);
}