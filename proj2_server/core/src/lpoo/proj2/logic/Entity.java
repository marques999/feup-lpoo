package lpoo.proj2.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity
{
	protected Vector2 pos;
	protected Vector2 old;
	protected Bounds bounds;
	private int color;
	private boolean colliding;
	private Sprite sprite;
	private float width;
	private float height;

	public Entity(float paramX, float paramY, int paramColor)
	{
		this(paramX, paramY, 0.0f, 0.0f, paramColor);
	}

	public Entity(float paramX, float paramY, float paramWidth, float paramHeight, int paramColor)
	{
		bounds = new Bounds(0.0f, 0.0f, 0.0f, 0.0f);
		pos = new Vector2(paramX, paramY);
		old = new Vector2(paramX, paramY);
		colliding = false;
		color = paramColor;
		width = paramWidth;
		height = paramHeight;
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
	
	public final float getWidth()
	{
		return width;
	}

	public final float getHeight()
	{
		return height;
	}
	
	public final int getColor()
	{
		return color;
	}

	public final Sprite getSprite()
	{
		return sprite;
	}

	public void setX(final float paramX)
	{
		pos.x = paramX;
	}

	public void setY(final float paramY)
	{
		pos.y = paramY;
	}
	
	protected void setColliding(boolean paramColliding)
	{
		colliding = paramColliding;
	}
	
	public void setColor(int paramColor)
	{
		color = paramColor;
	}

	protected void setPosition(final float paramX, final float paramY)
	{
		pos.set(paramX, paramY);
	}

	protected void setSize(final float paramWidth, final float paramHeight)
	{
		width = paramWidth;
		height = paramHeight;
	}

	protected void setSprite(Sprite paramSprite)
	{
		sprite = paramSprite;
		sprite.setCenter(pos.x, pos.y);
		width = paramSprite.getWidth() * paramSprite.getScaleX();
		height = paramSprite.getHeight() * paramSprite.getScaleY();
		bounds.minX = width / 2;
		bounds.minY = height / 2;
		bounds.maxX = Gdx.graphics.getWidth() - bounds.minX;
		bounds.maxY = Gdx.graphics.getHeight() - bounds.minY;
	}

	public void draw(SpriteBatch sb)
	{
		sprite.setCenter(pos.x, pos.y);
		sprite.draw(sb);
	}
}