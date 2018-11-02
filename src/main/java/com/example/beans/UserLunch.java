package com.example.beans;

import java.io.Serializable;

public class UserLunch implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;
	private String lunch;

	public UserLunch() {
		this.name = "";
		this.lunch = "";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLunch() {
		return lunch;
	}

	public void setLunch(String lunch) {
		this.lunch = lunch;
	}

}
