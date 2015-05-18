package lpoo.proj2.logic;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Entity
{
	protected float x;
	protected float y;
	protected int width;
	protected int height;

	public Entity(float x, float y)
	{
		this.x = x;
		this.y = y;
	}

	public final float getX()
	{
		return this.x;
	}

	public final float getY()
	{
		return this.y;
	}

	protected void setPosition(float x, float y)
	{
		this.x = x;
		this.y = y;
	}

	protected void setSize(int width, int height)
	{
		this.width = width;
		this.height = height;
	}

	public abstract void draw(SpriteBatch sb);
}