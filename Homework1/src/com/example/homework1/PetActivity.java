package com.example.homework1;

import com.example.homework1.R;

import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import android.app.Activity;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class PetActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pet);
		Questions q = new Questions();
		Resources res = this.getResources();
		int resID = 0;
		if (q.final_dogimage.equals("pug")) {
			resID = res.getIdentifier("fpug", "drawable", this.getPackageName());
		} else if (q.final_dogimage.equals("rottweiler")) {
			resID = res.getIdentifier("rw", "drawable", this.getPackageName());
		} else if (q.final_dogimage.equals("labrador")) {
			resID = res.getIdentifier("labrador", "drawable",
					this.getPackageName());
		} else if (q.final_dogimage.equals("boxer")) {
			resID = res.getIdentifier("bx", "drawable", this.getPackageName());
		} else if (q.final_dogimage.equals("bichon")) {
			resID = res.getIdentifier("bi", "drawable", this.getPackageName());
		}

		String strJson = "{ " + " \"questions\":[ " +

		"{" + "\"name\": " + q.final_name + "," + "\"dog_image\": " + resID
				+ "," + "\"age\":" + q.final_age + "," + "\"sex\":"
				+ q.final_sex + "}," + "]" + "} ";

		/** The parsing of the xml data is done in a non-ui thread */
		ListViewLoaderTask listViewLoaderTask = new ListViewLoaderTask();

		/** Start parsing xml data */
		listViewLoaderTask.execute(strJson);

	}

	private class ListViewLoaderTask extends
			AsyncTask<String, Void, SimpleAdapter> {

		JSONObject jObject;

		/** Doing the parsing of xml data in a non-ui thread */
		@Override
		protected SimpleAdapter doInBackground(String... strJson) {
			try {
				jObject = new JSONObject(strJson[0]);
				PetJSONParser PetJSONParser = new PetJSONParser();
				PetJSONParser.parse(jObject);
				Log.d("JSON Message", " "
						+ jObject.getJSONArray("questions").length());
			} catch (Exception e) {
				Log.d("JSON Exception1", e.toString());
			}

			PetJSONParser PetJSONParser = new PetJSONParser();

			List<HashMap<String, String>> pets = null;

			try {
				/** Getting the parsed data as a List construct */
				pets = PetJSONParser.parse(jObject);
			} catch (Exception e) {
				Log.d("Exception", e.toString());
			}

			/** Keys used in Hashmap */
			String[] from = { "name", "image", "details" };

			/** Ids of views in listview_layout */
			int[] to = { R.id.tv_animalname, R.id.iv_animalimage,
					R.id.tv_details };

			/**
			 * Instantiating an adapter to store each items
			 * R.layout.listview_layout defines the layout of each item
			 */
			SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), pets,
					R.layout.lv_layout, from, to);

			return adapter;
		}

		/**
		 * Invoked by the Android system on "doInBackground" is executed
		 * completely
		 */
		/** This will be executed in ui thread */
		@Override
		protected void onPostExecute(SimpleAdapter adapter) {

			/** Getting a reference to listview of main.xml layout file */
			ListView listView = (ListView) findViewById(R.id.lv_dogs);

			/** Setting the adapter containing the pets list to listview */
			listView.setAdapter(adapter);
		}
	}

}