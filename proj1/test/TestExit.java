package lpoo.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import lpoo.logic.Direction;
import lpoo.logic.GameState;
import lpoo.logic.Hero;
import lpoo.logic.Maze;
import lpoo.logic.Point;
import lpoo.logic.StaticMaze;
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
	@Test(timeout = 1000)
	public void test_reachExit()
	{
		final char playerMoves[] =
		{
			'd', 'd', 'd', 's', 's', 's', 's', 'a', 'a', 'a',
			's', 's', 's', 'w', 'w', 'w', 'd', 'd', 'd', 'd',
			'd', 'd', 's', 's', 's', 'd', 'd', 'w', 'w', 'w'
		};

		final Maze m = new StaticMaze();
		final Point exitPosition = new Point(9, 5);
		final Point playerPosition = new Point(8, 5);

		GameState.initializeStatic(m);
		GameState.setDragonMovement(2);

		assertEquals(exitPosition, GameState.getMaze().getExitPosition());
		assertFalse(GameState.canExit());

		TestInterface.movePlayer(playerMoves);

		assertTrue(GameState.canExit());

		final Hero p = GameState.getPlayer();

		assertEquals(p.getPosition(), playerPosition);

		GameState.update(Direction.RIGHT);

		assertEquals(p.getPosition(), exitPosition);
		assertTrue(GameState.playerWon());
	}

	@Test(timeout = 1000)
	public void test_reachExitIncomplete()
	{
		final char playerMoves[] =
		{
			's', 's', 's', 's', 'd', 'd', 'd', 's', 's',
			's', 'd', 'd', 'd', 'd', 'w', 'w', 'w', 'a',
		};

		final Maze m = new StaticMaze();
		final Point exitPosition = new Point(9, 5);
		final Point playerPosition = new Point(8, 5);

		GameState.initializeStatic(m);
		GameState.setDragonMovement(-1);

		TestInterface.movePlayer(playerMoves);

		final Hero p = GameState.getPlayer();

		assertFalse(GameState.canExit());
		assertEquals(p.getPosition(), playerPosition);

		GameState.update(Direction.RIGHT);

		assertFalse(GameState.playerWon());
		assertFalse(exitPosition.equals(p.getPosition()));
	}
}