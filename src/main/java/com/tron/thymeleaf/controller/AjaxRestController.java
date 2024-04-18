package com.tron.thymeleaf.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tron.thymeleaf.models.Customer;
import com.tron.thymeleaf.models.Customermodel;





//rest controller for index2 page which inside in the template  and its for ajax code only not for the thymeleaf

@RestController
@RequestMapping("api/ajaxrest")
public class AjaxRestController {
     
	@GetMapping("demo1")
	public ResponseEntity<String> demo1()
	{
		try
		{
			ResponseEntity<String> responseEntity=new ResponseEntity<String>("demo1" ,HttpStatus.OK);
			return responseEntity;
		}
		catch(Exception e)
		{
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping("demo2/{fullName}")
	public ResponseEntity<String> demo2(@PathVariable("fullName") String fullname)
	{
		try
		{
			ResponseEntity<String> responseEntity=new ResponseEntity<String>("hi " +fullname ,HttpStatus.OK);
			return responseEntity;
		}
		catch(Exception e)
		{
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping("demo3")
	public ResponseEntity<Customer> demo3()
	{
		try
		{
			Customermodel cm=new Customermodel();
			ResponseEntity<Customer> responseEntity=new ResponseEntity<Customer>(cm.find(),HttpStatus.OK);
			return responseEntity;
		}
		catch(Exception e)
		{
			return new ResponseEntity<Customer>(HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping("demo4")
	public ResponseEntity<List<Customer>> demo4()
	{
		try
		{
			Customermodel cm=new Customermodel();
			ResponseEntity<List<Customer>> responseEntity=new ResponseEntity<List<Customer>>(cm.findall(),HttpStatus.OK);
			return responseEntity;
		}
		catch(Exception e)
		{
			return new ResponseEntity<List<Customer>>(HttpStatus.BAD_REQUEST);
		}
	}
}
