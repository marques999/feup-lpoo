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

public class Puck extends Entity
{
	private World _world;
	private Body ballBody;
	private BodyDef ballBodyDef;
	private Fixture ballFixture;
	private FixtureDef ballFixtureDef;
	private Texture texture;
	private Sprite ballSprite;

	public Puck(float x, float y, World world)
	{
		this(x, y, Color.WHITE, world);
	}

	public Puck(float x, float y, Color color, World world)
	{
		super(x, y);

		ballBodyDef = new BodyDef();
		ballBodyDef.type = BodyDef.BodyType.DynamicBody;
		ballBodyDef.position.set(x, y);

		CircleShape ballShape = new CircleShape();

		ballShape.setRadius(0.50f);

		ballFixtureDef = new FixtureDef();
		ballFixtureDef.shape = ballShape;
		ballFixtureDef.density = 1;

		ballBody = _world.createBody(ballBodyDef);
		ballFixture = ballBody.createFixture(ballFixtureDef);
		ballBody.setLinearVelocity(-10, 0.1f);
        ballShape.dispose();

		if (color == Color.RED)
		{
			texture = new Texture(Gdx.files.internal("core/assets/ball_red.png"));
		}
		else if (color == Color.GREEN)
		{
			texture = new Texture(Gdx.files.internal("core/assets/ball_green.png"));
		}
		else if (color == Color.BLUE)
		{
			texture = new Texture(Gdx.files.internal("core/assets/ball_blue.png"));
		}
		else if (color == Color.YELLOW)
		{
			texture = new Texture(Gdx.files.internal("core/assets/ball_yellow.png"));
		}
		else
		{
			texture = new Texture(Gdx.files.internal("core/assets/ball_red.png"));
		}

		ballSprite = new Sprite(texture);
        ballSprite.setCenter(x, y);
        ballSprite.setScale(0.5f, 0.5f);
        _world = world;
	}

	public void setPosition(float posX, float posY)
	{
		ballBodyDef.position.set(posX, posY);
	}

	@Override
	public void draw(SpriteBatch sb)
	{
        ballSprite.setPosition(ballBody.getPosition().x, ballBody.getPosition().y);
        ballSprite.draw(sb);
	}
}