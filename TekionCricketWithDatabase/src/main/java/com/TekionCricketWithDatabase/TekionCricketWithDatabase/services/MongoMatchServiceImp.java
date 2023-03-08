package com.TekionCricketWithDatabase.TekionCricketWithDatabase.services;

import com.TekionCricketWithDatabase.TekionCricketWithDatabase.models.Match;
import com.TekionCricketWithDatabase.TekionCricketWithDatabase.repository.CricketRepositoryMongo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public abstract class MongoMatchServiceImp implements MatchService {
    @Autowired
    private final CricketRepositoryMongo cricketRepositoryMongo;
    @Autowired
    private final Match match;

    private final Game game;
    public List<Match> viewByTeam(String teamName) {
        List<Match> matches = cricketRepositoryMongo.findByTeam1Name(teamName);
        matches.addAll(cricketRepositoryMongo.findByTeam2Name(teamName));
        if (matches.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Match not found");
        return matches;
    }

    public Match viewById(String id) {
        if (cricketRepositoryMongo.existsById(id)) {
            Optional<Match> match = cricketRepositoryMongo.findById(id);
            return match.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Match not found");
    }

    public List<Match> showAll() {
        return cricketRepositoryMongo.findAll();
    }
    public Match startMatch() {
        game.startGame(match);
        //Match match = new Match();
        insertMatch(match);
        return match;
    }

    public void insertMatch(Match match) {
        try {
            cricketRepositoryMongo.insert(match);
            System.out.println("Added to MongoDB Database");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Adding to MongoDB failed");
        }
    }

    public void deleteAll() {
        cricketRepositoryMongo.deleteAll();
    }
}