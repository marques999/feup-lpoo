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
		GameState.setDragonMovement(GameState.DRAGON_STATIC_NOSLEEP);

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
	public void test_pickupSword()
	{
		char playerMoves[] = 
		{
			'w', 'd', 'd', 'd', 's', 's', 's', 
			's', 'a', 'a', 'a', 's', 's', 's'
		};

		Maze m = new StaticMaze();

		GameState.initializeStatic(m);
		GameState.setDragonMovement(GameState.DRAGON_STATIC_NOSLEEP);

		Item s = GameState.getSword();
		Point swordPosition = new Point(s.getX(), s.getY());

		assertTrue(swordPosition.equals(s.getPosition()));

		movePlayer(playerMoves);

		Hero p = GameState.getPlayer();

		assertEquals(p.getPosition(), swordPosition);
		assertTrue(p.hasSword());
	}
	
	@Test
	public void test_dragonOverlap()
	{
		char[][] initialMaze = 
			{
				{ 'X', 'X', 'X', 'X', 'X' },
				{ 'X', 'h', ' ', 'E', 'X' },
				{ 'X', ' ', 'X', 'V', 'S' },
				{ 'X', ' ', '*', 'D', 'X' },
				{ 'X', 'X', 'X', 'X', 'X' }
			};

			Maze maze = new Maze(5, 5);
			maze.setMatrix(initialMaze);

			GameState.initializeCustom(maze);
			GameState.setDragonMovement(GameState.DRAGON_STATIC_NOSLEEP);
			
			Dragon d = GameState.getDragon();
			
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
		GameState.setDragonMovement(GameState.DRAGON_STATIC_NOSLEEP);

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
    public void test_attackSword()
    {
        char playerMoves[] =
        {
        	/* apanhar escudo */
            'd', 'd', 'd', 
            /* apanhar espada */
            's', 's', 's', 's', 'a', 'a', 'a', 's', 's', 's', 
            /* seguir em direcção ao dragão */
            'w', 'w', 'w', 'd', 'd', 'd', 'd', 'd'
        };

        Maze m = new StaticMaze();

        GameState.initializeStatic(m);
        GameState.setDragonMovement(GameState.DRAGON_STATIC_NOSLEEP);

        Dragon d = GameState.getDragon();
        
        assertEquals(d.getHealth(), 100);
        
        movePlayer(playerMoves);
        
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
    
    @Test
    public void test_attackDarts()
    {
        char playerMoves1[] =
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
        
        char playerMoves2[] =
        	{
        		/* apanhar segundo dardo */
        		's',  's', 's', 'd', 'd'
        	};

        Maze m = new StaticMaze();

        Point dartPosition1 = new Point(4, 3);
        Point dartPosition2 = new Point(4, 8);
        Point dragonPosition = new Point(6, 4);

        GameState.initializeStatic(m);
        GameState.setDragonMovement(GameState.DRAGON_STATIC_NOSLEEP);
        GameState.placeDart(dartPosition1);
        GameState.placeDart(dartPosition2);
        GameState.placeDragon(dragonPosition);

        assertFalse(GameState.attackDarts(Direction.RIGHT));

        movePlayer(playerMoves1);

        Hero p = GameState.getPlayer();

        assertTrue(p.hasDarts());
        assertFalse(GameState.attackDarts(Direction.DOWN));
        assertFalse(p.hasDarts());

        movePlayer(playerMoves2);
        
        assertTrue(p.hasDarts());
        assertTrue(GameState.attackDarts(Direction.UP));
        assertEquals(GameState.getDragon().getHealth(), 0);
        assertFalse(p.hasDarts());
    }
}