package com.dmpm.tabletennis;

public class Entity
{
	private double posX;
	private double posY;

	public Entity()
	{
		this(0, 0);
	}

	public Entity(double x, double y)
	{
		posX = x;
		posY = y;
	}

	public double getX()
	{
		return posX;
	}

	public double getY()
	{
		return posY;
	}

	protected void setX(double x)
	{
		posX = x;
	}

	protected void setY(double y)
	{
		posY = y;
	}
}