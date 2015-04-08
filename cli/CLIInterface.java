package lpoo.cli;

import java.io.IOException;
import java.util.Scanner;
import lpoo.logic.Direction;
import lpoo.logic.GameState;

public class CLIInterface
{
	private static final Scanner in = new Scanner(System.in);

	private static void selectMaze()
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

		GameState.setDifficulty(readInteger(1, 5));
	}

	private static void showObjectives()
	{
		final String currentObjective = String.format("%-36s", GameState.getObjective());
		final String numberDragons = String.format("%-38d", GameState.getNumberDragons());
		final String numberDarts = String.format("%-40d", GameState.getNumberDarts());

		System.out.println("");
		System.out.println("====================================================");
		System.out.println("= OBJECTIVE : " + currentObjective + " =");
		System.out.println("= DRAGONS : " + numberDragons + " =");
		System.out.println("= DARTS : " + numberDarts + " =");
		System.out.println("====================================================");
		System.out.println("");
	}

	private static void selectDragon()
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

		GameState.setDragonMovement(readInteger(1, 4));
	}

	private static char readChar()
	{
		System.out.print("\nPlease enter a movement key (W, S, A, D) : ");

		return in.next().charAt(0);
	}

	private static int readInteger(int i, int j)
	{
		int userInput;

		do
		{
			System.out.print("Please enter an option: ");
			userInput = in.nextInt();
		} while (userInput < i || userInput > j);

		return userInput;
	}

	public static void main(String[] args) throws IOException
	{
		selectMaze();
		selectDragon();

		while (!GameState.gameOver())
		{
			boolean dartFailed = false;
			boolean dartThrown = false;

			showObjectives();
			GameState.printMaze();

			switch (readChar())
			{
			case 'W': case 'w':
				GameState.update(Direction.UP);
				dartThrown = false;
				break;
			case 'S': case 's':
				GameState.update(Direction.DOWN);
				dartThrown = false;
				break;
			case 'A': case 'a':
				GameState.update(Direction.LEFT);
				dartThrown = false;
				break;
			case 'D': case 'd':
				GameState.update(Direction.RIGHT);
				dartThrown = false;
				break;
			case '8':
				dartFailed = !GameState.attackDarts(Direction.UP);
				dartThrown = true;
				GameState.update(Direction.NONE);
				break;
			case '2':
				dartFailed = !GameState.attackDarts(Direction.DOWN);
				dartThrown = true;
				GameState.update(Direction.NONE);
				break;
			case '4':
				dartFailed = !GameState.attackDarts(Direction.LEFT);
				dartThrown = true;
				GameState.update(Direction.NONE);
				break;
			case '6':
				dartFailed = !GameState.attackDarts(Direction.RIGHT);
				dartThrown = true;
				GameState.update(Direction.NONE);
				break;
			}

			if (dartThrown)
			{
				displayMessage(dartFailed ? "MISSED TARGET" : "ATTACK SUCCESSFUL!");
			}
		}

		GameState.printMaze();
		displayGameOver();
	}

	private static void displayMessage(String msg) throws IOException
	{
		final String formattedMessage = String.format("%s-28", msg);

		System.out.println("");
		System.out.println("********************************");
		System.out.println("* " + formattedMessage + " *");
		System.out.println("********************************");
		System.in.read();
	}

	private static void displayGameOver()
	{
		if (GameState.gameOver())
		{
			if (GameState.playerWon())
			{
				System.out.println("Congratulations, you have reached the exit safe and sound :)");
			}
			else
			{
				System.out.println("You were killed by the dragon :(");
			}
		}
	}
}