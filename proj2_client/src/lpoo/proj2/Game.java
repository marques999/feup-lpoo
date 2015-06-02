package lpoo.proj2;

import java.io.IOException;
import java.util.HashMap;

import lpoo.proj2.Network.AddPlayer;
import lpoo.proj2.Network.Login;
import lpoo.proj2.Network.RemovePlayer;
import lpoo.proj2.Network.ServerFull;
import lpoo.proj2.Network.UpdateScore;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Listener.ThreadedListener;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

@SuppressLint("UseSparseArrays")
public class Game extends Activity
{
	private final int MAX_PLAYERS = 2;
	private final int MIN_PLAYERS = 0;
	private AlertDialog.Builder alertConnection;
	private AlertDialog.Builder alertServerFull;
	private Client client;
	private String playerName;
	private GameView gameView;
	private int playerColor = 0;
	private int playersConnected = 0;
	private String serverHost;
	private int serverPort;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.activity_game);

		alertConnection = new AlertDialog.Builder(this);
		alertConnection.setMessage("Could not connect to target computer... is the server running?");
		alertConnection.setTitle("Connection error");
		alertConnection.setPositiveButton("OK", null);
		alertConnection.setCancelable(false);

		alertServerFull = new AlertDialog.Builder(this);
		alertServerFull.setMessage("Could not connect to target computer: server is full!");
		alertServerFull.setTitle("Connection error");
		alertServerFull.setPositiveButton("OK", null);
		alertServerFull.setCancelable(false);

		client = new Client();
		
		Network.register(client);
		Bundle b = getIntent().getExtras();
		
		serverHost = b.getCharSequence("prefIP").toString();
		serverPort = b.getInt("prefPort");
		playerName = b.getCharSequence("prefUsername").toString();

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
				if (object instanceof AddPlayer)
				{
					AddPlayer msg = (AddPlayer) object;
					addPlayer(msg.id);
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
				}
				else if (object instanceof UpdateScore)
				{
					UpdateScore msg = (UpdateScore) object;
					updateScore(msg.id, msg.score);
				}
				else if (object instanceof RemovePlayer)
				{
					RemovePlayer msg = (RemovePlayer) object;
					removePlayer(msg.id);
				}
				else
				{
					System.out.println("[kryonet]: recieved nothing!!!");
				}
			}

			@Override
			public void disconnected(Connection connection)
			{
				System.exit(0);
			}
		}));

		new Thread(client).start();
		
		gameView = (GameView) findViewById(R.id.gameView);
		gameView.setClient(client);
	}
	
	private class InitializeNetwork extends Thread
	{
		@Override
		public void run()
		{
			try
			{
				client.connect(2000, serverHost, serverPort, serverPort + 1);
			}
			catch (IOException ex)
			{
				runOnUiThread(new Runnable()
				{
					public void run()
					{
						alertConnection.show();
					}
				});
			}

			Login login = new Login();
			login.name = playerName;
			login.color = playerColor;
			client.sendTCP(login);
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
	}

	public void updateScore(int id, int score)
	{
		players.put(id, score);
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
		if (item.getItemId() == R.id.action_settings)
		{
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}