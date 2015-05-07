package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

public class MyGdxGame extends ApplicationAdapter implements Screen, InputProcessor
{
	SpriteBatch batch;

	private BitmapFont mario2P;
	private Vector3 _lastMouseWorldMovePos;
	private Vector3 _lastMouseWorldDragPos;
	private Vector3 _lastMouseWorldPressPos;
	private Vector3 _lastMouseWorldReleasePos;
	private Vector3 _lastMouseScreenPos;
	private OrthographicCamera _camera;
	private ShapeRenderer shapeRenderer;
	Music raidenTheme;

	private ParticleEffect blueCollision;
	private float posX;
	private float posY;

	private Texture texture;
	private Sprite sprite;
	@Override
	public void create()
	{
		_lastMouseWorldMovePos = new Vector3();
		_lastMouseWorldDragPos = new Vector3();
		_lastMouseWorldPressPos = new Vector3();
		_lastMouseWorldReleasePos = new Vector3();
		_lastMouseScreenPos = new Vector3();
		_camera = new OrthographicCamera(480, 800);
		shapeRenderer = new ShapeRenderer();
		mario2P = new BitmapFont(Gdx.files.internal("core/assets/Mario2P.fnt"));
		mario2P.setColor(Color.WHITE);
		batch = new SpriteBatch();
		texture = new Texture(Gdx.files.internal("core/assets/gfx_hockeyPuck.png"));
		texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		sprite = new Sprite(texture);
		sprite.setOrigin(0, 0);
		sprite.setSize(480 / 8, 480 / 8);
		Gdx.input.setInputProcessor(this);

	showParticle();

		raidenTheme =  Gdx.audio.newMusic(Gdx.files.internal("core/assets/raidenb-027.ogg"));
		raidenTheme.setLooping(true);
		raidenTheme.play();
}

	public void showParticle()
	{
		blueCollision = new ParticleEffect();

		blueCollision.load(Gdx.files.internal("core/assets/gfx_glow.p"),
				Gdx.files.internal("core/assets"));
		blueCollision.setPosition(100, 100);
		blueCollision.start();
	}

	@Override
	public void render()
	{
		Gdx.gl.glClearColor(0.420f, 0.533f, 1.000f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		sprite.setPosition(posX, posY);
		batch.begin();
		sprite.draw(batch);
		batch.end();
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {

	//	_lastMouseWorldPressPos.set(screenX, screenY, 0);
	//	_camera.unproject(_lastMouseWorldPressPos);
		posX =   Gdx.graphics.getWidth() - screenX;
		posY =   Gdx.graphics.getHeight() - screenY;

		return false;

	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer)
	{
	//	_camera.position.set(screenX, screenY, 1);
	//	_camera.update();
		posX =   Gdx.graphics.getWidth() - screenX - sprite.getWidth()/2;
		posY =   Gdx.graphics.getHeight() - screenY - sprite.getHeight()/2;


		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return true;
	}

	public boolean touchMoved(int x, int y)
	{
	//	_lastMouseWorldMovePos.set(x, y, 0);
	//	_camera.unproject(_lastMouseWorldMovePos);
	//	_lastMouseScreenPos.set(x,y,0);


		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}


	public boolean pan(float x, float y, float deltaX, float deltaY) {

		_camera.translate(deltaX,0);
		_camera.update();

		return false;

	}


	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.420f, 0.533f, 1.000f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		blueCollision.update(delta);
		batch.begin();
		blueCollision.draw(batch, delta);
		batch.end();
	}

	@Override
	public void hide() {

	}
}