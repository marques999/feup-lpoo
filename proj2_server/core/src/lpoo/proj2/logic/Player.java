package lpoo.proj2.logic;

public class Player
{
	private String name;
	private int color;
	private int score;
	private int streak;
	private int id;

	public Player(int paramId, String paramName, int paramColor)
	{
		name = paramName;
		color = paramColor;
		id = paramId;
		score = 0;
		streak = 0;
	}

	public final int getColor()
	{
		return color;
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