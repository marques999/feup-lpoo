package lpoo.test;

import lpoo.logic.*;
import org.junit.Test;
import static lpoo.test.TestInterface.movePlayer;
import static org.junit.Assert.*;

public class TestItems
{
	@Test
	public void test_pickupShield()
	{
		char[][] m1 = 
		{
			{ 'X', 'X', 'X', 'X', 'X' },
			{ 'X', 'h', ' ', 'V', 'X' },
			{ 'X', ' ', 'X', ' ', 'S' },
			{ 'X', ' ', ' ', ' ', 'X' },
			{ 'X', 'X', 'X', 'X', 'X' }
		};

		Maze maze = new Maze(5, 5);
		maze.setMatrix(m1);

		GameState.initializeCustom(maze);
		GameState.setDragonMovement(-1);

		Item s = GameState.getShield();
		Point shieldPosition = new Point(3, 1);

		assertNull(GameState.getSword());

		GameState.update(Direction.RIGHT);
		GameState.update(Direction.RIGHT);

		assertEquals(shieldPosition, s.getPosition());
		assertNull(GameState.getShield());

		Hero p = GameState.getPlayer();

		assertEquals(shieldPosition, p.getPosition());
		assertTrue(p.hasShield());
	}

	@Test
	public void test_2a_PickupSword()
	{
		char playerMoves[] = 
		{
			'w', 'd', 'd', 'd', 's', 's', 's', 
			's', 'a', 'a', 'a', 's', 's', 's'
		};

		Maze m = new StaticMaze();

		GameState.initializeStatic(m);
		GameState.setDragonMovement(-1);

		Item s = GameState.getSword();
		Point swordPosition = new Point(s.getX(), s.getY());

		assertTrue(swordPosition.equals(s.getPosition()));

		movePlayer(playerMoves);

		Hero p = GameState.getPlayer();

		assertEquals(p.getPosition(), swordPosition);
		assertTrue(p.hasSword());
	}

	@Test
	public void test_pickupDarts()
	{
		char[][] initialMaze = 
		{
			{ 'X', 'X', 'X', 'X', 'X' },
			{ 'X', 'h', ' ', ' ', 'X' },
			{ 'X', ' ', 'X', ' ', 'S' },
			{ 'X', ' ', '*', ' ', 'X' },
			{ 'X', 'X', 'X', 'X', 'X' }
		};

		char playerMoves[] = 
		{
			'd', 'd', 's', 's', 'a', 
		};

		Maze maze = new Maze(5, 5);
		maze.setMatrix(initialMaze);

		GameState.initializeCustom(maze);
		GameState.setDragonMovement(-1);

		Item s = GameState.getDart();
		Point dartPosition = new Point(2, 3);

		assertNull(GameState.getSword());
		assertEquals(dartPosition, s.getPosition());

		movePlayer(playerMoves);

		Hero p = GameState.getPlayer();

		assertEquals(dartPosition, p.getPosition());
		assertNull(GameState.getDart());
		assertTrue(p.hasDarts());
	}
}