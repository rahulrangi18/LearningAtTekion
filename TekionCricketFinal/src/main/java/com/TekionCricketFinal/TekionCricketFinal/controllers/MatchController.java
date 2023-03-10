package com.TekionCricketFinal.TekionCricketFinal.controllers;

import com.TekionCricketFinal.TekionCricketFinal.models.Match;
import com.TekionCricketFinal.TekionCricketFinal.services.MatchService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MatchController {

    @Autowired
    private MatchService matchService;
    //private CricketRepository cricketRepository;

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

    @GetMapping("/check-all")
    public Page<Match> showAll(){
        return matchService.showAll();
    }

    @GetMapping("/start")
    public Match startMatch(){
        return matchService.startMatch();
    }
    @GetMapping("/view/{id}")
    public Match viewById(@PathVariable("id")String id){
        return matchService.viewById(id);
    }

    @PostMapping(
            value = "/view-team",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Page<Match> viewByTeam(@RequestBody @NotNull TeamName teamName){
        return matchService.viewByTeam(teamName.name());
    }

    @DeleteMapping("/del")
    public void deleteMatches(){
        matchService.deleteAll();
    }
    record TeamName(String name){

    }
}
