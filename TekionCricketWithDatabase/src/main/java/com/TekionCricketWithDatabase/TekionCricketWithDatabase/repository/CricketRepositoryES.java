package com.TekionCricketWithDatabase.TekionCricketWithDatabase.repository;

import com.TekionCricketWithDatabase.TekionCricketWithDatabase.models.Match;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CricketRepositoryES extends ElasticsearchRepository<Match, String> {
    List<Match> findByTeam1NameOrTeam2Name(String teamName1, String teamName2);
    List<Match> findByTeam1Name(String teamName);
    List<Match> findByTeam2Name(String teamName);

    Optional<Match> findById(String id);

    void save(Match match);

    void deleteAll();

    Match findAll();
}
