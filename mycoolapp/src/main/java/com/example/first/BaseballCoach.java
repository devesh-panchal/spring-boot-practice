package com.example.first;

import org.springframework.stereotype.Component;

@Component
public class BaseballCoach implements Coach {
	
	public BaseballCoach() {
		super();
		System.out.println("BaseballCoach constructor");
	}

	@Override
	public String getDailyWorkout() {
		// TODO Auto-generated method stub
		return "Spend 30 minutes in batting practice";
	}

}
