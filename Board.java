package lpoo;

import java.util.Random;
import java.util.Scanner;

enum Direction {
	UP, DOWN, LEFT, RIGHT, NONE
};

public class Board 
{
	private static Random rnd = new Random();
	private static Scanner in = new Scanner(System.in);

	public static final int NUM_COLUNAS = 10;
	public static final int NUM_LINHAS = 10;

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

	public static final void print() 
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

	public static void clear(int x, int y) 
	{
		if (x >= 0 && x < NUM_COLUNAS && y >= 0 && y < NUM_LINHAS)
		{
			m[y][x] = ' ';
		}
	}
	
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

	private static void moveDragon(Dragon dragon) 
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

	public static void main(String[] args)
	{
		Hero player1 = new Hero(1, 1);
		Dragon dragon1 = new Dragon(1, 3);
		Sword sword1 = new Sword(5, 4);
		
		boolean gameOver = false;;

		while (!player1.hasFinished()) 
		{
			print();
			System.out.print("Enter a key to move the character : ");
			
			char input = readChar();

			player1.makeMove(getDirection(input));
			player1.draw();
			
			player1.attackDragon(dragon1);
			dragon1.attackPlayer(player1);
			
			if (player1.getHealth() <= 0)
			{
				gameOver = true;
				break;
			}
			
			moveDragon(dragon1);
		}

		print();
		
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
