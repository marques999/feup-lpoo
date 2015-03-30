package lpoo.logic;

import java.io.*;

public final class Point implements Serializable
{
	public int x;
	public int y;

	/**
	 * @brief default constructor for class 'Point'
	 */
	public Point()
	{
		this(0, 0);
	}

	/**
	 * @brief constructor with parameters for class 'Point'
	 * @param x initial X coordinate
	 * @param y initial Y coordinate
	 */
	public Point(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	protected void readObject(ObjectInputStream aInputStream) throws IOException
	{
		this.x = aInputStream.readInt();
		this.y = aInputStream.readInt();
	}

	protected void writeObject(ObjectOutputStream aOutputStream) throws IOException
	{
		aOutputStream.writeInt(x);
		aOutputStream.writeInt(y);
	}

	/**
	 * @brief checks two points and their coordinates for equality
	 * @param o point to be compared
	 * @return returns 'true' if both points have the same coordinates; 'false' otherwise
	 */
	@Override
	public boolean equals(Object o)
	{
		return o instanceof Point && x == ((Point) o).x && y == ((Point) o).y;
	}
}