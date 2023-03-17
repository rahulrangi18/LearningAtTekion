package com.TekionCricketFinal.TekionCricketFinal.services;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.TekionCricketFinal.TekionCricketFinal.models.Match;
import com.TekionCricketFinal.TekionCricketFinal.repository.mongo.MongoCricketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
class MatchServiceTest {

    @Mock
    private MongoCricketRepository mongoCricketRepository;

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

        when(mongoCricketRepository.findByTeam1Name(any())).thenReturn(matches);
        when(mongoCricketRepository.findByTeam2Name(any())).thenReturn(matches);

        Page<Match> result = matchService.viewByTeam("testTeam");

        assertEquals(matches, result);
    }

    @Test
    void testViewByTeamNoMatchFound() {
        when(mongoCricketRepository.findByTeam1Name(any())).thenReturn(new ArrayList<>());
        when(mongoCricketRepository.findByTeam2Name(any())).thenReturn(new ArrayList<>());

        ResponseStatusException exception =
                org.junit.jupiter.api.Assertions.assertThrows(
                        ResponseStatusException.class, () -> matchService.viewByTeam("testTeam"));

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals("Match not found", exception.getReason());
    }

    @Test
    void testViewById() {
        String id = "testId";

        when(mongoCricketRepository.existsById(id)).thenReturn(true);
        when(mongoCricketRepository.findById(id)).thenReturn(Optional.of(match));

        Match result = matchService.viewById(id);

        assertEquals(match, result);
    }

    @Test
    void testViewByIdNoMatchFound() {
        String id = "testId";

        when(mongoCricketRepository.existsById(id)).thenReturn(false);

        ResponseStatusException exception =
                org.junit.jupiter.api.Assertions.assertThrows(
                        ResponseStatusException.class, () -> matchService.viewById(id));

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals("Match not found", exception.getReason());
    }

    @Test
    void testShowAll() {
        List<Match> matches = new ArrayList<>();
        matches.add(match);
        when(mongoCricketRepository.findAll()).thenReturn(matches);
        Page<Match> result = matchService.showAll();
        assertEquals(matches, result);
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