package com.example.homework1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class RegisterActivity extends Activity {

	private EditText mEmailField;
	private EditText mUsernameField;
	private EditText mPasswordField;
	private TextView mErrorField;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);

		mEmailField = (EditText) findViewById(R.id.register_email);
		mUsernameField = (EditText) findViewById(R.id.register_username);
		mPasswordField = (EditText) findViewById(R.id.register_password);
		mErrorField = (TextView) findViewById(R.id.error_messages);
	}

	

	public void register(final View v) {
		if (mEmailField.getText().length() == 0
				|| mUsernameField.getText().length() == 0
				|| mPasswordField.getText().length() == 0)
			return;

		v.setEnabled(false);
		ParseUser user = new ParseUser();
		user.setEmail(mEmailField.getText().toString());
		user.setUsername(mUsernameField.getText().toString());
		user.setPassword(mPasswordField.getText().toString());

		mErrorField.setText("");

		user.signUpInBackground(new SignUpCallback() {
			@Override
			public void done(ParseException e) {
				if (e == null) {
					Intent intent = new Intent(RegisterActivity.this,
							LoginActivity.class);
					startActivity(intent);
					finish();
				} else {
					// Sign up didn't succeed. Look at the ParseException
					// to figure out what went wrong
					switch (e.getCode()) {
					case ParseException.EMAIL_TAKEN:
						Toast.makeText(getApplicationContext(), "This e-mail has already been taken", Toast.LENGTH_LONG).show();
						break;
					case ParseException.EMAIL_MISSING:
						Toast.makeText(getApplicationContext(), "You must supply an e-mail address to register", Toast.LENGTH_LONG).show();
						break;
					case ParseException.INVALID_EMAIL_ADDRESS:
						Toast.makeText(getApplicationContext(), "You must supply a valid e-mail address to register", Toast.LENGTH_LONG).show();
						break;
					case ParseException.USERNAME_TAKEN:
						Toast.makeText(getApplicationContext(), "This username has already been taken", Toast.LENGTH_LONG).show();
						break;
					case ParseException.USERNAME_MISSING:
						Toast.makeText(getApplicationContext(), "You must supply a username to register", Toast.LENGTH_LONG).show();
						break;
					case ParseException.PASSWORD_MISSING:
						Toast.makeText(getApplicationContext(), "You must supply a password to register", Toast.LENGTH_LONG).show();
						break;
					default:
						mErrorField.setText(e.getLocalizedMessage());
					}
					v.setEnabled(true);
				}
			}
		});
	}

	public void showLogin(View v) {
		Intent intent = new Intent(this, LoginActivity.class);
		startActivity(intent);
		finish();
	}
}
