package lpoo.test;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Random;
import lpoo.logic.GameState;
import lpoo.logic.Item;
import lpoo.logic.Maze;
import lpoo.logic.Point;
import lpoo.logic.RandomMaze;

public
        class TestMaze
{

    // a) the maze boundaries must have exactly one exit and everything else walls
    // b) the exist cannot be a corner
    private static
            boolean checkBoundaries(Maze m)
    {
        int countExit = 0;
        int n = m.getWidth();

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (i == 0 || j == 0 || i == n - 1 || j == n - 1)
                {
                    if (m.symbolAt(i, j) == 'S')
                    {
                        if ((i == 0 || i == n - 1) && (j == 0 || j == n - 1))
                        {
                            return false;
                        }
                        else
                        {
                            countExit++;
                        }
                    }
                    else if (m.symbolAt(i, j) != 'X')
                    {
                        return false;
                    }
                }
            }
        }
        return countExit == 1;
    }

    public static boolean testSquares(Maze m)
    {
        char[][] badWalls =
        {
            {
                'X', 'X', 'X'
            },
            {
                'X', 'X', 'X'
            },
            {
                'X', 'X', 'X'
            }
        };

        char[][] badSpaces =
        {
            {
                ' ', ' '
            },
            {
                ' ', ' '
            }
        };

        char[][] badDiag1 =
        {
            {
                'X', ' '
            },
            {
                ' ', 'X'
            }
        };

        char[][] badDiag2 =
        {
            {
                ' ', 'X'
            },
            {
                'X', ' '
            }
        };
        
        return !hasSquare(m, badWalls) && !hasSquare(m, badSpaces) && !hasSquare(m, badDiag1) && !hasSquare(m, badDiag2);
            
    }
    // d) there cannot exist 2x2 (or greater) squares with blanks only 
    // e) there cannot exit 2x2 (or greater) squares with blanks in one diagonal and walls in the other
    // d) there cannot exist 3x3 (or greater) squares with walls only
    private static boolean hasSquare(Maze maze, char[][] square)
    {
        char[][] m = maze.getMatrix();

        for (int i = 0; i < m.length - square.length; i++)
        {
            for (int j = 0; j < m.length - square.length; j++)
            {
                boolean match = true;
                for (int x = 0; x < square.length; x++)
                {
                    for (int y = 0; y < square.length; y++)
                    {
                        if (m[i + x][j + y] != square[x][y])
                        {
                            match = false;
                        }
                    }
                }
                if (match)
                {
                    return true;
                }
            }
        }
        return false;
    }

    // c) there must exist a path between any blank cell and the maze exit 
    public static boolean testExitReachable(Maze maze)
    {
        Point p = maze.getExitPosition();

        char[][] m = deepClone(maze.getMatrix());

        visit(m, p.x, p.y);

        for (char[] m1 : m)
        {
            for (char m2 : m1)
            {
                if (m2 != 'X' && m2 != 'V')
                {
                    return false;
                }
            }
        }

        return true;
    }

    // auxiliary method used by checkExitReachable
    // marks a cell as visited (V) and proceeds recursively to its neighbors
    private static
            void visit(char[][] m, int i, int j)
    {
        if (i < 0 || i >= m.length || j < 0 || j >= m.length)
        {
            return;
        }
        if (m[i][j] == 'X' || m[i][j] == 'V')
        {
            return;
        }
        m[i][j] = 'V';
        visit(m, i - 1, j);
        visit(m, i + 1, j);
        visit(m, i, j - 1);
        visit(m, i, j + 1);
    }

    // Auxiliary method used by checkExitReachable.
    // Gets a deep clone of a char matrix.
    private static
            char[][] deepClone(char[][] m)
    {
        char[][] c = m.clone();

        for (int i = 0; i < m.length; i++)
        {
            c[i] = m[i].clone();
        }

        return c;
    }

    // Checks if all the arguments (in the variable arguments list) are not null and distinct
    private
            <T> boolean notNullAndDistinct(T... args)
    {
        for (int i = 0; i < args.length - 1; i++)
        {
            for (int j = i + 1; j < args.length; j++)
            {
                if (args[i] == null || args[j] == null || args[i].equals(args[j]))
                {
                    return false;
                }
            }
        }

        return true;
    }

    @Test
    public void testPlaceEntity() 
    {
		GameState.setDifficulty(2);
		GameState.setDragonMovement(GameState.DRAGON_STATIC_NOSLEEP);
		GameState.setDifficulty(2);
        	
		assertNotNull(GameState.getDart());
		assertNotNull(GameState.getShield());
		assertNotNull(GameState.getSword());
		assertNotNull(GameState.getDragon());
        assertNotNull(GameState.getPlayer());
    }
    
    @Test
    public
            void testRandomMazeGenerator() throws Exception
    {
        int numMazes = 1000;
        int maxSize = 101;

        char[][] badWalls =
        {
            {
                'X', 'X', 'X'
            },
            {
                'X', 'X', 'X'
            },
            {
                'X', 'X', 'X'
            }
        };

        char[][] badSpaces =
        {
            {
                ' ', ' '
            },
            {
                ' ', ' '
            }
        };

        char[][] badDiag1 =
        {
            {
                'X', ' '
            },
            {
                ' ', 'X'
            }
        };

        char[][] badDiag2 =
        {
            {
                ' ', 'X'
            },
            {
                'X', ' '
            }
        };

        Random rand = new Random();

        for (int i = 0; i < numMazes; i++)
        {
            int size = maxSize == 5 ? 5 : 5 + 2 * rand.nextInt((maxSize - 5) / 2);

            Maze m = new RandomMaze(size, size);

            assertTrue("Invalid maze boundaries in maze:\n" + m, checkBoundaries(m));
            assertNotNull("Invalid walls in maze:\n" + m, !hasSquare(m, badWalls));
            assertNotNull("Invalid spaces in maze:\n" + m, !hasSquare(m, badSpaces));
            assertNotNull("Invalid diagonals in maze:\n" + m, !hasSquare(m, badDiag1));
            assertNotNull("Invalid diagonals in maze:\n" + m, !hasSquare(m, badDiag2));
        }
    }
}
