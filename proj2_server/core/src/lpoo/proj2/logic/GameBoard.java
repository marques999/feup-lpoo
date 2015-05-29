package lpoo.proj2.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;

import lpoo.proj2.audio.AudioManager;
import lpoo.proj2.audio.SFX;

public class GameBoard
{
	private AudioManager audio;
	private GameRules rules;
	private Paddle p1Paddle;
	private Paddle p2Paddle;
	private Goal p1Goal;
	private Goal p2Goal;
	private EntityFactory factory;
	private Puck puck;
	private ArrayList<Wall> walls = new ArrayList<Wall>();
	protected boolean multiplayer = false;

	public GameBoard()
	{
		audio = AudioManager.getInstance();
		factory = new EntityFactory();
		initialize();
	}
	
	private void initialize()
	{
		rules = new RulesBest5();
		puck = factory.createPuck(Color.RED);
		p1Paddle = factory.createP1Paddle(Color.RED);
		p2Paddle = factory.createP2Paddle(Color.BLUE);
		p1Goal = factory.createP1Goal();
		p2Goal = factory.createP2Goal();
		walls = new ArrayList<Wall>();
		walls.add(new Wall(0, 0, 32, Gdx.graphics.getHeight() / 2, Color.RED));
		walls.add(new Wall(0, Gdx.graphics.getHeight() / 2, 32, Gdx.graphics.getHeight() / 2, Color.BLUE));
		walls.add(new Wall(Gdx.graphics.getWidth() - 32, 0, 32, Gdx.graphics.getHeight() / 2, Color.RED));
		walls.add(new Wall(Gdx.graphics.getWidth() - 32,  Gdx.graphics.getHeight() / 2, 32, Gdx.graphics.getHeight() / 2, Color.BLUE));
	}

	public void update(float delta)
	{
		p1Paddle.update(delta);
		puck.update(delta);
		p1Paddle.collides(p2Paddle);
		puck.collides(p1Paddle);
		puck.collides(p2Paddle);
		
		for (Wall wall : walls)
		{
			if (puck.collides(wall))
			{
				audio.playSound(SFX.SFX_PUCK_HIT);
			}
		}
	}
	
	public void draw(SpriteBatch sb)
	{
		p1Paddle.draw(sb);
		p2Paddle.draw(sb);
	
		for (Wall wall : walls)
		{
			wall.draw(sb);
		}
		
		puck.draw(sb);
	}
}