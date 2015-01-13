package com.example.homework1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends Activity {

	private EditText mUsernameField;
	private EditText mPasswordField;
	public String username;
	public static String DBpassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		mUsernameField = (EditText) findViewById(R.id.login_username);
		mPasswordField = (EditText) findViewById(R.id.login_password);
	}

	public void signIn(final View v) {
		v.setEnabled(false);
		ParseUser.logInInBackground(mUsernameField.getText().toString(),
				mPasswordField.getText().toString(), new LogInCallback() {
			@Override
			public void done(ParseUser user, ParseException e) {
				if (user != null) {

					username = mUsernameField.getText().toString();
					DBpassword=mPasswordField.getText().toString();
					Intent intent = new Intent(LoginActivity.this,
							HomepageActivity.class);
					startActivity(intent);
					finish();
				} else {
					// Sign up failed. Look at the ParseException to see
					// what happened.
					switch (e.getCode()) {
					case ParseException.USERNAME_TAKEN:
						Toast.makeText(getApplicationContext(),
								"This username has already been taken",
								Toast.LENGTH_LONG).show();

						break;
					case ParseException.USERNAME_MISSING:
						Toast.makeText(
								getApplicationContext(),
								"You must supply a username to register",
								Toast.LENGTH_LONG).show();

						break;
					case ParseException.PASSWORD_MISSING:
						Toast.makeText(
								getApplicationContext(),
								"You must supply a password to register",
								Toast.LENGTH_LONG).show();

						break;
					case ParseException.OBJECT_NOT_FOUND:
						Toast.makeText(getApplicationContext(),
								"Those credentials were invalid",
								Toast.LENGTH_LONG).show();

						break;
					}
					v.setEnabled(true);
				}
			}
		});
	}

	public void showRegistration(View v) {
		Intent intent = new Intent(this, RegisterActivity.class);
		startActivity(intent);
		finish();
	}
}
