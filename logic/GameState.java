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

	public static final int DRAGON_STATIC_SLEEP = 1;
	public static final int DRAGON_STATIC_NOSLEEP = 2;
	public static final int DRAGON_RANDOM_SLEEP = 3;
	public static final int DRAGON_RANDOM_NOSLEEP = 4;
	private static final Random randomGenerator = new Random();

	/**
	 * reads the game state from an input stream
	 * @param aInputStream objectInputStream to read from
	 * @throws IOException if stream invalid, insufficient permissions or file not found
	 * @throws ClassNotFoundException !!!
	 */
	public static void read(ObjectInputStream aInputStream) throws ClassNotFoundException, IOException
	{
		int numberItems;
		int numberDragons;

		maze = (Maze) aInputStream.readObject();
		player = (Hero) aInputStream.readObject();
		dragons = new LinkedList<>();
		items = new LinkedList<>();
		numberItems = aInputStream.readInt();

		for (int i = 0; i < numberItems; i++)
		{
			items.add((Item) aInputStream.readObject());
		}

		numberDragons = aInputStream.readInt();

		for (int i = 0; i < numberDragons; i++)
		{
			dragons.add((Dragon) aInputStream.readObject());
		}

		gameOver = aInputStream.readBoolean();
		playerWon = aInputStream.readBoolean();
		dragonMovement = aInputStream.readInt();
	}

	/**
	 * writes game state to an output stream
	 * @param aOutputStream objectOutputStream to write to
	 * @throws IOException if stream invalid, insufficient permissions or file not found
	 */
	public static void write(ObjectOutputStream aOutputStream) throws IOException
	{
		aOutputStream.writeObject(maze);
		aOutputStream.writeObject(player);
		aOutputStream.writeInt(items.size());

		for (final Item item : items)
		{
			aOutputStream.writeObject(item);
		}

		aOutputStream.writeInt(dragons.size());

		for (final Dragon dragon : dragons)
		{
			aOutputStream.writeObject(dragon);
		}

		aOutputStream.writeBoolean(gameOver);
		aOutputStream.writeBoolean(playerWon);
		aOutputStream.writeInt(dragonMovement);
	}

	/**
	 * gets the player entity from the current game
	 * @return the player entity from the current game
	 */
	public static Hero getPlayer()
	{
		return player;
	}

	/**
	 * gets the first occurrence of a dragon entity in the dragons collection
	 * @return the dragon entity if it exists in the collection, otherwise 'null'
	 */
	public static Dragon getDragon()
	{
		return dragons.peek();
	}

	/**
	 * gets the sword entity from the items collection
	 * @return the sword item if it exists in the collection, otherwise 'null'
	 */
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

	/**
	 * gets the shield entity from the items collection
	 * @return the shield item if it exists in the collection, otherwise 'null'
	 */
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

	/**
	 * gets the first occurrence of a dart entity from the items collection
	 * @return returns the dart item if it exists in the collection, otherwise 'null'
	 */
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

	/**
	 * gets the number of darts owned by the player in the current game
	 * @return returns the number of darts owned by the player
	 */
	public static int getNumberDarts()
	{
		return player.getNumberDarts();
	}

	/**
	 * gets the number of alive dragons in the current game
	 * @return returns the number of alive dragons
	 */
	public static final int getNumberDragons()
	{
		return dragons.size();
	}

	/**
	 * removes an item from the inventory (items collection)
	 * @param item an instance of the item to be removed
	 */
	protected static void removeItem(Item item)
	{
		if (items.contains(item))
		{
			items.remove(item);
		}
	}

	/**
	 * performs a long-ranged dart attack in a  direction
	 * @param direction
	 * @return
	 */
	public static boolean attackDarts(Direction direction)
	{
		return player.attackDarts(maze, direction);
	}
	
	/**
	 * gets the dragon entity placed at a given position
	 * @param pos the dragon position
	 * @return returns the dragon entity located at the given position if there is any, 'null' otherwise
	 */
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

	/**
	 * gets the item placed at a given position
	 * @param pos the item position
	 * @return returns the item located at the given position if there is any, 'null' otherwise
	 */
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

	/**
	 * updates the state of all the dragon in the current game
	 * deletes
	 */
	public static void updateDragons()
	{
		dragons.forEach((dragon) -> updateDragon(dragon));
		dragons.removeIf((dragon) -> dragon.getHealth() <= 0);
		dragons.forEach((dragon) -> dragon.updateFireball(maze, player));
	}

	/**
	 * checks if player can attack any of the dragons in the current game
	 */
	private static void attackDragons()
	{
		dragons.forEach((dragon) -> player.attackSword(maze, dragon));
	}

	/**
	 * gets the current game board
	 * @return the current game board
	 */
	public static Maze getMaze()
	{
		return maze;
	}

	/**
	 * checks if the dragon can be placed at 
	 * @param playerPosition the player position
	 * @param dragonPosition the dragon position
	 * @return 'true' if the can be placed on the maze whilst the player is in a safe position
	 */
	public static boolean validateDragon(Point playerPosition, Point dragonPosition)
	{
		if (playerPosition == null || dragonPosition == null)
		{
			return true;
		}

		return (Math.abs(playerPosition.y - dragonPosition.y) >= 3 || Math.abs(playerPosition.x - dragonPosition.x) >= 3);
	}

	// --------------------------------
	// | AUXILLIARY DRAWING FUNCTIONS |
	// --------------------------------

	/**
	 * draws all dragons from the current game on the maze
	 */
	private static void drawDragons()
	{
		dragons.forEach((dragon) -> dragon.draw(maze));
	}
	
	private static void drawPlayer()
	{
		if (player != null)
		{
			player.draw(maze);
		}
	}

	/**
	 * draws all entities and items from the current game on the maze
	 */
	private static void drawEverything()
	{
		drawPlayer();
		drawDragons();
		drawItems();
	}
	
	/**
	 * draws all items (swords, shields and darts) from the current game on the maze
	 */
	private static void drawItems()
	{
		items.forEach((item) -> item.draw(maze));
	}

	/**
	 * changes the value of dragonMovement to a given value (on user input)
	 * @param userInput an integer between 1 and 4 specifying the dragon movement type
	 */
	public static void setDragonMovement(int userInput)
	{
		if (userInput > 0 && userInput <= DRAGON_RANDOM_NOSLEEP)
		{
			dragonMovement = userInput;
		}
	}

	/**
	 * sets the game difficulty and initializes a new game board accordingly
	 * @param userInput an integer between 1 and 5 specifying the game difificulty
	 */
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

	/**
	 * generates a valid random direction to move a dragon
	 * @return the direction to move the dragon
	 */
	private static Direction generateDirection()
	{
		Direction moveDirection = Direction.NONE;

		switch (randomGenerator.nextInt(4))
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

		return moveDirection;
	}

	/**
	 * updates a dragon entity's state and allows them to move, sleep and attack
	 * @param dragon the dragon entity to be updated
	 */
	private static void updateDragon(Dragon dragon)
	{
		if (dragon.getHealth() <= 0)
		{
			return;
		}

		if ((dragonMovement == DRAGON_STATIC_SLEEP || dragonMovement == DRAGON_RANDOM_SLEEP) && !dragon.hasFireball())
		{
			if (randomGenerator.nextInt(6) == 1)
			{
				dragon.toggleSleep();
			}

			if (dragon.isSleeping())
			{
				return;
			}
		}

		if ((dragonMovement == DRAGON_RANDOM_NOSLEEP || dragonMovement == DRAGON_RANDOM_SLEEP) && !dragon.hasFireball())
		{
			Direction moveDirection = Direction.NONE;

			while (!dragon.validMove(maze, moveDirection))
			{
				moveDirection = generateDirection();
			}

			dragon.move(maze, moveDirection);
		}

		if (dragon.canAttack(player))
		{
			dragon.attack(maze, player);
		}
		else
		{
			if (!dragon.hasFireball())
			{
				dragon.throwFireball(maze, player);
			}
		}
	}

	/**
	 * manually initializes and places darts on the game board
	 * @param numberDarts the number of darts to initialize
	 */
	public static void initializeDarts(int numberDarts)
	{
		for (int i = 0; i < numberDarts; i++)
		{
			placeDart(maze.placeEntity('*', true));
		}

		drawItems();
	}

	/**
	 * places a dart in a given position (no verification)
	 * @param pos the position where to place the dart
	 */
	public static void placeDart(Point pos)
	{
		items.add(new Dart(pos));
	}

	/**
	 * places a dragon in a given position on the game board (no verification)
	 * @param pos coordinates for dragon's position
	 */
	public static void placeDragon(Point pos)
	{
		dragons.add(new Dragon(pos));
	}

	/**
	 * places the player at a given position on the game board (no verification)
	 * @param pos coordinates for player's position
	 */
	private static void placePlayer(Point pos)
	{
		player = new Hero(pos);
	}
	
	/**
	 * places the shield at a given position on the game board (no verification)
	 * @param pos coordinates for shield's position
	 */
	public static void placeShield(Point pos)
	{
		items.add(new Shield(pos));
	}
	
	/**
	 * places the sword at a given position on the game board (no verification)
	 * @param pos coordinates for sword's position
	 */
	private static void placeSword(Point pos)
	{
		items.add(new Sword(pos));
	}

	/**
	 * manually initializes and places dragons on the game board
	 * @param numberDragons the number of dragons to be placed
	 */
	public static void initializeDragons(int numberDragons)
	{
		do
		{
			Point dragonPosition = maze.placeEntity('D', false);

			if (GameState.validateDragon(player.getPosition(), dragonPosition))
			{
				placeDragon(dragonPosition);
				maze.placeSymbol(dragonPosition.x, dragonPosition.y, 'D');
				numberDragons--;
			}

		} while (numberDragons != 0);
	}

	/**
	 * resets the game state and initializes main data structures
	 */
	private static void initializeEmpty()
	{
		playerWon = false;
		gameOver = false;
		dragons = new LinkedList<>();
		items = new LinkedList<>();
	}


	/**
	 * initializes
	 * @param m
	 */
	public static void initialize(Maze m)
	{
		maze = m;
		
		initializeEmpty();
		placePlayer(maze.placeEntity('h', true));
		placeSword(maze.placeEntity('E', true));
		placeShield(maze.placeEntity('V', true));
		drawEverything();
	}

	public static void initializeStatic(Maze m)
	{
		final Point dragonPosition = new Point(6, 4);
		final Point playerPosition = new Point(1, 1);
		final Point swordPosition = new Point(1, 8);
		final Point shieldPosition = new Point(3, 1);

		maze = m;
		
		initializeEmpty();
		placePlayer(playerPosition);
		placeSword(swordPosition);
		placeShield(shieldPosition);
		placeDragon(dragonPosition);
		drawEverything();
	}

	/**
	 * initializes the game board given a previously generated maze
	 * primarily used to load saved games, custom mazes and to restart the current game
	 * @param m
	 */
	public static void initializeCustom(Maze m)
	{
		initializeEmpty();

		maze = m;

		final Point playerPosition = maze.findSymbol('h', true);
		final Point swordPosition = maze.findSymbol('E', true);
		final Point shieldPosition = maze.findSymbol('V', true);

		if (playerPosition != null)
		{
			placePlayer(playerPosition);
		}

		if (swordPosition != null)
		{
			placeSword(swordPosition);
		}

		if (shieldPosition != null)
		{
			placeShield(shieldPosition);
		}

		Point dragonPosition = maze.findSymbol('D', true);

		while (dragonPosition != null)
		{
			placeDragon(dragonPosition);
			dragonPosition = maze.findSymbol('D', true);
		}

		Point dartPosition = maze.findSymbol('*', true);

		while (dartPosition != null)
		{
			placeDart(dartPosition);
			dartPosition = maze.findSymbol('*', true);
		}

		drawEverything();
	}

	/**
	 * prints the maze using standard output (for command-line interface)
	 */
	public static final void printMaze()
	{
		maze.printMaze();
	}

	/**
	 * 
	 * @return returns 'true' if the curent game has ended (player died or reached the exit)
	 */
	public static final boolean gameOver()
	{
		return gameOver;
	}

	/**
	 * 
	 * @return returns 'true' if the player has reached the exit, 'fale' otherwise
	 */
	public static final boolean playerWon()
	{
		return playerWon;
	}

	/**
	 * guides the user, telling him what he should do next
	 * @return a string describing the current game objective
	 */
	public static final String getObjective()
	{
		if (getNumberDragons() > 0)
		{
			if (!player.hasSword() && !player.hasDarts())
			{
				return "pickup a weapon";
			}

			return "kill all dragons!";
		}

		return "reach the exit";
	}

	public static boolean canExit()
	{
		return player.hasSword() && dragons.size() == 0;
	}

	/**
	 * makes a move in a given direction, and updates the entire game board accordingly
	 * @param direction the direction in which to move the player
	 */
	public static void update(Direction direction)
	{
		player.move(maze, direction);
		drawPlayer();

		updateDragons();
		drawEverything();
		attackDragons();

		Point exitPosition = maze.getExitPosition();

		if (canExit())
		{
			maze.placeSymbol(exitPosition.x, exitPosition.y, 's');

			if (maze.isWall(player.pos.x, player.pos.y))
			{
				gameOver = true;
				playerWon = true;
			}
		}
		else
		{
			maze.placeSymbol(exitPosition.x, exitPosition.y, 'S');
		}

		if (player.getHealth() <= 0)
		{
			gameOver = true;
			playerWon = false;
		}
	}
}