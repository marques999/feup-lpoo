package com.esotericsoftware.kryonet;

import java.nio.ByteBuffer;

public interface Serialization
{
	public void write(Connection connection, ByteBuffer buffer, Object object);
	public Object read(Connection connection, ByteBuffer buffer);
	public int getLengthLength();
	public void writeLength(ByteBuffer buffer, int length);
	public int readLength(ByteBuffer buffer);
}