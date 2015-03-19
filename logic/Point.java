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

	/**
	 * @brief checks two points and their coordinates for equality
	 * @param p1 a second point to be compared
	 * @return returns 'true' if both points have the same coordinates; 'false' otherwise
	 */
	@Override
	public boolean equals (Object o)
	{
		return (o instanceof Point && x == ((Point)o).x && y == ((Point) o).y);
	}
}