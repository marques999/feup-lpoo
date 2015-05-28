package lpoo.proj2.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;

public class Paddle extends DynamicEntity 
{
	private Vector2 velocity;
	private ShapeRenderer circle;
	private Color color;

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
		this.color = color;
		velocity = new Vector2(0, 0);
		circle = new ShapeRenderer();
		bounding.setRadius(getWidth() / 2);
	}

//	@Override
//	public void draw(SpriteBatch sb) {
//		circle.begin(ShapeType.Filled);
//		circle.setColor(color);
//		circle.circle(bounding.x, bounding.y, getRadius());
//		circle.end();
//	}

	public void update(float delta) {
		float dx = (getX() - old.x) / delta;
		float dy = (getY() - old.y) / delta;

		if (Math.abs(dx) < 0.1) {
			velocity.set(new Vector2(0.0f, dy));
		} else if (Math.abs(dy) < 0.1) {
			velocity.set(new Vector2(dx, 0.0f));
		} else {
			velocity.set(new Vector2(dx, dy));
		}

		old.x = getX();
		old.y = getY();

	}

	@Override
	public boolean collides(Paddle paddle) {
		if (this.getX() > paddle.getX() - paddle.getWidth()
				&& this.getX() < paddle.getX() + paddle.getWidth()
				&& this.getY() + this.getHeight() > paddle.getY()
				&& this.getY() < paddle.getY() + paddle.getHeight()) {
			return Intersector.overlaps(bounding, paddle.bounding);
		}

		return false;
	}

	@Override
	public boolean collides(Puck puck) {
		if (this.getX() > puck.getX() - puck.getWidth()
				&& this.getX() < puck.getX() + puck.getWidth()
				&& this.getY() + this.getHeight() > puck.getY()
				&& this.getY() < puck.getY() + puck.getHeight()) {
			return Intersector.overlaps(bounding, puck.bounding);
		}
		return false;
	}

	@Override
	public boolean collides(Wall wall) {
		if (this.getX() <= wall.getX() + wall.getWidth()
				|| this.getX() >= wall.getX()
				|| this.getY() <= wall.getY() + wall.getHeight()
				|| this.getY() >= wall.getY())
		{
			System.out.println("BOING!");
			return Intersector.overlaps(bounding, wall.bounding);
		}
		return false;
	}
}