package com.tron.thymeleaf.models;

import java.io.Serializable;

public class Customer implements Serializable
{
    private int id;
    private String name;
    private String address;
    private long mobileno;
    private String email;
    private String date;
    private String gender;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getMobileno() {
		return mobileno;
	}
	public void setMobileno(long mobileno) {
		this.mobileno = mobileno;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public Customer(int id, String name, String address, long mobileno, String email, String date, String gender) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.mobileno = mobileno;
		this.email = email;
		this.date = date;
		this.gender = gender;
	}
	public Customer() {
		super();
			}
    
    
}
