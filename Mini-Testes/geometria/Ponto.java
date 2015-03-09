package geometria;

public class Ponto implements Comparable<Ponto>
{
	private int x;
	private int y;

	public Ponto(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public final int getX()
	{
		return this.x;
	}

	public final int getY()
	{
		return this.y;
	}

	public final String toString()
	{
		return "(" + this.x + ", " + this.y + ")";
	}

	@Override
	public boolean equals(Object o)
	{
		return (this.x == ((Ponto) o).x && this.y == ((Ponto) o).y);
	}

	@Override
	public int compareTo(Ponto o)
	{
		if (this.x == ((Ponto) o).x)
		{
			if (this.y == ((Ponto) o).y)
			{
				return 0;
			}

			return (this.y < ((Ponto) o).y) ? -1 : 1;
		}

		return (this.x < ((Ponto) o).x) ? -1 : 1;
	}
}