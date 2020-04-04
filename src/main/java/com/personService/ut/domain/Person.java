package com.personService.ut.domain;

public class Person {

	private Integer personId;
	private String firstName;
	private String lastName;
	private String phone;
	
	
	
	
	public Person(Integer personId, String firstName, String lastName, String phone) {
		super();
		this.personId = personId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
	}

	public Person () {
		
	}
	
	public Integer getPersonId() {
		return personId;
	}
	public void setPersonId(Integer personId) {
		this.personId = personId;
	}
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	
}
