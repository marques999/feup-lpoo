package lpoo.proj2.gui;

import lpoo.proj2.AirHockey;
import lpoo.proj2.StyleFactory;
import lpoo.proj2.audio.SFX;
import lpoo.proj2.audio.Song;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class GUIMainMenu extends GUIScreen
{
	private final Stage stage = new Stage();
	private final Table table = new Table();
	private final TiledBackground bg = new TiledBackground(Gdx.files.internal("menu/bg_main.png"), false);
	private final Label lblTitle = new Label("AIR HOCKEY", StyleFactory.TitleLabel);
	private final TextButton btnSingleplayer = new TextButton("Singleplayer", StyleFactory.MenuButton);
	private final TextButton btnMultiplayer = new TextButton("Multiplayer", StyleFactory.MenuButton);
	private final TextButton btnPreferences = new TextButton("Preferences", StyleFactory.MenuButton);
	private final TextButton btnCredits = new TextButton("Credits", StyleFactory.MenuButton);
	private final TextButton btnExit = new TextButton("Exit", StyleFactory.MenuButton);

	private class RunMultiplayer implements Runnable
	{
		@Override
		public void run()
		{
			parent.startMultiplayer();
		}
	};

	private class RunSingleplayer implements Runnable
	{
		@Override
		public void run()
		{
			parent.startSingleplayer();
		}
	};

	private class RunPreferences implements Runnable
	{
		@Override
		public void run()
		{
			parent.switchTo(2);
		}
	}

	private class RunCredits implements Runnable
	{
		@Override
		public void run()
		{
			parent.switchTo(4);
		}
	}

	public GUIMainMenu(final AirHockey parent)
	{
		super(parent, Song.THEME_MAIN_MENU);

		table.add(lblTitle).padBottom(64).row();
		table.defaults().size(216, 49).padBottom(16);
		table.add(btnSingleplayer).row();
		table.add(btnMultiplayer).row();
		table.add(btnPreferences).row();
		table.add(btnCredits).row();
		table.add(btnExit).row();
		table.setFillParent(true);
		stage.addActor(table);

		btnSingleplayer.addListener(new ClickListener()
		{
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				audio.playSound(SFX.MENU_CLICK);
				stage.addAction(Actions.sequence(Actions.moveTo(-480.0f, 0.0f, 0.5f), Actions.run(new RunSingleplayer())));
			}

			@Override
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
			{
				if (!btnSingleplayer.isPressed())
				{
					audio.playSound(SFX.MENU_SELECT);
				}
			}
		});

		btnMultiplayer.addListener(new ClickListener()
		{
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				audio.playSound(SFX.MENU_CLICK);
				stage.addAction(Actions.sequence(Actions.moveTo(-480.0f, 0.0f, 0.5f), Actions.run(new RunMultiplayer())));
			}

			@Override
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
			{
				if (!btnMultiplayer.isPressed())
				{
					audio.playSound(SFX.MENU_SELECT);
				}
			}
		});

		btnPreferences.addListener(new ClickListener()
		{
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				audio.playSound(SFX.MENU_CLICK);
				stage.addAction(Actions.sequence(Actions.moveTo(-480.0f, 0.0f, 0.5f), Actions.run(new RunPreferences())));
			}

			@Override
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
			{
				if (!btnPreferences.isPressed())
				{
					audio.playSound(SFX.MENU_SELECT);
				}
			}
		});

		btnCredits.addListener(new ClickListener()
		{
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				audio.playSound(SFX.MENU_CLICK);
				stage.addAction(Actions.sequence(Actions.moveTo(-480.0f, 0.0f, 0.5f), Actions.run(new RunCredits())));
			}

			@Override
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
			{
				if (!btnPreferences.isPressed())
				{
					audio.playSound(SFX.MENU_SELECT);
				}
			}
		});

		btnExit.addListener(new ClickListener()
		{
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				audio.playSound(SFX.MENU_CLICK);
				Gdx.app.exit();
			}

			@Override
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
			{
				if (!btnExit.isPressed())
				{
					audio.playSound(SFX.MENU_SELECT);
				}
			}
		});
	}

	@Override
	public void render(float delta)
	{
		stage.act();
		bg.update(delta);
		batch.begin();
		bg.render(batch);
		batch.end();
		stage.draw();
	}

	@Override
	public void resize(int width, int height)
	{
		stage.getViewport().update(width, height);
	}

	@Override
	public void show()
	{
		Gdx.input.setInputProcessor(stage);
		stage.addAction(Actions.moveTo(0.0f, 0.0f, 0.5f));
	}

	@Override
	public void dispose()
	{
		stage.dispose();
	}
}