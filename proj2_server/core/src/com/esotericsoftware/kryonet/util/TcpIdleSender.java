package com.esotericsoftware.kryonet.util;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

abstract public class TcpIdleSender extends Listener
{
	boolean started;

	public void idle(Connection connection)
	{
		if (!started)
		{
			started = true;
			start();
		}

		Object object = next();

		if (object == null)
		{
			connection.removeListener(this);
		}
		else
		{
			connection.sendTCP(object);
		}
	}

	protected void start()
	{
	}

	abstract protected Object next();
}