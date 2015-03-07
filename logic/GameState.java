package lpoo.logic;

import java.util.Random;

public class GameState
{
	private static Maze maze;
	private static Hero player;
	private static Sword sword;
	private static Dragon[] dragons;

	private static boolean gameOver = false;
	private static boolean playerWon = false;
	private static int dragonMovement = 1;

	private static Random randomGenerator = new Random();

	public static void setDragonMovement(int type)
	{
		dragonMovement = type;
	}
	
	public static void initializeDragons(int numberDragons)
	{
		dragons = new Dragon[numberDragons];
		
		for (int i = 0; i < numberDragons; i++)
		{
			Point dragonPosition = maze.placeEntity('D');
			dragons[i] = new Dragon(dragonPosition.x, dragonPosition.y);
			dragons[i].draw(maze);
		}
	}
	
	private static void updateDragons()
	{
		for (int i = 0; i < dragons.length; i++)
		{
			updateDragon(dragons[i]);
		}
	}
	
	private static void drawDragons()
	{
		for (int i = 0; i < dragons.length; i++)
		{
			dragons[i].draw(maze);
		}
	}
	
	private static void attackDragons()
	{
		for (int i = 0; i < dragons.length; i++)
		{
			player.attackDragon(maze, dragons[i]);
			dragons[i].attackPlayer(maze, player);
		}
	}
	
	private static void updateDragon(Dragon dragon)
	{
		if (dragon.getHealth() <= 0)
		{
			return;
		}

		if (dragonMovement == -1)
		{
			return;
		}

		if (dragonMovement == 1)
		{
			int randomSleep = randomGenerator.nextInt(6);

			if (randomSleep == 1)
			{
				dragon.toggleSleep();
			}

			if (dragon.isSleeping())
			{
				return;
			}
		}

		Direction moveDirection = Direction.NONE;

		while (!dragon.validMove(maze, moveDirection))
		{
			int randomMove = randomGenerator.nextInt(4);

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
			case 3:
			default:
				moveDirection = Direction.RIGHT;
				break;
			}
		}

		dragon.move(maze, moveDirection);
	}

	public static void initialize(Maze m)
	{
		maze = m;

		Point playerPosition = maze.placeEntity('H');
		Point swordPosition = maze.placeEntity('E');

		player = new Hero(playerPosition.x, playerPosition.y);
		player.draw(maze);

		sword = new Sword(swordPosition.x, swordPosition.y);
		sword.draw(maze);
	}

	public static void printMaze()
	{
		maze.printMaze();
	}

	public static boolean gameOver()
	{
		return gameOver;
	}

	public static boolean playerWon()
	{
		return playerWon;
	}

	public static final void showObjectives()
	{
		if (!player.hasSword())
		{
			System.out.println("OBJECTIVE: Pick up the sword!");
		}
//		} else if (dragon.getHealth() > 0)
//		{
//			System.out.println("OBJECTIVE: Kill the dragon!");
//		} 
	else
		{
			System.out.println("OBJECTIVE: Find the exit!");
		}
	}

	public static void update(Direction direction)
	{
		player.move(maze, direction);
		player.draw(maze);
		updateDragons();
		drawDragons();

		attackDragons();
		
		if (!player.hasSword())
		{
			sword.draw(maze);
		}

		if (player.getHealth() <= 0)
		{
			gameOver = true;
			playerWon = false;
		}

		if (player.hasEscaped())
		{
			gameOver = true;
			playerWon = true;
		}
	}
}
