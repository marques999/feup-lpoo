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
	private Paddle paddles[];
	private Goal goals[];
	private Player players[];
	private Player lastPlayed;
	private EntityFactory factory;
	private CPUPaddle cpu;
	private GUIGame parent;
	private ArrayList<Puck> pucks;
	private ArrayList<Wall> walls;
	private int serverPort;
	private boolean gameRunning;
	private boolean multiplayer;
	private boolean createP1Paddle;
	private boolean createP2Paddle;
	private final int PLAYER_1 = 0;
	private final int PLAYER_2 = 1;
	private float screenHeight;
	private float screenWidth;

	public GameBoard(GUIGame paramParent, int paramMode, boolean paramMultiplayer)
	{
		audio = AudioManager.getInstance();
		createP1Paddle = false;
		createP2Paddle = false;
		screenHeight = Gdx.graphics.getHeight();
		screenWidth = Gdx.graphics.getWidth();
		factory = new EntityFactory();
		lastPlayed = null;
		multiplayer = paramMultiplayer;
		parent = paramParent;
		goals = new Goal[2];
		paddles = new Paddle[2];
		players = new Player[2];
		pucks = new ArrayList<Puck>();
		walls = new ArrayList<Wall>();

		initializeRules(paramMode);
		initializePlayers();

		if (multiplayer)
		{
			connect();
		}
		else
		{
			setDifficulty(AirHockey.getDifficulty());
		}

		initializeBoard();
		initializeAI();
	}

	private void initializePlayers()
	{
		if (multiplayer)
		{
			players[PLAYER_1] = new Player(PLAYER_1, "Player 1", 0);
			players[PLAYER_2] = new Player(PLAYER_2, "Player 2", 0);
			connect();
		}
		else
		{
			players[PLAYER_1] = new Player(PLAYER_1, "Human", 2);
			players[PLAYER_2] = new Player(PLAYER_2, "CPU", 0);
		}
	}

	private void initializeAI()
	{
		if (!multiplayer)
		{
			cpu.start();

			synchronized (cpu)
			{
				gameRunning = true;
			}
		}
	}

	private void setDifficulty(int paramDifficulty)
	{
		switch (paramDifficulty)
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
	}

	private void initializeRules(int paramMode)
	{
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
	}

	public final Player getPlayer1()
	{
		return players[PLAYER_1];
	}

	public final Player getPlayer2()
	{
		return players[PLAYER_2];
	}

	public void setPlayer1(Player player)
	{
		players[PLAYER_1] = player;
		createP1Paddle = true;
	}

	public void setPlayer2(Player player)
	{
		players[PLAYER_2] = player;
		createP2Paddle = true;
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
				if (server != null)
				{
					server.actionDisconnect();
				}
			}
		}
	}

	public int getPort()
	{
		return serverPort;
	}

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

	private abstract class CPUPaddle extends Thread
	{
		private float reactionTime = 0.0f;
		private float movementSpeed = 0.0f;
		private long busyTime = 0;
		private Vector2 finalSpeed = new Vector2(0.0f, 0.0f);

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

			while (gameRunning)
			{
				float dx = 0.0f;
				float dy = 0.0f;
				boolean validMove = false;
				
				if (puck.goingDownwards())
				{
					// ACOMPANHAR PUCK
					if (puck.getPosition().y > screenHeight * 0.5)
					{
						dx = puck.getX() - paddles[PLAYER_2].getX();
						dy = puck.getY() - paddles[PLAYER_2].getY();
						validMove = true;
						System.out.println("[CPU] acompanhar puck");
					}

					// REPOSICIONAR PUCK
					else if (puck.getPosition().y >= screenHeight * 0.5 && puck.getPosition().y <= screenHeight * 0.75)
					{
						dx = screenWidth / 2 - paddles[1].getX();
						dy = screenHeight * 0.75f - paddles[1].getY();
						validMove = true;
						System.out.println("[CPU] reposicionar puck");
					}
				}
				else if (puck.goingUpwards())
				{
					// CONTRARIAR PUCK
					if (puck.getPosition().y <= screenHeight * 0.50f)
					{
						System.out.println("[CPU] contrariar puck");

						if (puck.goingRight())
						{
							dx = paddles[PLAYER_2].getBounds().minX - paddles[PLAYER_2].getX();
							dy = 0.0f;
						}
						else
						{
							dx = paddles[PLAYER_2].getBounds().maxX - paddles[PLAYER_2].getX();
							dy = 0.0f;
						}

						validMove = true;
					}
					else if (puck.getPosition().y <= screenHeight * 0.75f)
					{
						// DEFENDER
						if (puck.goingFast())
						{
							dx = goals[PLAYER_2].getX() - paddles[PLAYER_2].getX();
							dy = screenHeight - paddles[PLAYER_2].getBounds().minY - paddles[PLAYER_2].getY();
							validMove = true;
							System.out.println("[CPU] defesa");
							
						}
						// ATACAR
						else
						{
							dx = puck.getX() - paddles[PLAYER_2].getX();
							dy = puck.getY() - paddles[PLAYER_2].getY();
							finalSpeed.set(dx / movementSpeed, 2 * dy / movementSpeed);
							paddles[PLAYER_2].impulse(finalSpeed);
							System.out.println("[CPU] ataque");
						}
					}
				}

				if (validMove)
				{
					finalSpeed = new Vector2(dx / movementSpeed, dy / movementSpeed);
					paddles[PLAYER_2].impulse(finalSpeed);
				}
				
				try
				{
					Thread.sleep(busyTime);
				}
				catch (InterruptedException e)
				{
				}
			}
		}
	}

	private void initializeBoard()
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
		walls = factory.createWalls();
		paddles[PLAYER_1] = multiplayer ? null : factory.createP1Paddle(players[PLAYER_1].getColor());
		paddles[PLAYER_2] = multiplayer ? null : factory.createP2Paddle(players[PLAYER_2].getColor());
		goals[PLAYER_1] = factory.createP1Goal();
		goals[PLAYER_2] = factory.createP2Goal();
	}

	public void update(float delta)
	{
		if (multiplayer)
		{
			if (createP1Paddle)
			{
				paddles[PLAYER_1] = factory.createP1Paddle(players[PLAYER_1].getColor());
				createP1Paddle = false;
			}

			if (createP2Paddle)
			{
				paddles[PLAYER_2] = factory.createP2Paddle(players[PLAYER_2].getColor());
				createP2Paddle = false;
			}
		}

		updateState();
		updatePaddle(delta);
		updatePuck(delta);
	}

	private void updatePaddle(float delta)
	{
		paddles[PLAYER_1].update(delta);

		if (multiplayer)
		{
			paddles[PLAYER_2].update(delta);

		}
		else
		{
			paddles[PLAYER_2].update();
		}

		paddles[PLAYER_1].collides(paddles[PLAYER_2]);
	}

	private void updatePuck(float delta)
	{
		for (Puck puck : pucks)
		{
			puck.update(delta);

			if (paddles[PLAYER_1].collides(puck))
			{
				AudioManager.getInstance().playSound(SFX.SFX_PUCK_HIT);
				lastPlayed = players[PLAYER_1];
			}

			if (paddles[PLAYER_2].collides(puck))
			{
				AudioManager.getInstance().playSound(SFX.SFX_PUCK_HIT);
				lastPlayed = players[PLAYER_1];
			}

			for (Paddle paddle : paddles)
			{
				puck.collides(paddle);
			}

			if (puck.collides(goals[PLAYER_1]))
			{
				p2Goal(puck);
				break;
			}

			if (puck.collides(goals[PLAYER_2]))
			{
				p1Goal(puck);
				break;
			}
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

	private void updateState()
	{
		if (rules.checkOver())
		{
			if (rules.checkAce())
			{
				audio.playSpecial(Special.QUAKE_FLAWLESS);
			}

			if (rules.p1Wins())
			{
				if (multiplayer)
				{
					server.sendGameover(players[PLAYER_1]);
				}

				parent.actionGameover(players[PLAYER_1]);
			}

			if (rules.p2Wins())
			{
				if (multiplayer)
				{
					server.sendGameover(players[PLAYER_2]);
				}

				parent.actionGameover(players[PLAYER_2]);
			}
		}
		else if (rules.checkLast())
		{
			parent.displayMessage("LAST ROUND");
		}
		else if (rules.checkTie())
		{
			parent.displayMessage("TIE BREAK");
		}
	}

	private void p1Goal(Puck puck)
	{
		if (lastPlayed == players[PLAYER_2])
		{
			audio.playSpecial(Special.QUAKE_HUMILIATION);
		}

		rules.p1Score();
		parent.actionScore(players[PLAYER_1]);
		lastPlayed = null;

		if (rules instanceof RulesAttack)
		{
			pucks.remove(puck);
		}
		else
		{
			factory.resetPuck(puck, PLAYER_2);
			factory.resetP1Paddle(paddles[PLAYER_1]);
			factory.resetP2Paddle(paddles[PLAYER_2]);
		}

		if (multiplayer)
		{
			server.sendScore();
		}
	}

	private void p2Goal(Puck puck)
	{
		if (lastPlayed == players[PLAYER_1])
		{
			audio.playSpecial(Special.QUAKE_HUMILIATION);
		}

		rules.p2Score();
		parent.actionScore(players[PLAYER_2]);
		lastPlayed = null;

		if (rules instanceof RulesAttack)
		{
			pucks.remove(puck);
		}
		else
		{
			factory.resetPuck(puck, PLAYER_1);
			factory.resetP1Paddle(paddles[PLAYER_1]);
			factory.resetP2Paddle(paddles[PLAYER_2]);
		}

		if (multiplayer)
		{
			server.sendScore();
		}
	}

	public void movePaddle(int paddleId, float x, float y)
	{
		if (parent.isGamePaused())
		{
			return;
		}

		if (paddleId == PLAYER_2)
		{
			paddles[PLAYER_2].move(x, y);
		}
		else
		{
			if (multiplayer)
			{
				paddles[PLAYER_1].move(x, y + screenHeight / 2);
			}
			else
			{
				paddles[PLAYER_1].move(x, y);
			}
		}
	}

	public void draw(SpriteBatch sb)
	{
		for (Puck puck : pucks)
		{
			if (puck != null)
			{
				puck.draw(sb);
			}
		}

		for (Paddle paddle : paddles)
		{
			if (paddle != null)
			{
				paddle.draw(sb);
			}
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
			server.actionDisconnect();
		}
		else
		{
			synchronized (cpu)
			{
				gameRunning = false;
			}
		}
	}
}