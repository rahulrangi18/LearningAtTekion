package com.TekionCricketFinal.TekionCricketFinal.controllers;

import com.TekionCricketFinal.TekionCricketFinal.models.Match;
import com.TekionCricketFinal.TekionCricketFinal.services.MatchService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    @GetMapping("/view-matches-by-timestamp/{matchDate}")
    public Page<Match> viewMatchesByDate(@PathVariable("matchDate") String matchDate) {
        return matchService.viewMatchesByDate(matchDate);
    }
    @GetMapping("/view-matches-by-date/{partialDate}")
    public Page<Match> viewMatchesByPartialDate(@PathVariable("partialDate") String partialDate) {
        return matchService.viewMatchesByPartialDate(partialDate);
    }
    @GetMapping("/view-team-and-sort-by-date-asc")
    public Page<Match> viewByTeamAndSortByDateAsc() {
        return matchService.viewByTeamAndSortByDateAsc();
    }

    @GetMapping("/view-team-and-sort-by-date-desc")
    public Page<Match> viewByTeamAndSortByDateDesc() {
        return matchService.viewByTeamAndSortByDateDesc();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMatchById(@PathVariable("id") String id) {
        matchService.deleteMatchById(id);
        return ResponseEntity.ok("Match with ID: " + id + " is deleted successfully");
    }

    @DeleteMapping("/del-all")
    public ResponseEntity<String> deleteMatches() {
        matchService.deleteAll();
        return ResponseEntity.ok("All the Matches are Deleted from the Databases");
    }
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
