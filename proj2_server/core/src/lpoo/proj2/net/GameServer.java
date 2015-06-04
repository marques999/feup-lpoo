package lpoo.proj2.net;

import java.io.IOException;
import java.util.HashSet;

import lpoo.proj2.logic.GameBoard;
import lpoo.proj2.logic.Player;
import lpoo.proj2.net.Network.GameOver;
import lpoo.proj2.net.Network.Login;
import lpoo.proj2.net.Network.MovePaddle;
import lpoo.proj2.net.Network.PlayerConnected;
import lpoo.proj2.net.Network.PlayerConnection;
import lpoo.proj2.net.Network.PlayerDisconnected;
import lpoo.proj2.net.Network.ServerFull;
import lpoo.proj2.net.Network.UpdateScore;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

public class GameServer
{
	private int serverTcpPort;
	private int serverUdpPort;
	private int nextId;
	private GameBoard board;
	private Server server;
	private HashSet<Player> players = new HashSet<Player>();

	public GameServer(int tcpPort, int udpPort, GameBoard paramBoard) throws IOException
	{
		nextId = 0;
		serverTcpPort = tcpPort;
		serverUdpPort = udpPort;
		board = paramBoard;
		
		server = new Server()
		{
			@Override
			protected Connection newConnection()
			{
				return new PlayerConnection();
			}
		};

		Network.register(server);

		server.addListener(new Listener()
		{
			@Override
			public void received(Connection c, Object object)
			{
				PlayerConnection connection = (PlayerConnection) c;
				Player player = connection.player;

				if (object instanceof Login)
				{
					final Login login = (Login) object;
					
					if (player != null)
					{
						return;
					}

					for (Player other : players)
					{
						if (other.getName().equals(login.name))
						{
							c.close();
							return;
						}
					}

					if (players.size() < 2)
					{
						player = new Player(nextId++, login.name, login.color);
						System.out.println("ID: " + player.getID());
						System.out.println("Player name: " + login.name);
						System.out.println("Player color: " + login.color);
						
						if (nextId == 1)
						{
							board.setPlayer1(player);
						}
						else if (nextId == 2)
						{
							board.setPlayer2(player);
						}
						
						loggedIn(connection, player);
					}
					else
					{
						ServerFull serverFull = new ServerFull();
						serverFull.numberPlayers = players.size();
						connection.sendTCP(serverFull);
					}
				}
				else if (object instanceof MovePaddle)
				{
					final MovePaddle msg = (MovePaddle) object;
					
					if (player != null)
					{
						player.setX(msg.x);
						player.setY(msg.y);
						board.movePaddle(player.getID(), player.getX(), player.getY());
					}
				}
				else
				{
				}
			}

			@Override
			public void disconnected(Connection c)
			{
				final PlayerConnection connection = (PlayerConnection) c;

				if (connection.player != null)
				{
					players.remove(connection.player);
					PlayerDisconnected playerDisconnected = new PlayerDisconnected();
					playerDisconnected.id = connection.player.getID();
					nextId--;					
					server.sendToAllTCP(playerDisconnected);
				}
			}
		});

		server.bind(serverTcpPort, serverUdpPort);
		server.start();
	}

	private void loggedIn(PlayerConnection pc, Player player)
	{
		pc.player = player;

		for (Player other : players)
		{
			PlayerConnected playerConnected = new PlayerConnected();
			playerConnected.id = other.getID();
			pc.sendTCP(playerConnected);
		}

		players.add(player);
		PlayerConnected playerConnected = new PlayerConnected();
		playerConnected.id = player.getID();
		server.sendToAllTCP(playerConnected);
	}
	
	public int getPlayersConnected()
	{
		return nextId;
	}
	
	public void disconnect()
	{
		server.close();
		server.stop();
	}

	public void sendScore()
	{
		UpdateScore updateScore = new UpdateScore();
		updateScore.p1Score = board.getPlayer1().getScore();
		updateScore.p2Score = board.getPlayer2().getScore();
		server.sendToAllTCP(updateScore);
	}
	
	public void sendGameover()
	{
		GameOver gameOver = new GameOver();
		server.sendToAllTCP(gameOver);
	}
}