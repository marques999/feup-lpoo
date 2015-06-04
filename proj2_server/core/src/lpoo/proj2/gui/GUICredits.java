package lpoo.proj2.gui;

import lpoo.proj2.AirHockey;
import lpoo.proj2.StyleFactory;
import lpoo.proj2.audio.SFX;
import lpoo.proj2.audio.Song;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class GUICredits extends GUIScreen
{
	private final Stage stage = new Stage();
	private final Table table = new Table();
	private final Texture background = new Texture(Gdx.files.internal("menu/bg_credits.png"));
	private final TextButton btnBack = new TextButton("< BACK", StyleFactory.MenuButton);

	public GUICredits(final AirHockey parent)
	{
		super(parent, Song.THEME_CREDITS);

		table.add(new Label("credits", StyleFactory.TitleLabel)).padBottom(32).row();
		table.add(new Label("Music", StyleFactory.GradientLabel)).padBottom(16).row();
		table.add(new Label("\"Warp Room\"", StyleFactory.SmallLabel)).padBottom(8).row();
		table.add(new Label("Mark Mothersbaugh", StyleFactory.SmallLabel)).padBottom(8).row();
		table.add(new Label("Crash Bandicoot: Warped / SCEE", StyleFactory.SmallLabel)).padBottom(32).row();
		table.add(new Label("\"Gangnam Style\"", StyleFactory.SmallLabel)).padBottom(8).row();
		table.add(new Label("Psy", StyleFactory.SmallLabel)).padBottom(8).row();
		table.add(new Label("BLAHBLAHBLAH", StyleFactory.SmallLabel)).padBottom(48).row();
		table.add(new Label("\"Round Start\"", StyleFactory.SmallLabel)).padBottom(8).row();
		table.add(new Label("Katsushi Tatsuma", StyleFactory.SmallLabel)).padBottom(8).row();
		table.add(new Label("Chase H.Q / Taito", StyleFactory.SmallLabel)).padBottom(32).row();
		table.add(new Label("\"Name Entry\"", StyleFactory.SmallLabel)).padBottom(8).row();
		table.add(new Label("Go Satou", StyleFactory.SmallLabel)).padBottom(8).row();
		table.add(new Label("Raiden DX / Seibu Kaihatsu", StyleFactory.SmallLabel)).padBottom(48).row();
		table.add(new Label("Sound Effects", StyleFactory.GradientLabel)).padBottom(16).row();
		table.add(new Label("Quake II", StyleFactory.SmallLabel)).padBottom(8).row();
		table.add(new Label("id Software", StyleFactory.SmallLabel)).padBottom(32).row();
		table.add(new Label("Glow Hockey 2", StyleFactory.SmallLabel)).padBottom(8).row();
		table.add(new Label("Natenai Ariyatrakool", StyleFactory.SmallLabel)).padBottom(32).row();
		table.add(new Label("Physics", StyleFactory.GradientLabel)).padBottom(16).row();
		table.add(new Label("Diogo Marques", StyleFactory.SmallLabel)).padBottom(8).row();
		table.add(new Label("Pedro Melo", StyleFactory.SmallLabel)).padBottom(16).row();
		table.setFillParent(true);

		btnBack.setPosition(48, 30);
		btnBack.addListener(new ClickListener()
		{
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				audio.playSound(SFX.MENU_CLICK);
				parent.switchTo(1);
			}

			@Override
			public void enter(final InputEvent event, final float x, final float y, final int pointer, final Actor fromActor)
			{
				if (!btnBack.isPressed())
				{
					audio.playSound(SFX.MENU_SELECT);
				}
			}
		});

		stage.addActor(table);
		stage.addActor(btnBack);
	}

	public void reset()
	{
		if (table.getY() >= 1000.0f)
		{
			show();
		}
	}

	@Override
	public void render(float delta)
	{
		reset();
		stage.act();
		batch.begin();
		batch.draw(background, 0, 0, 480, 800);
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
		table.getActions().clear();
		table.setPosition(0.0f, -800.0f);
		table.addAction(Actions.moveBy(0.0f, 1800.0f, 30));
	}

	@Override
	public void dispose()
	{
		stage.dispose();
	}
}