package lpoo.logic;

public abstract class Item 
{
	protected Point pos;

	/**
	 * @brief default constructor for class 'Item'
	 * @param pos initial coordinates for item position
	 */
	protected Item(Point pos) 
	{
		this.pos = pos;
	}

	/**
	 * @return
	 * @brief returns the item's current position x coordinate
	 */
	public final int getX() 
	{
		return pos.x;
	}

	/**
	 * @return
	 * @brief returns the item's current position y coordinate
	 */
	public final int getY() 
	{
		return pos.y;
	}

	/**
	 * @return returns the item's current position
	 */
	public final Point getPosition() 
	{
		return this.pos;
	}

	/**
	 * @brief draws an item on the game board
	 * @param maze
	 */
	protected abstract void draw(Maze maze);
}