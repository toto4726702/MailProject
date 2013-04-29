package cn.edu.shiep.entity;

import java.io.Serializable;

public class User implements Serializable{

	private static final long serialVersionUID = 677484458789332877L;
	
	private int id;
	private String name;
	private String pass;
	private String email;
	private String contact;
	private String address;
	private String introduction;

	// getter¡¢setter

	@Override
	public String toString() {
		return this.id + "#" + this.name + "#" + this.email + "#"
				+ this.address + "#" + this.pass + "#" +this.contact + "#" + this.introduction;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	

}
