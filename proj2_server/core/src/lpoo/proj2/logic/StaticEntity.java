package lpoo.proj2.logic;

import com.badlogic.gdx.math.Rectangle;

public abstract class StaticEntity extends Entity
{
	protected Rectangle bounding;
	
	public StaticEntity(float paramX, float y)
	{
		this(paramX, y, 0);
	}

	public StaticEntity(float paramX, float y, int color)
	{
		this(paramX, y, 0, 0, color);
	}

	public StaticEntity(float paramX, float y, float paramWidth, float paramHeight)
	{
		this(paramX, y, paramWidth, paramHeight, 0);
	}

	public StaticEntity(float paramX, float paramY, float paramWidth, float paramHeight, int paramColor)
	{
		super(paramX, paramY, paramWidth, paramHeight, paramColor);
		bounding = new Rectangle(paramX, paramY, paramWidth, paramHeight);
	}

	public final Rectangle getBoudingRectangle()
	{
		return bounding;
	}
}