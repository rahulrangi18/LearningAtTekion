package com.TekionCricketFinal.TekionCricketFinal.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;

@Document(collection = "tekionMatches")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "tekion-matches")
@Data
@Component
@Builder
@AllArgsConstructor
public class Match {
    @Id
    private String matchId;
    @Field(type = FieldType.Object, name = "matchDate")
    private String matchDate;
    @Field(type = FieldType.Object, name = "team1")
    private Team team1;

    @Field(type = FieldType.Object, name = "team2")
    private Team team2;

    @Field(type = FieldType.Nested)
    private ArrayList<ArrayList<Character>> scoreBoard1;

    @Field(type = FieldType.Nested)
    private ArrayList<ArrayList<Character>> scoreBoard2;

    @Field(type = FieldType.Integer, name = "targetScore")
    private int targetScore;

    public Match(){

    }
    public Match(String matchId,Team t1, Team t2, ArrayList<ArrayList<Character>> sb1, ArrayList<ArrayList<Character>> sb2, int ts) {
        this.matchId=matchId;
        this.team1=t1;
        this.team2=t2;
        this.scoreBoard1=sb1;
        this.scoreBoard2=sb2;
        this.targetScore=ts;
    }
}
