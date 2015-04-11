package lpoo.logic;

public final class Shield extends Item
{
	private static final long serialVersionUID = -651778258839314342L;

	/**
	 * default constructor for class 'Shield'
	 * @param pos coordinates for shield's initial position
	 */
	protected Shield(Point pos)
	{
		super(pos);
	}

	/**
	 * draws the shield at its corresponding position on the game board
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
			maze.placeSymbol(pos.x, pos.y, 'V');
		}
	}
}