package com.spring.mvc.thymleaf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.spring.mvc.thymleaf.model.Student;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class StudentController {
	
	@Value("${countries}")
	private List<String> countries;
	
	@Value("${languages}")
	private List<String> languages;
	
	@Value("${systems}")
	private List<String> systems;
	
	 @GetMapping("/showStudentForm")
	    public String showForm(Model model) {
		 Student student = new Student();
		 
		 model.addAttribute("student", student);
		 
	     model.addAttribute("countries", countries);
	     
	     model.addAttribute("languages", languages);
	     
	     model.addAttribute("systems", systems);


		 
		 
	        return "student-form";
	    }
	 @PostMapping("/processStudentForm")
	 public String processStudentForm(@ModelAttribute("student") Student student) {
	 	//TODO: process POST request
	 	
		 System.out.println("theStudent: " + student.getFirstName() + " " + student.getLastName());

	        return "student-confirmation";
	 }
	 
}
