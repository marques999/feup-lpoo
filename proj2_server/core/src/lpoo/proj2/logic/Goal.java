package lpoo.proj2.logic;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Goal extends StaticEntity
{
	private final ShapeRenderer shape = new ShapeRenderer();

	protected Goal(float paramX, float paramY, float paramWidth, float paramHeight)
	{
		super(paramX, paramY, paramWidth, paramHeight);
	}

	@Override
	public void draw(SpriteBatch paramBatch)
	{
		shape.begin(ShapeType.Filled);
		shape.setColor(Color.BLACK);
		shape.rect(getX(), getY(), getWidth(), getHeight());
		shape.end();
	}
}