package lpoo.logic;

public final class Sword extends Item
{
	/**
	 * @brief default constructor for class 'Sword'
	 */
	protected Sword()
	{
		this(0, 0);
	}

	/**
	 * @brief constructor with parameters for class 'Sword'
	 * @param x initial X coordinate for sword position
	 * @param y initial Y coordinate for sword position
	 */
	protected Sword(int x, int y)
	{
		super(x, y);
	}
	
	protected final char getDescription()
	{
		return 'E';
	}

	/**
	 * @brief draws the sword on the game board
	 */
	protected final void draw(Maze maze)
	{
		if (!hasOwner())
		{
			if (maze.symbolAt(pos.x, pos.y) == 'D')
			{
				maze.placeSymbol(pos.x, pos.y, 'F');
			} 
			else
			{
				maze.placeSymbol(pos.x, pos.y, 'E');
			}
		}
	}
}