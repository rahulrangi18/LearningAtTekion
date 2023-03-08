package com.TekionCricketWithDatabase.TekionCricketWithDatabase.services;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.TekionCricketWithDatabase.TekionCricketWithDatabase.models.Match;
import com.TekionCricketWithDatabase.TekionCricketWithDatabase.repository.CricketRepositoryMongo;

class MatchServiceTest {

    @Mock
    private CricketRepositoryMongo cricketRepository;

    @Mock
    private MatchService matchService;

    private Match match;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        match = new Match();
    }

    @Test
    void testViewByTeam() {
        List<Match> matches = new ArrayList<>();
        matches.add(match);

        when(cricketRepository.findByTeam1Name(any())).thenReturn(matches);
        when(cricketRepository.findByTeam2Name(any())).thenReturn(matches);

        List<Match> result = matchService.viewByTeam("testTeam");

        //assertEquals(matches, result);
    }

    @Test
    void testViewByTeamNoMatchFound() {
        when(cricketRepository.findByTeam1Name(any())).thenReturn(new ArrayList<>());
        when(cricketRepository.findByTeam2Name(any())).thenReturn(new ArrayList<>());

        ResponseStatusException exception =
                org.junit.jupiter.api.Assertions.assertThrows(
                        ResponseStatusException.class, () -> matchService.viewByTeam("testTeam"));

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        //assertEquals("Match not found", exception.getReason());
    }

    @Test
    void testViewById() {
        String id = "testId";

        when(cricketRepository.existsById(id)).thenReturn(true);
        when(cricketRepository.findById(id)).thenReturn(Optional.of(match));

        Match result = matchService.viewById(id);

        //assertEquals(match, result);
    }

    @Test
    void testViewByIdNoMatchFound() {
        String id = "testId";

        when(cricketRepository.existsById(id)).thenReturn(false);

        ResponseStatusException exception =
                org.junit.jupiter.api.Assertions.assertThrows(
                        ResponseStatusException.class, () -> matchService.viewById(id));

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        //assertEquals("Match not found", exception.getReason());
    }

    @Test
    void testShowAll() {
        List<Match> matches = new ArrayList<>();
        matches.add(match);

        when(cricketRepository.findAll()).thenReturn(matches);

        List<Match> result = matchService.showAll();

        //assertEquals(matches, result);
    }

    @Test
    void testInsertMatch() {
        matchService.insertMatch(match);
    }

    @Test
    void testDeleteAll() {
        matchService.deleteAll();
    }
}