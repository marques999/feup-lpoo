package lpoo.proj2;

public class Player
{
	private String name;
	private int color;
	private int score;
	private int streak;
	private int id;
	private float x;
	private float y;

	public Player()
	{
		this("", 0);
	}

	public Player(String paramName, int paramColor)
	{
		this(0, paramName, paramColor);
	}
	
	public Player(int paramId, String paramName, int paramColor)
	{
		name = paramName;
		color = paramColor;
		id = paramId;
		score = 0;
		streak = 0;
		x = 0;
		y = 0;
	}

	public final int getColor()
	{
		return color;
	}
	
	public final float getX()
	{
		return x;
	}
	
	public final float getY()
	{
		return y;
	}

	public void setX(float paramX)
	{
		x = paramX;
	}
	
	public void setY(float paramY)
	{
		y = paramY;
	}
	
	public final int getID()
	{
		return id;
	}

	public final String getName()
	{
		return name;
	}

	public final int getScore()
	{
		return score;
	}

	public final int getStreak()
	{
		return streak;
	}

	public void setName(final String paramName)
	{
		name = paramName;
	}
	
	public void setID(final int paramId)
	{
		id = paramId;
	}

	public void setColor(final int paramColor)
	{
		color = paramColor;
	}

	public void resetScore()
	{
		score = 0;
		streak = 0;
	}

	public void resetStreak()
	{
		streak = 0;
	}

	public void score()
	{
		score++;
		streak++;
	}
}