package com.TekionCricketFinal.TekionCricketFinal.services;

import java.util.ArrayList;

import com.TekionCricketFinal.TekionCricketFinal.models.Player;
import com.TekionCricketFinal.TekionCricketFinal.models.Role;
import com.TekionCricketFinal.TekionCricketFinal.models.Team;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class MethodTest {
    @Test
    public void testRunPerBall() {
        Player batsman = new Player("Batsman", Role.BATSMAN);
        Player allRounder = new Player("All-rounder", Role.ALL_ROUNDER);
        Player bowler = new Player("Bowler", Role.BOWLER);

        int batsmanRuns = RunsPerBall.getRuns(batsman);
        assertTrue(batsmanRuns >= -1 && batsmanRuns <= 6);

        int allRounderRuns = RunsPerBall.getRuns(allRounder);
        assertTrue(allRounderRuns >= -1 && allRounderRuns <= 6);

        int bowlerRuns = RunsPerBall.getRuns(bowler);
        assertTrue(bowlerRuns >= -1 && bowlerRuns <= 6);
    }
    @Test
    public void testPlayInnings(){
        Team team1 = new Team();
        Team team2 = new Team();
        Innings playInnings = new Innings(team1, team2, true);
        playInnings.startInnings();
        ArrayList<ArrayList<Character>> scoreBoard = playInnings.getScoreBoard();
        assertTrue(scoreBoard.size() <= 20);
        for(ArrayList<Character> over : scoreBoard){
            assertTrue(over.size() <= 6);
            for(Character ball : over){
                assertTrue(ball == 'W' || (ball >= '0' && ball <= '6'));
            }
        }
    }
}