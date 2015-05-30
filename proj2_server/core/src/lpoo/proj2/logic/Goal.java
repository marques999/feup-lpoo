package lpoo.proj2.logic;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Goal extends StaticEntity
{
	private ShapeRenderer shape;

	protected Goal(float x, float y, float width, float height)
	{
		super(x, y, width, height);

		shape = new ShapeRenderer();
	}

	@Override
	public void draw(SpriteBatch sb)
	{
		shape.begin(ShapeType.Filled);
		shape.setColor(Color.WHITE);
		shape.rect(getX(), getY(), getWidth(), getHeight());
		shape.end();
	}
}