package lpoo.logic;

public final class Dart extends Item
{	
	/**
	 * @brief default constructor for class 'Dart'
	 */
	protected Dart()
	{
		this(0, 0);
	}

	/**
	 * @brief constructor with parameters for class 'Dart'
	 * @param x X coordinate for dart position
	 * @param y Y coordinate for dart position
	 */
	protected Dart(int x, int y)
	{
		super(x, y);
	}
	
	/**
	 * @brief draws a dart on the game board
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
				maze.placeSymbol(pos.x, pos.y, '*');
			}
		}
	}
	
	/**
	 * @return returns a symbol representing a dart
	 */
	protected final char type()
	{
		return '*';
	}
}