package lpoo.proj2.logic;

import com.badlogic.gdx.graphics.Color;

public class Player
{
	private Color color;
	private Paddle paddle;
	private String name;
	private int score;
	private int streak;
	private int id;

	public Player()
	{
		this("", Color.RED);
	}
	
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
	
	public int getID()
	{
		return this.id;
	}
	
	public void setID(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return this.name;
	}
	
	public final float getX()
	{
		return this.paddle.getX();
	}
	
	public final float getY()
	{
		return this.paddle.getY();
	}
	
	public void setX(float x)
	{
		this.paddle.setX(x);
	}
	
	public void setY(float y)
	{
		this.paddle.setY(y);
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

	public void setName(String name)
	{
		this.name = name;
	}

	public void setColor(Color color)
	{
		this.color = color;
	}

	public Paddle getPaddle()
	{
		return paddle;
	}

	public void setPaddle(Paddle paddle)
	{
		this.paddle = paddle;
	}
}