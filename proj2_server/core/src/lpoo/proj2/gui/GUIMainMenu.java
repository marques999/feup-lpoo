package lpoo.proj2.gui;

import lpoo.proj2.AirHockey;
import lpoo.proj2.audio.SFX;
import lpoo.proj2.audio.Song;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class GUIMainMenu extends GUIScreen
{
	private Stage stage = new Stage();
	private Table table = new Table();
	private TiledBackground bg = new TiledBackground(Gdx.files.internal("menu/bg_main.png"), false);
	private TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("menu/menu.atlas"));
	private Skin skin = new Skin(Gdx.files.internal("menu/menu.json"), atlas);
	private LabelStyle styleTitleLabel = new LabelStyle(skin.get("default", LabelStyle.class));
	private Label lblTitle = new Label("AIR HOCKEY", styleTitleLabel);
	private TextButtonStyle styleMenuButton = new TextButtonStyle(skin.get("menuLabel", TextButtonStyle.class));
	private TextButton btnSingleplayer = new TextButton("Singleplayer", styleMenuButton);
	private TextButton btnMultiplayer = new TextButton("Multiplayer", styleMenuButton);
	private TextButton btnPreferences = new TextButton("Preferences", styleMenuButton);
	private TextButton btnCredits = new TextButton("Credits", styleMenuButton);
	private TextButton btnExit = new TextButton("Exit", styleMenuButton);

	class RunMultiplayer implements Runnable
	{
		@Override
		public void run()
		{
			parent.startMultiplayer();
		}
	};

	class RunSingleplayer implements Runnable
	{
		@Override
		public void run()
		{
			parent.startSingleplayer();
		}
	};

	class RunPreferences implements Runnable
	{
		@Override
		public void run()
		{
			parent.switchTo(2);
		}
	}

	class RunCredits implements Runnable
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
			public void clicked(final InputEvent event, final float x, final float y)
			{
				audio.playSound(SFX.MENU_CLICK);
				stage.addAction(Actions.sequence(Actions.moveTo(-480.0f, 0.0f, 0.5f), Actions.run(new RunSingleplayer())));
			}

			@Override
			public void enter(final InputEvent event, final float x, final float y, final int pointer, final Actor fromActor)
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
			public void clicked(final InputEvent event, final float x, final float y)
			{
				audio.playSound(SFX.MENU_CLICK);
				stage.addAction(Actions.sequence(Actions.moveTo(-480.0f, 0.0f, 0.5f), Actions.run(new RunMultiplayer())));
			}

			@Override
			public void enter(final InputEvent event, final float x, final float y, final int pointer, final Actor fromActor)
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
			public void clicked(final InputEvent event, final float x, final float y)
			{
				audio.playSound(SFX.MENU_CLICK);
				stage.addAction(Actions.sequence(Actions.moveTo(-480.0f, 0.0f, 0.5f), Actions.run(new RunPreferences())));
			}

			@Override
			public void enter(final InputEvent event, final float x, final float y, final int pointer, final Actor fromActor)
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
			public void clicked(final InputEvent event, final float x, final float y)
			{
				audio.playSound(SFX.MENU_CLICK);
				stage.addAction(Actions.sequence(Actions.moveTo(-480.0f, 0.0f, 0.5f), Actions.run(new RunCredits())));
			}

			@Override
			public void enter(final InputEvent event, final float x, final float y, final int pointer, final Actor fromActor)
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
			public void enter(final InputEvent event, final float x, final float y, final int pointer, final Actor fromActor)
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
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
		atlas.dispose();
		skin.dispose();
		stage.dispose();
	}
}