package lpoo.logic;

public abstract class Entity
{
	protected Point pos;
	private int health;
	
	/**
	 * @brief default constructor for class 'Entity'
	 */
	protected Entity()
	{
		this(0, 0, 0);
	}

	/**
	 * @brief constructor with parameters for class 'Entity'
	 * @param x initial X coordinate for entity position
	 * @param y initial Y coordinate for entity position
	 */
	protected Entity(Point pos, int health)
	{
		this.pos = pos;
		this.health = health;
	}
	
	/**
	 * @brief constructor with three parameters for class 'Entity'
	 * @param x initial X coordinate for entity position
	 * @param y initial Y coordinate for entity position
	 * @param health initial entity health
	 */
	protected Entity(int x, int y, int health)
	{
		this(new Point(x, y), health);
	}
	
	/**
	 * @brief returns the entity's current position x coordinate
	 */
	public final int getX()
	{
		return pos.x;
	}
	
	/**
	 * @brief returns the entity's current position y coordinate
	 */
	public final int getY()
	{
		return pos.y;
	}
	
	/**
	 * @brief changes the entity's current position x coordinate
	 * @param x new value for x coordinate
	 */
	protected void setX(int x)
	{
		this.pos.x = x;
	}

	/**
	 * @brief changes the entity's current position y coordinate
	 * @param y new value for y coordinate
	 */
	protected void setY(int y)
	{
		this.pos.y = y;
	}
	
	/**
	 * @return returns the entity's current health
	 */
	public final int getHealth()
	{
		return this.health;
	}
	
	/**
	 * @brief changes the entity's current health
	 * @param health new value for entity's health
	 */
	protected void setHealth(int health)
	{
		this.health = health;
	}
	
	/**
	 * @return returns the entity's current position
	 */
	public final Point getPosition()
	{
		return this.pos;
	}

	/**
	 * @brief changes the entity's current position
	 * @param pos new coordinates for dragon position
	 */
	protected void setPosition(Point pos)
	{
		this.pos = pos;
	}
	
	/**
	 * @brief draws the entity on the game board
	 */
	protected abstract void draw(Maze maze);
	protected abstract void move(Maze maze, Direction direction);
}
