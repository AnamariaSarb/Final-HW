package com.example.homework1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PetJSONParser {
	
	/** Receives a JSONObject and returns a list */
	public List<HashMap<String,String>> parse(JSONObject jObject){		
		
		JSONArray jdogs = null;
		try {			
			/** Retrieves all the elements in the 'questions' array */
			jdogs = jObject.getJSONArray("questions");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		/** Invoking getdogs with the array of json object
		 * where each json object represent an dog
		 */
		return getdog(jdogs);
	}
	
	
	private List<HashMap<String, String>> getdog(JSONArray jdogs){
		int dogCount = jdogs.length();
		List<HashMap<String, String>> dogList = new ArrayList<HashMap<String,String>>();
		HashMap<String, String> dog = null;	

		/** Taking each dog, parses and adds to list object */
		for(int i=0; i<dogCount;i++){
			try {
				/** Call getdog with pets JSON object to parse the dog */
				dog = getdogs((JSONObject)jdogs.get(i));
				dogList.add(dog);

			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		
		return dogList;
	}
	
	/** Parsing the dog JSON object */
	private HashMap<String, String> getdogs(JSONObject jPets){

		HashMap<String, String> dog = new HashMap<String, String>();
		String Name = "";
		String dog_image="";
		String age = "";
		String sex = "";	
		try {
			Name = jPets.getString("name");
			dog_image = jPets.getString("dog_image");
			age = jPets.getString("age");
			sex = jPets.getString("sex");
			
			String details =        "Age : " + age + "\n" +
                     "Sex : " + sex + "\n";
			
			dog.put("name", Name);
			dog.put("image", dog_image);
			dog.put("details", details);
			
		} catch (JSONException e) {			
			e.printStackTrace();
		}		
		return dog;
	}
}