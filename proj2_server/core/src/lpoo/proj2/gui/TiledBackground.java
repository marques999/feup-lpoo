package lpoo.proj2.gui;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class TiledBackground
{
	private int textureWidth;
	private int textureHeight;
	private boolean horizontal;
	private final Texture image;
	private final TextureRegion region;
	private Vector2 position;
	private Vector2 speed;

	public TiledBackground(FileHandle paramName, boolean paramHorizontal)
	{
		image = new Texture(paramName);
		horizontal = paramHorizontal;

		if (horizontal)
		{
			textureWidth = image.getWidth() << 1;
			textureHeight = image.getHeight();
		}
		else
		{
			textureWidth = image.getWidth() << 2;
			textureHeight = image.getHeight() << 2;
		}

		region = new TextureRegion(image, textureWidth, textureHeight);

		if (horizontal)
		{
			position = new Vector2(0, 0);
			speed = new Vector2(-textureWidth >> 2, 0);
		}
		else
		{
			position = new Vector2(-textureWidth / 2.0f, -textureWidth / 2.0f);
			speed = new Vector2(textureWidth >> 4, textureWidth >> 4);
		}

		image.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		image.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);

		if (!horizontal)
		{
			region.setU(-4.0f);
			region.setU2(0.0f);
			region.setV(-4.0f);
			region.setV2(0.0f);
		}
	}

	public void update(float delta)
	{
		position.add(speed.x * delta, speed.y * delta);

		if (horizontal)
		{
			if (position.x < -textureWidth >> 1)
			{
				position.x += textureWidth >> 1;
			}
		}
		else
		{
			if (position.x > 0)
			{
				position.x -= textureWidth >> 1;
			}
			else if (position.x < -textureWidth)
			{
				position.x += textureWidth >> 1;
			}
			if (position.y > 0)
			{
				position.y -= textureHeight >> 1;
			}
			else if (position.y < -textureHeight)
			{
				position.y += textureHeight >> 1;
			}
		}
	}

	public final void render(SpriteBatch sb)
	{
		sb.draw(region, position.x, position.y, textureWidth, textureHeight);
	}
}