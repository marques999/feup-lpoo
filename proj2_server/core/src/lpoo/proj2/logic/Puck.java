package lpoo.proj2.logic;

import java.util.Random;
import lpoo.proj2.audio.AudioManager;
import lpoo.proj2.audio.SFX;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;

public class Puck extends DynamicEntity
{
	private Vector2 acceleration;
	private ShapeRenderer circle = new ShapeRenderer();

	public Puck(float x, float y, Color color)
	{
		super(x, y, color);
		
		Sprite sprite;

		if (color == Color.GREEN)
		{
			sprite = new Sprite(new Texture(Gdx.files.internal("gfx/ball_green.png")));
		}
		else if (color == Color.BLUE)
		{
			sprite = new Sprite(new Texture(Gdx.files.internal("gfx/ball_blue.png")));
		}
		else if (color == Color.YELLOW)
		{
			sprite = new Sprite(new Texture(Gdx.files.internal("gfx/ball_yellow.png")));
		}
		else
		{
			sprite = new Sprite(new Texture(Gdx.files.internal("gfx/ball_red.png")));
		}
		
		velocity = new Vector2(-128, -128);
		acceleration = new Vector2(-8, -8);
		setSprite(sprite);
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

	@Override
	public void draw(SpriteBatch sb)
	{
		circle.begin(ShapeType.Filled);
		circle.setColor(getColor());
		circle.circle(bounding.x, bounding.y, getRadius());
		circle.end();

	}
}