package lpoo;

public class Sword
{
	Point pos;

	/**
	 * @brief default constructor for class 'Sword'
	 */
	public Sword()
	{
		this(0, 0);
	}

	/**
	 * @brief constructor with parameters for class 'Sword'
	 * @param x
	 * @param y
	 */
	public Sword(int x, int y)
	{
		this.pos = new Point(x, y);
	}

	/**
	 * @return returns the sword's position X coordinate
	 */
	public final int getX()
	{
		return this.pos.x;
	}

	/**
	 * @return returns the sword's position Y coordinate
	 */
	public final int getY()
	{
		return this.pos.y;
	}

	/**
	 * @return returns the sword's position
	 */
	public final Point getPosition()
	{
		return this.pos;
	}

	/**
	 * @brief draws the sword at its corresponding position on the game board
	 */
	public final void draw()
	{
		if (Board.m[pos.y][pos.x] == 'D')
		{
			Board.m[pos.y][pos.x] = 'F';
		} 
		else
		{
			Board.m[pos.y][pos.x] = 'E';
		}
	}
}