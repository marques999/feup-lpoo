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

		velocity = new Vector2(0, 0);
		bounding.setRadius(getWidth() / 2);
	}

	
	
	public void update(float delta)
	{
		float dx = (getX() - old.x) / delta;
		float dy = (getY() - old.y) / delta;

		if(Math.abs(dx) <= minimumSpeed && dx != 0.0f)
			dx = minimumSpeed * Math.signum(dx);
		else if(dx == 0.0f)
			dx = minimumSpeed;
		
		if(Math.abs(dy) <= minimumSpeed && dy != 0.0f)
			dy = minimumSpeed * Math.signum(dy);
		else if(dy == 0.0f)
			dy = minimumSpeed;
		
		velocity.set(new Vector2(dx, dy));
		System.out.println(velocity.toString());
		
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
			
			puck.pos.add(velocity.x/100, velocity.y/100);
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