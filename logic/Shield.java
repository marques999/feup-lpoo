package lpoo.logic;

public final class Shield extends Item
{	
	/**
	 * @brief default constructor for class 'Dart'
	 */
	protected Shield()
	{
		this(0, 0);
	}

	/**
	 * @brief constructor with parameters for class 'Dart'
	 * @param x X coordinate for sword position
	 * @param y Y coordinate for sword position
	 */
	protected Shield(int x, int y)
	{
		super(x, y);
	}
	
	/**
	 * @brief draws the sword at its corresponding position on the game board
	 */
	protected final void draw(Maze maze)
	{
		if (!hasOwner())
		{
			if (maze.symbolAt(pos.x, pos.y) == 'D')
			{
				maze.placeSymbol(pos.x, pos.y, 'F');
			} 
			else
			{
				maze.placeSymbol(pos.x, pos.y, 'V');
			}
		}
	}
	
	/**
	 * @return returns a symbol representing the item type
	 */
	protected final char type()
	{
		return 'V';
	}
}