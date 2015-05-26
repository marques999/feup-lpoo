package lpoo.proj2.logic;

public class Goal extends Entity
{
	protected Goal(float x, float y, float width, float height)
	{
		super(x, y);
		setSize(width, height);
	}

	@Override
	public boolean move(float x, float y)
	{
		return false;
	}
}