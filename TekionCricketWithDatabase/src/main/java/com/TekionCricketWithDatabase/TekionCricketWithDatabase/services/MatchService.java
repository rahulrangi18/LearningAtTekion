package com.TekionCricketWithDatabase.TekionCricketWithDatabase.services;
import java.util.List;
public interface MatchService {
    List<Match> viewByTeam(String teamName);
    Match viewById(String id);
    List<Match> showAll();
    Match startMatch();
    void insertMatch(Match match);
    void deleteAll();
}
