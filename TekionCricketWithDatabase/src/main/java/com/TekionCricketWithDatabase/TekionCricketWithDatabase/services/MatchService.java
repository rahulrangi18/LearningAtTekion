package com.TekionCricketWithDatabase.TekionCricketWithDatabase.services;

import com.TekionCricketWithDatabase.TekionCricketWithDatabase.models.Match;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

public interface MatchService {
    List<Match> viewByTeam(String teamName);
    Match viewById(String id);
    List<Match> showAll();
    Match startMatch();
    void insertMatch(Match match);
    void deleteAll();
}
