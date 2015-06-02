package lpoo.proj2.logic;

public class Player
{
	private Paddle paddle;
	private String name;
	private int color;
	private int score;
	private int streak;
	private int id;

	public Player()
	{
		this("", 0);
	}

	public Player(String paramName, int paramColor)
	{
		name = paramName;
		color = paramColor;
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

	public void setID(int paramId)
	{
		id = paramId;
	}

	public final String getName()
	{
		return name;
	}

	public final float getX()
	{
		return paddle.getX();
	}

	public final float getY()
	{
		return paddle.getY();
	}

	public final int getScore()
	{
		return score;
	}

	public final int getStreak()
	{
		return streak;
	}

	public void setX(float x)
	{
		paddle.setX(x);
	}

	public void setY(float y)
	{
		paddle.setY(y);
	}

	public void setName(String paramName)
	{
		name = paramName;
	}

	public void setColor(int paramColor)
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

	public final Paddle getPaddle()
	{
		return paddle;
	}

	public void setPaddle(Paddle paramPaddle)
	{
		paddle = paramPaddle;
	}
}