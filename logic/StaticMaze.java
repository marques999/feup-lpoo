package lpoo.logic;

public class StaticMaze extends Maze
{
	public StaticMaze()
	{
		super(10);

		MazeBuilder m_builder = new MazeBuilder();

		m_builder.setType(0);
		m_builder.setSize(10);

		m = m_builder.getMatrix();
	}
}