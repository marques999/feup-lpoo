package lpoo.proj2.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import lpoo.proj2.AirHockey;
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
	private CPUPaddle cpu;
	private Player players[];
	private ArrayList<Puck> pucks = new ArrayList<Puck>();
	private ArrayList<Wall> walls = new ArrayList<Wall>();
	protected boolean multiplayer = false;

	public GameBoard(Player[] players, GameRules rules)
	{
		this.rules = rules;
		this.players = players;
		this.cpu = new CPUPaddle();
		this.audio = AudioManager.getInstance();
		this.factory = new EntityFactory();
		this.initialize();
	}
	
	private class CPUPaddle implements Runnable
	{
		@Override
		public void run()
		{
			if (pucks.get(0).pos.y > Gdx.graphics.getHeight() / 2)
			{
				p2Paddle.move(pucks.get(0).pos.x, Gdx.graphics.getHeight() - p2Paddle.getY());
			}
		}
	}

	private void initialize()
	{
		if (rules instanceof RulesAttack)
		{
			for (int i = 0; i < rules.numberPucks(); i++)
			{
				pucks.add(factory.createRandomPuck(AirHockey.getColor()));
			}
		}
		else
		{
			pucks.add(factory.createSinglePuck(AirHockey.getColor()));
		}

		walls.addAll(factory.createP1Walls(players[0].getColor()));
		walls.addAll(factory.createP2Walls(players[1].getColor()));
		p1Paddle = factory.createP1Paddle(players[0].getColor());
		p2Paddle = factory.createP2Paddle(players[1].getColor());
		p1Goal = factory.createP1Goal();
		p2Goal = factory.createP2Goal();
	}

	public void update(float delta)
	{
		p1Paddle.update(delta);
	
		for (Puck puck : pucks)
		{
			puck.update(delta);
			p1Paddle.collides(puck);
			p2Paddle.collides(puck);
			puck.collides(p1Paddle);
			puck.collides(p2Paddle);
		}
		
		new Thread(cpu).start();
		p1Paddle.collides(p2Paddle);

		for (Wall wall : walls)
		{
			for (Puck puck : pucks)
			{
				puck.collides(wall);
			}
		}
	}

	public void movePaddle(int paddleId, float x, float y)
	{
		if (!multiplayer)
		{
			p1Paddle.move(x, y);
			
			if (paddleId == 0)
			{
				rules.p1Score();
			}
		}
	}

	public void draw(SpriteBatch sb)
	{
		p1Paddle.draw(sb);
		p2Paddle.draw(sb);
		p1Goal.draw(sb);
		p2Goal.draw(sb);

		for (Wall wall : walls)
		{
			wall.draw(sb);
		}

		for (Puck puck : pucks)
		{
			puck.draw(sb);
		}
	}
}