package lpoo.proj2;

import java.io.IOException;
import java.util.HashMap;

import lpoo.proj2.Network.GameOver;
import lpoo.proj2.Network.PlayerConnected;
import lpoo.proj2.Network.Login;
import lpoo.proj2.Network.PlayerDisconnected;
import lpoo.proj2.Network.ServerFull;
import lpoo.proj2.Network.UpdateScore;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Listener.ThreadedListener;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

@SuppressLint("UseSparseArrays")
public class Game extends Activity
{
	private final int MAX_PLAYERS = 2;
	private final int MIN_PLAYERS = 1;

	private AlertDialog.Builder alertConnection;
	private AlertDialog.Builder alertDisconnected;
	private AlertDialog.Builder alertForfeited;
	private AlertDialog.Builder alertGameover;
	private AlertDialog.Builder alertServerFull;
	private ProgressDialog progressConnect;
	private ProgressDialog progressWaiting;

	private Client client;
	private GameView gameView;
	private TextView scores[];
	private String playerName;
	private UpdateScore playerScores;
	private String serverHost;
	private int playerColor;
	private int serverPort;

	private int playersConnected = 0;

	private class ExitActivity implements DialogInterface.OnClickListener
	{
		@Override
		public void onClick(DialogInterface dialog, int which)
		{
			finish();
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.activity_game);

		alertConnection = new AlertDialog.Builder(this);
		alertConnection.setMessage("Could not connect to target computer... is the server running?");
		alertConnection.setTitle("Connection Error");
		alertConnection.setPositiveButton("OK", new ExitActivity());
		alertConnection.setCancelable(false);

		alertDisconnected = new AlertDialog.Builder(this);
		alertDisconnected.setMessage("You were disconnected from the server.");
		alertDisconnected.setTitle("Disconnected");
		alertDisconnected.setPositiveButton("OK", new ExitActivity());
		alertDisconnected.setCancelable(false);

		alertForfeited = new AlertDialog.Builder(this);
		alertForfeited.setMessage("Your opponent has forfeited the match.");
		alertForfeited.setTitle("YOU WIN!");
		alertForfeited.setPositiveButton("OK", new ExitActivity());
		alertForfeited.setCancelable(false);

		alertServerFull = new AlertDialog.Builder(this);
		alertServerFull.setMessage("Could not connect to target computer: server is full.");
		alertServerFull.setTitle("Connection Error");
		alertServerFull.setPositiveButton("OK", new ExitActivity());
		alertServerFull.setCancelable(false);

		progressConnect = new ProgressDialog(this);
		progressConnect.setTitle("Please wait...");
		progressConnect.setMessage("Connecting to server... (1/1)");
		progressConnect.setCancelable(false);
		progressConnect.show();
		
		progressWaiting = new ProgressDialog(this);
		progressWaiting.setTitle("Please wait...");
		progressWaiting.setMessage("Waiting for players... (0/2)");
		progressWaiting.setCancelable(false);

		alertGameover = new AlertDialog.Builder(this);
		playerScores = new UpdateScore();
		client = new Client();

		Network.register(client);
		Bundle b = getIntent().getExtras();

		serverHost = b.getCharSequence("prefIP").toString();
		serverPort = b.getInt("prefPort");
		playerName = b.getCharSequence("prefUsername").toString();
		playerColor = b.getInt("prefColor");

		new InitializeNetwork().start();

