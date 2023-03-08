package com.TekionCricketWithDatabase.TekionCricketWithDatabase;
import com.TekionCricketWithDatabase.TekionCricketWithDatabase.repository.CricketRepositoryMongo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
@EnableMongoRepositories(basePackageClasses = CricketRepositoryMongo.class)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class TekionCricketWithDatabaseApplication {
    public static void main(String[] args) {

        SpringApplication.run(TekionCricketWithDatabaseApplication.class, args);
    }
}