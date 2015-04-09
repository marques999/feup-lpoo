package lpoo.test;

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

public class TestMove
{
    @Test
    public void test_validMoves()
    {
        Maze m = new StaticMaze();

        GameState.initializeStatic(m);
        GameState.setDragonMovement(-1);

        Hero p = GameState.getPlayer();

        Point initialPosition = new Point(1, 1);
        Point newPosition = new Point(2, 1);

        assertTrue(initialPosition.equals(p.getPosition()));

        GameState.update(Direction.RIGHT);

        assertTrue(newPosition.equals(p.getPosition()));
    }

    @Test
    public void test_invalidMoves()
    {
        Maze m = new StaticMaze();

        GameState.initializeStatic(m);
        GameState.setDragonMovement(-1);

        Point initialPosition = new Point(1, 1);
        Hero p = GameState.getPlayer();
        
        assertTrue(initialPosition.equals(p.getPosition()));
        
        GameState.update(Direction.UP);
        GameState.update(Direction.UP);
        GameState.update(Direction.UP);
                
        assertTrue(initialPosition.equals(p.getPosition()));
        
        GameState.update(Direction.LEFT);
        GameState.update(Direction.LEFT);
        
        assertTrue(initialPosition.equals(p.getPosition()));
    }
    
    @Test
    public void test_mixedMoves()
    {
        Maze m = new StaticMaze();

        GameState.initializeStatic(m);
        GameState.setDragonMovement(-1);

        Point initialPosition = new Point(1, 1);
        Hero p = GameState.getPlayer();
        
        assertEquals(initialPosition, p.getPosition());
        
        GameState.update(Direction.DOWN);
        
        Point secondPosition = new Point(1, 2);
        
        GameState.update(Direction.LEFT);
        GameState.update(Direction.RIGHT);
        GameState.update(Direction.LEFT);
        
        assertEquals(secondPosition, p.getPosition());
        
        GameState.update(Direction.DOWN);
        GameState.update(Direction.DOWN);
        GameState.update(Direction.LEFT);
        
        Point thirdPosition = new Point(1, 4);
        
        assertEquals(thirdPosition, p.getPosition());
    }
}