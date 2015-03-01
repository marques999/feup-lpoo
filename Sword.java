package lpoo;

public class Sword
{
	int x;
	int y;
	
	public Sword()
	{
		this(0, 0);
	}
	
	public Sword(int x, int y)
	{
		this.x = x;
		this.y = y;
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