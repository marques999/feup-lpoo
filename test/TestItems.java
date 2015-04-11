package lpoo.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import lpoo.logic.Direction;
import lpoo.logic.Dragon;
import lpoo.logic.GameState;
import lpoo.logic.Hero;
import lpoo.logic.Item;
import lpoo.logic.Maze;
import lpoo.logic.Point;
import lpoo.logic.StaticMaze;
import org.junit.Test;

public class TestItems
{
	@Test(timeout = 1000)
	public void test_pickupShield()
	{
		final char[][] m1 =
		{
			{ 'X', 'X', 'X', 'X', 'X' },
			{ 'X', 'h', ' ', 'V', 'X' },
			{ 'X', ' ', 'X', ' ', 'S' },
			{ 'X', ' ', ' ', ' ', 'X' },
			{ 'X', 'X', 'X', 'X', 'X' }
		};

		final Maze maze = new Maze(5, 5);
		
		maze.setMatrix(m1);
		GameState.initializeCustom(maze);
		GameState.setDragonMovement(GameState.DRAGON_STATIC_NOSLEEP);

		final Item s = GameState.getShield();
		final Point shieldPosition = new Point(3, 1);

		assertNull(GameState.getSword());

		GameState.update(Direction.RIGHT);
		GameState.update(Direction.RIGHT);

		assertEquals(shieldPosition, s.getPosition());
		assertNull(GameState.getShield());

		final Hero p = GameState.getPlayer();

		assertEquals(shieldPosition, p.getPosition());
		assertTrue(p.hasShield());
	}

	@Test(timeout = 1000)
	public void test_pickupSword()
	{
		final char playerMoves[] =
		{
			'w', 'd', 'd', 'd', 's', 's', 's',
			's', 'a', 'a', 'a', 's', 's', 's'
		};

		final Maze m = new StaticMaze();

		GameState.initializeStatic(m);
		GameState.setDragonMovement(GameState.DRAGON_STATIC_NOSLEEP);

		final Item s = GameState.getSword();
		final Point swordPosition = new Point(s.getX(), s.getY());

		assertTrue(swordPosition.equals(s.getPosition()));

		TestInterface.movePlayer(playerMoves);

		final Hero p = GameState.getPlayer();

		assertEquals(p.getPosition(), swordPosition);
		assertTrue(p.hasSword());
	}

	@Test(timeout = 1000)
	public void test_dragonOverlap()
	{
		final char[][] initialMaze =
		{
			{ 'X', 'X', 'X', 'X', 'X' },
			{ 'X', 'h', ' ', 'E', 'X' },
			{ 'X', ' ', 'X', 'V', 'S' },
			{ 'X', ' ', '*', 'D', 'X' },
			{ 'X', 'X', 'X', 'X', 'X' }
		};

		final Maze maze = new Maze(5, 5);
		
		maze.setMatrix(initialMaze);
		GameState.initializeCustom(maze);
		GameState.setDragonMovement(GameState.DRAGON_STATIC_NOSLEEP);

		final Dragon d = GameState.getDragon();

		GameState.moveDragon(d, Direction.LEFT);
		GameState.update(Direction.NONE);

		assertEquals(d.getPosition(), GameState.getDart().getPosition());
		assertEquals(maze.symbolAt(d.getX(), d.getY()), 'F');

		GameState.moveDragon(d, Direction.RIGHT);
		GameState.moveDragon(d, Direction.UP);
		GameState.update(Direction.NONE);

		assertEquals(d.getPosition(), GameState.getShield().getPosition());
		assertEquals(maze.symbolAt(d.getX(), d.getY()), 'F');

		GameState.moveDragon(d, Direction.UP);
		GameState.update(Direction.NONE);

		assertEquals(d.getPosition(), GameState.getSword().getPosition());
		assertEquals(maze.symbolAt(d.getX(), d.getY()), 'F');
	}

