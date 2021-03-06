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
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class GUISelect extends GUIScreen
{
	private final Stage stage = new Stage();
	private final Table table = new Table();
	private final Texture overlay = new Texture(Gdx.files.internal("menu/bg_overlay.png"));
	private final TiledBackground bg = new TiledBackground(Gdx.files.internal("menu/bg_select.png"), true);
	private final Label lblTitle = new Label("Singleplayer", StyleFactory.TitleLabel);
	private final TextButton btnMode1 = new TextButton("BEST OF 5", StyleFactory.MenuButton);
	private final TextButton btnMode2 = new TextButton("BEST OF 10", StyleFactory.MenuButton);
	private final TextButton btnMode3 = new TextButton("FIRST TO 15", StyleFactory.MenuButton);
	private final TextButton btnMode4 = new TextButton("PUCK ATTACK", StyleFactory.MenuButton);
	private final TextButton btnBack = new TextButton("< BACK", StyleFactory.MenuButton);

	private class MenuListener extends ClickListener
	{
		private final TextButton button;
		private final int mode;

		public MenuListener(TextButton paramButton, int paramMode)
		{
			button = paramButton;
			mode = paramMode;
		}

		@Override
		public void clicked(InputEvent event, float x, float y)
		{
			audio.playSound(SFX.MENU_CLICK);
			parent.setMode(mode);
			parent.switchTo(0);
		}

		@Override
		public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor)
		{
			if (!button.isPressed())
			{
				audio.playSound(SFX.MENU_SELECT);
			}
		}
	}

	public GUISelect(final AirHockey parent)
	{
		super(parent, Song.THEME_SELECT);

		table.add(lblTitle).padBottom(96).row();
		table.defaults().size(216, 49).padBottom(16);
		table.add(btnMode1).row();
		table.add(btnMode2).row();
		table.add(btnMode3).row();
		table.add(btnMode4).row();
		table.setFillParent(true);
		stage.addActor(table);
		stage.addActor(btnBack);
		btnBack.setPosition(36, 32);
		btnMode1.addListener(new MenuListener(btnMode1, 0));
		btnMode2.addListener(new MenuListener(btnMode2, 1));
		btnMode3.addListener(new MenuListener(btnMode3, 2));
		btnMode4.addListener(new MenuListener(btnMode4, 3));

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
	}

	@Override
	public void render(float delta)
	{
		bg.update(delta);
		stage.act();
		batch.begin();
		bg.render(batch);
		batch.draw(overlay, 0, 0, overlay.getWidth(), overlay.getHeight());
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

		if (parent.isMultiplayer())
		{
			lblTitle.setText("multiplayer");
			btnMode4.setVisible(true);
		}
		else
		{
			lblTitle.setText("singleplayer");
			btnMode4.setVisible(false);
		}
	}

	@Override
	public void dispose()
	{
		stage.dispose();
	}
}