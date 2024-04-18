package com.tron.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Customercontroller {

	
	//  controller for index2 page which inside in the template  and its for ajax code only not for the thymeleaf
	@GetMapping("demo")
	public String demo()
	{
		return "index2";
	}
}
