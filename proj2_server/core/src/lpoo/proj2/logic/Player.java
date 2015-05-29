package lpoo.proj2.logic;

public class Player
{
	private String name;
	private boolean fb;
	private int score;
	private int streak;

	public Player(String str)
	{
		name = str;
		score = 0;
		streak = 0;
		fb = true;
	}

	public boolean firstBlood()
	{
		return fb;
	}

	public int getScore()
	{
		return score;
	}

	public int getStreak()
	{
		return streak;
	}

	public String getName()
	{
		return name;
	}

	public void resetScore()
	{
		score = 0;
		streak = 0;
		fb = true;
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