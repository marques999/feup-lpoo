package lpoo.proj2.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Puck extends Entity implements DynamicEntity
{
	public Puck(float x, float y, Color color)
	{
		super(x, y);
		
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

		sprite.setCenter(x, y);
		sprite.setScale(0.5f, 0.5f);
		setSprite(sprite);
	}

	@Override
	public void setPosition(final float posX, final float posY)
	{
		bodyDef.position.set(posX, posY);
	}

	@Override
	public boolean collide(final Entity entity)
	{
		return false;
	}

	@Override
	public boolean move(final float x, final float y)
	{
		return false;
	}
}