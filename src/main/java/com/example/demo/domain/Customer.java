package com.example.demo.domain;

import java.math.BigInteger;

import org.springframework.data.mongodb.core.mapping.Document;

import com.google.gson.Gson;

@Document(collection = "customer")
public class Customer {
	private BigInteger id;
	private String firstName;
	private String lastName;

	public Customer(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Customer(BigInteger id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
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

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

}
