package lpoo.proj2;

import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main extends Activity
{
	private TextView txtUsername;
	private TextView txtIP;
	private TextView txtPort;
	private Button btnConnect;
	private Button btnExit;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		
		txtIP = (TextView) findViewById(R.id.txtIP);
		txtPort = (TextView) findViewById(R.id.txtPort);
		txtUsername = (TextView) findViewById(R.id.txtUsername);
		btnConnect = (Button) findViewById(R.id.btnConnect);
		btnExit = (Button) findViewById(R.id.btnExit);
		txtIP.addTextChangedListener(IPAddressTextWatcher);
		txtPort.addTextChangedListener(PortTextWatcher);
		txtUsername.addTextChangedListener(UsernameTextWatcher);
		
		btnConnect.setOnClickListener((new View.OnClickListener()
		{
			public void onClick(View v)
			{
				Bundle b = new Bundle();
				Intent myIntent = new Intent(Main.this, Game.class);

				b.putCharSequence("prefIP", txtIP.getText());
				b.putInt("prefPort", Integer.parseInt(txtPort.getText().toString()));
				b.putCharSequence("prefUsername", txtUsername.getText());
				myIntent.putExtras(b);
				startActivity(myIntent);

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

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.main, menu);

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
}