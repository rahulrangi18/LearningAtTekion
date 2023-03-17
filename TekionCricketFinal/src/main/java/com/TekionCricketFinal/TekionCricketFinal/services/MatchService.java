package com.TekionCricketFinal.TekionCricketFinal.services;

import com.TekionCricketFinal.TekionCricketFinal.models.Match;
import org.springframework.data.domain.Page;

import java.util.List;
public interface MatchService {
    Page<Match> showAll();
    Match startMatch();
    void insertMatch(Match match);
    void deleteAll();
    Page<Match> showAllES();
    Page<Match> showAllMongo();
    List<Match> partialSearch(String name);
    List<Match> exactSearch(String name);
    Page<Match> viewByTeam(String teamName);
    Match viewById(String id);

    Page<Match> viewMatchesByDate(String matchDate);

    Page<Match> viewMatchesByPartialDate(String partialDate);

    Page<Match> viewByTeamAndSortByDateAsc();
    Page<Match> viewByTeamAndSortByDateDesc();
}
