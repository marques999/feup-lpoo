package lpoo.logic;

public final class StaticMaze extends Maze 
{
    public StaticMaze() 
    {
        super(10);

        MazeBuilder m_builder = new MazeBuilder();

        m_builder.setType(0);
        m_builder.setSize(10);
        m_builder.generateMaze();

        setExitPosition(m_builder.getExitPosition());
        setMatrix(m_builder.getMatrix());
    }
}