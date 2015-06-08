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
	private float ratioX = 1.0f;
	private float ratioY = 1.0f;
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
		case 1:
			ballBitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.paddle_green);
			break;
		case 2:
			ballBitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.paddle_red);
			break;
		case 3:
			ballBitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.paddle_yellow);
			break;
		case 4:
			ballBitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.paddle_purple);
			break;
		case 5:
			ballBitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.paddle_orange);
			break;
		default:
			ballBitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.paddle_blue);
			break;
		}
	}

	@Override
	public void onSizeChanged(int xNew, int yNew, int xOld, int yOld)
	{
		super.onSizeChanged(xNew, yNew, xOld, yOld);

		ratioX = 480.0f / xNew;
		ratioY = 400.0f / yNew;
		bounds = new Bounds(0, 0, getWidth(), getHeight());
		posX = getWidth() >> 1;
		posY = getHeight() >> 1;
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
		canvas.drawBitmap(ballBitmap, posX - ballBitmap.getWidth() / 2, posY - ballBitmap.getHeight() / 2, lineColor);
	}

	protected void processMove(float paramX, float paramY)
	{
		posX = paramX <= bounds.maxX ? (paramX < bounds.minX ? bounds.minX : paramX) : bounds.maxX;
		posY = paramY <= bounds.maxY ? (paramY < bounds.minY ? bounds.minY : paramY) : bounds.maxY;
		new SendMessage(posX * ratioX, posY * ratioY).start();
	}

	private class SendMessage extends Thread
	{
		final UpdatePaddle msg = new UpdatePaddle();

		public SendMessage(float x, float y)
		{
			msg.x = x;
			msg.y = y;
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
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
		int parentHeight = MeasureSpec.getSize(heightMeasureSpec);

		setMeasuredDimension(parentWidth, parentHeight);

		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		float newX = event.getX();
		float newY = event.getY();
		final int action = event.getAction();

		switch (action & MotionEvent.ACTION_MASK)
		{
		case MotionEvent.ACTION_MOVE:
		case MotionEvent.ACTION_DOWN:
			processMove(newX, newY);
			invalidate();
			requestLayout();
			break;
		}

		return true;
	}
}