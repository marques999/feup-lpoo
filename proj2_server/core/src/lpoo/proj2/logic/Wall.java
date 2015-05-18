package lpoo.proj2.logic;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Wall extends Entity {

    private Color mColor;
    private Sprite mSprite;
    private int mWidth;

    public Wall(int x, int y, int width, Color color)
    {
        super(x, y);

        mWidth = width;
        mColor = color;
    }

    @Override
    public void draw(SpriteBatch sb) {

    }
}
