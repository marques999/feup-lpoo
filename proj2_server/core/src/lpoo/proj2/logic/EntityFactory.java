package lpoo.proj2.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class EntityFactory
{
	private World world;
	
	public EntityFactory(World world)
	{
		this.world = world;
	}
	
	public Puck createPuck(Color color)
	{
		CircleShape shape = new CircleShape();
		Puck puck = new Puck(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, color);
		BodyDef bodyDef = new BodyDef();
		FixtureDef fixtureDef = new FixtureDef();
		
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.position.set(puck.x, puck.y);
		shape.setRadius(0.50f);
		fixtureDef.shape = shape;
		fixtureDef.density = 1;
		
		Body body = world.createBody(bodyDef);
		Fixture fixture = body.createFixture(fixtureDef);
		
		body.setLinearVelocity(-10, 0.1f);
		shape.dispose();
		puck.setBody(body, bodyDef);
		puck.setFixture(fixture);
		
		return puck;
	}
	
	private Entity createPaddle(float x, float y, Color color)
	{
		CircleShape shape = new CircleShape();
		Entity paddle = new Paddle(x, y, color);
		BodyDef bodyDef = new BodyDef();
		FixtureDef fixtureDef = new FixtureDef();
		
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.position.set(x, y);
		shape.setRadius(0.50f);
		fixtureDef.shape = shape;
		fixtureDef.density = 1;
		
		Body body = world.createBody(bodyDef);
		Fixture fixture = body.createFixture(fixtureDef);
		
		body.setLinearVelocity(-10, 0.1f);
		shape.dispose();
		paddle.setBody(body, bodyDef);
		paddle.setFixture(fixture);
		
		return paddle;
	}
	
	private Goal createGoal(float x, float y)
	{
		PolygonShape shape = new PolygonShape();
		Goal goal = new Goal(x, y, 300, 64);
		BodyDef bodyDef = new BodyDef();
		FixtureDef fixtureDef = new FixtureDef();
		
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.position.set(x, y);
		shape.setRadius(0.50f);
		fixtureDef.shape = shape;
		fixtureDef.density = 1;
		
		Body body = world.createBody(bodyDef);
		Fixture fixture = body.createFixture(fixtureDef);
		
		body.setLinearVelocity(-10, 0.1f);
		shape.dispose();
		goal.setBody(body, bodyDef);
		goal.setFixture(fixture);
		
		return goal;
	}
	
	public Goal createP1Goal()
	{
		return createGoal(Gdx.graphics.getWidth() / 2, 0);
	}
	
	public Goal createP2Goal()
	{
		return createGoal(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight());
	}
	
	public Entity createP1Paddle(Color color)
	{
		return createPaddle(Gdx.graphics.getWidth() / 2, 64, color);
	}
	
	public Entity createP2Paddle(Color color)
	{
		return createPaddle(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() - 64, color);
	}
}
