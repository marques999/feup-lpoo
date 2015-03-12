/*!
 * \file CLIInterface.java
 *
 * LPOO_2014-2015_P1
 * \author Diogo Marques
 * \author Pedro Melo
 *
 * \date March 2015
 *
 */

package lpoo.cli;

import java.io.IOException;
import java.util.Scanner;

import lpoo.logic.Direction;
import lpoo.logic.GameState;
import lpoo.logic.RandomMaze;
import lpoo.logic.StaticMaze;

public class CLIInterface
{
	private static Scanner in = new Scanner(System.in);

	// ------------------------------
	// | INPUT METHODS |
	// ------------------------------

	private static final void selectMaze()
	{
		System.out.println("+ - \\ | / - \\ | / - \\ | / - \\ | / - \\ | / - \\ | / - \\ | / - \\ | / - +");  
		System.out.println("|                                                                   |");
		System.out.println("/                         M A Z E    R U N                          \\");
		System.out.println("-                                                                    -");
		System.out.println("\\                                                                   /");
		System.out.println("|     Select difficulty:                                            |");
		System.out.println("/                                                                   \\");
		System.out.println("-     1. Practice (static, 11x11, one dragon)                        -");
		System.out.println("\\     2. Easy (random, 11x11, two dragons)                          /");
		System.out.println("|     3. Medium (random, 15x15, three dragons)                      |");
		System.out.println("/     4. Hard (random, 23x23, six dragons)                          \\");
		System.out.println("-     5. Nightmare (random, 31x31, twelve dragons)                   -");
		System.out.println("\\                                                                   /");
		System.out.println("|                                                                   |");
		System.out.println("+ - / | \\ - / | \\ - / | \\ - / | \\ - / | \\ - / | \\ - / | \\ - / | \\ - +");
		System.out.println("");

		int userInput;

		do
		{
			System.out.print("Please enter an option: ");
			userInput = in.nextInt();
		} while (userInput < 1 || userInput > 5);

		switch (userInput)
		{
		case 1:
			GameState.initialize(new StaticMaze());
			GameState.initializeDarts(1);
			GameState.initializeDragons(1);
			break;
		case 2:
			GameState.initialize(new RandomMaze(11));
			GameState.initializeDarts(2);
			GameState.initializeDragons(2);
			break;
		case 3:
			GameState.initialize(new RandomMaze(15));
			GameState.initializeDarts(4);
			GameState.initializeDragons(3);
			break;
		case 4:
			GameState.initialize(new RandomMaze(23));
			GameState.initializeDarts(8);
			GameState.initializeDragons(6);
			break;
		case 5:
			GameState.initialize(new RandomMaze(31));
			GameState.initializeDarts(16);
			GameState.initializeDragons(12);
			break;
		}

	}
	
	private static final void showObjectives()
	{
		String currentObjective = String.format("%-24s", GameState.getObjective());
		String numberDragons = String.format("%4d", GameState.getNumberDragons());
		System.out.println("=====================================================");
		System.out.println("= OBJECTIVE: " + currentObjective + " DRAGONS: " + numberDragons + " =");
		System.out.println("=====================================================");
	}

	private static final void selectDragon()
	{
		System.out.println("");
		System.out.println("+ - \\ | / - \\ | / - \\ | / - \\ | / - \\ | / - \\ | / - \\ | / - \\ | / - +");  
		System.out.println("|                                                                   |");
		System.out.println("/                         M A Z E    R U N                          \\");
		System.out.println("-                                                                    -");
		System.out.println("\\                                                                   /");
		System.out.println("|     Select dragon difficulty:                                     |");
		System.out.println("/                                                                   \\");
		System.out.println("-     1. Beginner (static, sleeps randomly)                          -");
		System.out.println("\\     2. Easy (static, never sleeps)                                /");
		System.out.println("|     3. Medium (random, sleeps randomly)                           |");
		System.out.println("/     4. Hard (random, 23x23, never sleeps)                         \\");
		System.out.println("-                                                                    -");
		System.out.println("\\                                                                   /");
		System.out.println("|                                                                   |");
		System.out.println("+ - / | \\ - / | \\ - / | \\ - / | \\ - / | \\ - / | \\ - / | \\ - / | \\ - +");
		System.out.println("");

		int userInput;

		do
		{
			System.out.print("Please enter an option: ");
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

	public static void main(String[] args) throws IOException
	{
		selectMaze();
		selectDragon();

		boolean dartFailed;
		
		while (!GameState.gameOver())
		{
			dartFailed = false;
			showObjectives();
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
			case '8':
				dartFailed = !GameState.attackDarts(Direction.UP);
				GameState.update(Direction.NONE);
				break;
			case '2':
				dartFailed = !GameState.attackDarts(Direction.DOWN);
				GameState.update(Direction.NONE);
				break;
			case '4':
				dartFailed = !GameState.attackDarts(Direction.LEFT);
				GameState.update(Direction.NONE);
				break;
			case '6':
				dartFailed = !GameState.attackDarts(Direction.RIGHT);
				GameState.update(Direction.NONE);
				break;
			}
			
			if (dartFailed)
			{
				System.out.println("");
				System.out.println("*******************************");
				System.out.println("*        MISSED TARGET        *");
				System.out.println("*******************************");
				System.out.println("");
				System.in.read();
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
				System.out.println("--- GAME OVER --- R.I.P HERO :(");
			}
		}
	}
}
