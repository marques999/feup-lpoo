package lpoo.test;

import junit.framework.TestCase;
import lpoo.logic.*;
import org.junit.Test;

public class TestInterface extends TestCase
{
	public static void movePlayer(char[] seq)
	{
		for (final char input : seq)
		{
			switch (input)
			{
			case 'w':
				GameState.update(Direction.UP);
				break;
			case 's':
				GameState.update(Direction.DOWN);
				break;
			case 'a':
				GameState.update(Direction.LEFT);
				break;
			case 'd':
				GameState.update(Direction.RIGHT);
				break;
			}
		}
	}

	public static void moveDragon(Dragon dragon, char[] seq)
	{
		for (final char input : seq)
		{
			switch (input)
			{
			case 'w':
				GameState.moveDragon(dragon, Direction.UP);
				break;
			case 's':
				GameState.moveDragon(dragon, Direction.DOWN);
				break;
			case 'a':
				GameState.moveDragon(dragon, Direction.LEFT);
				break;
			case 'd':
				GameState.moveDragon(dragon, Direction.RIGHT);
				break;
			}
		}
	}

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

	@Test
	public void test_dragonAttack()
	{
		final char playerMoves[] =
		{
			's', 's', 's', 's', 'd', 'd', 'd', 'd', 'd'
		};

		final Maze m = new StaticMaze();

		GameState.initializeStatic(m);
		GameState.setDragonMovement(GameState.DRAGON_STATIC_NOSLEEP);

		assertEquals(GameState.getNumberDragons(), 1);

		TestInterface.movePlayer(playerMoves);

		final Hero p = GameState.getPlayer();

		assertEquals(0, p.getHealth());
		assertFalse(GameState.playerWon());
		assertTrue(GameState.gameOver());
	}
}