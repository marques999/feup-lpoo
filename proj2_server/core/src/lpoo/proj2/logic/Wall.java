package lpoo.proj2.logic;

import com.badlogic.gdx.math.Vector2;

public class Wall extends StaticEntity
{
	private final Vector2 normal = new Vector2(0.0f, 0.f);

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
}