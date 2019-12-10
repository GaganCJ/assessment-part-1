package com.training.assessment.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.training.assessment.bean"})
@EnableJpaRepositories(basePackages = {"com.training.assessment.repository"})
@EnableTransactionManagement
public class RepoConfig {
	
}
