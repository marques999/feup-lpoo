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
		table.add(new Label("\"Suki Sugite Baka Mitai\"", StyleFactory.SmallLabel)).padBottom(8).row();
		table.add(new Label("DEF.DIVA", StyleFactory.SmallLabel)).padBottom(8).row();
		table.add(new Label("Zetima", StyleFactory.SmallLabel)).padBottom(32).row();
		table.add(new Label("\"Sabre Dance\"", StyleFactory.SmallLabel)).padBottom(8).row();
		table.add(new Label("Aram Khachaturian", StyleFactory.SmallLabel)).padBottom(8).row();
		table.add(new Label("Berliner Philharmoniker", StyleFactory.SmallLabel)).padBottom(32).row();
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
		table.add(new Label("Graphics", StyleFactory.GradientLabel)).padBottom(16).row();
		table.add(new Label("Glow Hockey 2", StyleFactory.SmallLabel)).padBottom(8).row();
		table.add(new Label("Natenai Ariyatrakool", StyleFactory.SmallLabel)).padBottom(32).row();
		table.add(new Label("Programming", StyleFactory.GradientLabel)).padBottom(16).row();
		table.add(new Label("Diogo Marques", StyleFactory.SmallLabel)).padBottom(8).row();
		table.add(new Label("Pedro Melo", StyleFactory.SmallLabel)).padBottom(32).row();
		table.add(new Label("Game Design", StyleFactory.GradientLabel)).padBottom(16).row();
		table.add(new Label("Diogo Marques", StyleFactory.SmallLabel)).padBottom(8).row();
		table.add(new Label("Pedro Melo", StyleFactory.SmallLabel)).padBottom(32).row();
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
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
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

	@Override
	public void render(float delta)
	{
		if (table.getY() >= 1200.0f)
		{
			show();
		}

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
		table.setPosition(0.0f, -1200.0f);
		table.addAction(Actions.moveBy(0.0f, 2500.0f, 40));
	}

	@Override
	public void dispose()
	{
		stage.dispose();
	}
}