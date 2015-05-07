package com.dmpm.tabletennis;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main extends Activity implements SensorEventListener
{
	private float mSensorX;
	private float mSensorY;
	private float mSensorZ;
	private MediaPlayer mPlayer;
	private Display mDisplay;
	private long mLastUpdate;
	private AlertDialog.Builder mAlert;
	private boolean mInitialized;
	private SensorManager mSensorManager;
	private Sensor mAccelerometer;
	private String mIPAddress;
	private int mPort;
	private Socket mSocket;

	private class ConnectThread extends Thread
	{
		public void run()
		{
			try
			{
				mSocket = new Socket(InetAddress.getByName(mIPAddress), mPort);
				mSocket.setSoTimeout(10*1000);
				}
			catch (IOException e1)
			{
				//finish();
			}
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.activity_main);

		mAlert = new AlertDialog.Builder(Main.this);
		mAlert.setMessage("Could not connect to target computer...");
		mAlert.setTitle("Connect to socket");
		mAlert.setPositiveButton("OK", null);
		mAlert.setCancelable(false);

		if (savedInstanceState == null)
		{
			getFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
		}

		Bundle b = getIntent().getExtras();

		mIPAddress = b.getCharSequence("txtIP").toString();
		mPort = b.getInt("txtPort");

		ConnectThread mThread = new ConnectThread();

		mThread.start();
		mDisplay = getWindowManager().getDefaultDisplay();
		mPlayer = new MediaPlayer();
		mInitialized = false;
		mLastUpdate = 0L;
		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);



		AssetFileDescriptor afd;

		try
		{
			afd = getAssets().openFd("racket.ogg");
			mPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
			afd.close();
			mPlayer.prepareAsync();
			mPlayer.setVolume(1f, 1f);
			mPlayer.setLooping(false);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		if (mAccelerometer == null)
		{
			mAlert = new AlertDialog.Builder(this);
			mAlert.setMessage("Your Android device must support accelerometer to play this game...");
			mAlert.setTitle("Accelerometer Sensor");
			mAlert.setPositiveButton("OK", null);
			mAlert.setCancelable(false);
			mAlert.create().show();
			finish();
		}
		else
		{
			mAlert = new AlertDialog.Builder(this);
			mAlert.setMessage("Your Android device supports accelerometer...");
			mAlert.setTitle("Accelerometer Sensor");
			mAlert.setPositiveButton("OK", null);
			mAlert.setCancelable(true);
			mAlert.create().show();
		}

		mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.main, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		return item.getItemId() == R.id.action_settings || super.onOptionsItemSelected(item);
	}

	@Override
	protected void onResume()
	{
		super.onResume();

		mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	protected void onPause()
	{
		super.onPause();

		mSensorManager.unregisterListener(this);
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy)
	{
	}

	public static class PlaceholderFragment extends Fragment
	{
		private View mView;

		public PlaceholderFragment()
		{
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
		{
			View rootView = inflater.inflate(R.layout.fragment_main, container, false);
			mView = rootView;


			return rootView;
		}
	}

	@Override
	public void onSensorChanged(SensorEvent arg0)
	{
		long curTime = System.currentTimeMillis();

		if ((curTime - mLastUpdate) > 50)
		{
			long diffTime = (curTime - mLastUpdate);
			mLastUpdate = curTime;

			if ((arg0.values[2] - mSensorZ) > 15 && (arg0.values[1] - mSensorY) < -3)
			{
				mPlayer.start();
			}

			switch (mDisplay.getRotation())
			{
				case Surface.ROTATION_0:
					mSensorX = arg0.values[0];
					mSensorY = arg0.values[1];
					break;
				case Surface.ROTATION_90:
					mSensorX = -arg0.values[1];
					mSensorY = arg0.values[0];
					break;
				case Surface.ROTATION_180:
					mSensorX = -arg0.values[0];
					mSensorY = -arg0.values[1];
					break;
				case Surface.ROTATION_270:
					mSensorX = arg0.values[1];
					mSensorY = -arg0.values[0];
					break;
			}

			mSensorZ = arg0.values[2];
		}
	}
}