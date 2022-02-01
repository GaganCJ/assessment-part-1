package com.training.assessment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = {"com.training.assessment.controller"})
public class AssessmentManagementApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(AssessmentManagementApplication.class, args);
	}

}
