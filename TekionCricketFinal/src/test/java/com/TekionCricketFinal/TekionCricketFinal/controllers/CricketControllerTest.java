import com.TekionCricketFinal.TekionCricketFinal.controllers.CricketController;
import com.TekionCricketFinal.TekionCricketFinal.models.Match;
import com.TekionCricketFinal.TekionCricketFinal.models.Team;
import com.TekionCricketFinal.TekionCricketFinal.services.MatchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class CricketControllerTest {
    @Mock
    private MatchService matchService;

    @InjectMocks
    private CricketController cricketController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testShowAll() {
        // Create some dummy matches
        Match match1 = new Match(new Team("Team 1"), new Team("Team 2"), new ArrayList<>(), new ArrayList<>(), 0);
        Match match2 = new Match(new Team("Team 3"), new Team("Team 4"), new ArrayList<>(), new ArrayList<>(), 0);
        List<Match> matches = new ArrayList<>();
        matches.add(match1);
        matches.add(match2);
        Page<Match> expectedPage = new PageImpl<>(matches);
        when(matchService.showAll()).thenReturn(expectedPage);
        Page<Match> resultPage = cricketController.showAll();
        assertEquals(expectedPage, resultPage);
    }
    @Test
    public void testStartMatch() {
        Match match = new Match(new Team("Team 1"), new Team("Team 2"), new ArrayList<>(), new ArrayList<>(), 0);
        when(matchService.startMatch()).thenReturn(match);
        Match resultMatch = cricketController.startMatch();
        assertEquals(match, resultMatch);
    }
    @Test
    public void testViewById() {
        String matchId = "123";
        Match match = new Match(new Team("Team 1"), new Team("Team 2"), new ArrayList<>(), new ArrayList<>(), 0);
        when(matchService.viewById(anyString())).thenReturn(match);
        Match resultMatch = cricketController.viewById(matchId);
        assertEquals(match, resultMatch);
    }
}
