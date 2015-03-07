package lpoo.logic;

public abstract class Item
{
	protected Point pos;
	
	/**
	 * @brief default constructor for class 'Entity'
	 */
	public Item()
	{
		this(0, 0);
	}

	/**
	 * @brief constructor with parameters for class 'Entity'
	 * @param x
	 * @param y
	 */
	public Item(int x, int y)
	{
		this.pos = new Point(x, y);
	}
	
	/**
	 * @brief returns the entity's current position x coordinate
	 */
	public final int getX()
	{
		return pos.x;
	}
	
	/**
	 * @brief returns the entity's current position y coordinate
	 */
	public final int getY()
	{
		return pos.y;
	}
	
	/**
	 * @brief changes the entity's current position x coordinate
	 * @param x new value for x coordinate
	 */
	public void setX(int x)
	{
		this.pos.x = x;
	}

	/**
	 * @brief changes the entity's current position y coordinate
	 * @param y new value for y coordinate
	 */
	public void setY(int y)
	{
		this.pos.y = y;
	}
	

	/**
	 * @return returns the entity's current position
	 */
	public final Point getPosition()
	{
		return this.pos;
	}

	/**
	 * @brief changes the entity's current position
	 * @param pos new coordinates for dragon position
	 */
	public void setPosition(Point pos)
	{
		this.pos = pos;
	}
	
	/**
	 * @brief draws the entity on the game board
	 */
	public abstract void draw(Maze maze);
}
