package com.TekionCricketWithDatabase.TekionCricketWithDatabase.controllers;

import com.TekionCricketWithDatabase.TekionCricketWithDatabase.services.Match;
import com.TekionCricketWithDatabase.TekionCricketWithDatabase.repository.CricketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MatchController {

    @Autowired
    private CricketRepository cricketRepository;

    @GetMapping("/")
    public String mainPage(){
        String A="Namashkar Main Aakash Chopra, Swagat krta hu hamare 'Tekion Premier League (TPL) me'";
        String B="Intructions:-";
        String C="1.) /start -> Play the match";
        String D="2.) /check -> show all data";
        String E="4.) /del -> delete all";
        String finalMsg=A+"\n"+B+"\n"+C+"\n"+D+"\n"+E;
        return finalMsg;
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
    public String deleteAll(){
        try{
            cricketRepository.deleteAll();
            return "Success";
        }
        catch (Exception e){
            return "Try again";
        }
    }
}
