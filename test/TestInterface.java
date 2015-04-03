package lpoo.test;

import junit.framework.TestCase;
import lpoo.logic.*;
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
    public void test_3a_killedByDragon()
    {
        Maze m = new StaticMaze();
        Point dragonPosition = new Point(1, 3);

        GameState.initializeStatic(m);
        GameState.setDragonMovement(-1);
        GameState.placeDragon(dragonPosition);
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
            'd', 'd', 'd', 's', 's', 's', 's', 'a', 'a', 'a', 's', 's', 's', 'w', 'w', 'w', 'w'
        };

        Maze m = new StaticMaze();
        Point dragonPosition = new Point(1, 3);

        GameState.initializeStatic(m);
        GameState.setDragonMovement(-1);
        GameState.placeDragon(dragonPosition);

        Dragon d = GameState.getDragon();
        
        assertEquals(d.getHealth(), 100);
        
        movePlayer(playerMoves);

        d = GameState.getDragon();

        assertNull(d);
    }

    
    @Test
    public void test_6b_attackDarts()
    {
        char playerMoves1[] =
        {
            'd', 'd', 'd', 's', 's', 's',
        };

        Maze m = new StaticMaze();

        Point dartPosition1 = new Point(4, 4);
        Point dartPosition2 = new Point(3, 5);
        Point dragonPosition = new Point(8, 8);

        GameState.initializeStatic(m);
        GameState.setDragonMovement(-1);

        GameState.placeDart(dartPosition1);
        GameState.placeDart(dartPosition2);
        GameState.placeDragon(dragonPosition);

        assertFalse(GameState.attackDarts(Direction.RIGHT));

        movePlayer(playerMoves1);

        Hero p = GameState.getPlayer();

        assertTrue(p.hasDarts());

        assertFalse(GameState.attackDarts(Direction.UP));
        assertFalse(p.hasDarts());

        GameState.update(Direction.DOWN);
        GameState.update(Direction.LEFT);

        p = GameState.getPlayer();

        assertEquals(p.getPosition(), dartPosition2);
        assertTrue(p.hasDarts());

        char playerMoves2[] =
        {
            'd', 's', 's', 's'
        };

        movePlayer(playerMoves2);

        p = GameState.getPlayer();

        assertTrue(GameState.attackDarts(Direction.RIGHT));
        assertEquals(GameState.getDragon().getHealth(), 0);
        assertFalse(p.hasDarts());
    }
}
