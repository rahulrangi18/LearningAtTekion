package com.TekionCricketFinal.TekionCricketFinal.repository.mongo;
import com.TekionCricketFinal.TekionCricketFinal.models.Match;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface MongoCricketRepository extends MongoRepository<Match, String> {
    List<Match> findByTeam1Name(String name);
    List<Match> findByTeam2Name(String name);
    Page<Match> findByTeam1NameOrTeam2Name(String name1, String name2, Pageable pageable);
    Page<Match> findByMatchDate(String matchDate,Pageable pageable);
    Page<Match> findByMatchDateStartingWith(String partialDate, Pageable pageable);
}
