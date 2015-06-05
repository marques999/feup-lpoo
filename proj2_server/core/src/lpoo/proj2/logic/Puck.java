package lpoo.proj2.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;

public final class Puck extends DynamicEntity
{
	private final float minimumDelta = 0.05f;
	
	public Puck(float x, float y, int color)
	{
		super(x, y, color);

		switch (color)
		{
		case 1:
			setSprite(new Sprite(new Texture(Gdx.files.internal("gfx/ball_green.png"))));
			break;
		case 2:
			setSprite(new Sprite(new Texture(Gdx.files.internal("gfx/ball_red.png"))));
			break;
		case 3:
			setSprite(new Sprite(new Texture(Gdx.files.internal("gfx/ball_yellow.png"))));
			break;
		case 4:
			setSprite(new Sprite(new Texture(Gdx.files.internal("gfx/ball_purple.png"))));
			break;
		case 5:
			setSprite(new Sprite(new Texture(Gdx.files.internal("gfx/ball_orange.png"))));
			break;
		default:
			setSprite(new Sprite(new Texture(Gdx.files.internal("gfx/ball_blue.png"))));
			break;
		}

		setBoundingCircle(x, y);
		setRadius(getWidth() / 2);
		setVelocity(new Vector2(-128, -128));
	}

	public void update(float delta)
	{
		getPosition().add(getVelocity().cpy().scl(delta));
		setBoundingCircle(getX(), getY());
	}

	@Override
	public boolean collides(Paddle paddle)
	{
		return false;
	}

	@Override
	public boolean collides(Puck puck)
	{
		if (Intersector.overlaps(getBoundingCircle(), puck.getBoundingCircle()))
		{
			if (!isColliding())
			{
				getVelocity().scl(-1);
				setColliding(true);
				return true;
			}
		}

		return false;
	}

	public void impulse(Vector2 velocity)
	{
		float dx = Math.abs(velocity.x);
		float dy = Math.abs(velocity.y);
		
		if (dx < minimumDelta || dy < minimumDelta)
		{
			if (dx < minimumDelta)
			{
				getVelocity().scl(-1, 1);
			}
			
			if (dy < minimumDelta)
			{
				getVelocity().scl(1, -1);
			}
		}
		else
		{
			getVelocity().set(velocity);
		}
	}

	@Override
	public boolean collides(Wall wall)
	{
		if (Intersector.overlaps(getBoundingCircle(), wall.getBoundingRectangle()))
		{
			if (!isColliding())
			{
				getVelocity().scl(wall.getNormal());
				getPosition().mulAdd(getVelocity(), 0.02f);
				getVelocity().scl(0.95f);
				
//				if (getPosition().dst(getX(), wall.getY()) < 16.0f || 
//					getPosition().dst(wall.getX(), getY()) < 16.0f)
//					{
//						
//					}
				setColliding(true);
				return true;
			}
		}
		else
		{
			setColliding(false);
		}

		return false;
	}

	@Override
	public boolean collides(Goal goal)
	{
		if (Intersector.overlaps(getBoundingCircle(), goal.getBoundingRectangle()))
		{
			if (!isColliding())
			{
				getVelocity().scl(-1);
				setColliding(true);
				return true;
			}
		}
		else
		{
			setColliding(false);
		}

		return false;
	}
}