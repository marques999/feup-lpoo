package lpoo.logic;

public final class Shield extends Item
{
	/**
	 * @brief default constructor for class 'Dart'
	 */
	protected Shield()
	{
		super(0, 0);
	}

	/**
	 * @brief constructor with parameters for class 'Dart'
	 * @param x X coordinate for initial sword position
	 * @param y Y coordinate for initial sword position
	 */
	protected Shield(int x, int y)
	{
		super(x, y);
	}

	/**
	 * @brief constructor with parameters for class 'Dart'
	 * @param pos initial dart position
	 */
	protected Shield(Point pos)
	{
		super(pos);
	}

	/**
	 * @brief draws the sword at its corresponding position on the game board
	 */
	protected final void draw(Maze maze)
	{
		if (maze.symbolAt(pos.x, pos.y) == 'D' || maze.symbolAt(pos.x, pos.y) == 'd')
		{
			maze.placeSymbol(pos.x, pos.y, 'F');
		} 
		else
		{
			maze.placeSymbol(pos.x, pos.y, 'V');
		}
	}
}