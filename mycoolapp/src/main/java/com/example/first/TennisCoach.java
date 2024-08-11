/**
 * 
 */
package com.example.first;

import org.springframework.stereotype.Component;

/**
 * 
 */

@Component
public class TennisCoach implements Coach {
	
	

	public TennisCoach() {
		super();
		System.out.println("TennisCoach constructor");
	}

	@Override
	public String getDailyWorkout() {
		// TODO Auto-generated method stub
        return "Practice your backhand volley";
	}

}
