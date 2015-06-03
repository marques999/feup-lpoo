package lpoo.proj2.logic;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class Wall extends StaticEntity
{
	private final ShapeRenderer shape = new ShapeRenderer();
	private final Vector2 normal = new Vector2(0f, 0f);

	public Wall(float paramX, float paramY, int paramWidth, int paramHeight, int paramColor)
	{
		super(paramX, paramY, paramWidth, paramHeight, paramColor);
	}

	public Vector2 getNormal()
	{
		return normal;
	}

	public void setNormal(float paramX, float paramY)
	{
		normal.set(paramX, paramY);
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