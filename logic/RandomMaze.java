package lpoo.logic;

public final class RandomMaze extends Maze
{
	/**
	 * @brief generates a random maze (w x h)
	 * @param w number of columns
	 * @param h number of rows
	 */
	public RandomMaze(int w, int h)
	{
		super(w, h);

		final MazeBuilder m_builder = new MazeBuilder();

		m_builder.setType(MazeBuilder.RANDOM_MAZE);
		m_builder.setWidth(w);
                m_builder.setHeight(h);
		m_builder.generateMaze();

		setExitPosition(m_builder.getExitPosition());
		setMatrix(m_builder.getMatrix());
	}
}