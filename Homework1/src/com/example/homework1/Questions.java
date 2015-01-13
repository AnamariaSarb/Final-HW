package com.example.homework1;

import com.example.homework1.R;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Questions extends Activity implements OnItemClickListener {
	private EditText age, name;
	RadioGroup rg_breed, rg_sex;
	private RadioButton dog1, dog2, dog3, dog4, dog5, rb_f, rb_m;
	private Button done;
	public static String final_dog, final_name, final_sex, final_age,
			final_dogimage;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.questions);

		age = (EditText) findViewById(R.id.etage);
		name = (EditText) findViewById(R.id.etname);
		rg_breed = (RadioGroup) findViewById(R.id.radioGroup1);
		rg_sex = (RadioGroup) findViewById(R.id.RadioGroup02);
		dog1 = (RadioButton) findViewById(R.id.radio_pug);
		dog2 = (RadioButton) findViewById(R.id.radio_rottweiler);
		dog3 = (RadioButton) findViewById(R.id.labr);
		dog4 = (RadioButton) findViewById(R.id.boxer);
		dog5 = (RadioButton) findViewById(R.id.bichon);

		rb_f = (RadioButton) findViewById(R.id.female);
		rb_m = (RadioButton) findViewById(R.id.male);

		done = (Button) findViewById(R.id.bdone);

		done.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				switch (rg_breed.getCheckedRadioButtonId()) {
				case R.id.radio_pug:
					final_dog = dog1.getText().toString();
					final_dogimage = "pug";
					break;
				case R.id.radio_rottweiler:
					final_dog = dog2.getText().toString();
					final_dogimage = "rottweiler";
					break;
				case R.id.labr:
					final_dog = dog3.getText().toString();
					final_dogimage = "labrador";
					break;
				case R.id.boxer:
					final_dog = dog4.getText().toString();
					final_dogimage = "boxer";
					break;
				case R.id.bichon:
					final_dog = dog5.getText().toString();
					final_dogimage = "bichon";
					break;
				}
				switch (rg_sex.getCheckedRadioButtonId()) {
				case R.id.female:
					final_sex = rb_f.getText().toString();
					break;
				case R.id.male:
					final_sex = rb_m.getText().toString();
					break;
				}
				final_age = age.getText().toString();
				final_name = name.getText().toString();

				if ((dog1.isChecked() || dog2.isChecked() || dog3.isChecked()
						|| dog4.isChecked() || dog5.isChecked())
						&& (rb_m.isChecked() || rb_f.isChecked())
						&& final_age != null & final_name != null) {
					Intent j = new Intent(Questions.this, PetActivity.class);
					startActivity(j);
				} else {
					Toast.makeText(getApplicationContext(),
							"Please answer all the questions!",
							Toast.LENGTH_LONG).show();
				}
			}
		});

	}

	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub

	}
}
