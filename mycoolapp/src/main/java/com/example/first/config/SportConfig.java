package com.example.first.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.first.Coach;
import com.example.first.SwimCoach;

@Configuration
public class SportConfig {
	
	@Bean("aqua")
	public Coach swimCoach() {
		return new SwimCoach();
		
	}

}
