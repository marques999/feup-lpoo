/*!
 * \file Board.java
 *
 * LPOO_2014-2015_P1
 * \author Diogo Marques
 * \author Pedro Melo
 *
 * \date March 2015
 *
 */

package lpoo;

import java.util.Random;
import java.util.Scanner;

enum Direction 
{
	UP, DOWN, LEFT, RIGHT, NONE
};

public class Board 
{
	private static Random rnd = new Random();
	private static Scanner in = new Scanner(System.in);

	public static final int NUM_COLUNAS = 31;
	public static final int NUM_LINHAS = 31;
	
	private static Hero player;
	private static Dragon dragon;
	private static Sword sword;

	public static char[][] m = new char[][]
	{
		{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
		{ 'X', 'H', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
		{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
		{ 'X', 'D', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
		{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
		{ 'X', ' ', ' ', ' ', 'E', ' ', ' ', 'X', ' ', 'S' },
		{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
		{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
		{ 'X', ' ', 'X', 'X', ' ', ' ', ' ', ' ', ' ', 'X' },
		{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
	};

	// ------------------------------
	// |	SYMBOL METHODS		 	|
	// ------------------------------
	
	public static void clearSymbol(int x, int y) 
	{
		if (x >= 0 && x < NUM_COLUNAS && y >= 0 && y < NUM_LINHAS)
		{
			m[y][x] = ' ';
		}
	}
	
	public static void placeSymbol(int x, int y, char s)
	{
		if (x >= 0 && x < NUM_COLUNAS && y >= 0 && y < NUM_LINHAS)
		{
			m[y][x] = s;
		}
	}
	
	// ------------------------------
	// |	   INPUT METHODS		|
	// ------------------------------
	
	private static final char readChar() 
	{
		String getLine = in.next();

		return getLine.charAt(0);
	}

	private static final Direction getDirection(char input) 
	{
		switch (input) 
		{
		case 'W': case 'w':
			return Direction.UP;
		case 'S': case 's':
			return Direction.DOWN;
		case 'A': case 'a':
			return Direction.LEFT;
		case 'D': case 'd':
			return Direction.RIGHT;
		}

		return Direction.NONE;
	}

	private static final void moveDragon(Dragon dragon) 
	{
		Direction moveDirection = Direction.NONE;
		
		if (dragon.getHealth() <= 0)
		{
			return;
		}

		while (dragon.validMove(moveDirection) == false)
		{
			int randomMove = rnd.nextInt(4);
			
			switch (randomMove) 
			{
			case 0:
				moveDirection = Direction.UP;
				break;
			case 1:
				moveDirection = Direction.DOWN;
				break;
			case 2:
				moveDirection = Direction.LEFT;
				break;
			case 3: default:
				moveDirection = Direction.RIGHT;
				break;
			}
		}
		
		dragon.move(moveDirection);
	}	
	
	// ------------------------------
	// |	   MAZE  METHODS	 	|
	// ------------------------------
	
	public static final void printMaze() 
	{
		for (int y = 0; y < NUM_LINHAS; y++) 
		{
			for (int x = 0; x < NUM_COLUNAS; x++) 
			{
				System.out.print(m[y][x] + " ");
			}

			System.out.println("");
		}
	}
	
	private static final Point placeEntity(char symbol)
	{
		int initialX = 0;
		int initialY = 0;
				
		while (m[initialY][initialX] != ' ')
		{
			initialX = 1 + rnd.nextInt(NUM_COLUNAS - 2);
			initialY = 1 + rnd.nextInt(NUM_LINHAS - 2);
		}
				
		return new Point(initialX, initialY);
	}
	
	private static void initializeMaze(int n)
	{
		MazeGenerator maze = new MazeGenerator(NUM_LINHAS);
		
		m = maze.getMatrix();
		
		Point playerPosition = placeEntity('H');
		Point dragonPosition = placeEntity('D');
		Point swordPosition = placeEntity('E');
		
		player = new Hero(playerPosition.getX(), playerPosition.getY());
		player.draw();
		
		dragon = new Dragon(dragonPosition.getX(), dragonPosition.getY());
		dragon.draw();
		
		sword = new Sword(swordPosition.getX(), swordPosition.getY());
		sword.draw();
	}

	public static void main(String[] args)
	{
		initializeMaze(11);
		
		boolean gameOver = false;

		while (!player.hasFinished()) 
		{
			printMaze();
			System.out.print("Enter a key to move the character : ");
			
			char input = readChar();

			player.makeMove(getDirection(input));
			player.draw();
			
			player.attackDragon(dragon);
			dragon.attackPlayer(player);
			
			if (player.getHealth() <= 0)
			{
				gameOver = true;
				break;
			}
			
			moveDragon(dragon);
		}

		printMaze();
		
		if (gameOver)
		{
			System.out.println("GAME OVER :( R.I.P HERO");
		}
		else
		{
			System.out.println("CONGRATULATIONS!");
		}
		
	}
}
