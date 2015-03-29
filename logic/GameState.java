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

	private static transient final Random randomGenerator = new Random();

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

	protected static void removeItem(Item item)
	{
		items.remove(item);
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
		for (final Dragon dragon : dragons)
		{
			player.attackSword(maze, dragon);
			dragon.attack(maze, player);
		}
	}

	private static void attackDragonsFire()
	{
		dragons.forEach((d) -> d.attackFire(maze, player));
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
		dragons.forEach((d) -> d.draw(maze));
	}

	private static void drawItems()
	{
		items.forEach((i) -> i.draw(maze));
	}

	public static void setDragonMovement(int userInput)
	{
		switch (userInput)
		{
		case 1:
			dragonMovement = -2;
			break;
		case 2:
			dragonMovement = -1;
			break;
		case 3:
			dragonMovement = 1;
			break;
		case 4:
			dragonMovement = 0;
			break;
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

		if (dragonMovement == 1 || dragonMovement == -2)
		{
			final int randomSleep = randomGenerator.nextInt(6);

			if (randomSleep == 1)
			{
				dragon.toggleSleep();
			}

			if (dragon.isSleeping())
			{
				return;
			}
		}

		if (dragonMovement < 0)
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
		player.draw(maze);
                drawDragons();
                drawItems();
	}

	public static void initializeStatic(Maze m)
	{
		final Point playerPosition = new Point(1, 1);
		final Point swordPosition = new Point(1, 8);
		final Point shieldPosition = new Point(3, 1);

		maze = m;
		dragons = new LinkedList<>();
		items = new LinkedList<>();
		player = new Hero(playerPosition);
		items.add(new Sword(swordPosition));
		items.add(new Shield(shieldPosition));
		// dragons.add(new Dragon(1, 3));
		player.draw(maze);
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

		if (getNumberDragons() == 0 && maze.isWall(player.pos.x, player.pos.y))
		{
			gameOver = true;
			playerWon = true;
		}
	}
}