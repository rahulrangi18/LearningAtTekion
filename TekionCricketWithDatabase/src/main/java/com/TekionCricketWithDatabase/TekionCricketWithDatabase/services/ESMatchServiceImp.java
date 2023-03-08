package com.TekionCricketWithDatabase.TekionCricketWithDatabase.services;

import com.TekionCricketWithDatabase.TekionCricketWithDatabase.models.Match;
import com.TekionCricketWithDatabase.TekionCricketWithDatabase.repository.CricketRepositoryES;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class ESMatchServiceImp implements MatchService {

    @Autowired
    private CricketRepositoryES cricketRepositoryES;
    public List<Match> ESviewByTeam(String teamName) {
        return cricketRepositoryES.findByTeam1NameOrTeam2Name(teamName, teamName);
    }


    public Match ESviewById(String id) {
        return cricketRepositoryES.findById(id).orElse(null);
    }

    public List<Match> ESshowAll() {
        return (List<Match>) cricketRepositoryES.findAll();
    }

    public void ESinsertMatch(Match match) {
        cricketRepositoryES.save(match);
    }
    public void ESdeleteAll() {
        cricketRepositoryES.deleteAll();
    }

    @Override
    public List<Match> ESsearchByTeam(String teamName) {
        return cricketRepositoryES.findByTeam1NameOrTeam2Name(teamName, teamName);
    }
    //No need
    public Match ESstartMatch() {
        return null;
    }
    //No need
    public List<Match> ESsearchByResult(String result) {
        return null;
    }
}
