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
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class GUIOptions extends GUIScreen
{
	private SpriteBatch _sb;

	public GUIOptions(AirHockey parent)
	{
		super(parent);

		table.add(lblTitle).padBottom(64).row();
		table.add(lblSFXVolume).padBottom(16).row();
		table.add(sliderSFXVolume).padBottom(16).row();
		table.add(lblMusicVolume).padBottom(16).row();
		table.add(sliderMusicVolume).padBottom(16).row();
		table.add(btnOK).size(216, 49).padBottom(16).row();
		table.add(btnCancel).size(216, 49).padBottom(16).row();
		table.setFillParent(true);
		stage.addActor(table);
		Gdx.input.setInputProcessor(stage);
		
		_sb = new SpriteBatch();
		_bgmusic = Song.THEME_MAIN_MENU;

		sliderSFXVolume.setValue(AudioManager.getInstance().getSFXVolume());
		sliderMusicVolume.setValue(AudioManager.getInstance().getMusicVolume());

		sliderSFXVolume.addListener(new ChangeListener()
		{
			@Override
			public void changed(ChangeEvent event, Actor actor)
			{
				AudioManager.getInstance().setSFXVolume(sliderSFXVolume.getValue());
			}
		});

		sliderMusicVolume.addListener(new ChangeListener()
		{
			@Override
			public void changed(ChangeEvent event, Actor actor)
			{
				AudioManager.getInstance().setMusicVolume(sliderMusicVolume.getValue());
			}
		});

		btnCancel.addListener(new ClickListener()
		{
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				AudioManager.getInstance().playSound(SFX.MENU_CLICK);
				Gdx.app.exit();
			}
			
			@Override
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
			{
				AudioManager.getInstance().playSound(SFX.MENU_SELECT);
			}
		});

		btnOK.addListener(new ClickListener()
		{
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				AudioManager.getInstance().playSound(SFX.MENU_CLICK);
				Gdx.app.exit();
			}
			
			@Override
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
			{
				AudioManager.getInstance().playSound(SFX.MENU_SELECT);
			}
		});
	}

	private Stage stage = new Stage();
	private Table table = new Table();

	private Texture _bg = new Texture(Gdx.files.internal("menu/bg.png"));
	private TextureAtlas _buttons = new TextureAtlas(Gdx.files.internal("menu/menu.atlas"));
	private Skin skin = new Skin(Gdx.files.internal("menu/menu.json"), _buttons);
	
	private TextButton btnOK = new TextButton("Preferences", skin);
	private TextButton btnCancel = new TextButton("Exit", skin);
	private Slider sliderMusicVolume = new Slider(0.0f, 1.0f, 0.1f, false, skin);
	private Slider sliderSFXVolume = new Slider(0.0f, 1.0f, 0.1f, false, skin);
	private Label lblSFXVolume = new Label("SFX volume", skin, "smallLabel");
	private Label lblMusicVolume = new Label("Music Volume", skin, "smallLabel");
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

	@Override
	public void show()
	{

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
