package lpoo.logic;

public final class Shield extends Item 
{
	/**
	 * @brief default constructor for class 'Shield'
	 * @param pos initial shield position
	 */
	protected Shield(Point pos) 
	{
		super(pos);
	}

	/**
	 * @brief draws the shield at its corresponding position on the game board
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
			maze.placeSymbol(pos.x, pos.y, 'V');
		}
	}
}