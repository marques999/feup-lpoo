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
	private Label lblPhysics = new Label("Physics", styleGradientLabel);
	private Label lblMusic = new Label("Music", styleGradientLabel);
	private Label lblMarkMothersbaugh = new Label("Mark Mothersbaugh", styleSmallLabel);
	private Label lblCrashBandicoot = new Label("Crash Bandicoot 3: Warped", styleSmallLabel);
	private Label lblWarpRoom = new Label("Warp Room", styleSmallLabel);
	private Label lblDiogoMarques = new Label("Diogo Marques", styleSmallLabel);
	private Label lblPedroMelo = new Label("Pedro Melo", styleSmallLabel);
	private Label lblTaito = new Label("Taito", styleSmallLabel);
	private Label lblChaseHQ = new Label("Chase H.Q.", styleSmallLabel);
	private Label lblRoundStart = new Label("Round Start (X68000)", styleSmallLabel);
	private Label lblTitle = new Label("Credits", styleDefaultLabel);
	
	private TextButtonStyle styleButton = new TextButtonStyle(skin.get("menuLabel", TextButtonStyle.class));
	private TextButton btnBack = new TextButton("< BACK", styleButton);

	public GUICredits(AirHockey parent)
	{
		super(parent);

		table.add(lblTitle).padBottom(32).row();
		table.add(lblMusic).padBottom(16).row();
		table.add(lblWarpRoom).padBottom(8).row();
		table.add(lblMarkMothersbaugh).padBottom(8).row();
		table.add(lblCrashBandicoot).padBottom(32).row();
		table.add(lblRoundStart).padBottom(8).row();
		table.add(lblTaito).padBottom(8).row();
		table.add(lblChaseHQ).padBottom(32).row();
		table.add(lblPhysics).padBottom(16).row();
		table.add(lblDiogoMarques).padBottom(8).row();
		table.add(lblPedroMelo).padBottom(16).row();
		table.setFillParent(true);
		stage.addActor(table);
		btnBack.setPosition(48, 30);
		stage.addActor(btnBack);
		bgmusic = Song.THEME_CREDITS;
		btnBack.addListener(new MenuListener(1, SFX.MENU_SELECT, SFX.MENU_CLICK));
	}

	@Override
	public void render(float delta)
	{
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
		table.setPosition(0.0f, 0.0f);
		table.addAction(Actions.moveBy(0.0f, 800.0f, 12));
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