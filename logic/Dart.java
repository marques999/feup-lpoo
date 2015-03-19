package lpoo.logic;

public final class Dart extends Item
{
	/**
	 * @brief default constructor for class 'Dart'
	 * @param pos initial coordinates for dart position
	 */
	protected Dart(Point pos)
	{
		super(pos);
	}

	/**
	 * @brief draws a dart on the game board
	 */
	protected final void draw(Maze maze)
	{
		if (maze.symbolAt(pos.x, pos.y) == 'D' || maze.symbolAt(pos.x, pos.y) == 'd')
		{
			maze.placeSymbol(pos.x, pos.y, 'F');
		} 
		else
		{
			maze.placeSymbol(pos.x, pos.y, '*');
		}
	}
}