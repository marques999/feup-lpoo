package lpoo.proj2.logic;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Wall extends StaticEntity
{
	private ShapeRenderer shape;
	private Color color;

	public Wall(float x, float y, int width, int height, Color color)
	{
		super(x, y, width, height);
		this.color = color;
		this.shape = new ShapeRenderer();
	}

	@Override
	public void draw(SpriteBatch sb)
	{
		shape.begin(ShapeType.Filled);
		shape.setColor(color);
		shape.rect(getX(), getY(), getWidth(), getHeight());
		shape.end();
	}
}