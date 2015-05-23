package lpoo.proj2.logic;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;

public abstract class Entity
{
	protected float x;
	protected float y;
	protected Sprite sprite;
	protected World world;
	private float width;
	private float height;

	public Entity(float x, float y, World world)
	{
		this.x = x;
		this.y = y;
		this.world = world;
	}

	public Entity(float x, float y)
	{
		this(x, y, null);
	}

	public final float getX()
	{
		return this.x;
	}

	public final float getY()
	{
		return this.y;
	}

	protected void setPosition(final float x, final float y)
	{
		this.x = x;
		this.y = y;
	}

	protected void setSize(final float width, final float height)
	{
		this.width = width;
		this.height = height;
	}

	public final float getWidth()
	{
		return this.width;
	}

	public final float getHeight()
	{
		return this.height;
	}

	public final Sprite getSprite()
	{
		return this.sprite;
	}

	protected void setSprite(final Sprite sprite)
	{
		this.sprite = sprite;
	}

	public abstract void draw(SpriteBatch sb);
}