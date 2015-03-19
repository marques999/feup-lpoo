package lpoo.logic;

public abstract class Entity
{
	protected Point pos;
	private int health;
	
	/**
	 * @brief default constructor for class 'Entity'
	 * @param x initial X coordinate for entity position
	 * @param y initial Y coordinate for entity position
	 */
	protected Entity(Point pos, int health)
	{
		this.pos = pos;
		this.health = health;
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
	 * @brief draws the entity on the game board
	 */
	protected abstract void draw(Maze maze);
	protected abstract void move(Maze maze, Direction direction);
}