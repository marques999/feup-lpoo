package com.dmpm.tabletennis;

public class PaddleListener
{
	protected static final int WAITING = 0;
	protected static final int POSITION = 1;
	protected static final int SERVE = 2;
	protected static final int EXIT = 3;

	private int state;
	private int currentCoordinate;
	private Paddle mObject;

	public PaddleListener(Paddle paddle)
	{
		mObject = paddle;
		currentCoordinate = 0;
		state = WAITING;
	}

	public int getState() {
		return state;
	}

	public void processInput(String theInput)
	{
		if (state == WAITING)
		{
			if (theInput.equals("POSITION"))
			{
				state = POSITION;
				System.out.println("Received position command...");
			}
			else if (theInput.equals("SERVE"))
			{
				state = SERVE;
				System.out.println("Received serve command...");
			}
			else if (theInput.equals("EXIT"))
			{
				state = EXIT;
				System.out.println("Received exit command...");
			}
		}
		else if (state == POSITION)
		{
			if (theInput.startsWith("x=") && currentCoordinate == 0)
			{
				mObject.setX(Double.parseDouble(theInput.substring(2)));
				System.out.println("POSITION: paddle x coordinate is now " + mObject.getX());
				currentCoordinate++;
			}
			else if (theInput.startsWith("y=") && currentCoordinate == 1)
			{
				mObject.setY(Double.parseDouble(theInput.substring(2)));
				System.out.println("POSITION: paddle y coordinate is now " + mObject.getY());
				state = WAITING;
			}
			else
			{
				System.out.println("POSITION: command not recognized");
				currentCoordinate = 0;
				state = WAITING;
			}
		}
		else if (state == SERVE)
		{
			state = WAITING;
		}
		else if (state == EXIT)
		{
			state = WAITING;
		}
	}
}