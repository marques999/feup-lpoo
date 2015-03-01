package lpoo;

public class Point
{
	private int x; // x coordinate
	private int y; // y coordinate

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

	public final int getX()
	{
		return x;
	}

	public final int getY()
	{
		return y;
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public void setY(int y)
	{
		this.y = y;
	}

	public String toString()
	{
		return "(" + this.x + ", " + this.y + ")";
	}

	public boolean equals(Point p1)
	{
		return (this.x == p1.x && this.y == p1.y);
	}

	public double getDistance(Point p1)
	{
		int X_squared = (x - p1.x) * (x - p1.x);
		int Y_squared = (y - p1.y) * (y - p1.x);

		return Math.sqrt(X_squared + Y_squared);
	}
}