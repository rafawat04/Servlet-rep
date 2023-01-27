package model;

import java.io.Serializable;

public class Mutter implements Serializable{
	private String userName;
	private String text;
	private int id;

	public Mutter() {}
	public Mutter(String userName, String text) {
		this.userName = userName;
		this.text = text;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public Mutter(int id, String userName, String text) {
		this.id = id;
		this.userName = userName; //this.(userName , text)
		this.text = text;
	}
	public String getUserName() {
		return userName;
	}

	public String getText() {
		return text;
	}

}
