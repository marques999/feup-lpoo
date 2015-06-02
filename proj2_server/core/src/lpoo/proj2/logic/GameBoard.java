package lpoo.proj2.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

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
	private boolean multiplayer;

	public GameBoard(GUIGame paramParent, GameRules paramRules, boolean paramMultiplayer)
	{
		rules = paramRules;
		multiplayer = paramMultiplayer;
		lastPlayed = null;
		parent = paramParent;
		cpu = new CPUPaddle();
		audio = AudioManager.getInstance();
		factory = new EntityFactory();
		initialize();
	}

	private class CPUPaddle implements Runnable
	{
		private float currentTime = 0.0f;
		private float reactionTime = 0.5f;
		private Puck puck;
		private final float screenWidth = Gdx.graphics.getWidth();
		private final float screenHeight = Gdx.graphics.getHeight();

		@Override
		public void run()
		{
		   puck = pucks.get(0);
		   currentTime += Gdx.graphics.getDeltaTime();
		   
		    if (currentTime < 0.700f) 
		    {
		        defense(p2Paddle.getX(), p2Paddle.getY(), puck.getX(), puck.getY(), puck.getRadius());
		    }
		    else
		    {
		    	currentTime = 0.0f;
		    	 makeDecision(p2Paddle.getX(), p2Paddle.getY(), puck.getX(), puck.getY(), puck.getRadius());
		    }
		}
		
		public void makeDecision(float x, float y, float px, float py, float radius)
		{
			boolean puckInCorner = px < screenWidth / 5 || px > 4 * screenWidth / 5;

			if (puckInCorner && py < 2 * radius)
			{
				return;
			}

			if (py < (9 * screenHeight / 20))
			{
				moveTo(x, y, px, py - radius / 4);
			}

			defense(x, y, px, py, radius);
		}

		private boolean defense(float x, float y, float px, float py, float radius)
		{
			if (py < y && Math.abs(screenWidth / 2 - px) > screenWidth / 5)
			{
				moveTo(x, y, px, py - radius);
			}

			moveTo(x, y, screenWidth / 4 + screenWidth / 2 * (px / (float) (1.0 * screenWidth)), screenHeight / 6);

			return true;
		}

		private void moveTo(float x, float y, float px, float py)
		{
			// be random
			int speed = 0;
			// calculate deltas
			float dx = px - x;
			float dy = py - y;
			// calculate distance between puck and paddle position (we use
			// Pythagorean theorem)
			Vector2 distance = new Vector2(dx, dy);
			Vector2 velocity = distance.scl(0.2f, 0.2f);
			// if total distance is greater than the distance, of which we can
			// move in one step calculate new x and y coordinates somewhere
			// between current puck and paddle position.
			// if (distance > speed) {
			// // x = current padle x position + equally part of speed on x axis
			// px = x + speed / distance * dx;
			// py = y + speed / distance * dy;
			// }

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
		walls = factory.createWalls(parent.getP1().getColor());
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

				rules.p2Score();
				pucks.add(factory.createSinglePuck(AirHockey.getColor()));
				pucks.remove(puck);
				parent.actionScore(parent.getP2());
			}

			if (puck.collides(p2Goal))
			{
				if (lastPlayed == parent.getP2())
				{
					audio.playSpecial(Special.QUAKE_HUMILIATION);
				}

				rules.p1Score();
				pucks.add(factory.createSinglePuck(AirHockey.getColor()));
				pucks.remove(puck);
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

	public void movePaddle(float x, float y)
	{
		p1Paddle.move(x, y);
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