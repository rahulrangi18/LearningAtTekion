package com.TekionCricketFinal.TekionCricketFinal.services;

import com.TekionCricketFinal.TekionCricketFinal.models.Match;
import com.TekionCricketFinal.TekionCricketFinal.repository.mongo.MongoCricketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MatchMongoService {
    @Autowired
    private final MongoCricketRepository mongoCricketRepository;
    @Autowired
    private final Match match;

    private final Game game;

    public void save(Match match){
        mongoCricketRepository.insert(match);
    }
    public Page<Match> findAll(){
        return mongoCricketRepository.findAll(Pageable.unpaged());
    }
    public void deleteAll(){
        mongoCricketRepository.deleteAll();
    }
    public Match findById(String id){
        return mongoCricketRepository.findById(id).orElse(null);
    }
    public Page<Match> findByTeam(String team){
        return mongoCricketRepository.findByTeam1NameOrTeam2Name(team, team, Pageable.ofSize(10));
    }
}