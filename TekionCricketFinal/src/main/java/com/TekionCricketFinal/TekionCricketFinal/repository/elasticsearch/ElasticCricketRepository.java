package com.TekionCricketFinal.TekionCricketFinal.repository.elasticsearch;

import com.TekionCricketFinal.TekionCricketFinal.models.Match;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.stereotype.Repository;

@Repository
public interface ElasticCricketRepository extends ElasticsearchRepository<Match, String>{
    Page<Match> findByTeam1_NameOrTeam2_Name(String name1, String name2, Pageable pageable);
    @Query("{\"bool\": {\"should\": [{\"wildcard\": {\"team1.name\": \"*?0*\"}},{\"wildcard\": {\"team2.name\": \"*?0*\"}}]}}")
    Page<Match> findByPartialText(String name, Pageable pageable);

    @Query("{\"bool\": {\"should\": [{\"match\": {\"team1.name\": \"?0\"}},{\"match\": {\"team2.name\": \"?0\"}}]}}")
    Page<Match> findByExactText(String name, Pageable pageable);
    Page<Match> findByMatchDate(String matchDate, Pageable pageable);
}