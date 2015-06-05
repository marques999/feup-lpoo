package lpoo.proj2.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.io.IOException;
import java.util.ArrayList;

import lpoo.proj2.AirHockey;
import lpoo.proj2.audio.AudioManager;
import lpoo.proj2.audio.SFX;
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
	private boolean createP1 = false;
	private boolean createP2 = false;
	protected boolean multiplayer;

	public GameBoard(GUIGame paramParent, int paramMode, boolean paramMultiplayer)
	{
		parent = paramParent;
		players = new Player[2];
		multiplayer = paramMultiplayer;

		if (multiplayer)
		{
			players[0] = new Player(0, "Player 1", 0);
			players[1] = new Player(1, "Player 2", 0);
			connect();
		}
		else
		{
			players[0] = new Player(0, "Human", 2);
			players[1] = new Player(1, "CPU", 0);
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
	
	public void setPlayer1(Player player)
	{
		players[0] = player;
		createP1 = true;
	}
	
	public void setPlayer2(Player player)
	{
		players[1] = player;
		createP2 = true;
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
			if (pucks.get(0).getPosition().y > screenHeight / 2)
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
		if (createP1)
		{
			p1Paddle = factory.createP1Paddle(players[0].getColor());
			createP1 = false;
		}
		
		if (createP2)
		{
			p2Paddle = factory.createP2Paddle(players[1].getColor());
			createP2 = false;
		}
		
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
			
			if (multiplayer)
			{
				server.sendGameover();
			}
		}

		p1Paddle.update(delta);

		for (Puck puck : pucks)
		{
			puck.update(delta);

			if (p1Paddle.collides(puck))
			{
				AudioManager.getInstance().playSound(SFX.SFX_PUCK_HIT);
				lastPlayed = players[0];
			}

			if (p2Paddle.collides(puck))
			{
				AudioManager.getInstance().playSound(SFX.SFX_PUCK_HIT);
				lastPlayed = players[1];
			}

			puck.collides(p1Paddle);
			puck.collides(p2Paddle);

			if (puck.collides(p1Goal))
			{
				p2Goal(puck);
				break;
			}
			
			if (puck.collides(p2Goal))
			{
				p1Goal(puck);
				break;
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
				if (puck.collides(wall))
				{
					audio.playSound(SFX.SFX_PADDLE_HIT);
				}
			}
		}
	}

	private void p1Goal(Puck puck)
	{
		if (lastPlayed == players[1])
		{
			audio.playSpecial(Special.QUAKE_HUMILIATION);
		}

		rules.p1Score();
		parent.actionScore(players[0]);
		
		if (rules instanceof RulesAttack)
		{
			pucks.remove(puck);
		}
		else
		{
			factory.resetPuck(puck, 1);
			factory.resetP1Paddle(p1Paddle);
			factory.resetP2Paddle(p2Paddle);
		}
		
		if (multiplayer)
		{
			server.sendScore();
		}
	}

	private void p2Goal(Puck puck)
	{
		if (lastPlayed == players[0])
		{
			audio.playSpecial(Special.QUAKE_HUMILIATION);
		}

		rules.p2Score();
		parent.actionScore(players[1]);
		
		if (rules instanceof RulesAttack)
		{
			pucks.remove(puck);
		}
		else
		{
			factory.resetPuck(puck, 0);
			factory.resetP1Paddle(p1Paddle);
			factory.resetP2Paddle(p2Paddle);
		}

		if (multiplayer)
		{
			server.sendScore();
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

		if (p1Paddle != null)
		{
			p1Paddle.draw(sb);
		}
		
		if (p2Paddle != null)
		{
			p2Paddle.draw(sb);
		}
	}
	
	public int getPlayersConnected()
	{
		return server.getPlayersConnected();
	}
	
	public void dispose()
	{
		if (multiplayer)
		{
			server.disconnect();
		}
	}
}