package com.example.homework1;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Link")
public class Link extends ParseObject{

	public Link() {
		
	}
	public boolean isVisited(){
		return getBoolean("visited");
	}
	
	public void setVisited(boolean visit){
		put("visited", visit);
	}
	
	public String getDescription(){
		return getString("description");
	}
	
	public void setDescription(String description){
		put("description", description);
	}

	public void setUser(ParseUser currentUser) {
		put("user", currentUser);
	}
		

}
