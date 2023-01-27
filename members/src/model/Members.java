package model;

import java.io.Serializable;

public class Members implements Serializable{
	private int id;
	private String name;
	private String depart;
	private int age;
	private String updated;

	public Members() {}
	public Members(int id, String name, String depart, int age, String updated) {
		this.id = id;
		this.name = name;
		this.depart = depart;
		this.age= age;
		this.updated = updated;

	}

	public Members(String name,String depart, int age, String updated) {
		this.name = name;
		this.depart = depart;
		this.age= age;
		this.updated = updated;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepart() {
		return depart;
	}
	public void setDepart(String depart) {
		this.depart = depart;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getUpdated() {
		return updated;
	}
	public void setUpdated(String updated) {
		this.updated = updated;
	}



}
