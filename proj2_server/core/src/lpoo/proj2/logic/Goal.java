package lpoo.proj2.logic;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Goal extends Entity
{
	private Color _color;
	private ShapeRenderer _renderer;

	public Goal(float x, float y, Color color)
	{
		super(x, y);

		_color = color;
		_renderer = new ShapeRenderer();
	}

	public Goal(float x, float y)
	{
		this(x, y, Color.WHITE);
	}

	@Override
	public void draw(SpriteBatch sb)
	{
		_renderer.begin(ShapeRenderer.ShapeType.Filled);
		_renderer.setColor(_color);
		_renderer.rectLine(x, y, x, y + height, 1.0f);
		_renderer.rectLine(x, y + height, x + width, y + height, 1.0f);
		_renderer.rectLine(x + width, y, x + width, y + height, 1.0f);
		_renderer.end();
	}
}