package lpoo.proj2.logic;

import com.badlogic.gdx.graphics.Color;

public class Player
{
	private Color color;
	private String name;
	private int score;
	private int streak;

	public Player(String name, Color color)
	{
		this.name = name;
		this.color = color;
		this.score = 0;
		this.streak = 0;
	}

	public Color getColor()
	{
		return this.color;
	}

	public String getName()
	{
		return this.name;
	}

	public int getScore()
	{
		return this.score;
	}

	public int getStreak()
	{
		return this.streak;
	}

	public void resetScore()
	{
		this.score = 0;
		this.streak = 0;
	}

	public void resetStreak()
	{
		this.streak = 0;
	}

	public void score()
	{
		this.score++;
		this.streak++;
	}
}