	@Test(timeout = 1000)
	public void test_pickupDarts()
	{
		final char[][] initialMaze =
		{
			{ 'X', 'X', 'X', 'X', 'X' },
			{ 'X', 'h', ' ', ' ', 'X' },
			{ 'X', ' ', 'X', ' ', 'S' },
			{ 'X', ' ', '*', ' ', 'X' },
			{ 'X', 'X', 'X', 'X', 'X' }
		};

		final char playerMoves[] =
		{
			'd', 'd', 's', 's', 'a',
		};

		final Maze maze = new Maze(5, 5);
		
		maze.setMatrix(initialMaze);
		GameState.initializeCustom(maze);
		GameState.setDragonMovement(GameState.DRAGON_STATIC_NOSLEEP);

		final Item s = GameState.getDart();
		final Point dartPosition = new Point(2, 3);

		assertNull(GameState.getSword());
		assertEquals(dartPosition, s.getPosition());

		TestInterface.movePlayer(playerMoves);

		final Hero p = GameState.getPlayer();

		assertEquals(dartPosition, p.getPosition());
		assertNull(GameState.getDart());
		assertTrue(p.hasDarts());
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

	@Test(timeout = 1000)
	public void test_attackSword()
	{
		final char playerMoves[] =
		{
			'd', 'd', 'd', 's', 's', 's', 's',
			'a', 'a', 'a', 's', 's', 's', 'w',
			'w', 'w', 'd', 'd', 'd', 'd', 'd'
		};

		final Maze m = new StaticMaze();

		GameState.initializeStatic(m);
		GameState.setDragonMovement(GameState.DRAGON_STATIC_NOSLEEP);

		final Dragon d = GameState.getDragon();

		assertEquals(d.getHealth(), 100);

		TestInterface.movePlayer(playerMoves);

		assertEquals(d.getHealth(), 0);

		GameState.updateDragons();

		assertNull(GameState.getDragon());
	}

	/*
	-   0   1   2   3   4   5   6   7   8   9
	0   X   X   X   X   X   X   X   X   X   X
	1   X   H       V                       X
	2   X       X   X       X       X       X
	3   X       X   X   *   X       X       X
	4   X       X   X       X   D   X       X
	5   X                           X       S
	6   X       X   X       X       X       X
	7   X       X   X       X       X       X
	8   X   E   X   X   *                   X
	9   X   X   X   X   X   X   X   X   X   X
	 */

	@Test(timeout = 1000)
	public void test_attackDarts()
	{
		final char playerMoves1[] =
		{
			/* apanhar escudo */
			'd', 'd',
			/* apanhar primeiro dardo */
			'd', 's', 's',
			/* apanhar espada */
			's', 's', 'a', 'a', 'a', 's', 's', 's',
			/* seguir em direcção ao dragão */
			'w', 'w', 'w', 'd', 'd', 'd',
		};

		final char playerMoves2[] =
		{
			/* apanhar segundo dardo */
			's',  's', 's', 'd', 'd'
		};

		final Maze m = new StaticMaze();
		final Point dartPosition1 = new Point(4, 3);
		final Point dartPosition2 = new Point(4, 8);
		final Point dragonPosition = new Point(6, 4);

		GameState.initializeStatic(m);
		GameState.setDragonMovement(GameState.DRAGON_STATIC_NOSLEEP);
		GameState.placeDart(dartPosition1);
		GameState.placeDart(dartPosition2);
		GameState.placeDragon(dragonPosition);

		assertFalse(GameState.attackDarts(Direction.RIGHT));

		TestInterface.movePlayer(playerMoves1);

		final Hero p = GameState.getPlayer();

		assertTrue(p.hasDarts());
		assertEquals(GameState.getNumberDarts(), 1);
		assertFalse(GameState.attackDarts(Direction.DOWN));
		assertFalse(p.hasDarts());

		TestInterface.movePlayer(playerMoves2);

		assertTrue(p.hasDarts());
		assertTrue(GameState.attackDarts(Direction.UP));
		assertEquals(GameState.getDragon().getHealth(), 0);
		assertFalse(p.hasDarts());
	}
}