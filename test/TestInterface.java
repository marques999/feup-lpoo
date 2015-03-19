package lpoo.test;

import junit.framework.TestCase;
import lpoo.logic.Direction;
import lpoo.logic.Dragon;
import lpoo.logic.GameState;
import lpoo.logic.Hero;
import lpoo.logic.Item;
import lpoo.logic.Maze;
import lpoo.logic.Point;
import lpoo.logic.StaticMaze;
import org.junit.Test;

public class TestInterface extends TestCase
{
	public static boolean movePlayer(char[] seq)
	{
		boolean returnValue = true;
		
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
		
		return returnValue;
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
	public void test_1a_ValidMove()
	{
		Maze m = new StaticMaze();

		GameState.initializeStatic(m);
		GameState.setDragonMovement(-1);
		
		Hero p = GameState.getPlayer();
		
		Point initialPosition = new Point(1, 1);
		Point newPosition = new Point(2, 1);
		
		assertTrue(initialPosition.equals(p.getPosition()));

		GameState.update(Direction.RIGHT);
		p = GameState.getPlayer();

		assertTrue(newPosition.equals(p.getPosition()));
	}

	@Test
	public void test_1b_InvalidMove()
	{
		Maze m = new StaticMaze();

		GameState.initializeStatic(m);
		GameState.setDragonMovement(-1);
		
		Hero p = GameState.getPlayer();
		Point initialPosition = new Point(1, 1);
		
		GameState.update(Direction.UP);
		p = GameState.getPlayer();
		
		assertTrue(initialPosition.equals(p.getPosition()));
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
		
		Hero p = GameState.getPlayer();
		Item s = GameState.getSword();
		
		Point initialPosition = new Point(1, 1);
		Point swordPosition = new Point(s.getX(), s.getY());
		
		assertTrue(initialPosition.equals(p.getPosition()));
		assertTrue(swordPosition.equals(s.getPosition()));
		
		movePlayer(playerMoves);
		
		p = GameState.getPlayer();

		assertEquals(swordPosition, p.getPosition());
		assertTrue(p.hasSword());
	}
	
	@Test
	public void test_3a_killedByDragon()
	{
		Maze m = new StaticMaze();
		
		GameState.initializeStatic(m);
		GameState.setDragonMovement(-1);
		GameState.update(Direction.DOWN);
		
		Hero p = GameState.getPlayer();
		
		assertEquals(0, p.getHealth());
		assertTrue(GameState.gameOver());
	}
	
	@Test
	public void test_3b_killDragon()
	{
		char playerMoves[] =
		{
			'd', 'd', 'd',
			's', 's', 's', 's', 
			'a', 'a', 'a', 
			's', 's', 's', 
			'w', 'w', 'w', 'w'
		};
			
		Maze m = new StaticMaze();
			
		GameState.initializeStatic(m);
		GameState.setDragonMovement(-1);
		movePlayer(playerMoves);
			
		Dragon d = GameState.getDragon();
				
		assertEquals(0, d.getHealth());
	}
	
	@Test
	public void test_4a_ReachExit()
	{
		char playerMoves[] =
		{
			'd', 'd', 'd',
			's', 's', 's', 's', 
			'a', 'a', 'a',
			's', 's', 's',
			'w', 'w', 'w', 'w', 's',
			'd', 'd', 'd', 'd', 'd',
			's', 's', 's',
			'd', 'd',
			'w', 'w', 'w', 'd'
		};
			
		Maze m = new StaticMaze();
			
		GameState.initializeStatic(m);
		GameState.setDragonMovement(-1);
		
		assertFalse(GameState.canExit());
		
		movePlayer(playerMoves);
		
		assertTrue(GameState.canExit());
		
		Point exitPosition = new Point(9, 5);
		Hero p = GameState.getPlayer();
		
		assertEquals(p.getPosition(), exitPosition);
		assertTrue(GameState.playerWon());
	}
	
	@Test
	public void test_4b_ReachExitIncomplete()
	{
		char playerMoves[] =
		{
			'd', 'd', 'd', 'd', 'd', 'd', 'd',
			's', 's', 's', 's', 'd',
		};
			
		Maze m = new StaticMaze();
			
		GameState.initializeStatic(m);
		GameState.setDragonMovement(-1);
		movePlayer(playerMoves);
		
		Point exitPosition = new Point(8, 5);
		Hero p = GameState.getPlayer();
		
		assertFalse(GameState.canExit());
		assertEquals(p.getPosition(), exitPosition);
		assertFalse(GameState.playerWon());
	}
	
	@Test
	public void test_5a_pickupShield()
	{			
		Maze m = new StaticMaze();
		Point shieldPosition = new Point(3, 1);
			
		GameState.initializeStatic(m);
		GameState.setDragonMovement(-1);
		GameState.update(Direction.RIGHT);
		GameState.update(Direction.RIGHT);
			
		Hero p = GameState.getPlayer();
			
		assertEquals(p.getPosition(), shieldPosition);
		assertTrue(p.hasShield());
	}
	
	@Test
	public void test_5b_pickupDarts()
	{
		char playerMoves[] =
		{
			'd', 'd', 'd',	's', 's', 's',
		};
		
		Maze m = new StaticMaze();
		Point dartPosition = new Point(4, 4);
		
		GameState.initializeStatic(m);
		GameState.setDragonMovement(-1);
		GameState.placeDart(dartPosition);
		movePlayer(playerMoves);
		
		Hero p = GameState.getPlayer();
		
		assertEquals(p.getPosition(), dartPosition);
		assertTrue(p.hasDarts());
	}
	
	@Test
	public void test_6a_moveDragon()
	{
		char dragonMoves[] =
		{
			'a', 'd', 'a', 'a', 's', 's', 'd', 'a', 
			'd', 'd', 'w', 'd', 'w', 'w', 's', 's',
			's', 's', 's', 's', 'd', 'd', 'a', 'a', // some extra invalid steps
		};
		
		Maze m = new StaticMaze();

		Point dragonPosition = new Point(1, 3);
		Point finalPosition = new Point(4, 8);
		
		GameState.initializeStatic(m);
		GameState.setDragonMovement(-1);
		
		Dragon d = GameState.placeDragon(dragonPosition);

		assertEquals(d.getPosition(), dragonPosition);
		
		moveDragon(d, dragonMoves);
		
		System.out.println(d.getX() + ", " + d.getY());
		assertEquals(d.getPosition(), finalPosition);
	}
	
	@Test
	public void test_6b_attackDarts()
	{
//		char playerMoves1[] =
//			{
//				'd', 'd', 'd',	's', 's', 's',
//			};
//		
//		Maze m = new StaticMaze();
//		
//		Point dartPosition1 = new Point(4, 4);
//		Point dartPosition2 = new Point(3, 5);
//		
//		GameState.placeDart(dartPosition1);
//		GameState.placeDart(dartPosition2);
//		
//		assertFalse(GameState.attackDarts(Direction.RIGHT));
//		
//		movePlayer(playerMoves1);
//		moveDragon(dragonMoves);
//		
//		Hero p = GameState.getPlayer();
//		
//		assertTrue(p.hasDarts());
//		assertFalse(GameState.attackDarts(Direction.UP));
//		
//		p = GameState.getPlayer();
//		
//		assertFalse(p.hasDarts());
//		
//		GameState.update(Direction.DOWN);
//		GameState.update(Direction.LEFT);
	}
}
