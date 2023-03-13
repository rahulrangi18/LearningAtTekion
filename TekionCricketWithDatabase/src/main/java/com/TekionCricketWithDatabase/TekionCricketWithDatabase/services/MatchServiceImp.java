package com.TekionCricketWithDatabase.TekionCricketWithDatabase.services;

import com.TekionCricketWithDatabase.TekionCricketWithDatabase.models.Match;
import com.TekionCricketWithDatabase.TekionCricketWithDatabase.repository.CricketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MatchServiceImp implements MatchService {
    @Autowired
    private final CricketRepository cricketRepository;
    @Autowired
    private final Match match;

    private final Game game;
    public List<Match> viewByTeam(String teamName) {
        List<Match> matches = cricketRepository.findByTeam1Name(teamName);
        matches.addAll(cricketRepository.findByTeam2Name(teamName));
        if (matches.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Match not found");
        return matches;
    }

    public Match viewById(String id) {
        if (cricketRepository.existsById(id)) {
            Optional<Match> match = cricketRepository.findById(id);
            return match.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Match not found");
    }

    public List<Match> showAll() {
        return cricketRepository.findAll();
    }
    public Match startMatch() {
        game.startGame(match);
        //Match match = new Match();
        insertMatch(match);
        return match;
    }

    public void insertMatch(Match match) {
        try {
            cricketRepository.insert(match);
            System.out.println("Added to Database");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Adding to db failed");
        }
    }

    public void deleteAll() {
        cricketRepository.deleteAll();
    }
}