package lpoo.proj2;

import java.util.regex.Pattern;

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
		
		if (savedInstanceState != null)
		{
		    txtIP.setText(savedInstanceState.getCharSequence("prefIP"));
		    txtPort.setText(savedInstanceState.getInt("prefPort"));
		    txtUsername.setText(savedInstanceState.getCharSequence("prefUsername"));
		    spnColor.setSelection(savedInstanceState.getInt("prefColor"));
		}
	
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
			public void onClick(View v)
			{			
				if (connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected())
				{
					Bundle b = new Bundle();
					Intent myIntent = new Intent(Main.this, Game.class);
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
			public void onClick(View v)
			{
				finish();
			}
		}));
	}

	private final TextWatcher IPAddressTextWatcher = new TextWatcher()
	{
		private String mRegexPattern = "^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
		private Pattern mRegex = Pattern.compile(mRegexPattern);

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
				btnConnect.setEnabled(true);
			}
			else
			{
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
			catch (NumberFormatException e)
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
	public void onSaveInstanceState(Bundle savedInstanceState) 
	{
	    savedInstanceState.putCharSequence("prefIP", txtIP.getText());
	    savedInstanceState.putInt("prefPort", Integer.parseInt(txtPort.getText().toString()));
	    savedInstanceState.putCharSequence("prefUsername", txtUsername.getText());
	    savedInstanceState.putInt("prefColor", spnColor.getSelectedItemPosition());
	    
	    super.onSaveInstanceState(savedInstanceState);
	}
}