package lpoo.proj2.logic;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity
{
	private Vector2 pos;
	private Vector2 old;

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

	public final Vector2 getPosition()
	{
		return pos;
	}

	public final Vector2 getPrevious()
	{
		return old;
	}

	public final float getPreviousX()
	{
		return old.x;
	}

	public final float getPreviousY()
	{
		return old.y;
	}

	public void setPrevious(float paramX, float paramY)
	{
		old.set(paramX, paramY);
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
	}

	public void draw(SpriteBatch sb)
	{
		sprite.setCenter(pos.x, pos.y);
		sprite.draw(sb);
	}
}