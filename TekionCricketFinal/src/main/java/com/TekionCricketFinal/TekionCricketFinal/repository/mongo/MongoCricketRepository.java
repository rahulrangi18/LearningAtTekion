package com.TekionCricketFinal.TekionCricketFinal.repository.mongo;
import com.TekionCricketFinal.TekionCricketFinal.models.Match;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MongoCricketRepository extends MongoRepository<Match,String > {
    List<Match> findByTeam1Name(final String name);
    List<Match> findByTeam2Name(final String name);
    Page<Match> findByTeam1NameOrTeam2Name(final String name1, final String name2, Pageable pageable);
}
