package com.TekionCricketWithDatabase.TekionCricketWithDatabase.services;

import com.TekionCricketWithDatabase.TekionCricketWithDatabase.models.Match;

import java.util.List;

public interface MatchService {

    // methods for MongoDB implementation
    List<Match> viewByTeam(String teamName);
    Match viewById(String id);
    List<Match> showAll();
    Match startMatch();
    void insertMatch(Match match);
    void deleteAll();

    // methods for Elasticsearch implementation
    List<Match> ESsearchByTeam(String teamName);
    List<Match> searchByResult(String result);
    List<Match> ESviewByTeam(String teamName);
    Match ESviewById(String id);
    List<Match> ESshowAll();
    Match ESstartMatch();
    void ESinsertMatch(Match match);
    void ESdeleteAll();
}
