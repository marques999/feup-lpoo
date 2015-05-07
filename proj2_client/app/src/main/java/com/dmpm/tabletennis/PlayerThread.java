package com.dmpm.tabletennis;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class PlayerThread extends Thread
{
	protected Socket mSocket;
	protected Paddle mPaddle;
	private boolean connectedServer = false;
	private DataInputStream mInput = null;
	private PrintWriter mOutput = null;

	public PlayerThread(Socket clientSocket, Paddle clientPaddle)
	{
		mSocket = clientSocket;
		mPaddle = clientPaddle;
	}

	public void run()
	{
		try
		{
			mInput = new DataInputStream(mSocket.getInputStream());
			mOutput = new PrintWriter(mSocket.getOutputStream());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		while (connectedServer)
		{
			mOutput.write("POSITION");
			mOutput.write("x=" + mPaddle.getX());
			mOutput.write("y=" + mPaddle.getY());

			try
			{
				Thread.sleep(1000);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
}