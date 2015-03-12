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
	public static void moveSequence(char[] seq)
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
			'd', 'd', 'd', 's', 's', 's', 's', 'a', 'a', 'a', 's', 's', 's'	
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
		
		moveSequence(playerMoves);
		p = GameState.getPlayer();

		assertTrue(swordPosition.equals(p.getPosition()));
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
		moveSequence(playerMoves);
			
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
		moveSequence(playerMoves);
		
		Point exitPosition = new Point(9, 5);
		Hero p = GameState.getPlayer();
		
		assertTrue(exitPosition.equals(p.getPosition()));
		assertTrue(GameState.playerWon());
	}
	
	@Test
	public void test_4a_ReachExitIncomplete()
	{
		char playerMoves[] =
		{
				'd', 'd', 'd', 'd', 'd', 'd', 'd',
				's', 's', 's', 's', 'd',
		};
			
		Maze m = new StaticMaze();
			
		GameState.initializeStatic(m);
		GameState.setDragonMovement(-1);
		moveSequence(playerMoves);
		
		Point exitPosition = new Point(8, 5);
		Hero p = GameState.getPlayer();
		
		assertTrue(exitPosition.equals(p.getPosition()));
		assertFalse(GameState.playerWon());
	}
}
