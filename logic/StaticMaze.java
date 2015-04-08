package lpoo.logic;

public final class StaticMaze extends Maze
{
	private static final long serialVersionUID = 295455156635188483L;

	/**
	 * generates a static maze (10x10)
	 */
	public StaticMaze()
	{
		super(10, 10);

		final MazeBuilder m_builder = new MazeBuilder();

		m_builder.setType(MazeBuilder.STATIC_MAZE);
		m_builder.setWidth(10);
		m_builder.setHeight(10);
		m_builder.generateMaze();

		setExitPosition(m_builder.getExitPosition());
		setMatrix(m_builder.getMatrix());
	}
}