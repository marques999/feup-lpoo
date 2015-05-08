package com.mygdx.game.gui;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class MainMenu extends ApplicationAdapter implements Screen
{
	private TextButton btnPlay;
	private TextButton btnOptions;
	private SpriteBatch spriteBatch;

	public MainMenu()
	{
		spriteBatch = new SpriteBatch();
	}

	@Override
	public void show()
	{
		Gdx.gl.glClearColor(0.420f, 0.533f, 1.000f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		spriteBatch.begin();
		btnPlay.draw(spriteBatch, 1.0f);
		spriteBatch.end();
	}

	@Override
	public void render(float delta)
	{
	}

	@Override
	public void resize(int width, int height)
	{
	}

	@Override
	public void pause()
	{
	}

	@Override
	public void resume()
	{
	}

	@Override
	public void hide()
	{
	}

	@Override
	public void dispose()
	{
	}
}