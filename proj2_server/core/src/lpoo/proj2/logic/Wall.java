package lpoo.proj2.logic;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class Wall extends StaticEntity
{
	private final ShapeRenderer shape = new ShapeRenderer();
	private Vector2 normal;

	public Wall(float paramX, float paramY, int paramWidth, int paramHeight, int paramColor)
	{
		super(paramX, paramY, paramWidth, paramHeight, paramColor);
		normal = new Vector2(0f, 0f);
	}

	public void setNormal(float x, float y)
	{
		normal.set(x, y);
	}
	
	public Vector2 getNormal()
	{
		return this.normal;
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