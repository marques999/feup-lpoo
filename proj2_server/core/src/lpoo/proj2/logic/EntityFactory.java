package lpoo.proj2.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

public class EntityFactory
{	
	public Puck createPuck(Color color)
	{
		return new Puck(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, color);
	}
	
	private Paddle createPaddle(float x, float y, Color color)
	{
		return new Paddle(x, y, color);
	}
	
	public Paddle createP1Paddle(Color color)
	{
		return createPaddle(Gdx.graphics.getWidth() / 2, 64, color);
	}
	
	public Paddle createP2Paddle(Color color)
	{
		return createPaddle(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() - 64, color);
	}
	
	private Goal createGoal(float x, float y)
	{
		return new Goal(x, y, 300, 64);
	}
	
	public Goal createP1Goal()
	{
		return createGoal(Gdx.graphics.getWidth() / 2, 0);
	}
	
	public Goal createP2Goal()
	{
		return createGoal(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight());
	}
}
