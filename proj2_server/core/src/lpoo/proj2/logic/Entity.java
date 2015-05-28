package lpoo.proj2.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;

public abstract class Entity
{
	protected Vector2 pos;
	protected Vector2 old;
	protected Bounds bounds;
	private Sprite sprite;
	protected Body body;
	protected BodyDef bodyDef;
	protected Fixture fixture;
	protected FixtureDef fixtureDef;
	private float width;
	private float height;

	public Entity(float x, float y)
	{
		this(x, y, 0.0f, 0.0f);
	}
	
	public Entity(float x, float y, float w, float h)
	{
		bounds = new Bounds(0.0f, 0.0f, 0.0f, 0.0f);
		width = w;
		height = h;
		pos = new Vector2(x, y);
		old = new Vector2(x, y);
	}

	public final float getX()
	{
		return pos.x;
	}

	public final float getY()
	{
		return pos.y;
	}
	
	public void setX(float x)
	{
		pos.x = x;
	}
	
	public void setY(float y)
	{
		pos.y = y;
	}

	protected void setPosition(final float x, final float y)
	{
		pos.set(x, y);
	}
	
	protected void setBody(Body body, BodyDef bodyDef)
	{
		this.body = body;
		this.bodyDef = bodyDef;
	}
	
	protected void setFixture(Fixture fixture)
	{
		this.fixture = fixture;
	}

	protected void setSize(final float width, final float height)
	{
		this.width = width;
		this.height = height;
	}

	public final float getWidth()
	{
		return width;
	}

	public final float getHeight()
	{
		return height;
	}

	public final Sprite getSprite()
	{
		return sprite;
	}

	protected void setSprite(final Sprite sprite)
	{
		this.sprite = sprite;
		this.sprite.setCenter(pos.x, pos.y);
		this.width = sprite.getWidth() * sprite.getScaleX();
		this.height = sprite.getHeight() * sprite.getScaleY();
		this.bounds.minX = this.width / 2;
		this.bounds.minY = this.height / 2;
		this.bounds.maxX = Gdx.graphics.getWidth() - this.bounds.minX;
		this.bounds.maxY = Gdx.graphics.getHeight() - this.bounds.minY;
	}

	public void draw(SpriteBatch sb)
	{
		sprite.setCenter(pos.x, pos.y);
		sprite.draw(sb);
	}
}