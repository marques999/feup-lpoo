package lpoo.proj2.gui;

import lpoo.proj2.AirHockey;
import lpoo.proj2.audio.AudioManager;
import lpoo.proj2.audio.Song;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class GUIScreen implements Screen, InputProcessor
{
	protected AirHockey parent;
	protected SpriteBatch batch;
	protected AudioManager audio;
	protected Song bgmusic;

	public GUIScreen(final AirHockey parent)
	{
		this(parent, Song.THEME_NONE);
	}

	public GUIScreen(final AirHockey parent, Song bgmusic)
	{
		this.bgmusic = bgmusic;
		this.audio = AudioManager.getInstance();
		this.parent = parent;
		this.batch = new SpriteBatch();
	}

	public final Song getSong()
	{
		return this.bgmusic;
	}

	@Override
	public boolean keyDown(final int keycode)
	{
		return false;
	}

	@Override
	public boolean keyUp(final int keycode)
	{
		return false;
	}

	@Override
	public boolean keyTyped(final char character)
	{
		return false;
	}

	@Override
	public boolean touchDown(final int screenX, final int screenY, final int pointer, final int button)
	{

		return false;
	}

	@Override
	public boolean touchUp(final int screenX, final int screenY, final int pointer, final int button)
	{
		return false;
	}

	@Override
	public boolean touchDragged(final int screenX, final int screenY, final int pointer)
	{
		return false;
	}

	@Override
	public boolean mouseMoved(final int screenX, final int screenY)
	{
		return false;
	}

	@Override
	public boolean scrolled(final int amount)
	{
		return false;
	}

	@Override
	public void show()
	{
	}

	@Override
	public void render(final float delta)
	{
	}

	@Override
	public void resize(final int width, final int height)
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
		batch.dispose();
	}
}