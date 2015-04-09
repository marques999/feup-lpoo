package lpoo.test;
import static lpoo.test.TestInterface.movePlayer;
import static org.junit.Assert.*;
import lpoo.logic.*;
import org.junit.Test;

/*
	-   0   1   2   3   4   5   6   7   8   9
	0   X   X   X   X   X   X   X   X   X   X
	1   X   H       V                       X
	2   X       X   X       X       X       X
	3   X       X   X       X       X       X
	4   X       X   X       X   D   X       X
	5   X                           X       S
	6   X       X   X       X       X       X
	7   X       X   X       X       X       X
	8   X   E   X   X                       X
	9   X   X   X   X   X   X   X   X   X   X
 */

public class TestExit
{
	@Test
	public void test_reachExit()
	{
		final char playerMoves[] =
			{
				/* herói apanha espada e escudo */
				'd', 'd', 'd', 's', 's', 's', 's', 'a', 'a', 'a', 's', 's', 's',
				/* herói move-se em direcção ao dragão, atacando-o */
				'w', 'w', 'w', 'd', 'd', 'd', 'd', 'd', 'd',
				/* herói desloca-se para a saída */
				's', 's', 's', 'd', 'd', 'w', 'w', 'w'
			};

		final Maze m = new StaticMaze();
		final Point exitPosition = new Point(9, 5);

		GameState.initializeStatic(m);
		GameState.setDragonMovement(2);

		assertEquals(exitPosition, GameState.getMaze().getExitPosition());
		assertFalse(GameState.canExit());

		movePlayer(playerMoves);

		final Point playerPosition = new Point(8, 5);

		assertTrue(GameState.canExit());

		final Hero p = GameState.getPlayer();

		assertEquals(p.getPosition(), playerPosition);

		GameState.update(Direction.RIGHT);

		assertEquals(p.getPosition(), exitPosition);
		assertTrue(GameState.playerWon());
	}

	@Test
	public void test_reachExitIncomplete()
	{
		final char playerMoves[] =
		{
			's', 's', 's', 's', 'd', 'd', 'd', 's', 's',
			's', 'd', 'd', 'd', 'd', 'w', 'w', 'w', 'a',
		};

		final Maze m = new StaticMaze();

		GameState.initializeStatic(m);
		GameState.setDragonMovement(-1);
		movePlayer(playerMoves);

		final Point exitPosition = new Point(9, 5);
		final Point playerPosition = new Point(8, 5);

		Hero p = GameState.getPlayer();

		assertFalse(GameState.canExit());
		assertEquals(p.getPosition(), playerPosition);

		GameState.update(Direction.RIGHT);

		p = GameState.getPlayer();

		assertFalse(GameState.playerWon());
		assertFalse(exitPosition.equals(p.getPosition()));
	}
}