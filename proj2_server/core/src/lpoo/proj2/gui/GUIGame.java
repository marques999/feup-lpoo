package lpoo.proj2.gui;

import java.io.IOException;
import lpoo.proj2.AirHockey;
import lpoo.proj2.audio.SFX;
import lpoo.proj2.logic.GameBoard;
import lpoo.proj2.logic.Player;
import lpoo.proj2.logic.RulesBest5;
import lpoo.proj2.net.GameServer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
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

public class GUIGame extends GUIScreen
{
	private GameBoard game;
	private GameServer s;
	private Player players[];

	private enum State
	{
		RUNNING, EXIT, PAUSED
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

		players = new Player[2];
		players[0] = new Player("Diogo Marques", Color.YELLOW);
		players[1] = new Player("CPU", Color.BLUE);
		
		state = State.RUNNING;
		s = null;
		
		try
		{
			s = new GameServer(9732, 9733);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
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
	}

	private final TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("menu/menu.atlas"));
	private final Skin skin = new Skin(Gdx.files.internal("menu/menu.json"), atlas);
	private final Stage stagePause = new Stage();
	private final Stage stageConfirm = new Stage();
	private final Table tableExit = new Table();
	private final Table tablePaused = new Table();

	private final LabelStyle styleSmallLabel = new LabelStyle(skin.get("smallLabel", LabelStyle.class));
	private final Label lblConfirm = new Label("FORFEIT MATCH?", styleSmallLabel);
	private final Label lblPaused = new Label("GAME PAUSED", styleSmallLabel);

	private final TextButtonStyle styleMenuButton = new TextButtonStyle(skin.get("menuLabel", TextButtonStyle.class));
	private final TextButton btnResume = new TextButton("RESUME", styleMenuButton);
	private final TextButton btnYes = new TextButton("YES", styleMenuButton);
	private final TextButton btnNo = new TextButton("NO", styleMenuButton);

	@Override
	public void render(final float delta)
	{
		Gdx.gl.glClearColor(0.420f, 0.533f, 1.000f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		switch (state)
		{
		case RUNNING:
			game.update(delta);
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

		game.draw(batch);
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
	public boolean touchDown(final int screenX, final int screenY, final int pointer, final int button)
	{
		game.movePaddle(0, screenX, screenY);
		return true;
	}

	@Override
	public boolean touchUp(final int screenX, final int screenY, final int pointer, final int button)
	{
		return false;
	}

	@Override
	public boolean touchDragged(final int screenX, final int screenY, final int pointer)
	{
		game.movePaddle(1, screenX, screenY);
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
		game = new GameBoard(players, new RulesBest5(players));
		state = State.RUNNING;
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