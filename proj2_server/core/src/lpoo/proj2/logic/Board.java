package lpoo.proj2.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;

public class Board
{
    private Paddle p1Paddle;
    private Paddle p2Paddle;
    private Goal p1Goal;
    private Goal p2Goal;
    private Puck puck;
    private World world;
    private ArrayList<Wall> walls = new ArrayList<Wall>();
    private Vector2 gravity;

    public Board()
    {
        gravity = new Vector2(0.0f, 0.0f);
        world = new World(gravity, true);
        p1Paddle = new Paddle(0.0f, 0.0f, Color.RED, world);
        p2Paddle = new Paddle(1.0f, 1.0f, Color.BLUE, world);
        p1Goal = new Goal(0.0f, 0.0f, world);
        p2Goal = new Goal(1.0f, 1.0f, world);
        puck = new Puck(30.0f, 30.0f, Color.RED, world);
    }

    public void initialize()
    {
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();

		BodyDef wallDef = new BodyDef();
        EdgeShape wallEdge = new EdgeShape();
        FixtureDef wallFixture = new FixtureDef();

        wallDef.type = BodyDef.BodyType.StaticBody;
        wallDef.position.set(0, 0);
        wallEdge.set(-screenWidth / 2, -screenHeight / 2, screenWidth / 2, screenHeight / 2);
    }

    public void draw(SpriteBatch sb)
    {
        puck.draw(sb);
        p1Paddle.draw(sb);
        p2Paddle.draw(sb);
        
        for (Wall w : walls)
        {
        	w.draw(sb);
        }
    }
    
    public void dispose()
    {
    	world.dispose();
    }
}