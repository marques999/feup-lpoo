package lpoo.logic;

public class RandomMaze extends Maze
{
	public RandomMaze(int n)
	{
		super(n);

		MazeGenerator maze = new MazeGenerator(NUM_LINHAS);

		m = maze.getMatrix();
	}
}