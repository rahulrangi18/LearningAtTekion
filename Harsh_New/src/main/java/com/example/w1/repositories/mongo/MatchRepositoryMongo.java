package com.example.w1.repositories.mongo;


import com.example.w1.models.Match;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MatchRepositoryMongo extends MongoRepository<Match,String > {
    List<Match> findByTeam1Name(final String name);
    List<Match> findByTeam2Name(final String name);
    Page<Match> findByTeam1NameOrTeam2Name(final String name1, final String name2, Pageable pageable);
}
