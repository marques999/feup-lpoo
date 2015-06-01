package lpoo.proj2.gui;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class ScrollBackground
{
	private int textureWidth = 1600;
	private int textureHeight = 1600;
	private Texture image;
	private TextureRegion region;
	private Vector2 position;
	private Vector2 speed;

	public ScrollBackground(FileHandle name, int width, int height)
	{
		image = new Texture(name);
		textureWidth = width;
		textureHeight = height;
		region = new TextureRegion(image, textureWidth, textureHeight);
		position = new Vector2(-textureWidth / 2.0f, -textureWidth / 2.0f);
		speed = new Vector2(textureWidth >> 4, textureWidth >> 4);
		image.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		image.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
		region.setU(-4.0f);
		region.setU2(0.0f);
		region.setV(-4.0f);
		region.setV2(0.0f);
	}
	
	public void update(float delta)
	{
		position.add(speed.x * delta, speed.y * delta);

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

	public void render(SpriteBatch sb)
	{
		sb.draw(region, position.x, position.y, textureWidth, textureHeight);
	}
}