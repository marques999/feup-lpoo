package lpoo.proj2.gui;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import lpoo.proj2.audio.AudioManager;
import lpoo.proj2.audio.Song;
import lpoo.proj2.logic.Goal;
import lpoo.proj2.logic.Paddle;
import lpoo.proj2.logic.Puck;

public class GUIGame extends GUIScreen
{
	public GUIGame(Game parent)
	{
		super(parent);
	
		texture = new Texture(Gdx.files.internal("gfx/ball_red.png"));
		texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		sprite = new Sprite(texture);
		sprite.setOrigin(0, 0);
		sprite.setSize(480 / 8, 480 / 8);
		mGoal = new Goal(200, 150, Color.RED);
		p1Paddle = new Paddle(200, 150, Color.RED);
		p2Paddle = new Paddle(400, 150, Color.BLUE);
		batch = new SpriteBatch();
		
	}

	SpriteBatch batch;

	private OrthographicCamera _camera;
	private ParticleEffect blueCollision;
	private Goal mGoal;
	private float posX;
	private float posY;

	private Paddle p1Paddle;
	private Paddle p2Paddle;
    private Puck pPuck;
	private Texture texture;
	private Sprite sprite;

	@Override
	public void render(float delta)
	{
		Gdx.gl.glClearColor(0.420f, 0.533f, 1.000f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		sprite.setPosition(posX, posY);
		batch.begin();
		p1Paddle.draw(batch);
		p2Paddle.draw(batch);
		batch.end();
	}

	@Override
	public boolean keyDown(int keycode)
	{
		return false;
	}

	@Override
	public boolean keyUp(int mKeyCode)
	{
		switch (mKeyCode)
		{
			case Input.Keys.M:
				break;
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character)
	{
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
		p1Paddle.move(screenX, screenY);

		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button)
	{
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer)
	{
		p1Paddle.move(screenX, screenY);

		return true;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY)
	{
		return true;
	}

	@Override
	public boolean scrolled(int amount)
	{
		return false;
	}

	@Override
	public void show()
	{
	}

	@Override
	public void hide()
	{
	}
}