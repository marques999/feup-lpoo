package lpoo.proj2.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public abstract class DynamicEntity extends Entity implements CollisionDetector
{
	protected Circle bounding;
	protected Vector2 velocity;

	public DynamicEntity(final float paramX, final float paramY)
	{
		this(paramX, paramY, 0);
	}

	public DynamicEntity(final float paramX, final float paramY, final int paramColor)
	{
		super(paramX, paramY, paramColor);
		
		bounding = new Circle(paramX, paramY, 1.0f);
	}

	public final Circle getBoundingCircle()
	{
		return bounding;
	}

	public final float getRadius()
	{
		return bounding.radius;
	}

	public final Vector2 getVelocity()
	{
		return velocity;
	}

	@Override
	public boolean collides(Paddle paddle)
	{
		return false;
	}

	@Override
	public boolean collides(Puck puck)
	{
		return false;
	}

	@Override
	public boolean collides(Wall wall)
	{
		return false;
	}

	@Override
	public boolean collides(Goal goal)
	{
		return false;
	}

	public boolean move(float x, float y)
	{
		setX(x <= bounds.maxX ? (x < bounds.minX ? bounds.minX : x)	: bounds.maxX);

		if (y > bounds.maxY)
		{
			setY(Gdx.graphics.getHeight() - bounds.maxY);
		}
		else if (y < bounds.minY)
		{
			setY(Gdx.graphics.getHeight() - bounds.minY);
		}
		else
		{
			setY(Gdx.graphics.getHeight() - y);
		}

		bounding.setPosition(getX(), getY());

		return true;
	}
}