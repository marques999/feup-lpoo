package lpoo.logic;

public final class Sword extends Item
{
	private static final long serialVersionUID = 2899571387359273422L;

	/**
	 * default constructor for class 'Sword'
	 * @param pos coordinates for sword's initial position
	 */
	protected Sword(Point pos)
	{
		super(pos);
	}

	/**
	 * draws the sword at its corresponding position on the game board
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
			maze.placeSymbol(pos.x, pos.y, 'E');
		}
	}
}