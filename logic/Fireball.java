package lpoo.logic;

public class Fireball extends Item
{	
	/**
	 * @brief default constructor for class 'Dart'
	 */
	public Fireball()
	{
		this(0, 0);
	}

	/**
	 * @brief constructor with parameters for class 'Dart'
	 * @param x X coordinate for sword position
	 * @param y Y coordinate for sword position
	 */
	public Fireball(int x, int y)
	{
		super(x, y);
	}
	
	/**
	 * @brief draws the sword at its corresponding position on the game board
	 */
	public final void draw(Maze maze)
	{
		maze.placeSymbol(pos.x, pos.y, 'o');
	}
	
	/**
	 * @return returns a symbol
	 */
	protected final char type()
	{
		return '*';
	}
}