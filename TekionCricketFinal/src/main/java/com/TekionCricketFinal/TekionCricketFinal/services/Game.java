package com.TekionCricketFinal.TekionCricketFinal.services;

import com.TekionCricketFinal.TekionCricketFinal.models.Match;
import com.TekionCricketFinal.TekionCricketFinal.models.Team;
import com.TekionCricketFinal.TekionCricketFinal.models.TeamStatus;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.UUID;


@Service
@Data
public class Game {
    private Match match;

    public Game(){

    }
    public Game(Match m){
       this.match=m;
    }
    public Match startGame(Match match,Team team1,Team team2) {
        String matchId=UUID.randomUUID().toString();
        match.setMatchId(matchId);
        match.setTeam1(team1);
        match.setTeam2(team2);
        team1.setTeamId(UUID.randomUUID().toString());
        team1.setMatchIdForTeam(matchId);
        team2.setTeamId(UUID.randomUUID().toString());
        team2.setMatchIdForTeam(matchId);
        match.setScoreBoard1(new ArrayList<ArrayList<Character>>());
        match.setScoreBoard2(new ArrayList<ArrayList<Character>>());
        Scanner input = new Scanner(System.in);
        System.out.println("Let's play CRICKET, Let's set some basic rule\n");
        System.out.print("Enter No. of Overs in game: ");
        int TotalOvr = input.nextInt();

        Random rand = new Random();
        System.out.println();
        System.out.println("Teams are Ready for Toss\n");
        int choice = rand.nextInt(0, 2);
        if (choice == 0)
            System.out.println(team1.getTeamName() + " called for heads\n");
        else
            System.out.println(team1.getTeamName() + " called for tails\n");

        int toss = rand.nextInt(0, 2);

        if(toss==1)
            System.out.println("It's Heads\n");
        else
            System.out.println("It's Tails\n");

        if (choice == toss) {
            System.out.println(team1.getTeamName() + " Won the toss is batting first");
            team1.setStatus(TeamStatus.BATTING);
            team2.setStatus(TeamStatus.BOWLING);
        } else {
            System.out.println(team2.getTeamName() + " Won the toss is batting first");
            team2.setStatus(TeamStatus.BATTING);
            team1.setStatus(TeamStatus.BOWLING);
        }
        Team battingTeam = team1.getStatus() == TeamStatus.BATTING ? team1 : team2;
        Team bowlingTeam = team1.getStatus() == TeamStatus.BOWLING ? team1 : team2;

        Innings innings1 = new Innings(battingTeam, bowlingTeam, true, TotalOvr, team1.getPlayerInTeam());
        innings1.startInnings();
        //match.(battingTeam.getRuns() + 1);
        //targetScore = battingTeam.getRuns() + 1;
        int target = battingTeam.getRuns() + 1;

        System.out.println(battingTeam.getTeamName() + " is batting first");
        match.setScoreBoard2(innings1.getScoreBoard());
        //scoreBoard2 = innings1.getScoreBoard();
        System.out.println(innings1.getScoreBoard());
        System.out.println("Score = " + battingTeam.getRuns() + "/" + battingTeam.getWickets() + " in " + ballsToOvers(battingTeam.getBalls()));
        System.out.println();

        System.out.println(bowlingTeam.getTeamName() + " need " + target + " to win");
        System.out.println();
        battingTeam = team1.getStatus() == TeamStatus.BOWLING ? team1 : team2;
        bowlingTeam = team1.getStatus() == TeamStatus.BATTING ? team1 : team2;
        System.out.println(battingTeam.getTeamName() + " is batting now");
        Innings innings2 = new Innings(battingTeam, bowlingTeam, false, TotalOvr, team2.getPlayerInTeam());
        innings2.setTargetScore(target);
        match.setTargetScore(target);
        innings2.startInnings();
        match.setScoreBoard1(innings2.getScoreBoard());
        //scoreBoard1 = innings2.getScoreBoard();
        System.out.println(innings2.getScoreBoard());
        System.out.println("Final score = " + battingTeam.getRuns() + "/" + battingTeam.getWickets() + " in " + ballsToOvers(battingTeam.getBalls()));
        System.out.println();

        if (battingTeam.getStatus() == TeamStatus.WON) {
            System.out.println(battingTeam.getTeamName() + " won the match" + " by " + (10 - battingTeam.getWickets()) + " wickets");
        } else if (battingTeam.getStatus() == TeamStatus.LOST) {
            System.out.println(bowlingTeam.getTeamName() + " won the match" + " by " + (bowlingTeam.getRuns() - battingTeam.getRuns()) + " runs");
        } else {
            System.out.println("Match Draw");
        }
        return match;
    }

    private double ballsToOvers(int balls) {
        double overs = (int) balls / 6;
        overs += (double) (balls % 6) / 10;
        return overs;
    }
}
