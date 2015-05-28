package lpoo.proj2.gui;

import java.util.ArrayList;

import lpoo.proj2.AirHockey;
import lpoo.proj2.audio.SFX;
import lpoo.proj2.audio.Song;
import lpoo.proj2.audio.Special;
import lpoo.proj2.logic.EntityFactory;
import lpoo.proj2.logic.Paddle;
import lpoo.proj2.logic.Puck;
import lpoo.proj2.logic.Wall;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class GUIGame extends GUIScreen
{
	private final World world;
	private final Vector2 gravity;
	private final EntityFactory factory;
	
	private enum State
	{
		RUNNING,
		EXIT,
		PAUSED
	}
	
	private State state;
	
	public void actionResume()
	{
		Gdx.input.setInputProcessor(this);
		state = State.RUNNING;
	}
	
	public GUIGame(final AirHockey parent)
	{
		super(parent);

		state = State.RUNNING;
		gravity = new Vector2(0.0f, 0.0f);
		world = new World(gravity, true);
		factory = new EntityFactory(world);
		bgmusic = Song.THEME_NONE;
		walls = new ArrayList<Wall>();
	
		tablePaused.setFillParent(true);
		tablePaused.defaults().padBottom(16);
		tablePaused.add(lblPaused).row();
		tablePaused.add(btnResume);
		tableExit.setFillParent(true);
		tableExit.defaults().padBottom(16).center();
		tableExit.add(lblConfirm).padBottom(32).row();
		tableExit.add(btnYes).width(160).row();
		tableExit.add(btnNo).width(160).row();
		stagePause.addActor(tablePaused);
		stageConfirm.addActor(tableExit);
		
		btnResume.addListener(new ClickListener()
		{
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				audio.playSound(SFX.MENU_CLICK);
				actionResume();
			}
				
			@Override
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
			{
				audio.playSound(SFX.MENU_SELECT);
			}
		});
		
		btnYes.addListener(new ClickListener()
		{
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				audio.playSound(SFX.MENU_CLICK);
				parent.switchTo(1);
			}
				
			@Override
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
			{
				audio.playSound(SFX.MENU_SELECT);
			}
		});
		
		btnNo.addListener(new ClickListener()
		{
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				audio.playSound(SFX.MENU_CLICK);
				actionResume();
			}
				
			@Override
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
			{
				audio.playSound(SFX.MENU_SELECT);
			}
		});
		
		walls.add(new Wall(0, 0, 32, Gdx.graphics.getHeight() / 2, Color.RED));
		walls.add(new Wall(0, Gdx.graphics.getHeight() / 2, 32, Gdx.graphics.getHeight() / 2, Color.BLUE));
		walls.add(new Wall(Gdx.graphics.getWidth() - 32, 0, 32, Gdx.graphics.getHeight() / 2, Color.RED));
		walls.add(new Wall(Gdx.graphics.getWidth() - 32,  Gdx.graphics.getHeight() / 2, 32, Gdx.graphics.getHeight() / 2, Color.BLUE));
	}

	private Paddle p1Paddle;
	private Paddle p2Paddle;
	private Puck pPuck;
	private ArrayList<Wall> walls;
	private final ScoreListener sl = new ScoreListener();
	
	private final TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("menu/menu.atlas"));
	private final Skin skin = new Skin(Gdx.files.internal("menu/menu.json"), atlas);
	private final Stage stagePause = new Stage(new FitViewport(480,800));
	private final Stage stageConfirm = new Stage(new FitViewport(480,800));
	private final Table tableExit = new Table();
	private final Table tablePaused = new Table();
	
	private final LabelStyle styleSmallLabel = new LabelStyle(skin.get("smallLabel", LabelStyle.class));
	private final Label lblConfirm = new Label("FORFEIT MATCH?", styleSmallLabel);
	private final Label lblPaused = new Label("GAME PAUSED", styleSmallLabel);
	
	private final TextButtonStyle styleMenuButton = new TextButtonStyle(skin.get("menuLabel", TextButtonStyle.class));
	private final TextButton btnResume = new TextButton("RESUME", styleMenuButton);
	private final TextButton btnYes = new TextButton("YES", styleMenuButton);
	private final TextButton btnNo = new TextButton("NO", styleMenuButton);
	
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
			} 
			else if (p2FirstBlood)
			{
				audio.playSpecial(Special.QUAKE_FIRSTBLOOD);
				p2FirstBlood = false;
			} 
			else
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
		
		switch (state)
		{
		case RUNNING:
			p1Paddle.update(delta);
			pPuck.update(delta);
			p1Paddle.collides(p2Paddle);
			pPuck.collides(p1Paddle);
			pPuck.collides(p2Paddle);
			
			for (Wall wall : walls)
			{
				if (pPuck.collides(wall))
				{
					audio.playSound(SFX.SFX_PUCK_HIT);
				}
			}
			
			break;
		case PAUSED:
			stagePause.act();
			break;
		case EXIT:
			stageConfirm.act();
			break;

		}
		
		batch.begin();
		
		if (state == State.PAUSED)
		{
			stagePause.draw();
		}
		else if (state == State.EXIT)
		{
			stageConfirm.draw();
		}
				
		p1Paddle.draw(batch);
		p2Paddle.draw(batch);
	
		for (Wall wall : walls)
		{
			wall.draw(batch);
		}
		pPuck.draw(batch);

		batch.end();
	}

	@Override
	public boolean keyDown(final int mKeycode)
	{
		switch (mKeycode)
		{
		case Input.Keys.ESCAPE:
			state = State.EXIT;
			Gdx.input.setInputProcessor(stageConfirm);
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
	public boolean mouseMoved(int screenX, int screenY)
	{
		return true;
	}

	@Override
	public void show()
	{
		state = State.RUNNING;
		pPuck = factory.createPuck(Color.RED);
		p1Paddle = factory.createP1Paddle(Color.RED);
		p2Paddle = factory.createP2Paddle(Color.BLUE);
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void hide()
	{
	}
	
	
	@Override
	public void pause()
	{
		stagePause.addActor(tablePaused);
		state = State.PAUSED;
		Gdx.input.setInputProcessor(stagePause);
	}
}