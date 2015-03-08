package lpoo.logic;

public class RandomMaze extends Maze
{
	public RandomMaze(int n)
	{
		super(n);

		MazeBuilder m_builder = new MazeBuilder();

		m_builder.setType(1);
		m_builder.setSize(n);
		
		m = m_builder.getMatrix();
	}
}