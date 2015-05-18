package lpoo.proj2.gui;

import lpoo.proj2.AirHockey;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class GUIMainMenu extends GUIScreen
{
	private AirHockey parent;
	private TextButton btnSingleplayer;
	private TextButton btnMultiplayer;
	private TextButton btnOptions;
	private TextButton btnExit;
	
	private SpriteBatch _batch;

	public GUIMainMenu(Game parent)
	{
		super(parent);

	}

	@Override
	public void show()
	{
		this._batch = new SpriteBatch();
	}

	@Override
	public void render(float delta)
	{
		Gdx.gl.glClearColor(0.420f, 0.533f, 1.000f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		_batch.begin();
	//	btnSingleplayer.draw(spriteBatch, 1.0f);
	//	btnMultiplayer.draw(spriteBatch, 1.0f);
	//	btnOptions.draw(spriteBatch, 1.0f);
	//	btnExit.draw(spriteBatch, 1.0f);
		ShapeRenderer circle = new ShapeRenderer();
		circle.begin(ShapeType.Filled);
		circle.setColor(Color.RED);
		circle.circle(200, 300, 5.0f);
		_batch.end();
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
		System.out.println("ateqpoqtpqote");
		return false;
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