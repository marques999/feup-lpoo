package lpoo.test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.*;
import lpoo.logic.*;
import static lpoo.test.TestInterface.moveDragon;
import org.junit.Test;

public class TestDragon
{

    @Test(timeout = 1000)
    public void test_dragonMoveRandom()
    {
        char[][] m1 =
        {
            {
                'X', 'X', 'X', 'X', 'X'
            },
            {
                'X', 'h', ' ', ' ', 'X'
            },
            {
                'X', ' ', 'X', ' ', 'S'
            },
            {
                'X', ' ', ' ', 'D', 'X'
            },
            {
                'X', 'X', 'X', 'X', 'X'
            }
        };

        Maze maze = new Maze(5, 5);
        maze.setMatrix(m1);

        boolean movementSuccessful = false;

        for (int i = 0; i < 1000; i++)
        {
            GameState.initializeCustom(maze);
            GameState.setDragonMovement(1);
            GameState.updateDragons();

            if (GameState.getDragon().getPosition().equals(new Point(3, 2)))
            {
                movementSuccessful = true;
            }

            if (GameState.getDragon().getPosition().equals(new Point(2, 3)))
            {
                movementSuccessful = true;
            }

            assertTrue(movementSuccessful);
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
    public void test_dragonMove()
    {
        char dragonMoves[] =
        {
            'a', 'd', 'a', 'a', 'w', 'w', 'w', 'd', 'w',
            'd', 's', 's','s', 's', 'd', 's', 's', 's'
        };

        Maze m = new StaticMaze();

        Point dragonPosition = new Point(6, 4);
        Point finalPosition = new Point(8, 8);

        GameState.initializeStatic(m);
        GameState.setDragonMovement(-1);

        Dragon d = GameState.placeDragon(dragonPosition);

        assertEquals(d.getPosition(), dragonPosition);

        moveDragon(d, dragonMoves);

        assertEquals(d.getPosition(), finalPosition);
    }

    @Test(timeout = 1000)
    public void test_dragonSleep()
    {
        char[][] m1 =
        {
            {
                'X', 'X', 'X', 'X', 'X'
            },
            {
                'X', 'h', ' ', ' ', 'X'
            },
            {
                'X', ' ', 'X', ' ', 'S'
            },
            {
                'X', ' ', ' ', 'D', 'X'
            },
            {
                'X', 'X', 'X', 'X', 'X'
            }
        };

        Maze maze = new Maze(5, 5);
        maze.setMatrix(m1);
        GameState.initializeCustom(maze);
        GameState.setDragonMovement(1);

        for (int i = 0; i < 10; i++)
        {
            boolean hasSlept = false;

            for (int j = 0; j < 100; j++)
            {
                GameState.updateDragons();

                if (GameState.getDragon().isSleeping())
                {
                    hasSlept = true;
                }

                assertTrue(hasSlept);
            }
        }
    }
}