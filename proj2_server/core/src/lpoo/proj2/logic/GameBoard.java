package lpoo.proj2.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.io.IOException;
import java.util.ArrayList;
import lpoo.proj2.AirHockey;
import lpoo.proj2.audio.AudioManager;
import lpoo.proj2.audio.Special;
import lpoo.proj2.gui.GUIGame;
import lpoo.proj2.net.GameServer;

public class GameBoard
{
	private AudioManager audio;
	private GameServer server;
	private GameRules rules;
	private Paddle p1Paddle;
	private Paddle p2Paddle;
	private Goal p1Goal;
	private Goal p2Goal;
	private Player players[];
	private Player lastPlayed;
	private EntityFactory factory;
	private CPUPaddle cpu;
	private GUIGame parent;
	private final float screenHeight = Gdx.graphics.getHeight();
	private ArrayList<Puck> pucks = new ArrayList<Puck>();
	private ArrayList<Wall> walls = new ArrayList<Wall>();
	protected boolean multiplayer;

	public GameBoard(GUIGame paramParent, int paramMode, boolean paramMultiplayer)
	{
		parent = paramParent;
		players = new Player[2];
		multiplayer = paramMultiplayer;

		if (multiplayer)
		{
			players[0] = new Player("Player 1", 0);
			players[1] = new Player("Player 2", 0);
			connect();
		}
		else
		{
			players[0] = new Player("Human", 3);
			players[1] = new Player("CPU", 0);
		}

		switch (paramMode)
		{
		case 0:
			rules = new RulesBest5(players);
			break;
		case 1:
			rules = new RulesBest10(players);
			break;
		case 2:
			rules = new RulesFirst15(players);
			break;
		case 3:
			rules = new RulesAttack(players);
			break;
		}

		lastPlayed = null;
		cpu = new CPUPaddle();
		audio = AudioManager.getInstance();
		factory = new EntityFactory();

		initialize();
	}

	public final Player getPlayer1()
	{
		return players[0];
	}

	public final Player getPlayer2()
	{
		return players[1];
	}

	public void connect()
	{
		if (server == null)
		{
			try
			{
				server = new GameServer(9732, 9733, this);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	private class CPUPaddle implements Runnable
	{
		@Override
		public void run()
		{
			if (pucks.get(0).getPosition().x > screenHeight / 2)
			{
				p2Paddle.move(pucks.get(0).getPosition().x, screenHeight - p2Paddle.getY());
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
		walls = factory.createWalls(players[0].getColor());
		p1Paddle = factory.createP1Paddle(players[0].getColor());
		p2Paddle = factory.createP2Paddle(players[1].getColor());
		p1Goal = factory.createP1Goal();
		p2Goal = factory.createP2Goal();
	}

	public void update(float delta)
	{
		if (rules.checkOver())
		{
			if (rules.checkAce())
			{
				audio.playSpecial(Special.QUAKE_FLAWLESS);
			}

			if (rules.p1Wins())
			{
				parent.actionGameover(players[0]);
			}

			if (rules.p2Wins())
			{
				parent.actionGameover(players[1]);
			}
		}

		p1Paddle.update(delta);

		for (Puck puck : pucks)
		{
			puck.update(delta);

			if (p1Paddle.collides(puck))
			{
				lastPlayed = players[0];
			}

			if (p2Paddle.collides(puck))
			{
				lastPlayed = players[1];
			}

			puck.collides(p1Paddle);
			puck.collides(p2Paddle);

			if (puck.collides(p1Goal) || puck.collides(p2Goal))
			{
				p1Paddle = factory.createP1Paddle(players[0].getColor());
				p2Paddle = factory.createP2Paddle(players[1].getColor());
			}

			if (puck.collides(p1Goal))
			{
				if (lastPlayed == players[0])
				{
					audio.playSpecial(Special.QUAKE_HUMILIATION);
				}

				rules.p2Score();
				pucks.add(factory.createSinglePuck(AirHockey.getColor()));
				pucks.remove(puck);
				parent.actionScore(players[1]);

				if (multiplayer)
				{
					server.sendScore(players[1]);
				}
			}

			if (puck.collides(p2Goal))
			{
				if (lastPlayed == players[1])
				{
					audio.playSpecial(Special.QUAKE_HUMILIATION);
				}

				rules.p1Score();
				pucks.add(factory.createSinglePuck(AirHockey.getColor()));
				pucks.remove(puck);
				parent.actionScore(players[0]);

				if (multiplayer)
				{
					server.sendScore(players[0]);
				}
			}
		}

		p1Paddle.collides(p2Paddle);

		if (!multiplayer)
		{
			new Thread(cpu).start();
		}

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
		if (paddleId == 1)
		{
			p2Paddle.move(x, y);
		}
		else
		{
			if (multiplayer)
			{
				p1Paddle.move(x, y + screenHeight / 2);
			}
			else
			{
				p1Paddle.move(x, y);
			}
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
	
	public void dispose()
	{
		if (multiplayer)
		{
			server.disconnect();
		}
	}
}