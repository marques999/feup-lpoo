package lpoo.logic;

import java.util.LinkedList;
import java.util.Random;

public class GameState 
{
	private static Maze maze;
	private static Hero player;
	
	private static LinkedList<Item> items;
	private static LinkedList<Dragon> dragons;

	private static boolean gameOver = false;
	private static boolean playerWon = false;
	private static int dragonMovement = 1;

	private static Random randomGenerator = new Random();

	public static void setDragonMovement(int type) 
	{
		dragonMovement = type;
	}

	// -------------------------------
	// | 	  	 JUNIT TESTS		 |
	// -------------------------------
	
	public static Hero getPlayer() 
	{
		return player;
	}

	public static Dragon getDragon() 
	{
		return dragons.peek();
	}

	public static Item getSword() 
	{
		for (Item item : items)
		{
			if (item instanceof Sword)
			{
				return item;
			}
		}
		
		return null;
	}
	
	protected static void removeItem(Item item)
	{
		items.remove(item);
	}
	
	/**
	 * 
	 * @param direction
	 * @return
	 */
	public static boolean attackDarts(Direction direction) 
	{
		return player.attackDarts(maze, direction);
	}

	protected static Dragon dragonAt(Point pos) 
	{
		for (Dragon dragon : dragons) 
		{
			if (dragon.getPosition().equals(pos)) 
			{
				return dragon;
			}
		}

		return null;
	}

	public static Item itemAt(Point pos) 
	{
		for (Item item : items)
		{
			if (item.getPosition().equals(pos)) 
			{
				return item;
			}
		}

		return null;
	}

	private static void updateDragons() 
	{
		for (Dragon dragon : dragons) 
		{
			updateDragon(dragon);

			if (dragon.getHealth() <= 0) 
			{
				dragons.remove(dragon);
			}
		}
	}

	private static void attackDragons() 
	{
		for (Dragon dragon : dragons) 
		{
			player.attackSword(maze, dragon);
			dragon.attack(maze, player);
		}
	}

	private static void attackDragonsFire() 
	{
		for (Dragon dragon : dragons) 
		{
			dragon.attackFire(maze, player);
		}
	}

	// --------------------------------
	// | AUXILLIARY DRAWING FUNCTIONS |
	// --------------------------------
	
	private final static void drawDragons() 
	{
		for (Dragon dragon : dragons) 
		{
			if (dragon != null)
			{
				dragon.draw(maze);
			}
		}
	}

	private static void drawItems() 
	{
		for (Item item : items) 
		{
			if (item != null) 
			{
				item.draw(maze);
			}
		}
	}
	
	private static final void updateDragon(Dragon dragon) 
	{
		if (dragon.getHealth() <= 0 || dragonMovement == -1) 
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


	/**
	 * GAME INITIALIZATION
	 */
	
	public static void initializeDarts(int numberDarts) 
	{
		for (int i = 0; i < numberDarts; i++) 
		{
			items.add(new Dart(maze.placeEntity()));
		}
		
		drawItems();
	}

	public static void initializeDragons(int numberDragons) 
	{
		for (int i = 0; i < numberDragons; i++) 
		{
			dragons.add(new Dragon(maze.placeEntity()));
		}
		
		drawDragons();
	}	

	public static void initialize(Maze m) 
	{
		maze = m;
		dragons = new LinkedList<Dragon>();
		items = new LinkedList<Item>();

		Point playerPosition = maze.placeEntity();

		player = new Hero(playerPosition.x, playerPosition.y);
		items.add(new Sword(maze.placeEntity()));
		items.add(new Shield(maze.placeEntity()));
		
		player.draw(maze);
	}

	public static void initializeStatic(Maze m) 
	{
		maze = m;
		dragons = new LinkedList<Dragon>();
		items = new LinkedList<Item>();
		player = new Hero(1, 1);
		items.add(new Sword(1, 8));
		dragons.add(new Dragon(1, 3));
		items.add(new Shield(3, 1));
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
			return "Pick up a weapon (darts or sword)";
		}

		if (getNumberDragons() > 0) 
		{
			return "Kill all the dragons!";
		}

		return "Find the exit";
	}

	public static final int getNumberDragons() 
	{
		return dragons.size();
	}
	
	public static boolean canExit() 
	{
		return (player.hasSword() && getNumberDragons() == 0);
	}

	public static void update(Direction direction) 
	{
		updateDragons();
		player.move(maze, direction);
		attackDragonsFire();
		attackDragons();

		drawItems();
		player.draw(maze);
		drawDragons();

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