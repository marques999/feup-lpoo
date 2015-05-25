package lpoo.proj2.logic;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;

public class Board
{
    private Entity p1Paddle;
    private Entity p2Paddle;
    private Goal p1Goal;
    private Goal p2Goal;
    private EntityFactory factory;
    private Puck puck;
    private World world;
    private ArrayList<Wall> walls = new ArrayList<Wall>();
    private Vector2 gravity;

    public Board()
    {
        gravity = new Vector2(0.0f, 0.0f);
        world = new World(gravity, true);
        factory = new EntityFactory(world);
		puck = factory.createPuck(Color.RED);
		p1Paddle = factory.createP1Paddle(Color.GREEN);
		p2Paddle = factory.createP2Paddle(Color.YELLOW);
        p1Goal = factory.createP1Goal();
        p2Goal = factory.createP2Goal();
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