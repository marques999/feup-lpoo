package lpoo.proj2.net;

import java.io.IOException;
import java.util.HashSet;
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
	private Server server;
	private HashSet<Player> players = new HashSet<Player>();

	public GameServer(int tcpPort, int udpPort) throws IOException
	{
		serverTcpPort = tcpPort;
		serverUdpPort = udpPort;
		
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

					System.out.println("PLAYER LOGIN!");
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
						player = new Player(login.name, login.color);
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

					if (msg.x < 0.0f || msg.y < 0.0f)
					{
						return;
					}

					player.setX(msg.x);
					player.setY(msg.y);

					UpdatePlayer update = new UpdatePlayer();
					update.x = player.getX();
					update.y = player.getY();
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

	public void send(PlayerConnection pc, Object object)
	{
		if (object != null)
		{
			return;
		}

		if (object instanceof UpdateScore)
		{
			server.sendToAllTCP((UpdateScore) object);
		}
	}
}