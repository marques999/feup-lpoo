package lpoo.proj2.logic;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Wall extends StaticEntity
{
	private final ShapeRenderer shape = new ShapeRenderer();

	public Wall(float paramX, float paramY, int paramWidth, int paramHeight, int paramColor)
	{
		super(paramX, paramY, paramWidth, paramHeight, paramColor);
	}

	@Override
	public void draw(SpriteBatch paramBatch)
	{
		shape.begin(ShapeType.Filled);
		shape.setColor(Color.WHITE);
		shape.rect(getX(), getY(), getWidth(), getHeight());
		shape.end();
	}
}