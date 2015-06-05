package lpoo.proj2.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.io.IOException;
import java.net.ServerSocket;
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
	private int serverPort;
	protected boolean multiplayer;

	public GameBoard(GUIGame paramParent, int paramMode,
			boolean paramMultiplayer)
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

		switch (AirHockey.getDifficulty())
		{
		case 0:
			cpu = new EasyCPUPaddle();
			break;
		case 1:
			cpu = new MediumCPUPaddle();
			break;
		case 2:
			cpu = new HardCPUPaddle();
			break;
		case 3:
			cpu = new InsaneCPUPaddle();
			break;
		}

		audio = AudioManager.getInstance();
		factory = new EntityFactory();

		initialize();

		if (!multiplayer)
		{
			new Thread(cpu).start();
		}
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
				ServerSocket socket = new ServerSocket(0);
				serverPort = socket.getLocalPort();
				socket.close();
				server = new GameServer(serverPort, serverPort + 1, this);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	public int getPort()
	{
		return serverPort;
	}

	private float screenWidth = Gdx.graphics.getWidth();

	private class EasyCPUPaddle extends CPUPaddle
	{
		public EasyCPUPaddle()
		{
			setReactionTime(0.4f);
			setMovementSpeed(1.6f);
		}
	}

	private class MediumCPUPaddle extends CPUPaddle
	{
		public MediumCPUPaddle()
		{
			setReactionTime(0.3f);
			setMovementSpeed(1.2f);
		}
	}

	private class HardCPUPaddle extends CPUPaddle
	{
		public HardCPUPaddle()
		{
			setReactionTime(0.2f);
			setMovementSpeed(0.8f);
		}
	}

	private class InsaneCPUPaddle extends CPUPaddle
	{
		public InsaneCPUPaddle()
		{
			setReactionTime(0.1f);
			setMovementSpeed(0.5f);
		}
	}

	private abstract class CPUPaddle implements Runnable
	{
		private float reactionTime = 0.0f;
		private float movementSpeed = 0.0f;
		private long busyTime = 0;

		private Vector2 finalPosition = new Vector2(0.0f, 0.0f);

		public void setReactionTime(final float paramTime)
		{
			reactionTime = paramTime;
			busyTime = Math.round(reactionTime * 1000);
		}

		public void setMovementSpeed(final float paramSpeed)
		{
			movementSpeed = paramSpeed;
		}

		@Override
		public void run()
		{
			Puck puck = pucks.get(0);

			while (!rules.checkOver())
			{

				if (puck.getPosition().y <= screenHeight * 0.5
						&& puck.goingUpwards())
				{
					// acompanha o puck
					float dx = puck.getX() - p2Paddle.getX();
					float dy = puck.getY() - p2Paddle.getY();
					finalPosition = new Vector2(puck.getX(), puck.getY());
					Vector2 finalSpeed = new Vector2(dx / movementSpeed, dy
							/ movementSpeed);

					// if (puck.goingRight())
					// p2Paddle.move(p2Paddle.getPosition().x + 1,
					// p2Paddle.getPosition().y);
					// else
					// p2Paddle.move(p2Paddle.getPosition().x - 1,
					// p2Paddle.getPosition().y);

					try
					{
						p2Paddle.impulse(finalSpeed);
						System.out.println("[CPU] acompanhar puck");
						Thread.sleep(busyTime);
					}
					catch (InterruptedException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				else if (puck.getPosition().y <= screenHeight * 0.5
						&& puck.goingDownwards())
				{
					System.out.println("[CPU] contrariar puck");
					Vector2 finalSpeed;
					// contraria o puck
					if (puck.goingRight())
					{
						float dx = p2Paddle.getBounds().minX - p2Paddle.getX();
						float dy = 0.0f;
						finalPosition = new Vector2(p2Paddle.getBounds().minX, p2Paddle.getY());
						finalSpeed = new Vector2(dx / movementSpeed, dy / movementSpeed);
					}
					else
					{
						float dx = p2Paddle.getBounds().maxX - p2Paddle.getX();
						float dy = 0.0f;
						finalPosition = new Vector2(p2Paddle.getBounds().maxX, p2Paddle.getY());
						finalSpeed = new Vector2(dx / movementSpeed, dy / movementSpeed);
					}
					
					p2Paddle.impulse(finalSpeed);

					try
					{
						Thread.sleep(busyTime);
					}
					catch (InterruptedException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				else if (puck.getPosition().y >= screenHeight * 0.5f
						&& puck.getPosition().y <= screenHeight * 0.75f
						&& puck.goingUpwards())
				{

					if (!puck.goingFast())
					{
						float dx = puck.getX() - p2Paddle.getX();
						float dy = puck.getY() - p2Paddle.getY();

						Vector2 finalSpeed = new Vector2(2 * dx / movementSpeed, 2 * dy
								/ movementSpeed);

						p2Paddle.impulse(finalSpeed);
						System.out.println("[CPU] ataque");
						// // Ataca
						// if (puck.goingRight())
						// p2Paddle.move(p2Paddle.getPosition().x + 1,
						// p2Paddle.getPosition().y - 1);
						// else
						// p2Paddle.move(p2Paddle.getPosition().x - 1,
						// p2Paddle.getPosition().y - 1);
						try
						{
							Thread.sleep(busyTime);
						}
						catch (InterruptedException e)
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
					else
					{
						Vector2 finalPosition = new Vector2(p2Goal.getX()
								- p2Paddle.getX(), p2Goal.getY()
								- p2Paddle.getY());
						Vector2 finalSpeed = new Vector2(finalPosition.x
								/ movementSpeed, finalPosition.y
								/ movementSpeed);

						p2Paddle.impulse(finalSpeed);
						System.out.println("[CPU] defesa");
						try
						{
							Thread.sleep(busyTime);
						}
						catch (InterruptedException e)
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						// // Defende
						// if (puck.goingRight())
						// p2Paddle.move(p2Paddle.getPosition().x + 1,
						// p2Paddle.getPosition().y + 1);
						// else
						// p2Paddle.move(p2Paddle.getPosition().x - 1,
						// p2Paddle.getPosition().y + 1);

					}

				}
				else if (puck.getPosition().y >= screenHeight * 0.5
						&& puck.getPosition().y <= screenHeight * 0.75
						&& puck.goingDownwards())
				{
					float dx = screenWidth / 2 - p2Paddle.getX();
					float dy = screenHeight * 0.75f - p2Paddle.getY();
					Vector2 finalSpeed = new Vector2(dx / movementSpeed, dy
							/ movementSpeed);
					// Reposiciona
					p2Paddle.impulse(finalSpeed);
					System.out.println("[CPU] reposiciona");
					System.out.println(finalSpeed);
					// if (p2Paddle.getPosition().x > screenWidth / 2)
					// p2Paddle.move(p2Paddle.getPosition().x - 1,
					// p2Paddle.getPosition().y - 1);
					// else
					// p2Paddle.move(p2Paddle.getPosition().x + 1,
					// p2Paddle.getPosition().y - 1);
					//
					// if (p2Paddle.getPosition().y > screenHeight * 0.75)
					// p2Paddle.move(p2Paddle.getPosition().x,
					// p2Paddle.getPosition().y - 1);
					// else
					// p2Paddle.move(p2Paddle.getPosition().x + 1,
					// p2Paddle.getPosition().y + 1);

					try
					{
						Thread.sleep(busyTime);
					}
					catch (InterruptedException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				try
				{
					Thread.sleep(busyTime);
				}
				catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
		p2Paddle.update();

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

	public void actionDisconnect(Player paramPlayer)
	{
		parent.actionDisconnect(paramPlayer);
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