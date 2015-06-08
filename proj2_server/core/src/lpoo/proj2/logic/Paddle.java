package lpoo.proj2.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;

public final class Paddle extends DynamicEntity
{
	private static final float radiusAdjustment = 13.0f;
	private static final float minimumDelta = 0.05f;
	private static final float minimumSpeed = 200.0f;
	private static final float maximumSpeed = 800.0f;

	protected Paddle(float x, float y, int color)
	{
		super(x, y, color);

		switch (color)
		{
		case 1:
			setSprite(new Sprite(new Texture(Gdx.files.internal("gfx/paddle_green.png"))));
			break;
		case 2:
			setSprite(new Sprite(new Texture(Gdx.files.internal("gfx/paddle_red.png"))));
			break;
		case 3:
			setSprite(new Sprite(new Texture(Gdx.files.internal("gfx/paddle_yellow.png"))));
			break;
		case 4:
			setSprite(new Sprite(new Texture(Gdx.files.internal("gfx/paddle_purple.png"))));
			break;
		case 5:
			setSprite(new Sprite(new Texture(Gdx.files.internal("gfx/paddle_orange.png"))));
			break;
		default:
			setSprite(new Sprite(new Texture(Gdx.files.internal("gfx/paddle_blue.png"))));
			break;
		}

		setBoundingCircle(x, y);
		setRadius(getWidth() / 2 - radiusAdjustment);
	}

	public void update()
	{
		getPosition().add(getVelocity().cpy().scl(Gdx.graphics.getDeltaTime()));
		setBoundingCircle(getX(), getY());
	}

	public void update(float delta)
	{
		float dx = (getX() - getPreviousX()) / delta;
		float dy = (getY() - getPreviousY()) / delta;

		if (Math.abs(dx) < minimumSpeed)
		{
			dx = minimumSpeed * Math.signum(dx);
		}

		if (Math.abs(dy) < minimumSpeed)
		{
			dy = minimumSpeed * Math.signum(dy);
		}

		if (Math.abs(dx) > maximumSpeed)
		{
			dx = maximumSpeed * Math.signum(dx);
		}

		if (Math.abs(dy) > maximumSpeed)
		{
			dy = maximumSpeed * Math.signum(dy);
		}

		setBoundingCircle(getX(), getY());
		setVelocity(new Vector2(dx, dy));
		setPrevious(getX(), getY());
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
		return Intersector.overlaps(getBoundingCircle(), wall.getBoundingRectangle());
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
				puck.impulse(getVelocity());
				puck.getPosition().add(getVelocity().x / 100, getVelocity().y / 100);
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
		return false;
	}
}