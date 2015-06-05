package lpoo.proj2.gui;

import java.net.InetAddress;
import java.net.UnknownHostException;

import lpoo.proj2.AirHockey;
import lpoo.proj2.StyleFactory;
import lpoo.proj2.audio.SFX;
import lpoo.proj2.audio.Song;
import lpoo.proj2.audio.Special;
import lpoo.proj2.logic.GameBoard;
import lpoo.proj2.logic.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class GUIGame extends GUIScreen
{
	private GameBoard game;
	private GameState state;
	private final float screenWidth = Gdx.graphics.getWidth();
	private final float screenHeight = Gdx.graphics.getHeight();
	private final Texture background = new Texture( Gdx.files.internal("gfx/table.png"));
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
		private final TextButton btnCancel = new TextButton("CANCEL", StyleFactory.MenuButton);
		private final Label lblPlayers = new Label("0 / 2", StyleFactory.SmallLabel);

		public WaitingState()
		{
			tableWaiting.setFillParent(true);
			tableWaiting.defaults().padBottom(32);
			tableWaiting.add(new Label("WAITING FOR PLAYERS...", StyleFactory.GradientLabel)).colspan(2);
			tableWaiting.row();
			tableWaiting.add(new Label("Hostname:", StyleFactory.GradientLabel)).left().padBottom(16);

			try
			{
				tableWaiting.add(new Label(InetAddress.getLocalHost().getHostAddress(), StyleFactory.SmallLabel)).right().padBottom(16);
			}
			catch (final UnknownHostException e)
			{
				e.printStackTrace();
			}

			tableWaiting.row();
			tableWaiting.add(new Label("Port:", StyleFactory.GradientLabel)).left();
			tableWaiting.add(new Label(Integer.toString(game.getPort()), StyleFactory.SmallLabel)).right();
			tableWaiting.row();
			tableWaiting.add(new Label("Players connected:", StyleFactory.GradientLabel)).left();
			tableWaiting.add(lblPlayers).right().padBottom(32);
			tableWaiting.row();
			tableWaiting.add(btnCancel).colspan(2).width(180).center();
			stageWaiting.addActor(overlay);
			stageWaiting.addActor(tableWaiting);

			btnCancel.addListener(new ClickListener()
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

			audio.playSong(Song.THEME_LOBBY, true);
			Gdx.input.setInputProcessor(stageWaiting);
		}

		@Override
		public void update(float delta)
		{
			lblPlayers.setText(game.getPlayersConnected() + " / 2");

			if (game.getPlayersConnected() > 1)
			{
				changeState(new GameRunningState());
			}
			else
			{
				stageWaiting.act(delta);
			}
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
		private final Label lblResume = new Label("GAME PAUSED", StyleFactory.SmallLabel);
		private final TextButton btnResume = new TextButton("RESUME", StyleFactory.MenuButton);

		public GamePausedState()
		{
			tablePaused.setFillParent(true);
			tablePaused.defaults().padBottom(16);
			tablePaused.add(lblResume);
			tablePaused.row();
			tablePaused.add(btnResume);
			stagePause.addActor(overlay);
			stagePause.addActor(tablePaused);
			audio.playSound(SFX.SFX_PAUSE);

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
	
	private class DisconnectedState extends GameState
	{
		private final Stage stageDisconnected = new Stage();
		private final Table tableDisconnected = new Table();
		private final TextButton btnOK = new TextButton("OK", StyleFactory.MenuButton);

		public DisconnectedState(final Player paramPlayer)
		{
			final StringBuilder strName = new StringBuilder();

			strName.append(paramPlayer.getName());
			strName.append(" HAS DISCONNECTED.");
			
			tableDisconnected.setFillParent(true);
			tableDisconnected.defaults().padBottom(16);
			tableDisconnected.add(new Label(strName, StyleFactory.GradientLabel));
			tableDisconnected.row();
			tableDisconnected.add(btnOK);
			stageDisconnected.addActor(overlay);
			stageDisconnected.addActor(tableDisconnected);
			audio.playSound(SFX.SFX_PAUSE);

			btnOK.addListener(new ClickListener()
			{
				@Override
				public void clicked(InputEvent event, float x, float y)
				{
					audio.playSound(SFX.MENU_CLICK);
					changeState(new WaitingState());
				}

				@Override
				public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
				{
					audio.playSound(SFX.MENU_SELECT);
				}
			});

			Gdx.input.setInputProcessor(stageDisconnected);
		}

		@Override
		public void update(float delta)
		{
			stageDisconnected.act(delta);
		}

		@Override
		public void draw(SpriteBatch paramBatch)
		{
			stageDisconnected.draw();
		}
	}

	private class GameOverState extends GameState
	{
		private final SequenceAction blinkAction = Actions.sequence(Actions.fadeOut(0.5f), Actions.fadeIn(0.5f));
		private final Stage stageOver = new Stage();
		private final Table tableOver = new Table();
		private final Label lblContinue = new Label("TOUCH SCREEN TO CONTINUE...", StyleFactory.GradientLabel);
		private final Label lblName;
		private final Label lblScore;

		public GameOverState(Player p)
		{
			final StringBuilder strName = new StringBuilder();
			final StringBuilder strScore = new StringBuilder();

			strName.append(p.getName());
			strName.append(" WINS!");
			strScore.append(Integer.toString(game.getPlayer1().getScore()));
			strScore.append(" - ");
			strScore.append(Integer.toString(game.getPlayer2().getScore()));

			lblName = new Label(strName, StyleFactory.GradientLabel);
			lblScore = new Label(strScore, StyleFactory.TitleLabel);
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
		private final Stage stageGame = new Stage();
		private final Label lblPlayer1 = new Label(game.getPlayer1().getName(), StyleFactory.LabelStyles[game.getPlayer1().getColor()]);
		private final Label lblPlayer2 = new Label(game.getPlayer2().getName(), StyleFactory.LabelStyles[game.getPlayer2().getColor()]);

		public GameRunningState()
		{
			lblPlayer1.setPosition(48, 72);
			lblPlayer2.setPosition(48, screenHeight - 96);
			stageGame.addActor(lblPlayer1);
			stageGame.addActor(lblPlayer2);
			Gdx.input.setInputProcessor(GUIGame.this);
			audio.playSound(SFX.SFX_PUCK_PLACE);
		}

		@Override
		public void update(float delta)
		{
			stageGame.act();
			game.update(delta);
		}

		@Override
		public void draw(SpriteBatch paramBatch)
		{
			stageGame.draw();
		}
	}

	private class ConfirmExitState extends GameState
	{
		private final Label lblConfirm = new Label("FORFEIT MATCH?", StyleFactory.SmallLabel);
		private final TextButton btnYes = new TextButton("YES", StyleFactory.MenuButton);
		private final TextButton btnNo = new TextButton("NO", StyleFactory.MenuButton);
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
	
	public void actionDisconnect(Player p)
	{
		changeState(new DisconnectedState(p));
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
			tableScore.add(new Label(strName, StyleFactory.GradientLabel)).padBottom(8).row();
			tableScore.add(new Label(strScore, StyleFactory.TitleLabel));
			tableScore.setFillParent(true);
			tableScore.setTransform(true);
			tableScore.setOrigin(screenWidth / 2, screenHeight / 2);
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
	public void render(float delta)
	{
		state.update(delta);
		batch.begin();
		batch.draw(background, 0, 0, 480, 800);
		game.draw(batch);
		batch.end();
		state.draw(batch);
	}

	@Override
	public boolean keyDown(int mKeycode)
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
	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
		if (!parent.isMultiplayer())
		{
			game.movePaddle(0, screenX, screenY);
		}

		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer)
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
		if (state instanceof GameRunningState)
		{
			changeState(new GamePausedState());
		}
	}
}