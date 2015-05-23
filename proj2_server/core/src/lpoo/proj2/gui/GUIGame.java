package lpoo.proj2.gui;

import lpoo.proj2.AirHockey;
import lpoo.proj2.audio.Song;
import lpoo.proj2.audio.Special;
import lpoo.proj2.logic.Paddle;
import lpoo.proj2.logic.Puck;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class GUIGame extends GUIScreen
{
	private final World world;
	private final Vector2 gravity;

	public GUIGame(final AirHockey parent)
	{
		super(parent);

		gravity = new Vector2(0.0f, 0.0f);
		world = new World(gravity, true);
		Gdx.input.setInputProcessor(this);
		pPuck = new Puck(100, 200, Color.RED, world);
		p1Paddle = new Paddle(200, 150, Color.RED, null);
		p2Paddle = new Paddle(400, 150, Color.BLUE, null);
		bgmusic = Song.THEME_B;
	}

	private final Paddle p1Paddle;
	private final Paddle p2Paddle;
	private final Puck pPuck;
	private final ScoreListener sl = new ScoreListener();

	private class ScoreThread implements Runnable
	{
		private int p1Score = 0;
		private int p2Score = 0;
		private int p1Streak = 0;
		private int p2Streak = 0;
		private boolean p1FirstBlood = false;
		private boolean p2FirstBlood = false;

		@Override
		public void run()
		{
			if (p1FirstBlood)
			{
				audio.playSpecial(Special.QUAKE_FIRSTBLOOD);
				p1FirstBlood = false;
			} else if (p2FirstBlood)
			{
				audio.playSpecial(Special.QUAKE_FIRSTBLOOD);
				p2FirstBlood = false;
			} else
			{
				final int highestStreak = Math.max(p1Streak, p2Streak);

				switch (highestStreak)
				{
				case 2:
					audio.playSpecial(Special.QUAKE_DOUBLEKILL);
					break;
				case 4:
					audio.playSpecial(Special.QUAKE_KILLINGSPREE);
					break;
				case 6:
					audio.playSpecial(Special.QUAKE_HOLYSHIT);
					break;
				case 8:
					audio.playSpecial(Special.QUAKE_GODLIKE);
					break;
				}
			}
		}

		public void p1Goal()
		{
			if (p1Score == 0 && p2Score == 0)
			{
				p1FirstBlood = true;
			}

			p1Score++;
			p1Streak++;
			p2Streak = 0;
		}

		public void p2Goal()
		{
			if (p1Score == 0 && p2Score == 0)
			{
				p2FirstBlood = true;
			}

			p2Score++;
			p2Streak++;
			p1Streak = 0;
		}
	}

	private class ScoreListener
	{
		private final ScoreThread runner = new ScoreThread();

		public void update(final int playerId)
		{
			switch (playerId)
			{
			case 1:
				runner.p1Goal();
				break;
			case 2:
				runner.p2Goal();
				break;
			}

			new Thread(runner).start();
		}
	}

	@Override
	public void render(final float delta)
	{
		Gdx.gl.glClearColor(0.420f, 0.533f, 1.000f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		p1Paddle.draw(batch);
		pPuck.draw(batch);
		p2Paddle.draw(batch);
		batch.end();
	}

	@Override
	public boolean keyDown(final int mKeycode)
	{
		switch (mKeycode)
		{
		case Input.Keys.SPACE:
			parent.switchTo(1);
			break;
		}
		return false;
	}

	@Override
	public boolean keyUp(final int mKeycode)
	{

		return false;
	}

	@Override
	public boolean keyTyped(final char character)
	{
		return false;
	}

	@Override
	public boolean touchDown(final int screenX, final int screenY,
			final int pointer, final int button)
	{
		p1Paddle.move(screenX, screenY);
		sl.update(1);
		return true;
	}

	@Override
	public boolean touchUp(final int screenX, final int screenY,
			final int pointer, final int button)
	{
		return false;
	}

	@Override
	public boolean touchDragged(final int screenX, final int screenY,
			final int pointer)
	{
		p1Paddle.move(screenX, screenY);

		return true;
	}

	@Override
	public boolean mouseMoved(final int screenX, final int screenY)
	{
		return true;
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
	public void hide()
	{
	}
}