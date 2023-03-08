package com.TekionCricketWithDatabase.TekionCricketWithDatabase.repository;
import com.TekionCricketWithDatabase.TekionCricketWithDatabase.models.Match;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CricketRepositoryMongo extends MongoRepository<Match,String > {
//    List<Match> findByTeam1OrTeam2(Team t1, Team t2);
//    @Query("{'team1:.name': ?0}")
//    List<Match> findByName(final String name);
    List<Match> findByTeam1Name(final String name);
    List<Match> findByTeam2Name(final String name);
}
