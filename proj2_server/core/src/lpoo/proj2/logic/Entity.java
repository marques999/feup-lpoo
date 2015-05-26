package lpoo.proj2.logic;

import com.badlogic.gdx.Gdx;
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
	protected float xMin;
	protected float yMin;
	protected float xMax;
	protected float yMax;
	private Sprite sprite;
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
		this.sprite.setCenter(x, y);
		this.width = sprite.getWidth() * sprite.getScaleX();
		this.height = sprite.getHeight() * sprite.getScaleY();
		this.xMin = this.width / 2;
		this.yMin = this.height / 2;
		this.xMax = Gdx.graphics.getWidth() - this.xMin;
		this.yMax = Gdx.graphics.getHeight() - this.yMin;
	}

	public void draw(SpriteBatch sb)
	{
		sprite.setCenter(x, y);
		sprite.draw(sb);
	}

	public abstract boolean move(float x, float y);
}