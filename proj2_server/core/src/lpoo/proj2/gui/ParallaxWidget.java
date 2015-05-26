package lpoo.proj2.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;

public class ParallaxWidget extends Widget
{
	private final Texture[] textures = new Texture[2];
	private final float[] scrollFactors = new float[2];
	private final float[] scrollAmounts = new float[2];

	public ParallaxWidget(String path0, float factor0, String path1, float factor1)
	{
		scrollFactors[0] = factor0;
		scrollFactors[1] = factor1;
		scrollAmounts[0] = 0.0f;
		scrollAmounts[1] = 0.0f;
		textures[0] = new Texture(Gdx.files.internal(path0));
		textures[1] = new Texture(Gdx.files.internal(path1));
		textures[0].setWrap(TextureWrap.Repeat, TextureWrap.ClampToEdge);
		textures[1].setWrap(TextureWrap.Repeat, TextureWrap.ClampToEdge);
	}

	@Override
	public void draw(final Batch batch, float parentAlpha)
	{
		super.draw(batch, parentAlpha);

		final Color color = getColor();

		batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
		drawLayer(batch, 0);
		drawLayer(batch, 1);
	}

	public void updateScroll(float value)
	{
		scrollAmounts[0] = value * scrollFactors[0];
		scrollAmounts[1] = value * scrollFactors[1];
	}

	private void drawLayer(final Batch batch, int index)
	{
		final float x = getX();
		final float y = getY();
		final float w = getWidth();
		final float h = getHeight();
		final float th = textures[index].getHeight();
		final float tw = textures[index].getWidth() * h / th;
		final float u = scrollAmounts[index] / tw;
		final float v = 1.0f;
		final float u2 = u + (w / tw);
		final float v2 = 0.0f;

		batch.draw(textures[index], x, y, w, h, u, v, u2, v2);
	}
}