package lpoo.logic;

import java.util.Random;

public class GameState
{
	private static Maze maze;
	private static Hero player;
	private static Sword sword;
	private static Dart[] darts;
	private static Dragon[] dragons;

	private static boolean gameOver = false;
	private static int dragonsDefeated = 0;
	private static boolean playerWon = false;
	private static int dragonMovement = 1;

	private static Random randomGenerator = new Random();

	public static void setDragonMovement(int type)
	{
		dragonMovement = type;
	}
	
	public static boolean attackDarts(Direction direction)
	{
		return player.attackDarts(maze, direction);
	}
	
	protected static Dragon dragonAt(int x, int y)
	{
		for (Dragon dragon : dragons)
		{
			if (dragon.getX() == x && dragon.getY() == y)
			{
				return dragon;
			}
		}
		
		return null;
	}
	
	public static Item itemAt(Point pos)
	{
		if (sword.getPosition().equals(pos))
		{
			return sword;
		}
		
		for (Dart dart : darts)
		{
			if (dart.getPosition().equals(pos))
			{
				return dart;
			}
		}
		
		return null;
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
	
	private static void drawItems(Item[] items)
	{
		for (Item item : items)
		{
			if (item != null)
			{
				item.draw(maze);
			}
		}
	}
	
	public static void initializeDarts(int numberDarts)
	{
		darts = new Dart[numberDarts];
		
		for (int i = 0; i < numberDarts; i++)
		{
			Point dartPosition = maze.placeEntity('*');
			
			darts[i] = new Dart(dartPosition.x, dartPosition.y);
			darts[i].draw(maze);
		}
	}
	
	private static void updateDragons()
	{	
		dragonsDefeated = 0;
		
		for (Dragon dragon : dragons)
		{
			updateDragon(dragon);
			
			if (dragon.getHealth() <= 0)
			{
				dragonsDefeated++;
			}
		}
	}
	
	private final static void drawDragons()
	{
		for (Dragon dragon : dragons)
		{
			dragon.draw(maze);
		}
	}
	
	private static void attackDragons()
	{
		for (Dragon dragon : dragons)
		{
			player.attackSword(maze, dragon);
			dragon.attackPlayer(maze, player);
		}
	}
	
	private static final void updateDragon(Dragon dragon)
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
			case 3: default:
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

	public static final void printMaze()
	{
		maze.printMaze();
	}

	public static final boolean gameOver()
	{
		return gameOver;
	}

	public static final boolean playerWon()
	{
		return playerWon;
	}

	public static final String getObjective()
	{
		if (!player.hasSword() && !player.hasDarts())
		{
			return ("Pick up a weapon (darts or sword)!");
		} 
		
		if (getNumberDragons() > 0)
		{
			return("Kill the dragons!");
		} 
				
		return("Find the exit!");
	}
	
	public static final int getNumberDragons()
	{
		return dragons.length - dragonsDefeated;
	}

	public static void update(Direction direction)
	{
		player.move(maze, direction);
		
		updateDragons();
		attackDragons();
		sword.draw(maze);
		drawItems(darts);
		player.draw(maze);
		drawDragons();
		
		if (!player.hasSword())
		{
			sword.draw(maze);
		}

		if (player.getHealth() <= 0)
		{
			gameOver = true;
			playerWon = false;
		}

		if (getNumberDragons() == 0 && maze.isWall(player.pos))
		{
			gameOver = true;
			playerWon = true;
		}
	}
}
