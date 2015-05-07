package com.dmpm.tabletennis;

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

import java.util.regex.*;

public class ConnectMenu extends Activity
{
	private final TextWatcher IPAddressTextWatcher = new TextWatcher()
	{
		private String mRegexPattern = "^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
		private Pattern mRegex = Pattern.compile(mRegexPattern);

		public boolean validateIPAddress(String ipAddress)
		{
			return mRegex.matcher(ipAddress).matches();
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
			if (validateIPAddress(s.toString()))
			{
				btnConnect.setEnabled(true);
			}
			else
			{
				btnConnect.setEnabled(false);
			}
		}
	};

	private TextView txtUsername;
	private TextView txtIP;
	private TextView txtPort;
	private Button btnConnect;
	private Button btnExit;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_connect_menu);

		txtIP = (TextView) findViewById(R.id.txtIP);
		txtIP.addTextChangedListener(IPAddressTextWatcher);
		txtPort = (TextView) findViewById(R.id.txtPort);
		txtUsername = (TextView) findViewById(R.id.txtUsername);
		btnConnect = (Button) findViewById(R.id.btnConnect);

		btnConnect.setOnClickListener((new View.OnClickListener()
		{
			public void onClick(View v)
			{

				Bundle b = new Bundle();
				b.putCharSequence("txtIP", txtIP.getText());
				b.putCharSequence("txtUsername", txtUsername.getText());
				b.putInt("txtPort", Integer.parseInt(txtPort.getText().toString()));

				Intent myIntent = new Intent(ConnectMenu.this, Main.class);
				myIntent.putExtras(b);

				startActivity(myIntent);
			}
		}));

		btnExit = (Button) findViewById(R.id.btnExit);
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
		getMenuInflater().inflate(R.menu.menu_connect_menu, menu);

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