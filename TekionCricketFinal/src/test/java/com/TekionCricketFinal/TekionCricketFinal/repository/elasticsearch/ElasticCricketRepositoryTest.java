package com.TekionCricketFinal.TekionCricketFinal.repository.elasticsearch;

import com.TekionCricketFinal.TekionCricketFinal.models.Match;
import com.TekionCricketFinal.TekionCricketFinal.models.Team;
import com.TekionCricketFinal.TekionCricketFinal.repository.mongo.MongoCricketRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@SpringJUnitConfig
class ElasticCricketRepositoryTest {

    @Autowired
    private MongoCricketRepository mongoRepository;

    @Autowired
    private ElasticCricketRepository elasticRepository;

    String team1Name="India";
    String team2Name="Pakistan";
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