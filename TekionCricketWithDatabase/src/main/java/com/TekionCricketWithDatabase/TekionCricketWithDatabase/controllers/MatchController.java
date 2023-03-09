package com.TekionCricketWithDatabase.TekionCricketWithDatabase.controllers;

import com.TekionCricketWithDatabase.TekionCricketWithDatabase.models.Match;
import com.TekionCricketWithDatabase.TekionCricketWithDatabase.services.MatchMongoService;
import com.TekionCricketWithDatabase.TekionCricketWithDatabase.services.MatchService;
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

    // Elasticsearch
  //  @Autowired
   // private MatchElasticsearchService matchElasticsearchService;

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
        Match match = matchService.startMatch();
        // Elasticsearch
        //matchElasticsearchService.indexMatch(match);
        return match;
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

    record TeamName(String name){}

}
