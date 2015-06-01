package lpoo.proj2.gui;

import lpoo.proj2.AirHockey;
import lpoo.proj2.audio.SFX;
import lpoo.proj2.audio.Song;
import lpoo.proj2.audio.Special;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox.CheckBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
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
	private final String difficulty[] = { "Easy", "Medium", "Hard", "Insane" };
	private final String colors[] = { "Blue", "Green", "Red", "Yellow" };
	private final Color colorsKey[] = { Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW };

	private int colorIndex;
	private int difficultyIndex;

	private final TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("menu/menu.atlas"));
	private final ButtonGroup<TextButton> btnGroup = new ButtonGroup<TextButton>();
	private final Skin skin = new Skin(Gdx.files.internal("menu/menu.json"), atlas);
	private final Stage stage = new Stage();
	private final Table table = new Table();

	private final CheckBoxStyle styleCheckboxDefault = new CheckBoxStyle(skin.get("default", CheckBoxStyle.class));
	private final CheckBox chkQuake = new CheckBox("quake-style sounds", styleCheckboxDefault);

	private final LabelStyle styleSmallLabel = new LabelStyle(skin.get("smallLabel", LabelStyle.class));
	private final LabelStyle styleTitleLabel = new LabelStyle(skin.get("default", LabelStyle.class));
	private final Label lblDifficulty = new Label("DIFFICULTY", styleSmallLabel);
	private final Label lblBGM = new Label("BGM MUSIC", styleSmallLabel);
	private final Label lblMusicVolume = new Label("MUSIC VOLUME", styleSmallLabel);
	private final Label lblSFXVolume = new Label("SFX VOLUME", styleSmallLabel);
	private final Label lblPaddle = new Label("PADDLE COLOR", styleSmallLabel);
	private final Label lblTitle = new Label("preferences", styleTitleLabel);

	private final SliderStyle styleDefaultSlider = new SliderStyle(skin.get("default-horizontal", SliderStyle.class));
	private final Slider sliderMusicVolume = new Slider(0.0f, 1.0f, 0.1f, false, styleDefaultSlider);
	private final Slider sliderSFXVolume = new Slider(0.0f, 1.0f, 0.1f, false, styleDefaultSlider);

	private final ImageButtonStyle stylePreviewButton = new ImageButtonStyle(skin.get("default", ImageButtonStyle.class));
	private final ImageButton btnPreview = new ImageButton(stylePreviewButton);

	private final TextButtonStyle styleDefaultButton = new TextButtonStyle(skin.get("default", TextButtonStyle.class));
	private final TextButtonStyle styleMenuButton = new TextButtonStyle(skin.get("menuLabel", TextButtonStyle.class));
	private final TextButtonStyle styleGreenButton = new TextButtonStyle(skin.get("menuGreen", TextButtonStyle.class));
	private final TextButtonStyle styleRedButton = new TextButtonStyle(skin.get("menuRed", TextButtonStyle.class));
	private final TextButtonStyle styleYellowButton = new TextButtonStyle(skin.get("menuYellow", TextButtonStyle.class));
	private final TextButtonStyle styleToggleButton = new TextButtonStyle(skin.get("toggle", TextButtonStyle.class));
	private final TextButton btnOK = new TextButton("OK", styleMenuButton);
	private final TextButton btnCancel = new TextButton("Cancel", styleMenuButton);
	private final TextButton btnDifficulty = new TextButton(difficulty[difficultyIndex], styleDefaultButton);
	private final TextButton btnColor = new TextButton(colors[colorIndex], styleDefaultButton);
	private final TextButtonStyle btnColorStyle[] = { styleDefaultButton, styleGreenButton, styleRedButton, styleYellowButton };
	private final TextButton btnThemeA = new TextButton("THEME A", styleToggleButton);
	private final TextButton btnThemeB = new TextButton("THEME B", styleToggleButton);

	private float oldMusicVolume;
	private float oldSFXVolume;

	public GUIOptions(final AirHockey parent)
	{
		super(parent, Song.THEME_SPLASH);

		btnGroup.add(btnThemeA);
		btnGroup.add(btnThemeB);
		btnGroup.setMaxCheckCount(1);
		btnGroup.setMinCheckCount(0);
		btnGroup.setUncheckLast(true);
		table.setFillParent(true);
		table.padLeft(32).padRight(48);
		table.add(lblTitle).padBottom(48).colspan(2);
		table.row();
		table.defaults().right().padBottom(16);
		table.add(lblSFXVolume).left();
		table.add(sliderSFXVolume).width(180);
		table.row();
		table.add(lblMusicVolume).left();
		table.add(sliderMusicVolume).width(180);
		table.row();
		table.add(lblBGM).left();
		table.add(btnThemeA).width(180);
		table.row();
		table.add(btnPreview).left();
		table.add(btnThemeB).width(180);
		table.row();
		table.add(chkQuake).left().colspan(2);
		table.row();
		table.add(lblDifficulty).left();
		table.add(btnDifficulty).width(180);
		table.row();
		table.add(lblPaddle).left();
		table.add(btnColor).width(180);
		table.row();
		table.add(btnOK).padTop(48).width(160).center();
		table.add(btnCancel).padTop(48).width(160).center();
		table.row();
		stage.addActor(table);

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
			}
		});

		btnCancel.addListener(new ClickListener()
		{
			@Override
			public void clicked(final InputEvent event, float x, float y)
			{
				audio.playSound(SFX.MENU_CLICK);
				audio.setMusicVolume(oldMusicVolume);
				audio.setSFXVolume(oldSFXVolume);
				parent.switchTo(1);
			}

			@Override
			public void enter(final InputEvent event, float x, float y, int pointer, final Actor fromActor)
			{
				if (!btnCancel.isPressed())
				{
					audio.playSound(SFX.MENU_SELECT);
				}
			}
		});

		btnOK.addListener(new ClickListener()
		{
			@Override
			public void clicked(final InputEvent event, final float x, final float y)
			{
				audio.playSound(SFX.MENU_CLICK);
				audio.setMusicVolume(sliderMusicVolume.getValue());
				audio.setSFXVolume(sliderSFXVolume.getValue());

				if (chkQuake.isChecked())
				{
					AirHockey.enableQuake();
				}
				else
				{
					AirHockey.disableQuake();
				}

				AirHockey.setDifficulty(difficultyIndex);
				AirHockey.setColor(colorsKey[colorIndex]);
				AirHockey.setColorIndex(colorIndex);
				AirHockey.setTheme(btnThemeA.isChecked() ? 0 : 1);
				AirHockey.setMusicVolume(sliderMusicVolume.getValue());
				AirHockey.setSFXVolume(sliderSFXVolume.getValue());
				parent.switchTo(1);
			}

			@Override
			public void enter(final InputEvent event, final float x, final float y, final int pointer, final Actor fromActor)
			{
				if (!btnOK.isPressed())
				{
					audio.playSound(SFX.MENU_SELECT);
				}
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
			
			@Override
			public void enter(final InputEvent event, final float x, final float y, final int pointer, final Actor fromActor)
			{
				if (!btnDifficulty.isPressed())
				{
					audio.playSound(SFX.MENU_SELECT);
				}
			}
		});

		btnColor.addListener(new ClickListener()
		{
			@Override
			public void clicked(final InputEvent event, final float x, final float y)
			{
				audio.playSound(SFX.MENU_CLICK);
				colorIndex = (colorIndex + 1) % colors.length;
				btnColor.setText(colors[colorIndex]);
				btnColor.setStyle(btnColorStyle[colorIndex]);
			}
			
			@Override
			public void enter(final InputEvent event, final float x, final float y, final int pointer, final Actor fromActor)
			{
				if (!btnColor.isPressed())
				{
					audio.playSound(SFX.MENU_SELECT);
				}
			}
		});

		btnThemeA.addListener(new ClickListener()
		{
			@Override
			public void clicked(final InputEvent event, final float x, final float y)
			{
				audio.playSound(SFX.MENU_CLICK);
			}
		});

		btnThemeB.addListener(new ClickListener()
		{
			@Override
			public void clicked(final InputEvent event, final float x, final float y)
			{
				audio.playSound(SFX.MENU_CLICK);
			}
		});

		btnPreview.addListener(new ClickListener()
		{
			@Override
			public void clicked(final InputEvent event, final float x, final float y)
			{
				audio.playSound(SFX.MENU_CLICK);
				
				if (btnThemeA.isChecked())
				{
					audio.playSong(Song.THEME_A, false);
				}
				else
				{
					audio.playSong(Song.THEME_B, false);
				}
			}
			
			@Override
			public void enter(final InputEvent event, final float x, final float y, final int pointer, final Actor fromActor)
			{
				if (!btnPreview.isPressed())
				{
					audio.playSound(SFX.MENU_SELECT);
				}
			}
		});

		chkQuake.addListener(new ClickListener()
		{
			@Override
			public void clicked(final InputEvent event, final float x, final float y)
			{
				if (chkQuake.isChecked())
				{
					audio.playSpecial(Special.QUAKE_DOUBLEKILL);
				}
			}
		});
	}

	@Override
	public void render(final float delta)
	{
		Gdx.gl.glClearColor(0.100f, 0.100f, 0.100f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();
		stage.draw();
	}

	@Override
	public void resize(final int width, final int height)
	{
		stage.getViewport().update(width, height);
	}

	@Override
	public void show()
	{
		Gdx.input.setInputProcessor(stage);
		difficultyIndex = AirHockey.getDifficulty();
		btnDifficulty.setText(difficulty[difficultyIndex]);
		colorIndex = AirHockey.getColorIndex();
		btnColor.setText(colors[colorIndex]);
		btnColor.setStyle(btnColorStyle[colorIndex]);
		sliderSFXVolume.setValue(AirHockey.getSFXVolume());
		sliderMusicVolume.setValue(AirHockey.getMusicVolume());
		chkQuake.setChecked(AirHockey.isQuakeEnabled());

		if (AirHockey.getTheme() == Song.THEME_A)
		{
			btnThemeA.setChecked(true);
			btnThemeB.setChecked(false);
		}
		else
		{
			btnThemeA.setChecked(false);
			btnThemeB.setChecked(true);
		}

		oldMusicVolume = audio.getMusicVolume();
		oldSFXVolume = audio.getSFXVolume();
	}

	@Override
	public void pause()
	{
		System.out.println("GAME PAUSED");
	}

	@Override
	public void resume()
	{
		System.out.println("GAME RESUMED");
	}

	@Override
	public void dispose()
	{
		stage.dispose();
		atlas.dispose();
		skin.dispose();
	}
}