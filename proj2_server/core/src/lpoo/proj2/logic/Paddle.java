package lpoo.proj2.logic;

import lpoo.proj2.audio.AudioManager;
import lpoo.proj2.audio.SFX;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;

public class Paddle extends DynamicEntity
{
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
			setSprite(new Sprite(new Texture(Gdx.files.internal("gfx/paddle_blue.png"))));
			break;
		case 3:
			setSprite(new Sprite(new Texture(Gdx.files.internal("gfx/paddle_yellow.png"))));
			break;
		default:
			setSprite(new Sprite(new Texture(Gdx.files.internal("gfx/paddle_red.png"))));
			break;
		}

		setVelocity(new Vector2(0.0f, 0.0f));
		setRadius(getWidth() / 2);
	}
	

	public void update(float delta)
	{
		float dx = (getX() - getPreviousX()) / delta;
		float dy = (getY() - getPreviousY()) / delta;

		if (Math.abs(dx) < minimumSpeed && dx != 0.0f)
			dx = minimumSpeed * Math.signum(dx);
		else if (dx == 0.0f)
			dx = minimumSpeed;
	
		if (Math.abs(dy) < minimumSpeed && dy != 0.0f)
			dy = minimumSpeed * Math.signum(dy);
		else if (dy == 0.0f)
			dy = minimumSpeed;
		
		if (Math.abs(dx) > maximumSpeed)
			dx = maximumSpeed * Math.signum(dx);
		if (Math.abs(dy) > maximumSpeed)
			dy = maximumSpeed * Math.signum(dy);

		setVelocity(new Vector2(dx, dy));
		setPrevious(getX(), getY());
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
			if (!isColliding())
			{
				AudioManager.getInstance().playSound(SFX.SFX_PUCK_HIT);
				puck.impulse(getVelocity().scl(1 / 16f));
				setColliding(true);
				return true;
			}

			puck.getPosition().add(getVelocity().x / 100, getVelocity().y / 100);
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
		if (this.getX() <= wall.getX() + wall.getWidth() || this.getX() >= wall.getX() || this.getY() <= wall.getY() + wall.getHeight() || this.getY() >= wall.getY())
		{
			if (Intersector.overlaps(getBounding(), wall.getBounding()))
			{
				return true;
			}
		}

		return false;
	}
}