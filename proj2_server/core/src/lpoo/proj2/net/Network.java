package lpoo.proj2.net;

import lpoo.proj2.logic.Player;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.EndPoint;

public class Network
{
	static public void register(EndPoint endPoint)
	{
		Kryo kryo = endPoint.getKryo();
		kryo.register(Login.class);
		kryo.register(PlayerConnected.class);
		kryo.register(PlayerDisconnected.class);
		kryo.register(GameOver.class);
		kryo.register(UpdateScore.class);
		kryo.register(ServerFull.class);
		kryo.register(Player.class);
		kryo.register(MovePaddle.class);
	}

	static public class Login
	{
		public String name;
		public int color;
	}

	static public class PlayerConnected
	{
		public int id;
	}

	static public class PlayerDisconnected
	{
		public int id;
	}

	static public class UpdateScore
	{
		public int p1Score;
		public int p2Score;
	}

	static public class GameOver
	{
	}

	static public class ServerFull
	{
		public int numberPlayers;
	}

	static public class PlayerConnection extends Connection
	{
		public Player player;
	}

	static public class MovePaddle
	{
		public float x;
		public float y;
	}
};