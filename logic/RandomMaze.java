package lpoo.logic;

public final class RandomMaze extends Maze 
{
    public RandomMaze(int n) 
    {
        super(n);

        MazeBuilder m_builder = new MazeBuilder();

        m_builder.setType(1);
        m_builder.setSize(n);
        m_builder.generateMaze();

        setExitPosition(m_builder.getExitPosition());
        setMatrix(m_builder.getMatrix());
    }
}