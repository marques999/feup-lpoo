package lpoo.proj2.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Paddle extends Entity implements DynamicEntity
{	
	protected Paddle(float x, float y, Color color)
	{
		super(x, y);
		
		Sprite sprite;
		
		if (color == Color.GREEN)
		{
			sprite = new Sprite(new Texture(Gdx.files.internal("gfx/paddle_green.png")));
		}
		else if (color == Color.BLUE)
		{
			sprite = new Sprite(new Texture(Gdx.files.internal("gfx/paddle_blue.png")));
		}
		else if (color == Color.YELLOW)
		{
			sprite = new Sprite(new Texture(Gdx.files.internal("gfx/paddle_yellow.png")));
		}
		else
		{
			sprite = new Sprite(new Texture(Gdx.files.internal("gfx/paddle_red.png")));
		}
		
		setSprite(sprite);
	}

	public boolean move(float x, float y)
	{
		if (x < xMin)
		{
			this.x = xMin;
		}
		else if (x > xMax)
		{
			this.x = xMax;
		}
		else
		{
			this.x = x; 
		}
		
		if (y > yMax)
		{
			this.y = Gdx.graphics.getHeight() - yMax;
		}
		else if (y < yMin)
		{
			this.y = Gdx.graphics.getHeight() - yMin;
		}
		else
		{
			this.y = Gdx.graphics.getHeight() - y;
		}

		return true;
	}

	@Override
	public boolean collide(Entity entity)
	{
		return false;
	}
}