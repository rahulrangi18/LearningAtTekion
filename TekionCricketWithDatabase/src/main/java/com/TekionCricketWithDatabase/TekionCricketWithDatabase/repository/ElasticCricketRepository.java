package com.TekionCricketWithDatabase.TekionCricketWithDatabase.repository;
import com.TekionCricketWithDatabase.TekionCricketWithDatabase.models.Match;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.CrudRepository;

public interface ElasticCricketRepository extends ElasticsearchRepository<Match, String>, CrudRepository<Match,String> {
    Page<Match> findByTeamOneNameOrTeamTwoName(String name1, String name2, Pageable pageable);
    @Query("{\"bool\": {\"should\": [{\"wildcard\": {\"team1.name\": \"*?0*\"}},{\"wildcard\": {\"team2.name\": \"*?0*\"}}]}}")
    Page<Match> findByPartialText(String name, Pageable pageable);
}
