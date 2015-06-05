package lpoo.proj2.logic;

import com.badlogic.gdx.math.Rectangle;

public abstract class StaticEntity extends Entity
{
	private final Rectangle bounding;

	public StaticEntity(float paramX, float paramY)
	{
		this(paramX, paramY, 0);
	}

	public StaticEntity(float paramX, float paramY, int paramColor)
	{
		this(paramX, paramY, 0, 0, paramColor);
	}

	public StaticEntity(float paramX, float paramY, float paramWidth, float paramHeight)
	{
		this(paramX, paramY, paramWidth, paramHeight, 0);
	}

	public StaticEntity(float paramX, float paramY, float paramWidth, float paramHeight, int paramColor)
	{
		super(paramX, paramY, paramWidth, paramHeight, paramColor);
		bounding = new Rectangle(paramX, paramY, paramWidth, paramHeight);
	}

	public final Rectangle getBounding()
	{
		return bounding;
	}
}