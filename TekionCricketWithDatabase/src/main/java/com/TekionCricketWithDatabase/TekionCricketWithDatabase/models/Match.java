package com.TekionCricketWithDatabase.TekionCricketWithDatabase.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Document(collection = "tekionMatches")
@ToString
@Getter
@Setter
@Component
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Match {
    @Id
    private String id;

    private Team team1;
    private Team team2;
    private ArrayList<ArrayList<Character>> scoreBoard1;
    private ArrayList<ArrayList<Character>> scoreBoard2;
    private int targetScore;
    private String result;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<ArrayList<Character>> getScoreBoard1() {
        return scoreBoard1;
    }

    public void setScoreBoard1(ArrayList<ArrayList<Character>> scoreBoard1) {
        this.scoreBoard1 = scoreBoard1;
    }

    public ArrayList<ArrayList<Character>> getScoreBoard2() {
        return scoreBoard2;
    }

    public void setScoreBoard2(ArrayList<ArrayList<Character>> scoreBoard2) {
        this.scoreBoard2 = scoreBoard2;
    }

    public int getTargetScore(int i) {
        return targetScore;
    }

    public void setTargetScore(int targetScore) {
        this.targetScore = targetScore;
    }


    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    public int getTargetScore() {
        return targetScore;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }


}
