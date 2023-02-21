package com.TekionCricketWithDatabase.TekionCricketWithDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@SpringBootApplication
@RestController
@EnableMongoRepositories(basePackageClasses = CricketRepository.class)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class TekionCricketWithDatabaseApplication {
    @Autowired
    CricketRepository cricketRepository;

    public static void main(String[] args) {
        SpringApplication.run(TekionCricketWithDatabaseApplication.class, args);
    }

    @GetMapping("/")
    public String sm(){
        String A="Namashkar Main Aakash Chopra, Swagat krta hu hamare 'Tekion Premier League (TPL) me'";
        String B="Intructions:-";
        String C="1.) /start -> Play the match";
        String D="2.) /all -> show all data";
        String E="4.) /del -> delete all";
        String FinalMsg=A+"\n"+B+"\n"+C+"\n"+D+"\n"+E;
        return FinalMsg;
    }
    @GetMapping("/start")
    public Match startMatch( ){
        Match match = new Match();
        cricketRepository.insert(match);
        return match;
    }
    @GetMapping("/check")
    public List<Match> showAll(){
        return cricketRepository.findAll();
    }
    @GetMapping("/delete")
    public String del(){
        try{
            cricketRepository.deleteAll();
            return "Success";
        }
        catch (Exception e){
            return "Try again";
        }
    }
}