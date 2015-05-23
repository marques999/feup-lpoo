package lpoo.proj2.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class Puck extends Entity implements DynamicEntity
{
	private final Body ballBody;
	private final BodyDef ballBodyDef;
	private final Fixture ballFixture;
	private final FixtureDef ballFixtureDef;

	public Puck(float x, float y, Color color, World world)
	{
		super(x, y, world);

		ballBodyDef = new BodyDef();
		ballBodyDef.type = BodyDef.BodyType.DynamicBody;
		ballBodyDef.position.set(x, y);

		final CircleShape ballShape = new CircleShape();

		ballShape.setRadius(0.50f);

		ballFixtureDef = new FixtureDef();
		ballFixtureDef.shape = ballShape;
		ballFixtureDef.density = 1;
		ballBody = world.createBody(ballBodyDef);
		ballFixture = ballBody.createFixture(ballFixtureDef);
		ballBody.setLinearVelocity(-10, 0.1f);
		ballShape.dispose();

		if (color == Color.GREEN)
		{
			sprite = new Sprite(new Texture(Gdx.files.internal("gfx/ball_green.png")));
		}
		else if (color == Color.BLUE)
		{
			sprite = new Sprite(new Texture(Gdx.files.internal("gfx/ball_blue.png")));
		}
		else if (color == Color.YELLOW)
		{
			sprite = new Sprite(new Texture(Gdx.files.internal("gfx/ball_yellow.png")));
		}
		else
		{
			sprite = new Sprite(new Texture(Gdx.files.internal("gfx/ball_red.png")));
		}

		sprite.setCenter(x, y);
		sprite.setScale(0.5f, 0.5f);
	}

	public Puck(final float x, final float y, final World world)
	{
		this(x, y, Color.RED, world);
	}

	@Override
	public void setPosition(final float posX, final float posY)
	{
		ballBodyDef.position.set(posX, posY);
	}

	@Override
	public void draw(final SpriteBatch sb)
	{
		sprite.setPosition(ballBody.getPosition().x, ballBody.getPosition().y);
		sprite.draw(sb);
	}

	@Override
	public boolean collide(final Entity entity)
	{
		return false;
	}

	@Override
	public boolean move(final float x, final float y)
	{
		return false;
	}
}