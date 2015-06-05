package lpoo.proj2.net;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;

public class Network
{
	static public void register(EndPoint endPoint)
	{
		Kryo kryo = endPoint.getKryo();
		kryo.register(PlayerLogin.class);
		kryo.register(PlayerConnected.class);
		kryo.register(PlayerDisconnected.class);
		kryo.register(UpdatePaddle.class);
		kryo.register(UpdateScore.class);
		kryo.register(GameOver.class);
		kryo.register(ServerFull.class);
	}

	static public class PlayerLogin
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
	
	static public class UpdatePaddle
	{
		public float x;
		public float y;
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
};