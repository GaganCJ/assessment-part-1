package com.training.assessment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.training.assessment.controller"})
public class AssessmentManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssessmentManagementApplication.class, args);
	}

}
