package lpoo.logic;

import java.io.*;
import java.util.*;

public class GameState
{
	private static Maze maze;
	private static Hero player;

	private static LinkedList<Item> items;
	private static LinkedList<Dragon> dragons;

	private static boolean gameOver = false;
	private static boolean playerWon = false;
	private static int dragonMovement = 1;

	public static transient final int DRAGON_STATIC_SLEEP = 1;
	public static transient final int DRAGON_STATIC_NOSLEEP = 2;
	public static transient final int DRAGON_RANDOM_SLEEP = 3;
	public static transient final int DRAGON_RANDOM_NOSLEEP = 4;
	private static transient final Random randomGenerator = new Random();

	public static void read(ObjectInputStream aInputStream) throws ClassNotFoundException, IOException
	{
		maze = (Maze) aInputStream.readObject();
		player = (Hero) aInputStream.readObject();
		items = (LinkedList<Item>) aInputStream.readObject();
		dragons = (LinkedList<Dragon>) aInputStream.readObject();
		gameOver = aInputStream.readBoolean();
		playerWon = aInputStream.readBoolean();
		dragonMovement = aInputStream.readInt();
	}

	public static void write(ObjectOutputStream aOutputStream) throws IOException
	{
		aOutputStream.writeObject(maze);
		aOutputStream.writeObject(player);
		aOutputStream.writeObject(items);
		aOutputStream.writeObject(dragons);
		aOutputStream.writeBoolean(gameOver);
		aOutputStream.writeBoolean(playerWon);
		aOutputStream.writeInt(dragonMovement);
	}

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
		for (final Item item : items)
		{
			if (item instanceof Sword)
			{
				return item;
			}
		}

