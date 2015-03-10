package lpoo.logic;

public class Dart extends Item
{	
	/**
	 * @brief default constructor for class 'Sword'
	 */
	public Dart()
	{
		this(0, 0);
	}

	/**
	 * @brief constructor with parameters for class 'Sword'
	 * @param x
	 * @param y
	 */
	public Dart(int x, int y)
	{
		super(x, y);
	}
	
	/**
	 * @brief draws the sword at its corresponding position on the game board
	 */
	public final void draw(Maze maze)
	{
		if (!hasOwner())
		{
			if (maze.symbolAt(pos.x, pos.y) == 'D')
			{
				maze.placeSymbol(pos.x, pos.y, 'F');
			} 
			else
			{
				maze.placeSymbol(pos.x, pos.y, '*');
			}
		}
	}
	
	protected final char getDescription()
	{
		return '*';
	}
}