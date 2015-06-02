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
		default:
			setSprite(new Sprite(new Texture(Gdx.files.internal("gfx/ball_blue.png"))));
			break;
		}

		velocity = new Vector2(-128, -128);
		acceleration = new Vector2(-8, -8);
		bounding.setRadius(getWidth() / 2);
	}

	public void update(float delta)
	{
		velocity.add(acceleration.cpy().scl(delta));
		pos.add(velocity.cpy().scl(delta));
		bounding.x = getX();
		bounding.y = getY();
	}

	@Override
	public boolean collides(Paddle paddle)
	{
		if (Intersector.overlaps(bounding, paddle.bounding))
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
		if (Intersector.overlaps(bounding, puck.bounding))
		{
			acceleration.scl(-1);
			velocity.scl(-1);
			return true;
		}

		return false;
	}

	public void impulse(Vector2 velocity)
	{
		this.pos.add(velocity);
		this.velocity.add(velocity.scl(2));
	}

	@Override
	public boolean collides(Wall wall)
	{
		if (Intersector.overlaps(bounding, wall.bounding))
		{
			if (!isColliding())
			{
				AudioManager.getInstance().playSound(SFX.SFX_PUCK_HIT);
				acceleration.scl(-1);
				velocity.scl(-1);
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
		if (Intersector.overlaps(bounding, goal.bounding))
		{
			if (!isColliding())
			{
				AudioManager.getInstance().playSound(SFX.SFX_PUCK_HIT);
				acceleration.scl(-1);
				velocity.scl(-1);
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