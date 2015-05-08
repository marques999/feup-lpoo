package com.mygdx.game.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Paddle extends Entity
{
	private Texture _texture;
	private Sprite _sprite;

	public Paddle(float x, float y, Color color)
	{
		super(x, y);

		if (color == Color.RED)
		{
			_texture = new Texture(Gdx.files.internal("core/assets/paddle_red.png"));
		}
		else if (color == Color.GREEN)
		{
			_texture = new Texture(Gdx.files.internal("core/assets/paddle_green.png"));
		}
		else if (color == Color.BLUE)
		{
			_texture = new Texture(Gdx.files.internal("core/assets/paddle_blue.png"));
		}
		else if (color == Color.YELLOW)
		{
			_texture = new Texture(Gdx.files.internal("core/assets/paddle_yellow.png"));
		}
		else
		{
			_texture = new Texture(Gdx.files.internal("core/assets/paddle_red.png"));
		}

		_sprite = new Sprite(_texture);
		_sprite.setCenter(x, y);
		_sprite.setScale(0.5f, 0.5f);
	}

	public void move(float newX, float newY)
	{
		x = newX;
		y = Gdx.graphics.getHeight() - newY;
		_sprite.setCenter(x, y);
	}

	@Override
	public void draw(SpriteBatch sb)
	{
		_sprite.draw(sb);
	}
}