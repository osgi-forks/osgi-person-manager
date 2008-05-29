package com.siemens.ct.pm.model.internal;

import com.siemens.ct.pm.model.IPerson;

public class Person implements IPerson {

	public Person(String firstName, String lastName, String company) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.company = company;
	}

	private String firstName;
	private String lastName;
	private String company;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return firstName + " " + lastName;
	}

}
