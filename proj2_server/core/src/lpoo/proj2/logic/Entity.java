package lpoo.proj2.logic;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;

public abstract class Entity
{
	protected float x;
	protected float y;
	protected Sprite sprite;
	protected Body body;
	protected BodyDef bodyDef;
	protected Fixture fixture;
	protected FixtureDef fixtureDef;
	private float width;
	private float height;

	public Entity(float x, float y)
	{
		this.x = x;
		this.y = y;
	}

	public final float getX()
	{
		return this.x;
	}

	public final float getY()
	{
		return this.y;
	}

	protected void setPosition(final float x, final float y)
	{
		this.x = x;
		this.y = y;
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
		return this.width;
	}

	public final float getHeight()
	{
		return this.height;
	}

	public final Sprite getSprite()
	{
		return this.sprite;
	}

	protected void setSprite(final Sprite sprite)
	{
		this.sprite = sprite;
	}

	public void draw(SpriteBatch sb)
	{
		sprite.setCenter(x, y);
		sprite.draw(sb);
	}
	

	public abstract boolean move(float x, float y);
}