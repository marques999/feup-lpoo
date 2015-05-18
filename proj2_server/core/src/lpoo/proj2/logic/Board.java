package lpoo.proj2.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import java.util.ArrayList;

class Board
{
    private Paddle p1Paddle;
    private Paddle p2Paddle;
    private Puck mPuck;
    private World mWorld;
    private ArrayList<Wall> mWalls = new ArrayList<Wall>();

    private Body mPlayfield;

    public Board()
    {
        Vector2 mGravity = new Vector2(0.0f, 0.0f);

        mWorld = new World(mGravity, true);
        mPuck = new Puck(30.0f, 30.0f, Color.RED, mWorld);
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
        mPuck.draw(sb);
    }
}