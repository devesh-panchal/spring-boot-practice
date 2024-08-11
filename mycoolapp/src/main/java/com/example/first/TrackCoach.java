package com.example.first;

import org.springframework.stereotype.Component;

@Component
public class TrackCoach implements Coach {

	public TrackCoach() {
		super();
		System.out.println("TrackCoach constructor");
	}
	@Override
	public String getDailyWorkout() {
		// TODO Auto-generated method stub
		return "Run a hard 5k!";
	}

}
