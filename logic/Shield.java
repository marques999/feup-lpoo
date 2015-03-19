package lpoo.logic;

public final class Shield extends Item
<<<<<<< HEAD
{	
	/**
	 * @brief default constructor for class 'Dart'
	 */
	protected Shield()
	{
		this(0, 0);
	}

=======
{
>>>>>>> origin/master
	/**
	 * @brief constructor with parameters for class 'Dart'
	 * @param pos initial dart position
	 */
<<<<<<< HEAD
	protected Shield(int x, int y)
=======
	protected Shield(Point pos)
>>>>>>> origin/master
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