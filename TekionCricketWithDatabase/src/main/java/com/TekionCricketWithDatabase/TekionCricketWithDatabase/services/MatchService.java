package com.TekionCricketWithDatabase.TekionCricketWithDatabase.services;

import com.TekionCricketWithDatabase.TekionCricketWithDatabase.models.Match;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MatchService {
    Page<Match> viewByTeam(String teamName);
    Match viewById(String id);
    Page<Match> showAll();
    Match startMatch();
    void insertMatch(Match match);
    void deleteAll();
    Page<Match> showAllES();
    Page<Match> showAllMongo();
    List<Match> partialSearch(String name);
}
