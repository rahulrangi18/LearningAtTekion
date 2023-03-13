package com.TekionCricketFinal.TekionCricketFinal;

import com.TekionCricketFinal.TekionCricketFinal.repository.mongo.MongoCricketRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = MongoCricketRepository.class)

public class TekionCricketFinalApplication {
	public static void main(String[] args) {
		SpringApplication.run(TekionCricketFinalApplication.class, args);
	}

}
