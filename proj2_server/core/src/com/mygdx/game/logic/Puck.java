package com.mygdx.game.logic;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by MARQUES on 04-05-2015.
 */
public class Puck
{
	private World world;
	private Body ballBody;
	private BodyDef ballBodyDef;
	private Fixture ballFixture;
	private FixtureDef ballFixtureDef;


	public Puck(float posX, float posY)
	{
		ballBodyDef = new BodyDef();
		ballBodyDef.type = BodyDef.BodyType.DynamicBody;
		ballBodyDef.position.set(posX, posY);

		//Define a shape for the ball
		CircleShape ballShape = new CircleShape();
		ballShape.setRadius(0.50f);

		ballFixtureDef = new FixtureDef();
		ballFixtureDef.shape = ballShape;
		ballFixtureDef.density = 1;
		ballBody = world.createBody(ballBodyDef);
		ballFixture = ballBody.createFixture(ballFixtureDef);

		ballShape.dispose();

		ballBody.setLinearVelocity(-10, 0.1f);
	}

	public void setPosition(float posX,float posY)
	{
	ballBodyDef.position.set(posX,  posY);
	}
}