package lpoo.test;

import junit.framework.TestCase;
import lpoo.logic.*;
import org.junit.Test;

public class TestInterface extends TestCase
{
	public static void movePlayer(char[] seq)
	{
		for (char input : seq)
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
		for (char input : seq)
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

	@Test
	public void test_3a_killedByDragon()
	{
		final Maze m = new StaticMaze();
		final Point dragonPosition = new Point(1, 3);

		GameState.initializeStatic(m);
		GameState.setDragonMovement(-1);
		GameState.placeDragon(dragonPosition);
		GameState.update(Direction.DOWN);

		final Hero p = GameState.getPlayer();

		assertEquals(0, p.getHealth());
		assertTrue(GameState.gameOver());
	}
}
