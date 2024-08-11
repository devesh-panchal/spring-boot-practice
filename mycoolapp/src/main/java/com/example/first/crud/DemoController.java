package com.example.first.crud;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/test")
public class DemoController {
	
	@GetMapping("/hello")
	public String getHelloWorld() {
		return "Hello World";
	}
	

}
