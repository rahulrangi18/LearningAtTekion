package com.TekionCricketFinal.TekionCricketFinal.repository;

import com.TekionCricketFinal.TekionCricketFinal.models.Match;
import com.TekionCricketFinal.TekionCricketFinal.models.Team;
import com.TekionCricketFinal.TekionCricketFinal.repository.mongo.MongoCricketRepository;
import com.TekionCricketFinal.TekionCricketFinal.repository.elasticsearch.ElasticCricketRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@SpringJUnitConfig
public class RepositoryTests {

    @Autowired
    private MongoCricketRepository mongoRepository;

    @Autowired
    private ElasticCricketRepository elasticRepository;

    String team1Name="India";
    String team2Name="Pakistan";
    @Test
    public void testMongoRepository() {
        Match match = new Match();
        match.setTeam1(new Team(team1Name));
        match.setTeam2(new Team(team2Name));
        match.setMatchDate("2023-03-17T13:01:45.941994");
        PageRequest pageable = PageRequest.of(0, 10);
        mongoRepository.save(match);

        List<Match> matches1 = mongoRepository.findByTeam1Name(team1Name);
        assertThat(matches1);

        List<Match> matches2 = mongoRepository.findByTeam2Name(team2Name);
        assertThat(matches2);


        Page<Match> matches3 = mongoRepository.findByTeam1NameOrTeam2Name(team1Name, team2Name, pageable);
        assertThat(matches3);

        Page<Match> matches4 = mongoRepository.findByMatchDate("2023-03-17T13:01:45.941994", pageable);
        assertThat(matches4);

        Page<Match> matches5 = mongoRepository.findByMatchDateStartingWith("2023-03-17", pageable);
        assertThat(matches5);
    }

    @Test
    public void testElasticRepository() {
        Match match = new Match();
        match.setTeam1(new Team(team1Name));
        match.setTeam2(new Team(team2Name));
        match.setMatchDate("2023-03-17T13:01:45.941994");
        elasticRepository.save(match);

        PageRequest pageable = PageRequest.of(0, 10);

        Page<Match> matches1 = elasticRepository.findByTeam1_NameOrTeam2_Name("India", "Pakistan", pageable);
        assertThat(matches1);

        Page<Match> matches2 = elasticRepository.findByPartialText("India", pageable);
        assertThat(matches2);

        Page<Match> matches3 = elasticRepository.findByExactText("Ind", pageable);
        assertThat(matches3);

        Page<Match> matches4 = elasticRepository.findByMatchDate("2023-03-17T13:01:45.941994", pageable);
        assertThat(matches4);

        Page<Match> matches5 = elasticRepository.findByPartialMatchDate("2023-03-17", pageable);
        assertThat(matches5);
    }
}

