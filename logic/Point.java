package lpoo.logic;

public final class Point
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
	 * @param x
	 * @param y
	 */
	public Point(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public final String toString()
	{
		return "(" + this.x + ", " + this.y + ")";
	}

	/**
	 * @brief checks two points and their coordinates for equality
	 * @param p1 a second point to be compared
	 * @return returns 'true' if both points have the same coordinates; 'false' otherwise
	 */
	public final boolean equals(Point p1)
	{
		return (this.x == p1.x && this.y == p1.y);
	}

	/**
	 * @brief computes the distance between two points
	 * @param p1 a second point
	 * @return returns the distance between the points in game units
	 */
	public final double getDistance(Point p1)
	{
		int X_squared = (x - p1.x) * (x - p1.x);
		int Y_squared = (y - p1.y) * (y - p1.x);

		return Math.sqrt(X_squared + Y_squared);
	}
}