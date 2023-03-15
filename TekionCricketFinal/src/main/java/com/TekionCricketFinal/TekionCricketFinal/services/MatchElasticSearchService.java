package com.TekionCricketFinal.TekionCricketFinal.services;

import com.TekionCricketFinal.TekionCricketFinal.models.Match;
import com.TekionCricketFinal.TekionCricketFinal.repository.elasticsearch.ElasticCricketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchElasticSearchService {

    @Autowired
    private final ElasticCricketRepository elasticCricketRepository;

    public void deleteAll(){
        elasticCricketRepository.deleteAll();
    }
    public Page<Match> findByTeam(String team){
        return elasticCricketRepository.findByTeam1_NameOrTeam2_Name(team , team , Pageable.ofSize(2));
    }
    public Page<Match> findAll(){
        return elasticCricketRepository.findAll(Pageable.unpaged());
    }
    public Match findById(String id){
        return elasticCricketRepository.findById(id).orElse(null);
    }
    public void save(Match match){
        elasticCricketRepository.save(match);
    }
    public List<Match> partialSearch(String name){
        return elasticCricketRepository.findByPartialText(name, Pageable.unpaged()).getContent();
    }


}
