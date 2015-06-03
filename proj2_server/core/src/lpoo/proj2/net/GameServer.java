package lpoo.proj2.net;

import java.io.IOException;
import java.util.HashSet;
import lpoo.proj2.logic.GameBoard;
import lpoo.proj2.logic.Player;
import lpoo.proj2.net.Network.AddPlayer;
import lpoo.proj2.net.Network.Login;
import lpoo.proj2.net.Network.MovePaddle;
import lpoo.proj2.net.Network.PlayerConnection;
import lpoo.proj2.net.Network.RemovePlayer;
import lpoo.proj2.net.Network.ServerFull;
import lpoo.proj2.net.Network.UpdatePlayer;
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
					Login login = (Login) object;
					
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
					if (player == null)
					{
						return;
					}

					MovePaddle msg = (MovePaddle) object;
					UpdatePlayer update = new UpdatePlayer();
					
					player.setX(msg.x);
					player.setY(msg.y);
					update.x = msg.x;
					update.y = msg.y;
					board.movePaddle(player.getID(), player.getX(), player.getY());
					server.sendToAllTCP(update);
				}
				else
				{
				}
			}

			@Override
			public void disconnected(Connection c)
			{
				PlayerConnection connection = (PlayerConnection) c;

				if (connection.player != null)
				{
					players.remove(connection.player);
					RemovePlayer removeCharacter = new RemovePlayer();
					removeCharacter.id = connection.player.getID();
					nextId--;
					server.sendToAllTCP(removeCharacter);
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
			AddPlayer addCharacter = new AddPlayer();
			addCharacter.id = other.getID();
			pc.sendTCP(addCharacter);
		}

		players.add(player);
		AddPlayer addCharacter = new AddPlayer();
		addCharacter.id = player.getID();
		server.sendToAllTCP(addCharacter);
	}
	
	public void disconnect()
	{
		server.close();
		server.stop();
	}

	public void sendScore(Player player)
	{
		UpdateScore updateScore = new UpdateScore();
		updateScore.id = player.getID();
		updateScore.score = player.getScore();
		server.sendToAllTCP(updateScore);
	}
}