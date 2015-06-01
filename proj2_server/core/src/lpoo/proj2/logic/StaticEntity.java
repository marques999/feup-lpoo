package lpoo.proj2.logic;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;

public abstract class StaticEntity extends Entity
{
	protected Rectangle bounding;
	
	public StaticEntity(float x, float y)
	{
		this(x, y, Color.WHITE);
	}

	public StaticEntity(float x, float y, Color color)
	{
		this(x, y, 0, 0, color);
	}

	public StaticEntity(float x, float y, float w, float h)
	{
		this(x, y, w, h, Color.WHITE);
	}

	public StaticEntity(float x, float y, float w, float h, Color color)
	{
		super(x, y, w, h, color);
		bounding = new Rectangle(x, y, w, h);
	}

	public Rectangle getBoudingRectangle()
	{
		return bounding;
	}
}