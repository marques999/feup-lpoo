package com.mygdx.game.gfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.MathUtils;

public class Glow
{
	private Color laserColor;
	private TextureAtlas laserTexture;
	private AtlasRegion start;
	private AtlasRegion startBackground;
	private AtlasRegion middle;
	private AtlasRegion middleBackground;
	private AtlasRegion end;
	private AtlasRegion endBackground;

	public Glow()
	{
		laserTexture = new TextureAtlas(Gdx.files.internal("core/assets/gfx_laserBeam.txt"));
	}

	public void setColor(Color newColor)
	{
		laserColor = newColor;
	}

	public void draw(SpriteBatch s)
	{
		s.end();
		s.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE);
		s.begin();
	}

	private float[] generateMesh(float[] vertices, TextureAtlas.AtlasRegion region, int scale, float x, float y, float originX, float originY, float width, float height, float scaleX, float scaleY, float rotation, float alpha)
	{
		if (scale * 20 > vertices.length)
		{
			vertices = new float[20 * scale];
		}

		float color = laserColor.toFloatBits();
		float colorE;
		int idx = 0;
		final float worldOriginX = x + originX;
		final float worldOriginY = y + originY;
		float fx = -originX;
		float fy = -originY;
		float fx2 = width - originX;
		float fy2 = height - originY;

		if (scaleX != 1 || scaleY != 1)
		{
			fx *= scaleX;
			fy *= scaleY;
			fx2 *= scaleX;
			fy2 *= scaleY;
		}

		final float p1x = fx;
		final float p1y = fy;
		final float p2x = fx;
		final float p2y = fy2;
		final float p3x = fx2;
		final float p3y = fy2;
		final float p4x = fx2;
		final float p4y = fy;
		float Fx1;
		float Fy1;
		float Fx2;
		float Fy2;
		float Fx3;
		float Fy3;
		float Fx4;
		float Fy4;

		if (rotation != 0)
		{
			final float cos = MathUtils.cosDeg(rotation);
			final float sin = MathUtils.sinDeg(rotation);

			Fx1 = cos * p1x - sin * p1y;
			Fy1 = sin * p1x + cos * p1y;
			Fx2 = cos * p2x - sin * p2y;
			Fy2 = sin * p2x + cos * p2y;
			Fx3 = cos * p3x - sin * p3y;
			Fy3 = sin * p3x + cos * p3y;
			Fx4 = Fx1 + (Fx3 - Fx2);
			Fy4 = Fy3 - (Fy2 - Fy1);
		}
		else
		{
			Fx1 = p1x;
			Fy1 = p1y;
			Fx2 = p2x;
			Fy2 = p2y;
			Fx3 = p3x;
			Fy3 = p3y;
			Fx4 = p4x;
			Fy4 = p4y;
		}

		float x1 = Fx1 + worldOriginX;
		float y1 = Fy1 + worldOriginY;
		float x2 = Fx2 + worldOriginX;
		float y2 = Fy2 + worldOriginY;
		float scaleX2 = ((Fx2 - Fx1) / scale);
		float scaleY2 = ((Fy2 - Fy1) / scale);
		float scaleX3 = ((Fx3 - Fx4) / scale);
		float scaleY3 = ((Fy3 - Fy4) / scale);
		float scaleAlpha = (laserColor.a - (laserColor.a * alpha)) / scale;
		float x3 = x1;
		float y3 = y1;
		float x4 = x2;
		float y4 = y2;

		final float u = region.getU();
		final float v = region.getV();
		final float u2 = region.getU2();
		final float v2 = region.getV2();

		for (int i = 1; i <= scale; i++)
		{
			x1 = Fx1 + scaleX2 * (i - 1) + worldOriginX;
			y1 = Fy1 + scaleY2 * (i - 1) + worldOriginY;
			x2 = Fx1 + scaleX2 * i + worldOriginX;
			y2 = Fy1 + scaleY2 * i + worldOriginY;
			x3 = Fx4 + scaleX3 * i + worldOriginX;
			y3 = Fy4 + scaleY3 * i + worldOriginY;
			x4 = Fx4 + scaleX3 * (i - 1) + worldOriginX;
			y4 = Fy4 + scaleY3 * (i - 1) + worldOriginY;
			color = laserColor.toFloatBits();
			laserColor.a -= scaleAlpha;
			colorE = laserColor.toFloatBits();
			vertices[idx++] = x1;
			vertices[idx++] = y1;
			vertices[idx++] = color;
			vertices[idx++] = u;
			vertices[idx++] = v;
			vertices[idx++] = x2;
			vertices[idx++] = y2;
			vertices[idx++] = colorE;
			vertices[idx++] = u;
			vertices[idx++] = v2;
			vertices[idx++] = x3;
			vertices[idx++] = y3;
			vertices[idx++] = colorE;
			vertices[idx++] = u2;
			vertices[idx++] = v2;
			vertices[idx++] = x4;
			vertices[idx++] = y4;
			vertices[idx++] = color;
			vertices[idx++] = u2;
			vertices[idx++] = v;
		}

		return vertices;
	}
}