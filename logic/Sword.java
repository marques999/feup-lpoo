package lpoo.logic;

public final class Sword extends Item
{
	private static final long serialVersionUID = 2899571387359273422L;

	/**
	 * default constructor for class 'Sword'
         * 
	 * @param pos initial coordinates for Sword's position
	 */
	protected Sword(Point pos)
	{
		super(pos);
	}

	/**
	 * draws the Sword at its corresponding position
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
			maze.placeSymbol(pos.x, pos.y, 'E');
		}
	}
}