		return null;
	}

	public static Item getShield()
	{
		for (final Item item : items)
		{
			if (item instanceof Shield)
			{
				return item;
			}
		}

		return null;
	}

	public static Item getDart()
	{
		for (final Item item : items)
		{
			if (item instanceof Dart)
			{
				return item;
			}
		}

		return null;
	}

	protected static void removeItem(Item item)
	{
		if (items.contains(item))
		{
			items.remove(item);
		}
	}

	public static boolean attackDarts(Direction direction)
	{
		return player.attackDarts(maze, direction);
	}

	public static Dragon dragonAt(Point pos)
	{
		for (final Dragon dragon : dragons)
		{
			if (dragon.getPosition().equals(pos))
			{
				return dragon;
			}
		}

		return null;
	}
        
        protected final Point placeDragon()
        {
 		int initialX = 0;
		int initialY = 0;
/*
		while (mazeMatrix[initialY][initialX] != ' ')
		{
			initialX = 1 + mazeRandom.nextInt(mazeWidth - 2);
			initialY = 1 + mazeRandom.nextInt(mazeHeight - 2);
		}

		placeSymbol(initialX, initialY, 'D');
*/
		return new Point(initialX, initialY);
        }

	public static Item itemAt(Point pos)
	{
		for (final Item item : items)
		{
			if (item.getPosition().equals(pos))
			{
				return item;
			}
		}

		return null;
	}

	public static void updateDragons()
	{
		dragons.forEach((dragon) -> updateDragon(dragon));
		dragons.removeIf((dragon) -> dragon.getHealth() <= 0);
	}

	private static void attackDragons()
	{
		dragons.forEach((dragon) -> player.attackSword(maze, dragon));
	}

	public static Maze getMaze()
	{
		return maze;
	}

	// --------------------------------
	// | AUXILLIARY DRAWING FUNCTIONS |
	// --------------------------------
	private static void drawDragons()
	{
		dragons.forEach((dragon) -> dragon.draw(maze));
	}

	private static void drawItems()
	{
		items.forEach((item) -> item.draw(maze));
	}

	public static void setDragonMovement(int userInput)
	{
		if (userInput > 0 && userInput <= 4)
		{
			dragonMovement = userInput;
		}
	}

	public static void setDifficulty(int userInput)
	{
		switch (userInput)
		{
		case 1:
			initialize(new StaticMaze());
			initializeDarts(2);
			initializeDragons(1);
			break;
		case 2:
			initialize(new RandomMaze(11, 11));
			initializeDarts(3);
			initializeDragons(2);
			break;
		case 3:
			initialize(new RandomMaze(15, 15));
			initializeDarts(4);
			initializeDragons(3);
			break;
		case 4:
			initialize(new RandomMaze(23, 23));
			initializeDarts(7);
			initializeDragons(6);
			break;
		case 5:
			initialize(new RandomMaze(31, 31));
			initializeDarts(13);
			initializeDragons(12);
			break;
		}
	}

	public static void moveDragon(Dragon dragon, Direction direction)
	{
		if (dragon.validMove(maze, direction))
		{
			dragon.move(maze, direction);
		}
	}

	private static void updateDragon(Dragon dragon)
	{
		if (dragon.getHealth() <= 0)
		{
			return;
		}
                
                if (dragon.canAttackFire(maze, player))
                {
                    dragon.attackFire(maze, player);
                    
                    return;
                }

		if (dragonMovement == DRAGON_STATIC_SLEEP || dragonMovement == DRAGON_RANDOM_SLEEP)
		{
			final int randomSleep = randomGenerator.nextInt(6);

			if (randomSleep == 1)
			{
				dragon.toggleSleep();
                                
                                return;
			}

			if (dragon.isSleeping())
			{
				return;
			}
		}

		if (dragonMovement < DRAGON_RANDOM_SLEEP)
		{
			return;
		}

		Direction moveDirection = Direction.NONE;

		while (!dragon.validMove(maze, moveDirection))
		{
			final int randomMove = randomGenerator.nextInt(4);

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
				moveDirection = Direction.RIGHT;
				break;
			}
		}

		dragon.move(maze, moveDirection);
                
                if (dragon.canAttack(player))
                {
                    dragon.attack(maze, player);
                }
	}

	/**
	 * GAME INITIALIZATION
	 *
	 * @param numberDarts
	 */
	public static void initializeDarts(int numberDarts)
	{
		while (numberDarts != 0)
		{
			placeDart(maze.placeEntity('*'));
			numberDarts--;
		}

		drawItems();
	}

	public static void placeDart(Point pos)
	{
		items.add(new Dart(pos));
	}

	public static Dragon placeDragon(Point pos)
	{
		dragons.add(new Dragon(pos));

		return dragons.peek();
	}

	public static void initializeDragons(int numberDragons)
	{
		while (numberDragons != 0)
		{
			dragons.add(new Dragon(maze.placeEntity('D')));
			numberDragons--;
		}

		drawDragons();
	}

	public static void initialize(Maze m)
	{
		maze = m;
		playerWon = false;
		gameOver = false;
		dragons = new LinkedList<>();
		items = new LinkedList<>();
		player = new Hero(maze.placeEntity('h'));
		items.add(new Sword(maze.placeEntity('E')));
		items.add(new Shield(maze.placeEntity('V')));
                
		drawEverything();
	}

	public static void initializeStatic(Maze m)
	{
		final Point playerPosition = new Point(1, 1);
		final Point swordPosition = new Point(1, 8);
		final Point shieldPosition = new Point(3, 1);

		maze = m;
		playerWon = false;
		gameOver = false;
		dragons = new LinkedList<>();
		items = new LinkedList<>();
		player = new Hero(playerPosition);
		items.add(new Sword(swordPosition));
		items.add(new Shield(shieldPosition));
		dragons.add(new Dragon(6, 4));
                
		drawEverything();
	}

	private static void drawEverything()
	{
		player.draw(maze);
		drawDragons();
		drawItems();
	}

	public static void initializeCustom(Maze m)
	{
		maze = m;
		items = new LinkedList<>();

		playerWon = false;
		gameOver = false;
		final Point playerPosition = maze.findSymbol('h');
		System.out.println("Initializing player at position " + playerPosition);
		final Point swordPosition = maze.findSymbol('E');
		System.out.println("Initializing sword at position " + swordPosition);
		final Point shieldPosition = maze.findSymbol('V');
		System.out.println("Initializing shield at position " + shieldPosition);

		if (playerPosition != null)
		{
			player = new Hero(playerPosition);
		}

		if (swordPosition != null)
		{
			items.add(new Sword(swordPosition));
		}
		if (shieldPosition != null)
		{
			items.add(new Shield(shieldPosition));
		}

		Point dragonPosition = maze.findSymbol('D');

		dragons = new LinkedList<>();

		while (dragonPosition != null)
		{
			System.out.println("Initializing dragon at position " + dragonPosition);
			dragons.add(new Dragon(dragonPosition));
			dragonPosition = maze.findSymbol('D');
		}

		Point dartPosition = maze.findSymbol('*');

		while (dartPosition != null)
		{
			System.out.println("Initializing dart at position " + dartPosition);
			items.add(new Dart(dartPosition));
			dartPosition = maze.findSymbol('*');
		}

		drawEverything();
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
		return player.hasSword() && dragons.size() == 0;
	}

	public static void update(Direction direction)
	{
		player.move(maze, direction);
		player.draw(maze);

		drawItems();
		updateDragons();
		drawDragons();
		attackDragons();

		player.draw(maze);

		if (player.getHealth() <= 0)
		{
			gameOver = true;
			playerWon = false;
		}

		if (canExit() && maze.isWall(player.pos.x, player.pos.y))
		{
			gameOver = true;
			playerWon = true;
		}
	}
}