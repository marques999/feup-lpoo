package com.mygdx.game.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class Board implements Screen
{
	private Paddle paddle;
	//private Wall walls;
	private Puck puck;
	private OrthographicCamera cam;
	private World world;
	private Box2DDebugRenderer debug;

	private boolean playerOne = false;
	private boolean playerTwo = false;

	private boolean wallTop = false;
	private boolean wallBottom = false;

	private boolean ballExists = false;
	private int ballTotal;

	// GameScreen default constructor
	public GameScreen(Pong pong)
	{

		// Initiate the camera
		cam = new OrthographicCamera(20f, 14f);
		cam.position.set(10f, 7f, 0);

		// Initiate the world, no gravity
		world = new World(new Vector2(0, -10), true);

		// Initiate the renderer
		debug = new Box2DDebugRenderer();

		// Initiate the paddle
		paddle = new Paddle();

		//Initiate the walls
		//	wall = new Walls();

		//Initiate the ball
		puck = new Puck(300, 300);
	}

	public void render(float delta)
	{
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		cam.update();

		debug.render(world, cam.combined);

		if (!playerOne)
		{
			//	paddle.createPaddle(1, 7);
			playerOne = true;
			System.out.println("Creating player ones paddle");
		}
		if (!playerTwo)
		{
			//	paddle.createaddle(19, 7);
			playerTwo = true;
			System.out.println("Creating player twos paddle");
		}

		//Check if walls exist, if not create them
		if (!wallBottom)
		{
			//	wall.createWalls(0, 0.1f);
			wallBottom = true;
			System.out.println("Creating top wall");
		}
		if (!wallTop)
		{
			//	wall.createWalls(0, 13f);
			wallTop = true;
			System.out.println("Creating bottom wall");
		}
