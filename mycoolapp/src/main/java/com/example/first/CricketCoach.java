package com.example.first;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
//@Scope("prototype")
public class CricketCoach implements Coach {
	
	public CricketCoach() {
		super();
		System.out.println("CricketCoach constructor");
	}
	
//		@PostConstruct
//	   public void doMyStartupStuff() {
//	        System.out.println("In doMyStartupStuff(): " + getClass().getSimpleName());
//	    }
//
//		@PreDestroy
//		public void doMyCleanupStuff() {
//	        System.out.println("In doMyCleanupStuff(): " + getClass().getSimpleName());
//	    }
	@Override
	public String getDailyWorkout() {
        return "Practice fast bowling for 15 minutes";
	}

}
