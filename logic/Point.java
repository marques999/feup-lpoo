package lpoo.logic;

import java.io.*;

public final class Point implements Serializable
{
	private static final long serialVersionUID = -2123191411932878529L;
	
	public int x;
	public int y;

	/**
	 * default constructor for class 'Point'
	 */
	public Point()
	{
		this(0, 0);
	}

	/**
	 * constructor with parameters for class 'Point'
	 * @param x initial X coordinate
	 * @param y initial Y coordinate
	 */
	public Point(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

        /**
         * converts the point's coordinates to String
         * @return a formatted, user-friendly string containing the point's current coordinates
         */
	@Override
	public String toString()
	{
		return "(" + x + ", " + y + ")";
	}

	/**
	 * checks two points and their coordinates for equality
	 * @param o an object containing the point to be compared
	 * @return 'true' if both points have the same coordinates; 'false' otherwise
	 */
	@Override
	public boolean equals(Object o)
	{
		return o instanceof Point && x == ((Point) o).x && y == ((Point) o).y;
	}
}