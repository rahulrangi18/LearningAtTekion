package com.TekionCricketFinal.TekionCricketFinal.repository.elasticsearch;

import com.TekionCricketFinal.TekionCricketFinal.models.Match;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElasticCricketRepository extends ElasticsearchRepository<Match, String>{
    Page<Match> findByTeamOneNameOrTeamTwoName(String name1, String name2, Pageable pageable);
    @Query("{\"bool\": {\"should\": [{\"w ildcard\": {\"team1.name\": \"*?0*\"}},{\"wildcard\": {\"team2.name\": \"*?0*\"}}]}}")
    Page<Match> findByPartialText(String name, Pageable pageable);
}