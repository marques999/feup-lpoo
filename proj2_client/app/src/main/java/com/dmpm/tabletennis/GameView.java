package com.dmpm.tabletennis;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class GameView extends View
{
	private int ballX;
	private int ballY;
	private int minX;
	private int maxX;
	private int minY;
	private int maxY;
	private long elapsedTime;
	private float speedX;
	private float speedY;
	private int centerX;
	private int centerY;

	private Bitmap ballBitmap;
	private Paint lineColor;

	public GameView(Context context)
	{
		super(context);

		ballBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher);
		initializePaint();
	}

	public GameView(Context context, AttributeSet attrs)
	{
		super(context, attrs);

		ballBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher);
		initializePaint();
	}

	public GameView(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);

		ballBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher);
		initializePaint();
	}

	private void initializePaint()
	{
		lineColor = new Paint();
		lineColor.setStrokeWidth(5.0f);
		lineColor.setStyle(Paint.Style.FILL);
		lineColor.setColor(Color.LTGRAY);
		elapsedTime = System.currentTimeMillis();
	}

	@Override
	public void onSizeChanged(int xNew, int yNew, int xOld, int yOld)
	{
		centerX = (getWidth() - ballBitmap.getWidth()) / 2;
		centerY = (getHeight() - ballBitmap.getHeight()) / 2;
		minY = 0;
		minX = 0;
		maxX = getWidth() - ballBitmap.getWidth();
		maxY = getHeight() - ballBitmap.getHeight();
		ballX = centerX;
		ballY = centerY;
	}

	@Override
	public void onDraw(Canvas canvas)
	{
		canvas.drawLine(0, 0, getWidth(), 0, lineColor);
		canvas.drawLine(0, getHeight(), getWidth(), getHeight(), lineColor);
		canvas.drawLine(0, 0, 0, getHeight(), lineColor);
		canvas.drawLine(getWidth(), 0, getWidth(), getHeight(), lineColor);
		canvas.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight(), lineColor);
		canvas.drawLine(0, getHeight() / 2,  getWidth(), getHeight() / 2, lineColor);
		canvas.drawText(Float.toString(speedX), 100, 100, lineColor);
		canvas.drawBitmap(ballBitmap, ballX, ballY, lineColor);
	}

	private void processMove(int newX, int newY)
	{
		ballX = newX <= maxX ? (newX < minX ? minX : newX) : maxX;
		ballY = newY <= maxY ? (newY < minY ? minY : newY) : maxY;
	}

	private void processSpeed(int newX, int newY)
	{
		if (elapsedTime > 20)
		{
			float newSpeedX = 1000 * (newX - ballX) / (float) elapsedTime;
			float newSpeedY = 1000 * (newY - ballY) / (float) elapsedTime;

			if (Math.abs(newSpeedX) > 1e-2 && Math.abs(newSpeedY) > 1e-2)
			{
				speedX = newSpeedX;
				speedY = newSpeedY;
			}

			elapsedTime = System.currentTimeMillis() - elapsedTime;
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		int newX = (int) event.getX();
		int newY = (int) event.getY();
		int action = event.getAction();

		switch (action & MotionEvent.ACTION_MASK)
		{
			case MotionEvent.ACTION_DOWN:
			{
				processMove(newX, newY);
				invalidate();
				requestLayout();
				break;
			}
			case MotionEvent.ACTION_MOVE:
			{
				processSpeed(newX, newY);
				processMove(newX, newY);
				invalidate();
				requestLayout();
				break;
			}
			case MotionEvent.ACTION_UP:
			{
				processMove(centerX, centerY);
				invalidate();
				requestLayout();
			}
		}

		return true;
	}
}