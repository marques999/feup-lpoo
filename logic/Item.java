package lpoo.logic;

public abstract class Item
{
	protected Point pos;
	private Entity owner;
	
	/**
	 * @brief default constructor for class 'Entity'
	 */
	protected Item()
	{
		this(0, 0);
	}

	/**
	 * @brief constructor with parameters for class 'Entity'
	 * @param x initial X coordinate for item position
	 * @param y initial Y coordinate for item position
	 */
	protected Item(int x, int y)
	{
		this.pos = new Point(x, y);
		this.owner = null;
	}
	
	/**
	 * @brief returns the entity's current position x coordinate
	 */
	protected final int getX()
	{
		return pos.x;
	}
	
	/**
	 * @brief returns the entity's current position y coordinate
	 */
	protected final int getY()
	{
		return pos.y;
	}
	
	/**
	 * @brief changes the entity's current position x coordinate
	 * @param x new value for x coordinate
	 */
	protected void setX(int x)
	{
		this.pos.x = x;
	}

	/**
	 * @brief changes the entity's current position y coordinate
	 * @param y new value for y coordinate
	 */
	protected void setY(int y)
	{
		this.pos.y = y;
	}
	
	/**
	 * @return returns the entity's current position
	 */
	protected final Point getPosition()
	{
		return this.pos;
	}

	/**
	 * @brief changes the entity's current position
	 * @param pos new coordinates for dragon position
	 */
	protected void setPosition(Point pos)
	{
		this.pos = pos;
	}
	
	protected void setOwner(Entity owner)
	{
		this.owner = owner;
	}
	
	protected void removeOwner(Entity owner)
	{
		this.owner = null;
	}
	
	protected boolean hasOwner()
	{
		return (this.owner != null);
	}
		
	/**
	 * @brief draws the entity on the game board
	 */
	protected abstract void draw(Maze maze);
	protected abstract char type();
}
