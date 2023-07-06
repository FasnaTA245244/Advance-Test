package com.ust.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {
	
	@GetMapping("/forAll")
	public String greet()
	{
		return "Working";
	}
	
	@GetMapping("/admin")
	public String greetAdmin()
	{
		return "Admin@Work";
	}
	
	@GetMapping("/users")
	public String greetUser()
	{
		return "User@work";
	}

}
