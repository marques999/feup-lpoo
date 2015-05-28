package lpoo.proj2.logic;

import com.badlogic.gdx.math.Rectangle;

public abstract class StaticEntity extends Entity 
{
	public StaticEntity(float x, float y) 
	{
		this(x, y, 0, 0);
	}
	
	public StaticEntity(float x, float y, float w, float h)
	{
		super(x, y, w, h);
		
		bounding = new Rectangle(x, y, w, h);
	}

	protected Rectangle bounding;
	
	public Rectangle getBoudingRectangle()
	{
		return this.bounding;
	}
}
