package lpoo.logic;

public final class Dart extends Item
{
	private static final long serialVersionUID = 2572702298191276648L;

	/**
	 * default constructor for class 'Dart'
	 * @param pos coordinates for dart's initial position
	 */
	protected Dart(Point pos)
	{
		super(pos);
	}

	/**
	 * draws the dart at its corresponding position on the game board
	 * @param maze an object containing the game board
	 */
	@Override
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