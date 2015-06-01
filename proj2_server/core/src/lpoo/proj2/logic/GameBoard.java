package lpoo.proj2.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import lpoo.proj2.AirHockey;
import lpoo.proj2.audio.AudioManager;
import lpoo.proj2.audio.Special;
import lpoo.proj2.gui.GUIGame;

public class GameBoard
{
	private AudioManager audio;
	private GameRules rules;
	private Paddle p1Paddle;
	private Paddle p2Paddle;
	private Goal p1Goal;
	private Goal p2Goal;
	private Player lastPlayed;
	private EntityFactory factory;
	private CPUPaddle cpu;
	private GUIGame parent;
	private ArrayList<Puck> pucks = new ArrayList<Puck>();
	private ArrayList<Wall> walls = new ArrayList<Wall>();
	protected boolean multiplayer = false;

	public GameBoard(GUIGame paramParent, GameRules paramRules)
	{
		rules = paramRules;
		lastPlayed = null;
		parent = paramParent;
		cpu = new CPUPaddle();
		audio = AudioManager.getInstance();
		factory = new EntityFactory();
		initialize();
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

		rules.reset();
		walls.addAll(factory.createP1Walls(parent.getP1().getColor()));
		walls.addAll(factory.createP2Walls(parent.getP2().getColor()));
		p1Paddle = factory.createP1Paddle(parent.getP1().getColor());
		p2Paddle = factory.createP2Paddle(parent.getP2().getColor());
		p1Goal = factory.createP1Goal();
		p2Goal = factory.createP2Goal();
	}

	public void update(float delta)
	{
		if (rules.checkOver())
		{
			if (rules.p1Wins())
			{
				parent.actionGameover(parent.getP1());
			}
			
			if (rules.p2Wins())
			{
				parent.actionGameover(parent.getP2());
			}
		}
		
		p1Paddle.update(delta);
	
		for (Puck puck : pucks)
		{
			puck.update(delta);
			
			if (p1Paddle.collides(puck))
			{
				lastPlayed = parent.getP1();
			}
			
			if (p2Paddle.collides(puck))
			{
				lastPlayed = parent.getP2();
			}
			
			puck.collides(p1Paddle);
			puck.collides(p2Paddle);
			
			if (puck.collides(p1Goal))
			{
				if (lastPlayed == parent.getP1())
				{
					audio.playSpecial(Special.QUAKE_HUMILIATION);
				}
				
				rules.p1Score();
				pucks.remove(puck);
				pucks.add(factory.createSinglePuck(AirHockey.getColor()));
				parent.actionScore(parent.getP2());
			}
			
			if (puck.collides(p2Goal))
			{
				if (lastPlayed == parent.getP2())
				{
					audio.playSpecial(Special.QUAKE_HUMILIATION);
				}
				
				rules.p2Score();
				pucks.remove(puck);
				pucks.add(factory.createSinglePuck(AirHockey.getColor()));
				parent.actionScore(parent.getP1());
			}
		}
		
		p1Paddle.collides(p2Paddle);
				
		new Thread(cpu).start();
		
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
		}
	}

	public void draw(SpriteBatch sb)
	{
		for (Puck puck : pucks)
		{
			puck.draw(sb);
		}
		
		p1Paddle.draw(sb);
		p2Paddle.draw(sb);
	}
}