package lpoo.proj2.gui;

import java.io.IOException;
import lpoo.proj2.AirHockey;
import lpoo.proj2.audio.SFX;
import lpoo.proj2.audio.Song;
import lpoo.proj2.logic.GameBoard;
import lpoo.proj2.logic.Player;
import lpoo.proj2.logic.RulesAttack;
import lpoo.proj2.logic.RulesBest10;
import lpoo.proj2.logic.RulesBest5;
import lpoo.proj2.logic.RulesFirst15;
import lpoo.proj2.net.GameServer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
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
	private Texture bg;
	private GameState state;

	public void changeState(GameState newState)
	{
		state = newState;
	}
	
	public void actionGameover(Player p)
	{
		changeState(new GameOverState(p));
	}

	public Player getP1()
	{
		return players[0];
	}

	public Player getP2()
	{
		return players[1];
	}

	private abstract class GameState
	{	
		public abstract void update(float delta);
		public abstract void draw();
	}
	
	private class GamePausedState extends GameState
	{
		private final Stage stagePause = new Stage();
		private final Table tablePaused = new Table();
		private final Label lblResume = new Label("GAME PAUSED", styleSmallLabel);
		private final TextButton btnResume = new TextButton("RESUME", styleMenuButton);

		public GamePausedState()
		{
			tablePaused.setFillParent(true);
			tablePaused.defaults().padBottom(16);
			tablePaused.add(lblResume);
			tablePaused.row();
			tablePaused.add(btnResume);
			stagePause.addActor(tablePaused);

			btnResume.addListener(new ClickListener()
			{
				@Override
				public void clicked(InputEvent event, float x, float y)
				{
					audio.playSound(SFX.MENU_CLICK);
					changeState(new GameRunningState());
				}

				@Override
				public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
				{
					audio.playSound(SFX.MENU_SELECT);
				}
			});

			Gdx.input.setInputProcessor(stagePause);
		}

		@Override
		public void update(float delta)
		{
			stagePause.act(delta);
		}

		@Override
		public void draw()
		{
			stagePause.draw();
		}
	}

	private class GameOverState extends GameState
	{
		private final Stage stageOver = new Stage();
		private final Table tableOver = new Table();

		public GameOverState(Player p)
		{
			StringBuilder strName = new StringBuilder();
			StringBuilder strScore = new StringBuilder();

			strName.append(p.getName());
			strName.append(" WINS!");
			strScore.append(Integer.toString(players[0].getScore()));
			strScore.append(" - ");
			strScore.append(Integer.toString(players[1].getScore()));

			tableOver.defaults().padBottom(16).center();
			tableOver.add(new Label(strName, styleSmallLabel)).padBottom(32).row();
			tableOver.add(new Label(strScore, styleSmallLabel));
			tableOver.setFillParent(true);
			stageOver.addActor(tableOver);
			audio.playSong(Song.THEME_SELECT, true);
			
			stageOver.addListener(new ClickListener()
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

			Gdx.input.setInputProcessor(stageOver);
		}
		
		@Override
		public void update(float delta)
		{
			tableOver.act(delta);
		}

		@Override
		public void draw()
		{
			stageOver.draw();
		}
	}

	private class GameRunningState extends GameState
	{
		public GameRunningState()
		{
			Gdx.input.setInputProcessor(GUIGame.this);
		}

		@Override
		public void update(float delta)
		{
			game.update(delta);
		}

		@Override
		public void draw()
		{
		}
	}

	private class ConfirmExitState extends GameState
	{
		private final Label lblConfirm = new Label("FORFEIT MATCH?", styleSmallLabel);
		private final TextButton btnYes = new TextButton("YES", styleMenuButton);
		private final TextButton btnNo = new TextButton("NO", styleMenuButton);
		private final Stage stageConfirm = new Stage();
		private final Table tableConfirm = new Table();
		
		public ConfirmExitState()
		{
			tableConfirm.setFillParent(true);
			tableConfirm.defaults().padBottom(16).center();
			tableConfirm.add(lblConfirm).padBottom(32).row();
			tableConfirm.add(btnYes).width(160).row();
			tableConfirm.add(btnNo).width(160).row();
			stageConfirm.addActor(tableConfirm);
			
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
					changeState(new GameRunningState());
				}

				@Override
				public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
				{
					audio.playSound(SFX.MENU_SELECT);
				}
			});
			
			Gdx.input.setInputProcessor(stageConfirm);
		}

		@Override
		public void update(float delta)
		{
			stageConfirm.act();
		}

		@Override
		public void draw()
		{
			stageConfirm.draw();
		}
	}

	public void actionScore(Player player)
	{
		changeState(new PlayerScoredState(player));
	}

	public void actionGameOver(Player player)
	{
		changeState(new PlayerScoredState(player));
	}

	private class PlayerScoredState extends GameState
	{
		private final Table tableScore = new Table();
		private final Stage stageScore = new Stage();

		public PlayerScoredState(Player p)
		{
			StringBuilder strName = new StringBuilder();
			StringBuilder strScore = new StringBuilder();

			strName.append(p.getName());
			strName.append(" SCORES!");
			strScore.append(Integer.toString(players[0].getScore()));
			strScore.append(" - ");
			strScore.append(Integer.toString(players[1].getScore()));

			tableScore.defaults().padBottom(16).center();
			tableScore.add(new Label(strName, styleSmallLabel)).padBottom(32).row();
			tableScore.add(new Label(strScore, styleSmallLabel));
			tableScore.setFillParent(true);
			tableScore.setTransform(true);
			tableScore.setOrigin(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
			stageScore.addActor(tableScore);
			tableScore.addAction(Actions.rotateBy(360, 1.0f));
			audio.playSound(SFX.SFX_GOAL);

			stageScore.addListener(new ClickListener()
			{
				@Override
				public void clicked(InputEvent event, float x, float y)
				{
					audio.playSound(SFX.MENU_CLICK);
					changeState(new GameRunningState());
				}
			});
			
			Gdx.input.setInputProcessor(stageScore);
		}

		@Override
		public void update(float delta)
		{
			stageScore.act();
		}

		@Override
		public void draw()
		{
			stageScore.draw();
		}
	}

	public GUIGame(final AirHockey parent)
	{
		super(parent, Song.THEME_NONE);

		players = new Player[2];
		players[0] = new Player("Diogo Marques", Color.YELLOW);
		players[1] = new Player("CPU", Color.BLUE);
		s = null;
		bg = new Texture(Gdx.files.internal("gfx/table.png"));
		changeState(new GameRunningState());

		try
		{
			s = new GameServer(9732, 9733);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private final TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("menu/menu.atlas"));
	private final Skin skin = new Skin(Gdx.files.internal("menu/menu.json"), atlas);
	private final LabelStyle styleSmallLabel = new LabelStyle(skin.get("smallLabel", LabelStyle.class));
	private final TextButtonStyle styleMenuButton = new TextButtonStyle(skin.get("menuLabel", TextButtonStyle.class));

	@Override
	public void render(final float delta)
	{
		Gdx.gl.glClearColor(0.420f, 0.533f, 1.000f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		state.update(delta);
		batch.begin();
		batch.draw(bg, 0, 0, 480, 800);
		game.draw(batch);
		batch.end();
		state.draw();
	}

	@Override
	public boolean keyDown(final int mKeycode)
	{
		switch (mKeycode)
		{
		case Input.Keys.ESCAPE:
			changeState(new ConfirmExitState());
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
		switch (parent.getMode())
		{
		case 0:
			game = new GameBoard(this, new RulesBest5(players));
			break;
		case 1:
			game = new GameBoard(this, new RulesBest10(players));
			break;
		case 2:
			game = new GameBoard(this, new RulesFirst15(players));
			break;
		case 3:
			game = new GameBoard(this, new RulesAttack(players));
			break;
		}
		
		changeState(new GameRunningState());
	}

	@Override
	public void hide()
	{
	}

	@Override
	public void pause()
	{
		changeState(new GamePausedState());
	}
}