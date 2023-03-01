package com.TekionCricketWithDatabase.TekionCricketWithDatabase.services;
import com.TekionCricketWithDatabase.TekionCricketWithDatabase.repository.CricketRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class MatchServiceTest {

    @Autowired
    private MatchService matchService;

    @Autowired
    private CricketRepository cricketRepository;

    @Test
    @DisplayName("View By Team")
    void viewByTeam() {
        Match match = matchService.startMatch();
        List<Match> matches = matchService.viewByTeam(match.getTeam1().getName());
        List<Match> matches1 = matchService.viewByTeam(match.getTeam2().getName());
        assertTrue(matches.contains(match));
        assertTrue(matches1.contains(match));
    }

    @Test
    @DisplayName("View By Id")
    void viewById() {
        Match match = matchService.startMatch();
        Match match1 = matchService.viewById(match.getId());
        assertEquals(match, match1);
    }

    @Test
    @DisplayName("Show All")
    void showAll() {
        Match match = matchService.startMatch();
        Match match1 = matchService.startMatch();
        List<Match> matches = matchService.showAll();
        assertTrue(matches.contains(match));
        assertTrue(matches.contains(match1));
    }

    @Test
    @DisplayName("Start Match")
    void startMatch() {
        int n = matchService.showAll().size();
        matchService.startMatch();
        assertEquals(n+1, matchService.showAll().size());
    }

    @Test
    @DisplayName("Delete All")
    void deleteAll() {
        startMatch();
        matchService.deleteAll();
        assertEquals(0, matchService.showAll().size());
    }

    @Test
    @DisplayName("Insert Match")
    void insertMatch() {
        Match match = new Match();
        doNothing().when(cricketRepository).insert(match);
        matchService.insertMatch(match);
        Mockito.verify(cricketRepository, times(1)).insert(match);
    }
}
