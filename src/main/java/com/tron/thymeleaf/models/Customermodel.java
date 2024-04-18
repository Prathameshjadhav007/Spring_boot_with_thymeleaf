package com.tron.thymeleaf.models;

import java.util.ArrayList;
import java.util.List;

public class Customermodel {

	public Customer find()
	{
	return new Customer(1,"pj","pune",98448989,"pj@123","23/2/2022","male");
	}
	
	public List<Customer> findall()
	{
		List<Customer> customer=new ArrayList<Customer>();
		customer.add(new Customer(1,"pj","pune",98448989,"pj@123","16/01/2002","male"));
		customer.add(new Customer(2,"rj","satara",96778989,"rj@123","09/07/2003","female"));
		customer.add(new Customer(3,"sj","sangli",98448989,"sj@123","11/03/2005","male"));
		return customer;
	}
}
