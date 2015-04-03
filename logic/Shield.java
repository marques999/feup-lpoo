package lpoo.logic;

public final class Shield extends Item
{
	private static final long serialVersionUID = -651778258839314342L;

	/**
	 * default constructor for class 'Shield'
         * 
	 * @param pos initial coordinates for Shield's position
	 */
	protected Shield(Point pos)
	{
		super(pos);
	}

	/**
	 * draws the Shield at its corresponding position
         * 
	 * @param maze an instance of 'Maze' class
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