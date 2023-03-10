package com.TekionCricketFinal.TekionCricketFinal.services;

import com.TekionCricketFinal.TekionCricketFinal.models.Match;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

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

    private final Game game;
    @Override
    public Page<Match> viewByTeam(String teamName) {
        return matchElasticSearchService.findByTeam(teamName);
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
        game.startGame(match);
        insertMatch(match);
        return match;
    }

    @Override
    public void insertMatch(Match match) {
        matchElasticSearchService.save(match);
        matchMongoService.save(match);
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