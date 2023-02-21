package com.learnSpring.learnSpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
//@Configuration
//@ComponentScan(com.learnSpring.learnSpring.topic.TopicRepository)
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
//@Component
//@RestController
public class LearnSpringAndDatabaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearnSpringAndDatabaseApplication.class, args);
		System.out.println("Running LearningSpringAndDatabase");
	}
}
