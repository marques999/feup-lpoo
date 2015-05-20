package lpoo.proj2.gui;

import lpoo.proj2.AirHockey;
import lpoo.proj2.audio.AudioManager;
import lpoo.proj2.audio.SFX;
import lpoo.proj2.audio.Song;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class GUIMainMenu extends GUIScreen
{
	private SpriteBatch _sb;

	public GUIMainMenu(AirHockey parent)
	{
		super(parent);

		table.add(lblTitle).padBottom(64).row();
		table.add(btnSingleplayer).size(216, 49).padBottom(16).row();
		table.add(btnMultiplayer).size(216, 49).padBottom(16).row();
		table.add(btnPreferences).size(216, 49).padBottom(16).row();
		table.add(btnExit).size(216, 49).padBottom(16).row();
		table.setFillParent(true);
		stage.addActor(table);

		_sb = new SpriteBatch();
		_bgmusic = Song.THEME_MAIN_MENU;
		
		btnSingleplayer.addListener(new MenuListener(0, SFX.MENU_SELECT, SFX.MENU_CLICK));
		btnMultiplayer.addListener(new MenuListener(0, SFX.MENU_SELECT, SFX.MENU_CLICK));
		btnPreferences.addListener(new MenuListener(0, SFX.MENU_SELECT, SFX.MENU_CLICK));
		
		btnExit.addListener(new ClickListener()
		{
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				Gdx.app.exit();
			}
		});
	}

	private Stage stage = new Stage();
	private Table table = new Table();
	private Texture _bg = new Texture(Gdx.files.internal("menu/bg.png"));
	private TextureAtlas _buttons = new TextureAtlas(Gdx.files.internal("menu/menu.atlas"));
	private Skin skin = new Skin(Gdx.files.internal("menu/menu.json"), _buttons);
	private TextButton btnSingleplayer = new TextButton("Singleplayer", skin);
	private TextButton btnMultiplayer = new TextButton("Multiplayer", skin);
	private TextButton btnPreferences = new TextButton("Preferences", skin);
	private TextButton btnExit = new TextButton("Exit", skin);
	private Label lblTitle = new Label("Air Hockey", skin);

	@Override
	public void render(float delta)
	{
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act();
		_sb.begin();
		_sb.draw(_bg, 0, 0, 480, 800);
		_sb.end();
		stage.draw();
	}

	@Override
	public void resize(int width, int height)
	{
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
			AudioManager.getInstance().playSound(_click);
			_parent.switchTo(_id);
		}
		
		@Override
		public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
		{
			AudioManager.getInstance().playSound(_hover);
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
		stage.dispose();
		skin.dispose();
	}
}