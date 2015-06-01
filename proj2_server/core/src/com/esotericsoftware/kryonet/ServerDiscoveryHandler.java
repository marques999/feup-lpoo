package com.esotericsoftware.kryonet;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public interface ServerDiscoveryHandler
{
	public static final ServerDiscoveryHandler DEFAULT = new ServerDiscoveryHandler()
	{
		private ByteBuffer emptyBuffer = ByteBuffer.allocate(0);

		@Override
		public boolean onDiscoverHost(DatagramChannel datagramChannel, InetSocketAddress fromAddress, Serialization serialization) throws IOException
		{
			datagramChannel.send(emptyBuffer, fromAddress);

			return true;
		}
	};

	public boolean onDiscoverHost(DatagramChannel datagramChannel, InetSocketAddress fromAddress, Serialization serialization) throws IOException;
}