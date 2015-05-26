package lpoo.proj2.gui;

import lpoo.proj2.AirHockey;
import lpoo.proj2.audio.SFX;
import lpoo.proj2.audio.Song;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class GUIMainMenu extends GUIScreen
{
	private Stage stage = new Stage();
	private Table table = new Table();
	private Texture texture = new Texture(Gdx.files.internal("menu/bg.png"));
	private TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("menu/menu.atlas"));
	private Skin skin = new Skin(Gdx.files.internal("menu/menu.json"), atlas);
	
	private LabelStyle styleTitleLabel = new LabelStyle(skin.get("default", LabelStyle.class));
	private Label lblTitle = new Label("Air Hockey", styleTitleLabel);
	
	private TextButtonStyle styleMenuButton = new TextButtonStyle(skin.get("menuLabel", TextButtonStyle.class));
	private TextButton btnSingleplayer = new TextButton("Singleplayer", styleMenuButton);
	private TextButton btnMultiplayer = new TextButton("Multiplayer", styleMenuButton);
	private TextButton btnPreferences = new TextButton("Preferences", styleMenuButton);
	private TextButton btnCredits = new TextButton("Credits", styleMenuButton);
	private TextButton btnExit = new TextButton("Exit", styleMenuButton);

	public GUIMainMenu(final AirHockey parent)
	{
		super(parent);

		table.add(lblTitle).padBottom(64).row();
		table.defaults().size(216, 49).padBottom(16);
		table.add(btnSingleplayer).row();
		table.add(btnMultiplayer).row();
		table.add(btnPreferences).row();
		table.add(btnCredits).row();
		table.add(btnExit).row();
		table.setFillParent(true);
		stage.addActor(table);
		bgmusic = Song.THEME_MAIN_MENU;
		
		btnSingleplayer.addListener(new ClickListener()
		{
			@Override
			public void clicked(final InputEvent event, final float x, final float y)
			{
				audio.playSound(SFX.MENU_CLICK);
				parent.startSingleplayer();
			}

			@Override
			public void enter(final InputEvent event, final float x, final float y, final int pointer, final Actor fromActor)
			{
				audio.playSound(SFX.MENU_SELECT);
			}
		});
		
		btnMultiplayer.addListener(new ClickListener()
		{
			@Override
			public void clicked(final InputEvent event, final float x, final float y)
			{
				audio.playSound(SFX.MENU_CLICK);
				parent.startMultiplayer();
			}

			@Override
			public void enter(final InputEvent event, final float x, final float y, final int pointer, final Actor fromActor)
			{
				audio.playSound(SFX.MENU_SELECT);
			}
		});

		btnPreferences.addListener(new MenuListener(2, SFX.MENU_SELECT, SFX.MENU_CLICK));
		btnCredits.addListener(new MenuListener(4, SFX.MENU_SELECT, SFX.MENU_CLICK));
		
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
				audio.playSound(SFX.MENU_SELECT);
			}
		});
	}

	@Override
	public void render(float delta)
	{
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();
		batch.begin();
		batch.draw(texture, 0, 0, texture.getWidth(), texture.getHeight());
		batch.end();
		stage.draw();
	}

	@Override
	public void resize(int width, int height)
	{
		  stage.getViewport().update(width, height);
	}
	
	private class MenuListener extends ClickListener
	{
		private int _id;
		private SFX _hover;
		private SFX _click;
		
		public MenuListener(int menuId, SFX sfxHover, SFX sfxClick)
		{
			_id = menuId;
			_hover = sfxHover;
			_click = sfxClick;
		}
		
		@Override
		public void clicked(InputEvent event, float x, float y)
		{
			audio.playSound(_click);
			parent.switchTo(_id);
		}
		
		@Override
		public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
		{
			audio.playSound(_hover);
		}
	}

	@Override
	public void show()
	{
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void hide()
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
	public void dispose()
	{
		atlas.dispose();
		skin.dispose();
		stage.dispose();
	}
}