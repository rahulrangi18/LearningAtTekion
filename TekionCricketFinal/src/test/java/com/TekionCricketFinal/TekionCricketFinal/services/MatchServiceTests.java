package com.TekionCricketFinal.TekionCricketFinal.services;

import com.TekionCricketFinal.TekionCricketFinal.models.Match;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class MatchServiceTests{
    @Autowired
    private MatchService matchService;
    @Test
    public void testShowAll(){
        Match match = matchService.startMatch();
        Match match1 = matchService.startMatch();
        List<Match> matches = matchService.showAll().getContent();
        assertTrue(matches.contains(match));
        assertTrue(matches.contains(match1));
    }
    @Test
    public void testStartMatch(){
        int size = matchService.showAll().getContent().size();
        matchService.startMatch();
        assertEquals(size+1, matchService.showAll().getContent().size());
    }
    @Test
    public void testViewById(){
        Match match = matchService.startMatch();
        Match match1 = matchService.viewById(match.getMatchId());
        assertEquals(match, match1);
    }
    @Test
    public void testViewByTeam(){
        Match match = matchService.startMatch();
        List<Match> matches = matchService.viewByTeam(match.getTeam1().getName()).getContent();
        List<Match> matches1 = matchService.viewByTeam(match.getTeam2().getName()).getContent();
        assertTrue(matches.contains(match));
        assertTrue(matches1.contains(match));
    }
    @Test
    public void testDeleteAll(){
        testStartMatch();
        matchService.deleteAll();
        assertEquals(0, matchService.showAll().getContent().size());
    }
}