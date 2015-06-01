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
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class GUICredits extends GUIScreen
{
	private Stage stage = new Stage();
	private Table table = new Table();
	private Texture _bg = new Texture(Gdx.files.internal("menu/bg_credits.png"));
	private TextureAtlas _buttons = new TextureAtlas(Gdx.files.internal("menu/menu.atlas"));
	private Skin skin = new Skin(Gdx.files.internal("menu/menu.json"), _buttons);

	private LabelStyle styleDefaultLabel = new LabelStyle(skin.get("default", LabelStyle.class));
	private LabelStyle styleGradientLabel = new LabelStyle(skin.get("gradientLabel", LabelStyle.class));
	private LabelStyle styleSmallLabel = new LabelStyle(skin.get("smallLabel", LabelStyle.class));
	private Label lblDiogoMarques = new Label("Diogo Marques", styleSmallLabel);
	private Label lblPedroMelo = new Label("Pedro Melo", styleSmallLabel);

	private TextButtonStyle styleButton = new TextButtonStyle(skin.get("menuLabel", TextButtonStyle.class));
	private TextButton btnBack = new TextButton("< BACK", styleButton);

	public GUICredits(final AirHockey parent)
	{
		super(parent, Song.THEME_CREDITS);

		table.add(new Label("credits", styleDefaultLabel)).padBottom(32).row();
		table.add(new Label("Music", styleGradientLabel)).padBottom(16).row();
		table.add(new Label("\"Warp Room\"", styleSmallLabel)).padBottom(8).row();
		table.add(new Label("Mark Mothersbaugh", styleSmallLabel)).padBottom(8).row();
		table.add(new Label("Crash Bandicoot: Warped / SCEE", styleSmallLabel)).padBottom(32).row();
		table.add(new Label("\"Round Start\"", styleSmallLabel)).padBottom(8).row();
		table.add(new Label("Katsushi Tatsuma", styleSmallLabel)).padBottom(8).row();
		table.add(new Label("Chase H.Q / Taito", styleSmallLabel)).padBottom(32).row();
		table.add(new Label("\"Name Entry\"", styleSmallLabel)).padBottom(8).row();
		table.add(new Label("Go Satou", styleSmallLabel)).padBottom(8).row();
		table.add(new Label("Raiden DX / Seibu Kaihatsu", styleSmallLabel)).padBottom(48).row();
		table.add(new Label("Sound Effects", styleGradientLabel)).padBottom(16).row();
		table.add(new Label("Quake II", styleSmallLabel)).padBottom(8).row();
		table.add(new Label("id Software", styleSmallLabel)).padBottom(32).row();
		table.add(new Label("Glow Hockey 2", styleSmallLabel)).padBottom(8).row();
		table.add(new Label("Natenai Ariyatrakool", styleSmallLabel)).padBottom(32).row();
		table.add(new Label("Physics", styleGradientLabel)).padBottom(16).row();
		table.add(lblDiogoMarques).padBottom(8).row();
		table.add(lblPedroMelo).padBottom(16).row();
		table.setFillParent(true);
		btnBack.setPosition(48, 30);
		btnBack.addListener(new MenuListener(1, SFX.MENU_SELECT, SFX.MENU_CLICK));
		stage.addActor(table);
		stage.addActor(btnBack);
	}

	@Override
	public void render(float delta)
	{
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if (table.getY() >= 1800.0f)
		{
			show();
		}
		
		stage.act();
		batch.begin();
		batch.draw(_bg, 0, 0, 480, 800);
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
		table.getActions().clear();
		table.setPosition(0.0f, -800.0f);
		table.addAction(Actions.moveBy(0.0f, 1800.0f, 30));
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