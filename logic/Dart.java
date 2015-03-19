package lpoo.logic;

public final class Dart extends Item
<<<<<<< HEAD
{	
=======
{
>>>>>>> origin/master
	/**
	 * @brief default constructor for class 'Dart'
	 * @param pos initial coordinates for dart position
	 */
<<<<<<< HEAD
	protected Dart()
=======
	protected Dart(Point pos)
>>>>>>> origin/master
	{
		super(pos);
	}

	/**
<<<<<<< HEAD
	 * @brief constructor with parameters for class 'Dart'
	 * @param x X coordinate for dart position
	 * @param y Y coordinate for dart position
	 */
	protected Dart(int x, int y)
	{
		super(x, y);
	}
	
	/**
=======
>>>>>>> origin/master
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