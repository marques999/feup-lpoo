package lpoo.proj2.logic;

import java.util.ArrayList;
import java.util.Random;
import com.badlogic.gdx.Gdx;

public class EntityFactory
{
	private final Random rand = new Random();
	private final int screenWidth = Gdx.graphics.getWidth();
	private final int screenHeight = Gdx.graphics.getHeight();

	private final Puck createPuck(float x, float y, int color)
	{
		return new Puck(x, y, color);
	}

	public final Puck createSinglePuck(int paramColor)
	{
		return createPuck(screenWidth / 2, screenHeight / 2, paramColor);
	}

	public final Puck createRandomPuck(int paramColor)
	{
		return createPuck(rand.nextInt(screenWidth), rand.nextInt(screenHeight), paramColor);
	}

	private final Paddle createPaddle(float x, float y, int color)
	{
		return new Paddle(x, y, color);
	}

	public final Paddle createP1Paddle(int paramColor)
	{
		return createPaddle(screenWidth / 2, 64, paramColor);
	}

	public final Paddle createP2Paddle(int color)
	{
		return createPaddle(screenWidth / 2, screenHeight - 64, color);
	}

	private final Goal createGoal(float x, float y)
	{
		return new Goal(x, y, (screenWidth - 100) / 2, 16);
	}

	public final Goal createP1Goal()
	{
		return createGoal(screenWidth * 0.3f, 0);
	}

	public final Goal createP2Goal()
	{
		return createGoal(screenWidth * 0.3f, screenHeight - 16);
	}

	public final ArrayList<Wall> createP1Walls(int color)
	{
		ArrayList<Wall> walls = new ArrayList<Wall>();

		walls.add(new Wall(0, 0, 32, screenHeight, color));
		walls.add(new Wall(screenWidth - 32, 0, 32, screenHeight, color));
		walls.add(new Wall(32, 0, screenWidth / 4, 32, color));
		walls.add(new Wall(screenWidth * 0.75f - 32, 0, screenWidth / 4, 32, color));
		
		walls.get(0).setNormal(-1, 1);
		walls.get(1).setNormal(-1, 1);
		walls.get(2).setNormal(1, -1);
		walls.get(3).setNormal(1, -1);
		

		return walls;
	}

	public final ArrayList<Wall> createP2Walls(int color)
	{
		ArrayList<Wall> walls = new ArrayList<Wall>();

		walls.add(new Wall(32, screenHeight - 32, screenWidth / 4, 32, color));
		walls.add(new Wall(screenWidth * 0.75f - 32, screenHeight - 32, screenWidth / 4, 32, color));
		
		walls.get(0).setNormal(1, -1);
		walls.get(1).setNormal(1, -1);

		return walls;
	}
}