		client.addListener(new ThreadedListener(new Listener()
		{
			@Override
			public void connected(Connection connection)
			{
			}

			@Override
			public void received(Connection connection, Object object)
			{
				if (object instanceof PlayerConnected)
				{
					PlayerConnected playerConnected = (PlayerConnected) object;
					
					addPlayer(playerConnected.id);
					
					runOnUiThread(new Runnable()
					{
						public void run()
						{
							progressWaiting.setMessage("Waiting for players... (" + playersConnected + "/2)");
							
							if (playersConnected == MAX_PLAYERS && progressWaiting.isShowing())
							{
								progressWaiting.dismiss();
							}
						}
					});
				}
				else if (object instanceof GameOver)
				{
					runOnUiThread(new Runnable()
					{
						public void run()
						{
							alertGameover.setTitle("Game Over");

							if (playerScores.p1Score > playerScores.p2Score)
							{
								alertGameover.setMessage("Player 1 won the match!");
							}
							else
							{
								alertGameover.setMessage("Player 2 won the match!");
							}

							alertGameover.setPositiveButton("OK", new ExitActivity());
							alertGameover.setCancelable(false);
							alertGameover.show();
						}
					});
				}
				else if (object instanceof ServerFull)
				{
					runOnUiThread(new Runnable()
					{
						public void run()
						{
							alertServerFull.show();
						}
					});

					if (client != null)
					{
						client.close();
						client = null;
					}
				}
				else if (object instanceof UpdateScore)
				{
					final UpdateScore updateScore = (UpdateScore) object;

					runOnUiThread(new Runnable()
					{
						public void run()
						{
							scores[0].setText(Integer.toString(updateScore.p1Score));
							scores[1].setText(Integer.toString(updateScore.p2Score));
						}
					});
				}
				else if (object instanceof PlayerDisconnected)
				{
					PlayerDisconnected playerDisconnected = (PlayerDisconnected) object;
					removePlayer(playerDisconnected.id);

					runOnUiThread(new Runnable()
					{
						public void run()
						{
							alertForfeited.show();
						}
					});
				}
			}

			@Override
			public void disconnected(Connection connection)
			{
				if (client != null)
				{
					client.close();
					client = null;
				}

				runOnUiThread(new Runnable()
				{
					public void run()
					{
						alertDisconnected.show();
					}
				});
			}
		}));

		new Thread(client).start();

		gameView = (GameView) findViewById(R.id.gameView);
		gameView.setClient(client);
		gameView.setBitmap(playerColor);
		scores = new TextView[2];
		scores[0] = (TextView) findViewById(R.id.txtP1Score);
		scores[1] = (TextView) findViewById(R.id.txtP2Score);
	}

	private class InitializeNetwork extends Thread
	{
		@Override
		public void run()
		{
			try
			{
				client.connect(2000, serverHost, serverPort, serverPort + 1);

				runOnUiThread(new Runnable()
				{
					public void run()
					{
						if (progressConnect != null && progressConnect.isShowing())
						{
							progressConnect.dismiss();
							progressWaiting.show();
						}
					}
				});
			}
			catch (IOException ex)
			{
				runOnUiThread(new Runnable()
				{
					public void run()
					{
						if (progressConnect != null && progressConnect.isShowing())
						{
							progressConnect.dismiss();
						}

						if (alertConnection != null)
						{
							alertConnection.show();
						}
					}
				});

				if (client != null)
				{
					client.close();
					client = null;
				}
			}

			Login login = new Login();
			login.name = playerName;
			login.color = playerColor;

			if (client != null)
			{
				client.sendTCP(login);
			}
		}
	}

	private class CloseNetwork extends Thread
	{
		@Override
		public void run()
		{
			if (client != null)
			{
				client.close();
				client = null;
			}
		}
	}

	HashMap<Integer, Integer> players = new HashMap<Integer, Integer>();

	public void addPlayer(int id)
	{
		if (playersConnected < MAX_PLAYERS)
		{
			players.put(id, 0);
			playersConnected++;
		}
	}

	public void removePlayer(int id)
	{
		if (playersConnected > MIN_PLAYERS)
		{
			players.remove(id);
			playersConnected--;
		}
		else
		{
			alertForfeited.show();
			new CloseNetwork().start();
			System.exit(0);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.game_menu, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		if (item.getItemId() == R.id.action_disconnect)
		{
			new CloseNetwork().start();
			finish();
		}

		return super.onOptionsItemSelected(item);
	}
}