package lpoo.proj2.logic;

import java.util.Random;
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
	private Vector2 velocity;
	private Vector2 acceleration;
	private ShapeRenderer circle;
	private Color color;
	private Random rand = new Random();

	public Puck(float x, float y, Color color)
	{
		super(x, y);
		this.color = color;
		Sprite sprite;

		if (color == Color.GREEN)
		{
			sprite = new Sprite(new Texture(Gdx.files.internal("gfx/ball_green.png")));
		} else if (color == Color.BLUE)
		{
			sprite = new Sprite(new Texture(Gdx.files.internal("gfx/ball_blue.png")));
		} else if (color == Color.YELLOW)
		{
			sprite = new Sprite(new Texture(Gdx.files.internal("gfx/ball_yellow.png")));
		} else
		{
			sprite = new Sprite(new Texture(Gdx.files.internal("gfx/ball_red.png")));
		}
		velocity = new Vector2(-128, -128);
		circle = new ShapeRenderer();
		acceleration = new Vector2(rand.nextInt() % 50, rand.nextInt() % 50);
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
		if (this.getX() > paddle.getX() - paddle.getWidth()
				&& this.getX() < paddle.getX() + paddle.getWidth()
				&& this.getY() + this.getHeight() > paddle.getY()
				&& this.getY() < paddle.getY() + paddle.getHeight())
		{
			if (Intersector.overlaps(bounding, paddle.bounding))
			{
				acceleration.scl(-1);
				velocity.scl(-1);
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean collides(Puck puck)
	{
		if (this.getX() > puck.getX() - puck.getWidth()
				&& this.getX() < puck.getX() + puck.getWidth()
				&& this.getY() + this.getHeight() > puck.getY()
				&& this.getY() < puck.getY() + puck.getHeight())
		{
			if (Intersector.overlaps(bounding, puck.bounding))
			{
				acceleration.scl(-1);
				velocity.scl(-1);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean collides(Wall wall)
	{
		if (Intersector.overlaps(bounding, wall.bounding))
		{
			acceleration.scl(-1);
			velocity.scl(-1);
			return true;
		}
		return false;
	}

	@Override
	public void draw(SpriteBatch sb)
	{
		circle.begin(ShapeType.Filled);
		circle.setColor(color);
		circle.circle(bounding.x, bounding.y, getRadius());
		circle.end();

	}
}