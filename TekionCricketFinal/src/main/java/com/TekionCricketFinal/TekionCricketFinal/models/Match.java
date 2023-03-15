package com.TekionCricketFinal.TekionCricketFinal.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Document(collection = "tekionMatches")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "tekion-matches")
@Data
@Component
@Builder
@AllArgsConstructor
public class Match {
    @Id
    private String id;
    private Team team1;
    private Team team2;
    private ArrayList<ArrayList<Character>> scoreBoard1;
    private ArrayList<ArrayList<Character>> scoreBoard2;
    private int targetScore;

    public Match(){

    }

    public Match(Team t1, Team t2, ArrayList<ArrayList<Character>> sb1, ArrayList<ArrayList<Character>> sb2, int ts) {
        this.team1=t1;
        this.team2=t2;
        this.scoreBoard1=sb1;
        this.scoreBoard2=sb2;
        this.targetScore=ts;
    }
}
