package com.esotericsoftware.kryonet;

public interface FrameworkMessage
{
	static final FrameworkMessage.KeepAlive keepAlive = new KeepAlive();

	static public class RegisterTCP implements FrameworkMessage
	{
		public int connectionID;
	}

	static public class RegisterUDP implements FrameworkMessage
	{
		public int connectionID;
	}

	static public class KeepAlive implements FrameworkMessage
	{
	}

	static public class DiscoverHost implements FrameworkMessage
	{
	}

	static public class Ping implements FrameworkMessage
	{
		public int id;
		public boolean isReply;
	}
}