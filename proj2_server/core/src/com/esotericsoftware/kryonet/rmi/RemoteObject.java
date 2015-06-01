package com.esotericsoftware.kryonet.rmi;

import com.esotericsoftware.kryonet.Connection;

public interface RemoteObject
{
	public void setResponseTimeout(int timeoutMillis);
	public void setNonBlocking(boolean nonBlocking);
	public void setTransmitReturnValue(boolean transmit);
	public void setTransmitExceptions(boolean transmit);
	public void setUDP(boolean udp);
	public void setRemoteToString(boolean remoteToString);

	/**
	 * Waits for the response to the last method invocation to be received or
	 * the response timeout to be reached. Must not be called from the
	 * connection's update thread.
	 */
	public Object waitForLastResponse();

	/**
	 * Gets the ID of response for the last method invocation.
	 */
	public byte getLastResponseID();

	/**
	 * Waits for the specified method invocation response to be received or the
	 * response timeout to be reached. Must not be called from the connection's
	 * update thread. Response IDs use a six bit identifier, with one identifier
	 * reserved for "no response". This means that this method should be called
	 * to get the result for a non-blocking call before an additional 63
	 * non-blocking calls are made, or risk undefined behavior due to identical
	 * IDs.
	 */
	public Object waitForResponse(byte responseID);

	/**
	 * Causes this RemoteObject to stop listening to the connection for method
	 * invocation response messages.
	 */
	public void close();

	/**
	 * Returns the local connection for this remote object.
	 */
	public Connection getConnection();
}