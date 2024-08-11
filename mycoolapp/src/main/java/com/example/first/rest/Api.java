package com.example.first.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Api {
	
	@Value("${name}")
	public String name;
	
	
	@GetMapping("/")
	public String Hello(){
		return "Hello "+name.toUpperCase();
		
	}

}
