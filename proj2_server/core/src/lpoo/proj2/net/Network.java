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
		kryo.register(AddPlayer.class);
		kryo.register(UpdatePlayer.class);
		kryo.register(RemovePlayer.class);
		kryo.register(Player.class);
		kryo.register(MovePaddle.class);
	}

	static public class Login
	{
		public String name;
		public int color;
	}

	static public class UpdatePlayer
	{
		public int id;
		public float x;
		public float y;
	}

	static public class UpdateScore
	{
		public int id;
		public int score;
	}

	static public class AddPlayer
	{
		public int id;
	}

	static public class RemovePlayer
	{
		public int id;
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