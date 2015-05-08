package com.mygdx.game.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class Ball extends Entity
{
	private World _world;
	private Body _ballBody;
	private BodyDef ballBodyDef;
	private Fixture _ballFixture;
	private FixtureDef ballFixtureDef;
	private ShapeRenderer _renderer;
	private Texture _texture;
	private Sprite _sprite;

	public Ball(float x, float y)
	{
		this(x, y, Color.WHITE);
	}

	public Ball(float x, float y, Color color)
	{
		super(x, y);

		_renderer = new ShapeRenderer();
		ballBodyDef = new BodyDef();
		ballBodyDef.type = BodyDef.BodyType.DynamicBody;
		ballBodyDef.position.set(x, y);

		CircleShape ballShape = new CircleShape();

		ballShape.setRadius(0.50f);
		ballFixtureDef = new FixtureDef();
		ballFixtureDef.shape = ballShape;
		ballFixtureDef.density = 1;
		_ballBody = _world.createBody(ballBodyDef);
		_ballFixture = _ballBody.createFixture(ballFixtureDef);
		ballShape.dispose();
		_ballBody.setLinearVelocity(-10, 0.1f);

		if (color == Color.RED)
		{
			_texture = new Texture(Gdx.files.internal("core/assets/ball_red.png"));
		}
		else if (color == Color.GREEN)
		{
			_texture = new Texture(Gdx.files.internal("core/assets/ball_green.png"));
		}
		else if (color == Color.BLUE)
		{
			_texture = new Texture(Gdx.files.internal("core/assets/ball_blue.png"));
		}
		else if (color == Color.YELLOW)
		{
			_texture = new Texture(Gdx.files.internal("core/assets/ball_yellow.png"));
		}
		else
		{
			_texture = new Texture(Gdx.files.internal("core/assets/ball_red.png"));
		}

		_sprite = new Sprite(_texture);
		_sprite.setCenter(x, y);
		_sprite.setScale(0.5f, 0.5f);
	}

	public void setPosition(float posX, float posY)
	{
		ballBodyDef.position.set(posX, posY);
	}

	@Override
	public void draw(SpriteBatch sb)
	{
	}
}