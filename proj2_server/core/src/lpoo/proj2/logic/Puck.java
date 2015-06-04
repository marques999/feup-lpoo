package lpoo.proj2.logic;

import lpoo.proj2.audio.AudioManager;
import lpoo.proj2.audio.SFX;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;

public class Puck extends DynamicEntity
{
	private Vector2 acceleration;

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

		setRadius(getWidth() / 2);
		setVelocity(new Vector2(-128, -128));
		acceleration = new Vector2(-8, -8);
	}

	public void update(float delta)
	{
		getVelocity().add(acceleration.cpy().scl(delta));
		getPosition().add(getVelocity().cpy().scl(delta));
		setBounding(getX(), getY());
	}

	@Override
	public boolean collides(Paddle paddle)
	{
		if (Intersector.overlaps(getBounding(), paddle.getBounding()))
		{
			if (!isColliding())
			{
				AudioManager.getInstance().playSound(SFX.SFX_PUCK_HIT);
			}

			setColliding(true);
		}
		else
		{
			setColliding(false);
		}

		return false;
	}

	@Override
	public boolean collides(Puck puck)
	{
		if (Intersector.overlaps(getBounding(), puck.getBounding()))
		{
			acceleration.scl(-1);
			getVelocity().scl(-1);
			return true;
		}

		return false;
	}

	public void impulse(Vector2 velocity)
	{
		getVelocity().mulAdd(velocity, 2);
	}

	@Override
	public boolean collides(Wall wall)
	{
		if (Intersector.overlaps(getBounding(), wall.getBounding()))
		{
			if (!isColliding())
			{
				AudioManager.getInstance().playSound(SFX.SFX_PUCK_HIT);
				acceleration.scl(wall.getNormal());
				getVelocity().scl(wall.getNormal());
			}
			
			setColliding(true);
			
			return true;
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
		if (Intersector.overlaps(getBounding(), goal.getBounding()))
		{
			if (!isColliding())
			{
				AudioManager.getInstance().playSound(SFX.SFX_PUCK_HIT);
				acceleration.scl(-1);
				getVelocity().scl(-1);
				setColliding(true);

				return true;
			}

			setColliding(true);
		}
		else
		{
			setColliding(false);
		}

		return false;
	}
}