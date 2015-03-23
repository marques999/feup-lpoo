package lpoo.logic;

public final class Sword extends Item
{
	/**
	 * @brief default constructor for class 'Sword'
	 * @param pos initial sword position
	 */
	protected Sword(Point pos)
	{
		super(pos);
	}

	/**
	 * @brief draws the sword on the game board
	 */
	@Override
	protected void draw(Maze maze)
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