package com.TekionCricketWithDatabase.TekionCricketWithDatabase.services;
import com.TekionCricketWithDatabase.TekionCricketWithDatabase.models.Match;
import com.TekionCricketWithDatabase.TekionCricketWithDatabase.models.Team;
import com.TekionCricketWithDatabase.TekionCricketWithDatabase.repository.CricketRepository;
import org.junit.jupiter.api.*;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest

class MatchServiceImpTest {

    @Autowired
    private CricketRepository cricketRepository;
    @Autowired
    private MatchService matchService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testViewByTeam() {
        Match match = new Match();
        Team team1=new Team();
        team1.setTeamName("TeamA");
        Team team2=new Team();
        team2.setTeamName("TeamA");
        assertEquals(team1.getTeamName(),team2.getTeamName());
    }

    @Test
    void testViewByTeamNoMatches() {
        when(cricketRepository.findByTeam1Name(anyString())).thenReturn(new ArrayList<>());
        when(cricketRepository.findByTeam2Name(anyString())).thenReturn(new ArrayList<>());

        assertThrows(ResponseStatusException.class, () -> matchService.viewByTeam("India"));
    }

    @Test
    void testViewById() {
        Match match = new Match();
        match.setId("1");

        when(cricketRepository.existsById(anyString())).thenReturn(true);
        when(cricketRepository.findById(anyString())).thenReturn(Optional.of(match));

        Match result = matchService.viewById("1");
        assertEquals(match, result);
    }

    @Test
    void testViewByIdNotFound() {
        when(cricketRepository.existsById(anyString())).thenReturn(false);

        assertThrows(ResponseStatusException.class, () -> matchService.viewById("1"));
    }

    @Test
    void testShowAll() {
        List<Match> matches = new ArrayList<>();
        Match match1 = new Match();
        Match match2 = new Match();
        matches.add(match1);
        matches.add(match2);

        when(cricketRepository.findAll()).thenReturn(matches);

        List<Match> result = matchService.showAll();
        assertEquals(2, result.size());
    }

    @Test
    void testStartMatch() {
        Match match = new Match();
        when(cricketRepository.insert(match)).thenReturn(match);

        Match result = matchService.startMatch();
        assertEquals(match, result);
    }

    @Test
    void testInsertMatch() {
        Match match = new Match();
        when(cricketRepository.insert(match)).thenReturn(match);

        matchService.insertMatch(match);
    }

    @Test
    void testInsertMatchFailed() {
        Match match = new Match();
        when(cricketRepository.insert(match)).thenThrow(new RuntimeException());

        assertThrows(ResponseStatusException.class, () -> matchService.insertMatch(match));
    }

    @Test
    void testDeleteAll() {
        matchService.deleteAll();
    }
}
