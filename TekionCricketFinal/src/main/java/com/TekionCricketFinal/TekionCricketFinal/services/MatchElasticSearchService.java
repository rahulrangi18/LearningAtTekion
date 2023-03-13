package com.TekionCricketFinal.TekionCricketFinal.services;

import com.TekionCricketFinal.TekionCricketFinal.models.Match;
import com.TekionCricketFinal.TekionCricketFinal.repository.elasticsearch.ElasticCricketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchElasticSearchService {

    private final ElasticCricketRepository elasticCricketRepository;

    private final Match match;
    public void deleteAll(){
        elasticCricketRepository.deleteAll();
    }
    public Page<Match> findByTeam(String team){
        return elasticCricketRepository.findByTeamOneNameOrTeamTwoName(team , team , Pageable.ofSize(2));
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
