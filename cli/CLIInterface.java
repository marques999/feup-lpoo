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

package lpoo.cli;

import java.util.Scanner;
import lpoo.logic.GameState;
import lpoo.logic.Direction;
import lpoo.logic.RandomMaze;
import lpoo.logic.StaticMaze;

public class CLIInterface
{
	private static Scanner in = new Scanner(System.in);

	// ------------------------------
	// | INPUT METHODS |
	// ------------------------------

	private static void selectMaze()
	{
		System.out.println("-----------------------------------");
		System.out.println("              MAZE RUN             ");
		System.out.println("-----------------------------------");
		System.out.println("");
		System.out.println("1. Practice (static, 11x11, 1D)");
		System.out.println("2. Easy (random, 11x11, 1D)");
		System.out.println("3. Medium (random, 15x15, 2D)");
		System.out.println("4. Hard (random, 23x23, 4D)");
		System.out.println("5. Nightmare (random, 31x31, 8D)");
		System.out.println("");

		int userInput;

		do
		{
			System.out.print("Select maze difficulty: ");
			userInput = in.nextInt();
		} while (userInput < 1 || userInput > 5);

		switch (userInput)
		{
		case 1:
			GameState.initialize(new StaticMaze());
			GameState.initializeDragons(1);
			break;
		case 2:
			GameState.initialize(new RandomMaze(11));
			GameState.initializeDragons(1);
			break;
		case 3:
			GameState.initialize(new RandomMaze(15));
			GameState.initializeDragons(2);
			break;
		case 4:
			GameState.initialize(new RandomMaze(23));
			GameState.initializeDragons(4);
			break;
		case 5:
			GameState.initialize(new RandomMaze(31));
			GameState.initializeDragons(8);
			break;
		}

	}

	private static void selectDragon()
	{
		System.out.println("");
		System.out.println("1. Easy (static, never sleeps)");
		System.out.println("2. Medium (random)");
		System.out.println("3. Hard (random, never sleeps)");
		System.out.println("");

		int userInput;

		do
		{
			System.out.print("Select dragon difficulty: ");
			userInput = in.nextInt();
		} while (userInput < 1 || userInput > 3);

		switch (userInput)
		{
		case 1:
			GameState.setDragonMovement(-1);
			break;
		case 2:
			GameState.setDragonMovement(1);
			break;
		case 3:
			GameState.setDragonMovement(0);
			break;
		}
	}

	
	private static final char readChar()
	{
		return in.next().charAt(0);
	}

	// ------------------------------
	// | MAZE METHODS |
	// ------------------------------

	public static void main(String[] args)
	{
		selectMaze();
		selectDragon();

		while (!GameState.gameOver())
		{
			GameState.showObjectives();
			GameState.printMaze();

			System.out.print("Enter a key to move the character : ");

			char input = readChar();

			switch (input)
			{
			case 'W':
			case 'w':
				GameState.update(Direction.UP);
				break;
			case 'S':
			case 's':
				GameState.update(Direction.DOWN);
				break;
			case 'A':
			case 'a':
				GameState.update(Direction.LEFT);
				break;
			case 'D':
			case 'd':
				GameState.update(Direction.RIGHT);
				break;
			}
		}

		GameState.printMaze();
		displayMessage();
	}

	private static void displayMessage()
	{
		if (GameState.gameOver())
		{
			if (GameState.playerWon())
			{
				System.out.println("CONGRATULATIONS!");
			}
			else
			{
				System.out.println("GAME OVER :( R.I.P HERO");
			}
		}

	}
}
