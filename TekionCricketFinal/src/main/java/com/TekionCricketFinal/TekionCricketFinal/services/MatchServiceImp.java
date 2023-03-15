package com.TekionCricketFinal.TekionCricketFinal.services;

import com.TekionCricketFinal.TekionCricketFinal.models.Match;
import com.TekionCricketFinal.TekionCricketFinal.models.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Service
@RequiredArgsConstructor
public class MatchServiceImp implements MatchService {

    @Autowired
    private MatchElasticSearchService matchElasticSearchService;
    @Autowired
    private MatchMongoService matchMongoService;
    @Autowired
    private final Match match;
    @Autowired
    private final Game game;
    @Override
    public Page<Match> viewByTeam(String teamName) {
        Page<Match> matches = matchElasticSearchService.findByTeam(teamName);
        if (matches.isEmpty()) {
            matches = matchMongoService.findByTeam(teamName);
        }
        return matches;
    }

    @Override
    public Match viewById(String id) {
        Match match = matchElasticSearchService.findById(id);
        if (match == null) {
            match = matchMongoService.findById(id);
        }
        return match;
    }

    @Override
    public Page<Match> showAll() {
        Page<Match> page = matchElasticSearchService.findAll();
        if (page.isEmpty()) {
            page = matchMongoService.findAll();
        }
        return page;
    }

    @Override
    public Match startMatch() {
        boolean start=true;
        Team team1=new Team(start);
        Team team2=new Team(start);
        game.startGame(match,team1,team2);
        insertMatch(match);
        return match;
    }

    @Override
    public void insertMatch(Match match) {
        try {
            matchElasticSearchService.save(match);
            matchMongoService.save(match);
            System.out.println("Added to Database MongoDB and ElasticSearch");
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Adding to db failed");
        }
    }

    @Override
    public void deleteAll() {
        matchElasticSearchService.deleteAll();
        matchMongoService.deleteAll();
    }

    @Override
    public Page<Match> showAllES() {
        return matchElasticSearchService.findAll();
    }

    @Override
    public Page<Match> showAllMongo() {
        return matchMongoService.findAll();
    }

    @Override
    public List<Match> partialSearch(String name) {
        return matchElasticSearchService.partialSearch(name);
    }

}