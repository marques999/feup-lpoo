package lpoo.logic;

public class Sword extends Item
{
	/**
	 * @brief default constructor for class 'Sword'
	 */
	public Sword()
	{
		this(0, 0);
	}

	/**
	 * @brief constructor with parameters for class 'Sword'
	 * @param x
	 * @param y
	 */
	public Sword(int x, int y)
	{
		super(x, y);
	}

	/**
	 * @brief draws the sword at its corresponding position on the game board
	 */
	public final void draw(Maze maze)
	{
		if (maze.m[pos.y][pos.x] == 'D')
		{
			maze.m[pos.y][pos.x] = 'F';
		} 
		else
		{
			maze.m[pos.y][pos.x] = 'E';
		}
	}
}