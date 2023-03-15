package com.TekionCricketFinal.TekionCricketFinal.models;

import com.TekionCricketFinal.TekionCricketFinal.services.Game;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TekionCricketFinalTest {

    @Test
    public void testMatchClass() {
        Team team1 = new Team();
        team1.setTeamName("India");
        team1.setPlayerInTeam(11);
        team1.setPlayers(new ArrayList<>());
        ArrayList<ArrayList<Character>> scoreBoard1 = new ArrayList<>();
        ArrayList<Character> inning1 = new ArrayList<>();
        inning1.add('W');
        inning1.add('W');
        inning1.add('1');
        inning1.add('4');
        inning1.add('6');
        scoreBoard1.add(inning1);
        Match match = new Match("1", team1, null, scoreBoard1, null, 100);
        Assertions.assertEquals(match.getId(), "1");
        Assertions.assertEquals(match.getTeam1(), team1);
        Assertions.assertNull(match.getTeam2());
        Assertions.assertEquals(match.getScoreBoard1(), scoreBoard1);
        Assertions.assertNull(match.getScoreBoard2());
        Assertions.assertEquals(match.getTargetScore(), 100);
    }

    @Test
    public void testPlayerClass() {
        Player player = new Player("John", Role.ALL_ROUNDER);
        Assertions.assertEquals(player.getName(), "John");
        Assertions.assertEquals(player.getRole(), Role.ALL_ROUNDER);
        Assertions.assertEquals(player.getRuns(), 0);
        Assertions.assertEquals(player.getWickets(), 0);
        Assertions.assertEquals(player.getBalls(), 0);
    }

    @Test
    public void testTeamClass() {
        Team team = new Team();
        team.setTeamName("India");
        team.setPlayerInTeam(11);
        ArrayList<Player> players = new ArrayList<>();
        Player player = new Player("Virat", Role.ALL_ROUNDER);
        players.add(player);
        team.setPlayers(players);
        team.setStatus(TeamStatus.BATTING);
        team.setRuns(100);
        team.setWickets(3);
        team.setBalls(30);
        team.setTeamId("1");
        Assertions.assertEquals(team.getTeamName(), "India");
        Assertions.assertEquals(team.getPlayerInTeam(), 11);
        Assertions.assertEquals(team.getPlayers(), players);
        Assertions.assertEquals(team.getStatus(), TeamStatus.BATTING);
        Assertions.assertEquals(team.getRuns(), 100);
        Assertions.assertEquals(team.getWickets(), 3);
        Assertions.assertEquals(team.getBalls(), 30);
        Assertions.assertEquals(team.getTeamId(), "1");
    }

    @Test
    public void testGameClass() {
        Team team1 = new Team();
        team1.setTeamName("India");
        team1.setPlayerInTeam(11);
        team1.setPlayers(new ArrayList<>());
        ArrayList<ArrayList<Character>> scoreBoard1 = new ArrayList<>();
        ArrayList<Character> inning1 = new ArrayList<>();
        Team team2 = new Team();
        team2.setTeamName("India");
        team2.setPlayerInTeam(11);
        team2.setPlayers(new ArrayList<>());
        ArrayList<ArrayList<Character>> scoreBoard2 = new ArrayList<>();
        inning1.add('W');
        inning1.add('W');
        inning1.add('1');
        inning1.add('4');
        inning1.add('6');
        scoreBoard1.add(inning1);
        Match match = new Match(team1, team2, new ArrayList<>(), new ArrayList<>(), 0);
        Game game = new Game(match);
        Assertions.assertEquals(game.getMatch(), match);
    }
}
