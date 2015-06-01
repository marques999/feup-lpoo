package lpoo.proj2.net;

import java.io.IOException;
import java.util.HashSet;
import lpoo.proj2.logic.Player;
import lpoo.proj2.net.Network.AddPlayer;
import lpoo.proj2.net.Network.Login;
import lpoo.proj2.net.Network.MovePaddle;
import lpoo.proj2.net.Network.PlayerConnection;
import lpoo.proj2.net.Network.RemovePlayer;
import lpoo.proj2.net.Network.UpdatePlayer;
import lpoo.proj2.net.Network.UpdateScore;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

public class GameServer
{
	private int serverPort;
	private Server server;
	private HashSet<Player> players = new HashSet<Player>();

	public GameServer(int port) throws IOException
	{
		serverPort = port;
		
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

					player = new Player(login.name, login.color);
					loggedIn(connection, player);
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
		
		server.bind(serverPort);
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