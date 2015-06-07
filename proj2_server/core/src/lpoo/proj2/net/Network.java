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
		public String playerName;
		public int playerColor;
	}

	static public class PlayerConnected
	{
		public int playerId;
	}

	static public class PlayerDisconnected
	{
		public int playerId;
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
		public String playerName;
	}

	static public class ServerFull
	{
	}
};