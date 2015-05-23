package lpoo.proj2.gui;

import lpoo.proj2.AirHockey;
import lpoo.proj2.audio.AudioManager;
import lpoo.proj2.audio.SFX;
import lpoo.proj2.audio.Song;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox.CheckBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Slider.SliderStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class GUIOptions extends GUIScreen
{
	private final String difficulty[] =
		{
			"Easy", "Medium", "Hard", "Insane"
		};

	private final Texture background = new Texture(Gdx.files.internal("menu/bg.png"));
	private final TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("menu/menu.atlas"));
	private final Skin skin = new Skin(Gdx.files.internal("menu/menu.json"), atlas);
	private final Table table = new Table();
	private final ButtonGroup<TextButton> btnGroup = new ButtonGroup<TextButton>();
	private final Group guiGroup = new Group();

	private final CheckBoxStyle styleCheckboxDefault = new CheckBoxStyle(skin.get("default", CheckBoxStyle.class));
	private final CheckBox chkQuake = new CheckBox("quake-style sounds", styleCheckboxDefault);

	private final LabelStyle styleSmallLabel = new LabelStyle(skin.get("smallLabel", LabelStyle.class));
	private final LabelStyle styleTitleLabel = new LabelStyle(skin.get("default", LabelStyle.class));
	private final Label lblDifficulty = new Label("Difficulty", styleSmallLabel);
	private final Label lblBGM = new Label("BGM Music", styleSmallLabel);
	private final Label lblMusicVolume = new Label("Music Volume", styleSmallLabel);
	private final Label lblSFXVolume = new Label("SFX volume", styleSmallLabel);
	private final Label lblTitle = new Label("Preferences", styleTitleLabel);

	private final SliderStyle styleDefaultSlider = new SliderStyle(skin.get("default-horizontal", SliderStyle.class));
	private final Slider sliderMusicVolume = new Slider(0.0f, 1.0f, 0.1f, false, styleDefaultSlider);
	private final Slider sliderSFXVolume = new Slider(0.0f, 1.0f, 0.1f, false, styleDefaultSlider);
	
	private final TextButtonStyle styleDefaultButton = new TextButtonStyle(skin.get("default", TextButtonStyle.class));
	private final TextButtonStyle styleMenuButton = new TextButtonStyle(skin.get("menuLabel", TextButtonStyle.class));
	private final TextButton btnOK = new TextButton("OK", styleMenuButton);
	private final TextButton btnCancel = new TextButton("Cancel", styleMenuButton);
	private final TextButton btnThemeA = new TextButton("THEME A", styleDefaultButton);
	private final TextButton btnThemeB = new TextButton("THEME B", styleDefaultButton);
	private final TextButton btnDifficulty = new TextButton(difficulty[0], styleDefaultButton);

	private int difficultyIndex = 0;

	public GUIOptions(final AirHockey parent)
	{
		super(parent);

		btnGroup.add(btnThemeA);
		btnGroup.add(btnThemeB);
		btnGroup.setMaxCheckCount(1);
		btnGroup.setMinCheckCount(1);
		guiGroup.addActor(btnThemeA);
		guiGroup.addActor(btnThemeB);
		table.setFillParent(true);
		table.padLeft(32).padRight(32);
		table.add(lblTitle).padBottom(64).colspan(2).row();
		table.defaults().left().padBottom(16);
		table.add(lblSFXVolume).width(100);
		table.add(sliderSFXVolume).width(180).center().row();
		table.add(lblMusicVolume);
		table.add(sliderMusicVolume).width(180).center().row();
		table.add(lblBGM);
		table.add(guiGroup).width(120).row();
		table.add(lblDifficulty);
		table.add(btnDifficulty).left();
		table.row();
		table.add(chkQuake).colspan(2);
		table.padBottom(32).row();
		table.add(btnOK).width(180).center();
		table.add(btnCancel).width(180).center();
		table.row();
		stage.addActor(table);

		bgmusic = Song.THEME_GAME_OVER;

		sliderSFXVolume.setValue(AudioManager.getInstance().getSFXVolume());
		sliderMusicVolume.setValue(AudioManager.getInstance().getMusicVolume());

		sliderSFXVolume.addListener(new ChangeListener()
		{
			@Override
			public void changed(final ChangeEvent event, final Actor actor)
			{
				audio.setSFXVolume(sliderSFXVolume.getValue());
			}
		});

		sliderMusicVolume.addListener(new ChangeListener()
		{
			@Override
			public void changed(final ChangeEvent event, final Actor actor)
			{
				audio.setMusicVolume(sliderMusicVolume.getValue());
				audio.playSong(bgmusic, true);
			}
		});

		btnCancel.addListener(new ClickListener()
		{
			@Override
			public void clicked(final InputEvent event, final float x, final float y)
			{
				audio.playSound(SFX.MENU_CLICK);
				parent.switchTo(1);
			}

			@Override
			public void enter(final InputEvent event, final float x, final float y, final int pointer, final Actor fromActor)
			{
				audio.playSound(SFX.MENU_SELECT);
			}
		});

		btnOK.addListener(new ClickListener()
		{
			@Override
			public void clicked(final InputEvent event, final float x, final float y)
			{
				audio.playSound(SFX.MENU_CLICK);
				parent.switchTo(1);
			}

			@Override
			public void enter(final InputEvent event, final float x, final float y, final int pointer, final Actor fromActor)
			{
				audio.playSound(SFX.MENU_SELECT);
			}
		});

		btnDifficulty.addListener(new ClickListener()
		{
			@Override
			public void clicked(final InputEvent event, final float x, final float y)
			{
				audio.playSound(SFX.MENU_CLICK);
				difficultyIndex = (difficultyIndex + 1) % difficulty.length;
				btnDifficulty.setText(difficulty[difficultyIndex]);
			}
		});


		chkQuake.addListener(new ClickListener()
		{
			@Override
			public void clicked(final InputEvent event, final float x, final float y)
			{
				audio.playSound(SFX.MENU_CLICK);
			}
		});
	}

	private final Stage stage = new Stage();

	@Override
	public void render(final float delta)
	{
		Gdx.gl.glClearColor(0.100f, 0.100f, 0.100f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act();
		batch.begin();
		batch.draw(background, 0, 0, 480, 800);
		stage.draw();
		batch.end();
	}

	@Override
	public void resize(final int width, final int height)
	{
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
