package lpoo.logic;

public abstract class Item
{
	protected Point pos;
	
	/**
	 * @brief default constructor for class 'Item'
	 */
	protected Item()
	{
		this(0, 0);
	}

	/**
	 * @brief constructor with parameters for class 'Item'
	 * @param x initial X coordinate for item position
	 * @param y initial Y coordinate for item position
	 */
	protected Item(int x, int y)
	{
		this(new Point(x, y));
	}
	
	/**
	 * @brief alternative constructor with parameters for class 'Item'
	 */
	protected Item(Point pos)
	{
		this.pos = pos;
	}
	
	/**
	 * @brief returns the item's current position x coordinate
	 */
	public final int getX()
	{
		return pos.x;
	}
	
	/**
	 * @brief returns the item's current position y coordinate
	 */
	public final int getY()
	{
		return pos.y;
	}
	
	/**
	 * @brief changes the item's current position x coordinate
	 * @param x new value for x coordinate
	 */
	protected void setX(int x)
	{
		this.pos.x = x;
	}

	/**
	 * @brief changes the item's current position y coordinate
	 * @param y new value for y coordinate
	 */
	protected void setY(int y)
	{
		this.pos.y = y;
	}
	
	/**
	 * @return returns the item's current position
	 */
	public final Point getPosition()
	{
		return this.pos;
	}

	/**
	 * @brief changes the item's current position
	 * @param pos new coordinates for dragon position
	 */
	protected void setPosition(Point pos)
	{
		this.pos = pos;
	}
	
	/**
	 * @brief draws an item on the game board
	 */
	protected abstract void draw(Maze maze);
}
