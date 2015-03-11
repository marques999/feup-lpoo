package lpoo.logic;

public class Shield extends Item
{	
	/**
	 * @brief default constructor for class 'Dart'
	 */
	public Shield()
	{
		this(0, 0);
	}

	/**
	 * @brief constructor with parameters for class 'Dart'
	 * @param x X coordinate for sword position
	 * @param y Y coordinate for sword position
	 */
	public Shield(int x, int y)
	{
		super(x, y);
	}
	
	/**
	 * @brief draws the sword at its corresponding position on the game board
	 */
	public final void draw(Maze maze)
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
	 * @return returns a symbol
	 */
	protected final char type()
	{
		return 'V';
	}
}