package lpoo.proj2.gui;

import lpoo.proj2.AirHockey;
import lpoo.proj2.StyleFactory;
import lpoo.proj2.audio.SFX;
import lpoo.proj2.audio.Song;
import lpoo.proj2.audio.Special;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class GUIOptions extends GUIScreen
{
	private final String difficulty[] = { "Easy", "Medium", "Hard", "Insane" };
	private final String colors[] = { "Blue", "Green", "Red", "Yellow", "Purple", "Orange" };

	private int colorIndex;
	private int difficultyIndex;
	private boolean oldQuake;
	private float oldMusicVolume;
	private float oldSFXVolume;

	private final ButtonGroup<TextButton> btnGroup = new ButtonGroup<TextButton>();
	private final Stage stage = new Stage();
	private final Table table = new Table();
	private final CheckBox chkQuake = new CheckBox("quake-style sounds", StyleFactory.DefaultCheckbox);
	private final Label lblDifficulty = new Label("DIFFICULTY", StyleFactory.SmallLabel);
	private final Label lblBGM = new Label("BGM MUSIC", StyleFactory.SmallLabel);
	private final Label lblMusicVolume = new Label("MUSIC VOLUME", StyleFactory.SmallLabel);
	private final Label lblSFXVolume = new Label("SFX VOLUME", StyleFactory.SmallLabel);
	private final Label lblPaddle = new Label("PUCK COLOR", StyleFactory.SmallLabel);
	private final Label lblTitle = new Label("preferences", StyleFactory.TitleLabel);
	private final Slider sliderMusicVolume = new Slider(0.0f, 1.0f, 0.1f, false, StyleFactory.DefaultSlider);
	private final Slider sliderSFXVolume = new Slider(0.0f, 1.0f, 0.1f, false, StyleFactory.DefaultSlider);
	private final ImageButton btnPreview = new ImageButton(StyleFactory.VolumeButton);
	private final TextButton btnOK = new TextButton("OK", StyleFactory.MenuButton);
	private final TextButton btnCancel = new TextButton("Cancel", StyleFactory.MenuButton);
	private final TextButton btnDifficulty = new TextButton(difficulty[difficultyIndex], StyleFactory.BlueButton);
	private final TextButton btnColor = new TextButton(colors[colorIndex], StyleFactory.BlueButton);
	private final TextButton btnThemeA = new TextButton("THEME A", StyleFactory.ToggleButton);
	private final TextButton btnThemeB = new TextButton("THEME B", StyleFactory.ToggleButton);

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
			public void clicked(InputEvent event, float x, float y)
			{
				audio.playSound(SFX.MENU_CLICK);
				audio.setMusicVolume(oldMusicVolume);
				audio.setSFXVolume(oldSFXVolume);

				if (oldQuake)
				{
					AirHockey.enableQuake();
				}
				else
				{
					AirHockey.disableQuake();
				}

				parent.switchTo(1);
			}

			@Override
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
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
			public void clicked(InputEvent event, float x, float y)
			{
				audio.playSound(SFX.MENU_CLICK);
				AirHockey.setDifficulty(difficultyIndex);
				AirHockey.setColor(colorIndex);
				AirHockey.setTheme(btnThemeA.isChecked() ? 0 : 1);
				AirHockey.setMusicVolume(sliderMusicVolume.getValue());
				AirHockey.setSFXVolume(sliderSFXVolume.getValue());
				parent.switchTo(1);
			}

			@Override
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
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
			public void clicked(InputEvent event, float x, float y)
			{
				audio.playSound(SFX.MENU_CLICK);
				difficultyIndex = (difficultyIndex + 1) % difficulty.length;
				btnDifficulty.setText(difficulty[difficultyIndex]);
			}

			@Override
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
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
			public void clicked(InputEvent event, float x, float y)
			{
				audio.playSound(SFX.MENU_CLICK);
				colorIndex = (colorIndex + 1) % colors.length;
				btnColor.setText(colors[colorIndex]);
				btnColor.setStyle(StyleFactory.ButtonStyles[colorIndex]);
			}

			@Override
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
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
			public void clicked(InputEvent event, float x, float y)
			{
				audio.playSound(SFX.MENU_CLICK);
			}

			@Override
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
			{
				if (!btnThemeA.isPressed() && !btnThemeA.isChecked())
				{
					audio.playSound(SFX.MENU_SELECT);
				}
			}
		});

		btnThemeB.addListener(new ClickListener()
		{
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				audio.playSound(SFX.MENU_CLICK);
			}

			@Override
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
			{
				if (!btnThemeB.isPressed() && !btnThemeB.isChecked())
				{
					audio.playSound(SFX.MENU_SELECT);
				}
			}
		});

		btnPreview.addListener(new ClickListener()
		{
			@Override
			public void clicked(InputEvent event, float x, float y)
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
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
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
			public void clicked(InputEvent event, float x, float y)
			{
				if (chkQuake.isChecked())
				{
					AirHockey.enableQuake();
					audio.playSpecial(Special.QUAKE_DOUBLEKILL);
				}
				else
				{
					AirHockey.disableQuake();
				}
			}
		});
	}

	@Override
	public void render(float delta)
	{
		Gdx.gl.glClearColor(0.100f, 0.100f, 0.100f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();
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
		difficultyIndex = AirHockey.getDifficulty();
		btnDifficulty.setText(difficulty[difficultyIndex]);
		colorIndex = AirHockey.getColor();
		btnColor.setText(colors[colorIndex]);
		btnColor.setStyle(StyleFactory.ButtonStyles[colorIndex]);
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

		oldQuake = AirHockey.isQuakeEnabled();
		oldMusicVolume = audio.getMusicVolume();
		oldSFXVolume = audio.getSFXVolume();
	}

	@Override
	public void dispose()
	{
		stage.dispose();
	}
}