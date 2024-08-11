package com.example.first.rest;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;

import com.example.first.Coach;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class CoachController {
	


	 private Coach myCoach;
//	    private Coach anotherCoach;
	
	public CoachController( @Qualifier("aqua") Coach theCoach
            //@Qualifier("cricketCoach") Coach theAnotherCoach
            ) {
		super();
		System.out.println("coach constructor");
		 myCoach = theCoach;
//	        anotherCoach = theAnotherCoach;
	}
	
	
	public Coach getCoach() {
		return myCoach;
	}

//	@Autowired
//	public void setCoach(Coach coach) {
//		this.coach = coach;
//	}


	@GetMapping("/circket-coach")
	public String getDailyWorkout() {
		System.out.println(getCoach());
		return myCoach.getDailyWorkout();
	}
	
//	   @GetMapping("/check")
//	    public String check() {
//	        return "Comparing beans: myCoach == anotherCoach, " + (myCoach == anotherCoach);
//	    }
	

}
