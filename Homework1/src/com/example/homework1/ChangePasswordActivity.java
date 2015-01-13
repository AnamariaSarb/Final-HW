package com.example.homework1;

import com.parse.Parse;
import com.parse.ParseUser;

import com.example.homework1.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePasswordActivity extends Activity {

	private EditText oldPassField, newPassField;
	Button ok, cancel;
	private static String password;

	ParseUser currentUser = ParseUser.getCurrentUser();

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_change_password);

		Parse.initialize(this, "BmbiDoWmcHnINiTcLUymM6lcP84rHYd5XrfC2gFW",
				"KY1MGCdC2HawkP4msjtNx4Y1V3qNNWNEbSlEfG82");
		getIntent();

		
		oldPassField = (EditText) findViewById(R.id.old_password);
		newPassField = (EditText) findViewById(R.id.new_password);
		ok = (Button) findViewById(R.id.button2);

		ok.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				changePass();

			}
		});

		cancel = (Button) findViewById(R.id.button1);
		cancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();

			}
		});

	}

	public void changePass() {
		 
          password=LoginActivity.DBpassword;
	    	if(oldPassField.getText().toString().equals(password) && newPassField.getText().length()>0)
	    	{
	    		ParseUser user = ParseUser.getCurrentUser();
	    		user.setPassword(newPassField.getText().toString());
	    		//user.put("password",newPassField.getText().toString());
       		user.saveInBackground();
       		Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
         		Intent i = new Intent(getApplicationContext(), LoginActivity.class);
              startActivity(i);
          }
         	  else
         	  {
         		  Toast.makeText(getApplicationContext(), "Invalid old/new password", Toast.LENGTH_SHORT).show();
         	  }
                  
	    
	 
	}

}