package models;

import java.util.Observable;
import java.util.prefs.Preferences;

public class ModelUsername extends Observable {

	private String username;
	private Preferences preferences;
	
	public ModelUsername(){
		this.preferences = Preferences.userRoot().node("manganime");
		this.username = this.preferences.get("username", "");
	}	

	public ModelUsername(String username){
		this.username = username;
	}
	
	public void setUsername(String username){
		this.preferences.put("username", username);
		this.username = username;
		this.setChanged();
		this.notifyObservers();
	}
	
	public String getUsername(){
		return username;
	}
	
	public void erase() {
		this.preferences.put("username", "");
		this.username = "";
		this.setChanged();
		this.notifyObservers();
	}
	
	public void init(){
		this.setChanged();
		this.notifyObservers();
	}
}
