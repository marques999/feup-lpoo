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
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class GUIDifficulty extends GUIScreen
{
	private Stage stage = new Stage();
	private Table table = new Table();
	private Texture background = new Texture(Gdx.files.internal("menu/bg.png"));
	private TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("menu/menu.atlas"));
	private Skin skin = new Skin(Gdx.files.internal("menu/menu.json"), atlas);
	
	private LabelStyle styleTitleLabel = new LabelStyle(skin.get("default", LabelStyle.class));
	private Label lblTitle = new Label("Difficulty", styleTitleLabel);
	
	private TextButtonStyle styleButton = new TextButtonStyle(skin.get("menuLabel", TextButtonStyle.class));
	private TextButton btnMode1 = new TextButton("BEST OF 5", styleButton);
	private TextButton btnMode2 = new TextButton("BEST OF 10", styleButton);
	private TextButton btnMode3 = new TextButton("FIRST TO 20", styleButton);
	private TextButton btnMode4 = new TextButton("PUCK ATTACK", styleButton);
	private TextButton btnBack = new TextButton("< BACK", styleButton);

	public GUIDifficulty(AirHockey parent)
	{
		super(parent);

		table.add(lblTitle).padBottom(64).row();
		table.defaults().size(216, 49).padBottom(16);
		table.add(btnMode1).row();
		table.add(btnMode2).row();
		table.add(btnMode3).row();
		table.add(btnMode4).row();
		table.add(btnBack).row();
		table.setFillParent(true);
		stage.addActor(table);
		bgmusic = Song.THEME_MAIN_MENU;
		btnMode1.addListener(new MenuListener(0, SFX.MENU_SELECT, SFX.MENU_CLICK));
		btnMode2.addListener(new MenuListener(0, SFX.MENU_SELECT, SFX.MENU_CLICK));
		btnMode3.addListener(new MenuListener(2, SFX.MENU_SELECT, SFX.MENU_CLICK));
		btnMode4.addListener(new MenuListener(2, SFX.MENU_SELECT, SFX.MENU_CLICK));
		btnBack.addListener(new MenuListener(1, SFX.MENU_SELECT, SFX.MENU_CLICK));
	}

	@Override
	public void render(float delta)
	{
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act();
		batch.begin();
		batch.draw(background, 0, 0, 480, 800);
		batch.end();
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
		stage.dispose();
		skin.dispose();
	}
}