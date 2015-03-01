package lpoo;

public class Sword
{
	int x;
	int y;
	boolean overlapping;
	
	public Sword()
	{
		this(0, 0);
	}
	
	public Sword(int x, int y)
	{
		this.x = x;
		this.y = y;
		this.overlapping = false;
	}
	
	public final boolean isOverlapping()
	{
		return overlapping;
	}
	
	public void setOverlapping(boolean overlapping)
	{
		this.overlapping = overlapping;
	}
		
	public void draw()
	{
		if (Board.m[y][x] == 'D')
		{
			Board.m[y][x] = 'F';
		}
		else
		{
			Board.m[y][x] = 'E';
		}
	}
}
