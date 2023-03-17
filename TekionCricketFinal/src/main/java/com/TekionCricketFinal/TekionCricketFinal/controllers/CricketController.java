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
public class CricketController {
    @Autowired
    private MatchService matchService;

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

    @PostMapping(value = "/partial-search",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Match> partialSearch(@RequestBody @NotNull TeamName teamName){
        return matchService.partialSearch(teamName.name());
    }

    @PostMapping(value = "/exact-search",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Match> exactSearch(@RequestBody @NotNull TeamName teamName){
        return matchService.exactSearch(teamName.name());
    }

    @GetMapping("/view-team-and-sort-by-date-asc")
    public Page<Match> viewByTeamAndSortByDateAsc() {
        return matchService.viewByTeamAndSortByDateAsc();
    }

    @GetMapping("/view-team-and-sort-by-date-desc")
    public Page<Match> viewByTeamAndSortByDateDesc() {
        return matchService.viewByTeamAndSortByDateDesc();
    }
    @DeleteMapping("/del")
    public void deleteMatches(){
        matchService.deleteAll();
    }
    // testing
    @GetMapping("/es-all")
    public Page<Match> showAllES(){
        return matchService.showAllES();
    }
    @GetMapping("/mongo-all")
    public Page<Match> showAllMongo(){
        return matchService.showAllMongo();
    }
    record TeamName(String name){
    }
}
