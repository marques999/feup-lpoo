package lpoo.proj2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.esotericsoftware.kryonet.Client;
import lpoo.proj2.Network.UpdatePaddle;

@SuppressLint("ClickableViewAccessibility")
public class GameView extends View
{
	private float posX;
	private float posY;
	private Client client;
	private Bitmap ballBitmap;
	private Paint lineColor;
	private Bounds bounds;

	public GameView(Context context)
	{
		super(context);
		initialize();
	}

	public GameView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		initialize();
	}

	public GameView(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
		initialize();
	}

	protected void setClient(Client clientParam)
	{
		client = clientParam;
	}

	private void initialize()
	{
		ballBitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.paddle_red);

		if (lineColor == null)
		{
			lineColor = new Paint();
			lineColor.setStrokeWidth(5.0f);
			lineColor.setStyle(Paint.Style.FILL);
			lineColor.setColor(Color.LTGRAY);
		}
	}

	protected void setBitmap(int paramIndex)
	{
		switch (paramIndex)
		{
		case 0:
			ballBitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.paddle_green);
			break;
		case 1:
			ballBitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.paddle_red);
			break;
		case 2:
			ballBitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.paddle_yellow);
			break;
		case 3:
			ballBitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.paddle_blue);
			break;
		case 4:
			ballBitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.paddle_purple);
			break;
		case 5:
			ballBitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.paddle_orange);
			break;
		}
	}

	@Override
	public void onSizeChanged(int xNew, int yNew, int xOld, int yOld)
	{
		bounds = new Bounds(0, 0, getWidth() - ballBitmap.getWidth(), getHeight() - ballBitmap.getHeight());
		posX = (getWidth() - ballBitmap.getWidth()) / 2;
		posY = (getHeight() - ballBitmap.getHeight()) / 2;
	}

	@Override
	public void onDraw(Canvas canvas)
	{
		canvas.drawLine(0, 0, getWidth(), 0, lineColor);
		canvas.drawLine(0, getHeight(), getWidth(), getHeight(), lineColor);
		canvas.drawLine(0, 0, 0, getHeight(), lineColor);
		canvas.drawLine(getWidth(), 0, getWidth(), getHeight(), lineColor);
		canvas.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight(), lineColor);
		canvas.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2, lineColor);
		canvas.drawBitmap(ballBitmap, posX, posY, lineColor);
	}

	protected void processMove(float paramX, float paramY)
	{
		posX = paramX <= bounds.maxX ? (paramX < bounds.minX ? bounds.minX : paramX) : bounds.maxX;
		posY = paramY <= bounds.maxY ? (paramY < bounds.minY ? bounds.minY : paramY) : bounds.maxY;
	}

	private class SendMessage extends Thread
	{
		final UpdatePaddle msg = new UpdatePaddle();

		public SendMessage(float x, float y)
		{
			msg.x = posX;
			msg.y = posY;
		}

		@Override
		public void run()
		{
			if (client != null)
			{
				client.sendTCP(msg);
			}
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		float newX = event.getX();
		float newY = event.getY();
		final float ratioX = 480 / getWidth();
		final float ratioY = 400 / getHeight();
		final int action = event.getAction();

		switch (action & MotionEvent.ACTION_MASK)
		{
		case MotionEvent.ACTION_DOWN:
		case MotionEvent.ACTION_MOVE:
			processMove(newX, newY);
			invalidate();
			requestLayout();
			break;
		}

		newX *= ratioX;
		newY *= ratioY;

		new SendMessage(newX, newY).start();

		return true;
	}
}