package com.TekionCricketWithDatabase.TekionCricketWithDatabase.services;

import com.TekionCricketWithDatabase.TekionCricketWithDatabase.services.RunsPerBall;
import com.TekionCricketWithDatabase.TekionCricketWithDatabase.models.Team;
import com.TekionCricketWithDatabase.TekionCricketWithDatabase.models.TeamStatus;

import java.util.ArrayList;

public class Innings {
    private ArrayList<ArrayList<Character>> scoreBoard = new ArrayList<ArrayList<Character>>();
    private int overs = 0;

    public int getOvers() {
        return overs;
    }

    public void setOvers(int overs) {
        this.overs = overs;
    }


    private int TotalOver;
    private int PlayerInTeam;

    public int getTotalOver() {
        return TotalOver;
    }

    public void setTotalOver(int totalOver) {
        this.TotalOver = totalOver;
    }


    private Team battingTeam;
    private Team bowlingTeam;
    private boolean isFirstInnings;
    private int targetScore = 0;

    public Innings(Team battingTeam, Team bowlingTeam, boolean isFirstInnings, int TotalOvr, int PlayerInTm) {
        this.isFirstInnings = isFirstInnings;
        this.battingTeam = battingTeam;
        this.bowlingTeam = bowlingTeam;
        this.TotalOver = TotalOvr;
        this.PlayerInTeam = PlayerInTm;
    }

    public ArrayList<ArrayList<Character>> getScoreBoard() {
        return this.scoreBoard;
    }

    public void setTargetScore(int targetScore) {
        this.targetScore = targetScore;
    }

    public void startInnings() {
        // start the innings
        while (overs < TotalOver) {// play the over
            for (int i = 0; i < 6; i++) {
                // play the ball
                int runs = RunsPerBall.getRuns(this.battingTeam.getPlayers().get(this.battingTeam.getWickets()));
                if (runs != -1) {
                    this.battingTeam.getPlayers().get(this.battingTeam.getWickets()).setRuns(runs);
                    this.battingTeam.setRuns(this.battingTeam.getRuns() + runs);
                }
                this.battingTeam.getPlayers().get(this.battingTeam.getWickets()).setBalls(this.battingTeam.getPlayers().get(this.battingTeam.getWickets()).getBalls() + 1);
                this.battingTeam.setBalls(this.battingTeam.getBalls() + 1);
                if (targetScore != 0 && this.battingTeam.getRuns() >= targetScore) {
                    this.battingTeam.setStatus(TeamStatus.WON);
                    this.bowlingTeam.setStatus(TeamStatus.LOST);
                    return;
                }
                if (this.scoreBoard.size() == this.overs) {
                    this.scoreBoard.add(new ArrayList<Character>());
                }
                if (runs == -1) {
                    this.scoreBoard.get(this.overs).add('W');
                    this.battingTeam.setWickets(this.battingTeam.getWickets() + 1);
                    if (this.battingTeam.getWickets() == this.PlayerInTeam - 1) {
                        if (isFirstInnings) {
                            return;
                        } else if (this.battingTeam.getRuns() == this.targetScore) {
                            this.battingTeam.setStatus(TeamStatus.DRAW);
                            this.bowlingTeam.setStatus(TeamStatus.DRAW);
                            return;
                        } else {
                            this.battingTeam.setStatus(TeamStatus.LOST);
                            this.bowlingTeam.setStatus(TeamStatus.WON);
                            return;
                        }
                    }
                } else {
                    this.scoreBoard.get(this.overs).add(Character.forDigit(runs, 10));
                }
                if (this.battingTeam.getStatus() == TeamStatus.LOST)
                    break;
            }
            this.overs++;
        }
        if (!isFirstInnings) {
            if (this.battingTeam.getRuns() == this.targetScore) {
                this.battingTeam.setStatus(TeamStatus.DRAW);
                this.bowlingTeam.setStatus(TeamStatus.DRAW);
            } else if (this.battingTeam.getRuns() > this.targetScore) {
                this.battingTeam.setStatus(TeamStatus.WON);
                this.bowlingTeam.setStatus(TeamStatus.LOST);
            } else {
                this.battingTeam.setStatus(TeamStatus.LOST);
                this.bowlingTeam.setStatus(TeamStatus.WON);
            }
        }
    }
}
