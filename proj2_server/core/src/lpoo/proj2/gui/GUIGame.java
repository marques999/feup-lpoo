package lpoo.proj2.gui;

import java.net.InetAddress;
import java.net.UnknownHostException;

import lpoo.proj2.AirHockey;
import lpoo.proj2.audio.SFX;
import lpoo.proj2.audio.Song;
import lpoo.proj2.audio.Special;
import lpoo.proj2.logic.GameBoard;
import lpoo.proj2.logic.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class GUIGame extends GUIScreen
{
	private GameBoard game;
	private GameState state;

	private final TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("menu/menu.atlas"));
	private final BitmapFont nameFont = new BitmapFont(Gdx.files.internal("menu/bebasbold.fnt"));
	private final Skin skin = new Skin(Gdx.files.internal("menu/menu.json"), atlas);
	private final LabelStyle styleDefaultLabel = new LabelStyle(skin.get("default", LabelStyle.class));
	private final LabelStyle styleSmallLabel = new LabelStyle(skin.get("smallLabel", LabelStyle.class));
	private final LabelStyle styleGradientLabel = new LabelStyle(skin.get("gradientLabel", LabelStyle.class));
	private final TextButtonStyle styleMenuButton = new TextButtonStyle(skin.get("menuLabel", TextButtonStyle.class));
	private final Texture background = new Texture(Gdx.files.internal("gfx/table.png"));
	private final Image overlay = new Image(new Texture(Gdx.files.internal("menu/bg_darken.png")));

	public void changeState(GameState newState)
	{
		state = newState;
	}

	private abstract class GameState
	{	
		public abstract void update(float delta);
		public abstract void draw(SpriteBatch paramBatch);
	}
	
	private class WaitingState extends GameState
	{
		private final Stage stageWaiting = new Stage();
		private final Table tableWaiting = new Table();
		private final TextButton btnResume = new TextButton("RESUME", styleMenuButton);

		public WaitingState()
		{
			tableWaiting.setFillParent(true);
			tableWaiting.defaults().padBottom(16);
			tableWaiting.add(new Label("WAITING FOR PLAYERS...", styleGradientLabel)).colspan(2);
			tableWaiting.row();
			tableWaiting.add(new Label("Hostname:", styleGradientLabel)).left();
			
			try
			{
				tableWaiting.add(new Label(InetAddress.getLocalHost().getHostAddress(), styleSmallLabel)).right();
			}
			catch (UnknownHostException e)
			{
				e.printStackTrace();
			}
			
			tableWaiting.row();
			tableWaiting.add(new Label("Port:", styleGradientLabel)).left().padBottom(32);
			tableWaiting.add(new Label(Integer.toString(9732), styleSmallLabel)).right().padBottom(32);
			tableWaiting.row();
			tableWaiting.add(new Label("Players connected:", styleGradientLabel)).left();
			tableWaiting.add(new Label("0 / 2", styleSmallLabel)).right();
			stageWaiting.addActor(overlay);
			stageWaiting.addActor(tableWaiting);

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

			Gdx.input.setInputProcessor(stageWaiting);
		}

		@Override
		public void update(float delta)
		{
			stageWaiting.act(delta);
		}

		@Override
		public void draw(SpriteBatch paramBatch)
		{
			stageWaiting.draw();
		}
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
			stagePause.addActor(overlay);
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
		public void draw(SpriteBatch paramBatch)
		{
			stagePause.draw();
		}
	}
	
	private class GameOverState extends GameState
	{
		private final SequenceAction blinkAction = Actions.sequence(Actions.fadeOut(0.5f), Actions.fadeIn(0.5f));
		private final Stage stageOver = new Stage();
		private final Table tableOver = new Table();
		private Label lblContinue = new Label("TOUCH SCREEN TO CONTINUE...", styleGradientLabel);
		private Label lblName;
		private Label lblScore;
		
		public GameOverState(Player p)
		{
			StringBuilder strName = new StringBuilder();
			StringBuilder strScore = new StringBuilder();

			strName.append(p.getName());
			strName.append(" WINS!");
			strScore.append(Integer.toString(game.getPlayer1().getScore()));
			strScore.append(" - ");
			strScore.append(Integer.toString(game.getPlayer2().getScore()));
			
			lblName = new Label(strName, styleGradientLabel);
			lblScore = new Label(strScore, styleDefaultLabel);
			lblName.addAction(Actions.forever(blinkAction));
			tableOver.defaults().center();
			tableOver.add(lblName).padBottom(8);
			tableOver.row();
			tableOver.add(lblScore).padBottom(24);
			tableOver.row();
			tableOver.add(lblContinue);
			tableOver.setFillParent(true);
			stageOver.addActor(overlay);
			stageOver.addActor(tableOver);
			audio.playSong(Song.THEME_GAME_OVER, true);
			
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
		public void draw(SpriteBatch paramBatch)
		{
			stageOver.draw();
		}
	}

	private class GameRunningState extends GameState
	{
		private boolean displayMessage = false;
		private String strMessage = "";
		
		public GameRunningState()
		{
			Gdx.input.setInputProcessor(GUIGame.this);
		}

		@Override
		public void update(float delta)
		{
			game.update(delta);
		}
		
		public void display(String paramString)
		{
			strMessage = paramString;
			displayMessage = true;
			
			Timer.schedule(new Task()
			{
					@Override
					public void run()
					{
						displayMessage = false;
					}
			}, 1);
		}

		@Override
		public void draw(SpriteBatch paramBatch)
		{
			if (displayMessage)
			{
				paramBatch.begin();
				nameFont.draw(paramBatch, strMessage, Gdx.graphics.getWidth() >> 1, Gdx.graphics.getHeight() >> 1);
				paramBatch.end();
			}
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
			stageConfirm.addActor(overlay);
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
		public void draw(SpriteBatch paramBatch)
		{
			stageConfirm.draw();
		}
	}

	public void actionScore(Player player)
	{
		changeState(new PlayerScoredState(player));
		
		if (parent.isMultiplayer())
		{
			Timer.schedule(new ReturnGame(), 2);
		}
	}

	public void actionGameover(Player p)
	{
		changeState(new GameOverState(p));
	}

	private class PlayerScoredState extends GameState
	{
		private final Table tableScore = new Table();
		private final Stage stageScore = new Stage();

		public PlayerScoredState(Player p)
		{
			final StringBuilder strName = new StringBuilder();
			final StringBuilder strScore = new StringBuilder();

			strName.append(p.getName());
			strName.append(" SCORES!");
			strScore.append(Integer.toString(game.getPlayer1().getScore()));
			strScore.append(" - ");
			strScore.append(Integer.toString(game.getPlayer2().getScore()));

			tableScore.defaults().padBottom(16).center();
			tableScore.add(new Label(strName, styleGradientLabel)).padBottom(8).row();
			tableScore.add(new Label(strScore, styleDefaultLabel));
			tableScore.setFillParent(true);
			tableScore.setTransform(true);
			tableScore.setOrigin(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
			stageScore.addActor(overlay);
			stageScore.addActor(tableScore);
			tableScore.addAction(Actions.rotateBy(360, 1.0f));
			
			if (!AirHockey.isQuakeEnabled())
			{
				audio.playSound(SFX.SFX_GOAL);
			}

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
		public void draw(SpriteBatch paramBatch)
		{
			stageScore.draw();
		}
	}

	public GUIGame(final AirHockey parent)
	{
		super(parent, Song.THEME_NONE);
	}
	
	class ReturnGame extends Task
	{
		@Override
		public void run()
		{
			changeState(new GameRunningState());
		}	
	}

	@Override
	public void render(final float delta)
	{
		state.update(delta);
		batch.begin();
		batch.draw(background, 0, 0, 480, 800);
		nameFont.draw(batch, game.getPlayer1().getName(), 64, 64);
		nameFont.draw(batch, game.getPlayer2().getName(), 64, 720);
		game.draw(batch);
		batch.end();
		state.draw(batch);
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
	public boolean touchDown(final int screenX, final int screenY, final int pointer, final int button)
	{
		if (!parent.isMultiplayer())
		{
			game.movePaddle(0, screenX, screenY);
		}
		
		return true;
	}

	@Override
	public boolean touchDragged(final int screenX, final int screenY, final int pointer)
	{
		if (!parent.isMultiplayer())
		{
			game.movePaddle(0, screenX, screenY);
		}
		
		return true;
	}

	@Override
	public void show()
	{
		game = new GameBoard(this, parent.getMode(), parent.isMultiplayer());
		
		audio.playSpecial(Special.QUAKE_PREPARE);
		
		if (parent.isMultiplayer())
		{
			changeState(new WaitingState());
		}
		else
		{
			changeState(new GameRunningState());
			
			if (state instanceof GameRunningState)
			{
				((GameRunningState)state).display("PREPARE TO FIGHT!");
			}
		}
	}
	
	@Override
	public void hide()
	{
		game.dispose();
	}

	@Override
	public void pause()
	{
		changeState(new GamePausedState());
	}
}