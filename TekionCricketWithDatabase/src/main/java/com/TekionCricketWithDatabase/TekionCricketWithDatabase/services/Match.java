package com.TekionCricketWithDatabase.TekionCricketWithDatabase.services;
import com.TekionCricketWithDatabase.TekionCricketWithDatabase.models.Team;
import com.TekionCricketWithDatabase.TekionCricketWithDatabase.models.TeamStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

@Document(collection = "tekionMatches")
@ToString
@Getter
@Setter
public
class Match {
    @Id
    private String id;
    private Team team1 = new Team();
    private Team team2 = new Team();
    private ArrayList<ArrayList<Character>> scoreBoard1;
    private ArrayList<ArrayList<Character>> scoreBoard2;
    private int targetScore;

    public Match() {
        Scanner input = new Scanner(System.in);
        System.out.println("Let's play CRICKET, Let's set some basic rule\n");
        System.out.print("Enter No. of Overs in game: ");
        int TotalOvr = input.nextInt();

        Random rand = new Random();
        System.out.println();
        System.out.println("Teams are Ready for Toss\n");
        int choice = rand.nextInt(0, 2);
        if (choice == 0)
            System.out.println(team1.getName() + " called for head\n");
        else
            System.out.println(team1.getName() + " called for tails\n");

        int toss = rand.nextInt(0, 2);
        if (choice == toss) {
            System.out.println(team1.getName() + " Won the toss is batting first");
            team1.setStatus(TeamStatus.BATTING);
            team2.setStatus(TeamStatus.BOWLING);
        } else {
            System.out.println(team2.getName() + " Won the toss is batting first");
            team2.setStatus(TeamStatus.BATTING);
            team1.setStatus(TeamStatus.BOWLING);
        }
        Team battingTeam = team1.getStatus() == TeamStatus.BATTING ? team1 : team2;
        Team bowlingTeam = team1.getStatus() == TeamStatus.BOWLING ? team1 : team2;

        Innings innings1 = new Innings(battingTeam, bowlingTeam, true, TotalOvr, team1.getPlayerInTeam());
        innings1.startInnings();
        targetScore = battingTeam.getRuns() + 1;
        int target = battingTeam.getRuns() + 1;

        System.out.println(battingTeam.getName() + " is batting first");
        scoreBoard2 = innings1.getScoreBoard();
        System.out.println(innings1.getScoreBoard());
        System.out.println("Score = " + battingTeam.getRuns() + "/" + battingTeam.getWickets() + " in " + ballsToOvers(battingTeam.getBalls()));
        System.out.println();

        System.out.println(bowlingTeam.getName() + " need " + target + " to win");
        System.out.println();
        battingTeam = team1.getStatus() == TeamStatus.BOWLING ? team1 : team2;
        bowlingTeam = team1.getStatus() == TeamStatus.BATTING ? team1 : team2;
        System.out.println(battingTeam.getName() + " is batting now");
        Innings innings2 = new Innings(battingTeam, bowlingTeam, false, TotalOvr, team2.getPlayerInTeam());
        innings2.setTargetScore(target);
        innings2.startInnings();
        scoreBoard1 = innings2.getScoreBoard();
        System.out.println(innings2.getScoreBoard());
        System.out.println("Final score = " + battingTeam.getRuns() + "/" + battingTeam.getWickets() + " in " + ballsToOvers(battingTeam.getBalls()));
        System.out.println();

        if (battingTeam.getStatus() == TeamStatus.WON) {
            System.out.println(battingTeam.getName() + " won the match" + " by " + (10 - battingTeam.getWickets()) + " wickets");
        } else if (battingTeam.getStatus() == TeamStatus.LOST) {
            System.out.println(bowlingTeam.getName() + " won the match" + " by " + (bowlingTeam.getRuns() - battingTeam.getRuns()) + " runs");
        } else {
            System.out.println("Match Draw");
        }
    }

    private double ballsToOvers(int balls) {
        double overs = (int) balls / 6;
        overs += (double) (balls % 6) / 10;
        return overs;
    }
}
