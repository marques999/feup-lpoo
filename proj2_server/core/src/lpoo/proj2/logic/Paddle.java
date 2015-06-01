package lpoo.proj2.logic;

import lpoo.proj2.audio.AudioManager;
import lpoo.proj2.audio.SFX;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;

public class Paddle extends DynamicEntity
{
	protected Paddle(float x, float y, Color color)
	{
		super(x, y, color);

		Sprite sprite;

		if (color == Color.GREEN)
		{
			sprite = new Sprite(new Texture(Gdx.files.internal("gfx/paddle_green.png")));
		}
		else if (color == Color.BLUE)
		{
			sprite = new Sprite(new Texture(Gdx.files.internal("gfx/paddle_blue.png")));
		}
		else if (color == Color.YELLOW)
		{
			sprite = new Sprite(new Texture(Gdx.files.internal("gfx/paddle_yellow.png")));
		}
		else
		{
			sprite = new Sprite(new Texture(Gdx.files.internal("gfx/paddle_red.png")));
		}

		setSprite(sprite);
		velocity = new Vector2(0, 0);
		bounding.setRadius(getWidth() / 2);
	}

	public void update(float delta)
	{
		float dx = (getX() - old.x) / delta;
		float dy = (getY() - old.y) / delta;

		if (Math.abs(dx) < 0.1)
		{
			velocity.set(new Vector2(0.0f, dy));
		}
		else if (Math.abs(dy) < 0.1)
		{
			velocity.set(new Vector2(dx, 0.0f));
		}
		else
		{
			velocity.set(new Vector2(dx, dy));
		}

		old.x = getX();
		old.y = getY();
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
			if (!isColliding())
			{
				AudioManager.getInstance().playSound(SFX.SFX_PUCK_HIT);
				puck.impulse(velocity.scl(1 / 16f));
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

	@Override
	public boolean collides(Wall wall)
	{
		if (this.getX() <= wall.getX() + wall.getWidth()
				|| this.getX() >= wall.getX()
				|| this.getY() <= wall.getY() + wall.getHeight()
				|| this.getY() >= wall.getY())
		{
			if (Intersector.overlaps(bounding, wall.bounding))
			{
				return true;
			}
		}

		return false;
	}
}