package lpoo.proj2.logic;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;

public class Wall extends Entity
{
	public Wall(int x, int y, int width, Color color, World world)
	{
		super(x, y, world);
	}

	@Override
	public void draw(SpriteBatch sb)
	{
	}
}
