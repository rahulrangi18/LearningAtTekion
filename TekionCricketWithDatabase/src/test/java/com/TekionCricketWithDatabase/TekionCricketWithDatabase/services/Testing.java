package com.TekionCricketWithDatabase.TekionCricketWithDatabase.services;

import com.TekionCricketWithDatabase.TekionCricketWithDatabase.models.Match;
import com.TekionCricketWithDatabase.TekionCricketWithDatabase.repository.CricketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class Testing {

    private MatchServiceImp matchServiceImp;

    @Mock
    private CricketRepository cricketRepository;

    @BeforeEach
    public void setUp() {
        matchServiceImp = new MatchServiceImp(cricketRepository, new Match());
    }

    @Test
    public void testViewByTeam() {
        List<Match> matches = new ArrayList<>();
        matches.add(new Match());
        when(cricketRepository.findByTeam1Name("India")).thenReturn(matches);
        when(cricketRepository.findByTeam2Name("India")).thenReturn(matches);

        List<Match> result = matchServiceImp.viewByTeam("India");

        verify(cricketRepository, times(1)).findByTeam1Name("India");
        verify(cricketRepository, times(1)).findByTeam2Name("India");
        assertEquals(matches.size() * 2, result.size());
    }

    @Test
    public void testViewByTeamNotFound() {
        when(cricketRepository.findByTeam1Name("Australia")).thenReturn(new ArrayList<>());
        when(cricketRepository.findByTeam2Name("Australia")).thenReturn(new ArrayList<>());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            matchServiceImp.viewByTeam("Australia");
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals("Match not found", exception.getReason());
    }

    @Test
    public void testViewById() {
        Match match = new Match();
        when(cricketRepository.existsById("1")).thenReturn(true);
        when(cricketRepository.findById("1")).thenReturn(Optional.of(match));

        Match result = matchServiceImp.viewById("1");

        verify(cricketRepository, times(1)).findById("1");
        assertEquals(match, result);
    }

    @Test
    public void testViewByIdNotFound() {
        when(cricketRepository.existsById("1")).thenReturn(false);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            matchServiceImp.viewById("1");
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals("Match not found", exception.getReason());
    }

    @Test
    public void testShowAll() {
        List<Match> matches = new ArrayList<>();
        matches.add(new Match());
        when(cricketRepository.findAll()).thenReturn(matches);

        List<Match> result = matchServiceImp.showAll();

        verify(cricketRepository, times(1)).findAll();
        assertEquals(matches, result);
    }

    @Test
    public void testStartMatch() {
        Match match = new Match();
        doNothing().when(cricketRepository).insert(match);

        Match result = matchServiceImp.startMatch();

        verify(cricketRepository, times(1)).insert(match);
        assertEquals(match, result);
    }
}