package lpoo.proj2.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity
{
	protected Vector2 pos;
	protected Vector2 old;
	protected Bounds bounds;
	private Color color;
	private boolean colliding;
	private Sprite sprite;
	private float width;
	private float height;

	public Entity(float x, float y, Color color)
	{
		this(x, y, 0.0f, 0.0f, color);
	}

	public Entity(float x, float y, float w, float h, Color c)
	{
		bounds = new Bounds(0.0f, 0.0f, 0.0f, 0.0f);
		color = c;
		colliding = false;
		width = w;
		height = h;
		pos = new Vector2(x, y);
		old = new Vector2(x, y);
	}

	public final boolean isColliding()
	{
		return colliding;
	}
	public final float getX()
	{
		return pos.x;
	}

	public final float getY()
	{
		return pos.y;
	}

	public final Color getColor()
	{
		return color;
	}

	public void setColor(Color color)
	{
		this.color = color;
	}

	public void setX(float x)
	{
		pos.x = x;
	}

	public void setY(float y)
	{
		pos.y = y;
	}
	
	protected void setColliding(boolean colliding)
	{
		this.colliding = colliding;
	}

	protected void setPosition(final float x, final float y)
	{
		pos.set(x, y);
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