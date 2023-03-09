package com.example.w1.repositories.es;

import com.example.w1.models.Match;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface MatchRepositoryES extends ElasticsearchRepository<Match, String> {
    Page<Match> findByTeam1_NameOrTeam2_Name(String name1, String name2, Pageable pageable);
    @Query("{\"bool\": {\"should\": [{\"wildcard\": {\"team1.name\": \"*?0*\"}},{\"wildcard\": {\"team2.name\": \"*?0*\"}}]}}")
    Page<Match> findByPartialText(String name, Pageable pageable);
}
