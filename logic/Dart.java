package lpoo.logic;

public final class Dart extends Item
{
	private static final long serialVersionUID = 2572702298191276648L;

	/**
	 * default constructor for 'Dart' class
	 * @param pos coordinates for dart's initial position
	 */
	protected Dart(Point pos)
	{
		super(pos);
	}

	/**
	 * draws the dart at its corresponding position on the board matrix
	 * @param maze an object containing the board matrix
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