package lpoo.proj2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.regex.Pattern;

public class Main extends Activity
{
	private TextView txtUsername;
	private TextView txtIP;
	private TextView txtPort;
	private Spinner spnColor;
	private Button btnConnect;
	private Button btnExit;
	private AlertDialog.Builder alertWifi;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		txtIP = (TextView) findViewById(R.id.txtIP);
		txtPort = (TextView) findViewById(R.id.txtPort);
		txtUsername = (TextView) findViewById(R.id.txtUsername);
		spnColor = (Spinner) findViewById(R.id.spnColor);
		btnConnect = (Button) findViewById(R.id.btnConnect);
		btnExit = (Button) findViewById(R.id.btnExit);
		txtIP.addTextChangedListener(IPAddressTextWatcher);
		txtPort.addTextChangedListener(PortTextWatcher);
		txtUsername.addTextChangedListener(UsernameTextWatcher);

		final ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		final WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);

		alertWifi = new AlertDialog.Builder(this);
		alertWifi.setMessage("No active network connections, do you want to enable wireless?");
		alertWifi.setTitle("Wi-Fi Disabled");

		alertWifi.setPositiveButton("Yes", new DialogInterface.OnClickListener()
		{
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				wifiManager.setWifiEnabled(true);
			}
		});

		alertWifi.setNegativeButton("No", null);
		alertWifi.setCancelable(false);

		btnConnect.setOnClickListener((new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected())
				{
					final Bundle b = new Bundle();
					final Intent myIntent = new Intent(Main.this, Game.class);

					b.putCharSequence("prefIP", txtIP.getText());
					b.putInt("prefPort", Integer.parseInt(txtPort.getText().toString()));
					b.putCharSequence("prefUsername", txtUsername.getText());
					b.putInt("prefColor", spnColor.getSelectedItemPosition());
					myIntent.putExtras(b);
					startActivity(myIntent);
				}
				else
				{
					alertWifi.show();
				}
			}
		}));

		btnExit.setOnClickListener((new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				finish();
			}
		}));
	}

	private final TextWatcher IPAddressTextWatcher = new TextWatcher()
	{
		private String mPreviousText = "";
		private final String mRegexPattern = "^((25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[0-9])\\.){0,3}((25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[0-9])){0,1}$"; 
		private final Pattern mRegex = Pattern.compile(mRegexPattern);

		public boolean validate(String str)
		{
			return mRegex.matcher(str).matches();
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after)
		{
			btnConnect.setEnabled(true);
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count)
		{
		}

		@Override
		public void afterTextChanged(Editable s)
		{
			if (validate(s.toString()))
			{
				mPreviousText = s.toString();
				btnConnect.setEnabled(true);
			}
			else
			{
			    s.replace(0, s.length(), mPreviousText);
				btnConnect.setEnabled(false);
			}
		}
	};

	private final TextWatcher UsernameTextWatcher = new TextWatcher()
	{
		public boolean validate(String str)
		{
			if (str == null || str.trim().length() == 0)
			{
				return false;
			}

			str = str.trim();

			return true;
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after)
		{
			btnConnect.setEnabled(true);
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count)
		{
		}

		@Override
		public void afterTextChanged(Editable s)
		{
			if (validate(s.toString()))
			{
				btnConnect.setEnabled(true);
			}
			else
			{
				btnConnect.setEnabled(false);
			}
		}
	};

	private final TextWatcher PortTextWatcher = new TextWatcher()
	{
		public boolean validate(String str)
		{
			int portNumber;

			try
			{
				portNumber = Integer.parseInt(str);
			}
			catch (final NumberFormatException e)
			{
				return false;
			}

			return portNumber > 0 && portNumber <= 65536;
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after)
		{
			btnConnect.setEnabled(true);
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count)
		{
		}

		@Override
		public void afterTextChanged(Editable s)
		{
			if (validate(s.toString()))
			{
				btnConnect.setEnabled(true);
			}
			else
			{
				btnConnect.setEnabled(false);
			}
		}
	};

	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState)
	{
		txtIP.setText(savedInstanceState.getCharSequence("prefIP", "127.0.0.1"));
		txtPort.setText(Integer.toString(savedInstanceState.getInt("prefPort", 8080)));
		txtUsername.setText(savedInstanceState.getCharSequence("prefUsername", "Anonymous"));
		spnColor.setSelection(savedInstanceState.getInt("prefColor", 0));
		
		super.onRestoreInstanceState(savedInstanceState);
	}

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState)
	{
		savedInstanceState.putCharSequence("prefIP", txtIP.getText());
		savedInstanceState.putInt("prefPort", Integer.parseInt(txtPort.getText().toString()));
		savedInstanceState.putCharSequence("prefUsername", txtUsername.getText());
		savedInstanceState.putInt("prefColor", spnColor.getSelectedItemPosition());
		
		super.onSaveInstanceState(savedInstanceState);
	}
}