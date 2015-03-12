package lpoo.logic;

public final class Sword extends Item
{
	/**
	 * @brief default constructor for class 'Sword'
	 */
	protected Sword()
	{
		this(0, 0);
	}
	
	/**
	 * @brief constructor with parameters for class 'Sword'
	 * @param pos initial sword position
	 */
	protected Sword(Point pos)
	{
		super(pos);
	}

	/**
	 * @brief constructor with parameters for class 'Sword'
	 * @param x X coordinate for sword position
	 * @param y Y coordinate for initial sword position
	 */
	protected Sword(int x, int y)
	{
		super(x, y);
	}

	/**
	 * @brief draws the sword on the game board
	 */
	protected final void draw(Maze maze)
	{
		if (maze.symbolAt(pos.x, pos.y) == 'D' || maze.symbolAt(pos.x, pos.y) == 'd')
		{
			maze.placeSymbol(pos.x, pos.y, 'F');
		} 
		else
		{
			maze.placeSymbol(pos.x, pos.y, 'E');
		}
	}
}