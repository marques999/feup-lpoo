package lpoo.proj2.logic;

import java.util.ArrayList;
import java.util.Random;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

public class EntityFactory
{
	private Random rand = new Random();
	private final int screenWidth = Gdx.graphics.getWidth();
	private final int screenHeight = Gdx.graphics.getHeight();

	private Puck createPuck(float x, float y, Color color)
	{
		return new Puck(x, y, color);
	}

	public Puck createSinglePuck(Color color)
	{
		return createPuck(screenWidth / 2, screenHeight / 2, color);
	}

	public Puck createRandomPuck(Color color)
	{
		return createPuck(rand.nextInt(screenWidth), rand.nextInt(screenHeight), color);
	}

	private Paddle createPaddle(float x, float y, Color color)
	{
		return new Paddle(x, y, color);
	}

	public Paddle createP1Paddle(Color color)
	{
		return createPaddle(screenWidth / 2, 64, color);
	}

	public Paddle createP2Paddle(Color color)
	{
		return createPaddle(screenWidth / 2, screenHeight - 64, color);
	}

	private Goal createGoal(float x, float y)
	{
		return new Goal(x, y, (screenWidth - 64) / 2, 16);
	}

	public Goal createP1Goal()
	{
		return createGoal(screenWidth * 0.25f, 0);
	}

	public Goal createP2Goal()
	{
		return createGoal(screenWidth * 0.25f, screenHeight - 16);
	}

	public ArrayList<Wall> createP1Walls(Color color)
	{
		ArrayList<Wall> walls = new ArrayList<Wall>();

		walls.add(new Wall(0, 0, 32, screenHeight / 2, color));
		walls.add(new Wall(screenWidth - 32, 0, 32, screenHeight / 2, color));
		walls.add(new Wall(32, 0, screenWidth / 4, 32, color));
		walls.add(new Wall(screenWidth * 0.75f - 32, 0, screenWidth / 4, 32, color));

		return walls;
	}

	public ArrayList<Wall> createP2Walls(Color color)
	{
		ArrayList<Wall> walls = new ArrayList<Wall>();

		walls.add(new Wall(0, screenHeight / 2, 32, screenHeight / 2, color));
		walls.add(new Wall(screenWidth - 32, screenHeight / 2, 32, screenHeight / 2, color));
		walls.add(new Wall(32, screenHeight - 32, screenWidth / 4, 32, color));
		walls.add(new Wall(screenWidth * 0.75f - 32, screenHeight - 32, screenWidth / 4, 32, color));

		return walls;
	}
}