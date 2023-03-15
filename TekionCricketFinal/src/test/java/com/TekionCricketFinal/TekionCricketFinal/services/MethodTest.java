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
    public void testRunPerBall(){
        Player player = new Player("Virat", Role.BATSMAN);
        for(int i=0; i<10; i++){
            player.setRole(Role.BATSMAN);
            int runs = RunsPerBall.getRuns(player);
            assertTrue(runs >= -1 && runs <= 6);
            player.setRole(Role.ALL_ROUNDER);
            runs = RunsPerBall.getRuns(player);
            assertTrue(runs >= -1 && runs <= 6);
            player.setRole(Role.BOWLER);
            runs = RunsPerBall.getRuns(player);
            assertTrue(runs >= -1 && runs <= 2);
            player.setRole(Role.WICKET_KEEPER);
            runs = RunsPerBall.getRuns(player);
            assertTrue(runs >= -1 && runs <= 6);
        }
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