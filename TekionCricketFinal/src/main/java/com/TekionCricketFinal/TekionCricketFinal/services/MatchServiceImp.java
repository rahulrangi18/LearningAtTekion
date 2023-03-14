package com.TekionCricketFinal.TekionCricketFinal.services;

import com.TekionCricketFinal.TekionCricketFinal.models.Match;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class MatchServiceImp implements MatchService{

    private MatchElasticSearchService matchElasticSearchService;
    private MatchMongoService matchMongoService;
    private final Match match;
    private final Game game;
    public Page<Match> viewByTeam(String teamName){
        CompletableFuture[] futures = new CompletableFuture[]{
                CompletableFuture.supplyAsync(()->matchElasticSearchService.findByTeam(teamName)),
                CompletableFuture.supplyAsync(()->matchMongoService.findByTeam(teamName))
        };

        CompletableFuture<Page<Match>> result = CompletableFuture.anyOf(futures).thenApplyAsync((resultObj)->{
            // check cast is legal
            if(!(resultObj instanceof Page)){
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error in db");
            }
            return (Page<Match>)resultObj;
        });
        return result.join();
    }
    public Match viewById(String id){
        CompletableFuture[] futures = new CompletableFuture[]{
                CompletableFuture.supplyAsync(()->matchElasticSearchService.findById(id)),
                CompletableFuture.supplyAsync(()->matchMongoService.findById(id))
        };
        CompletableFuture<Match> result = CompletableFuture.anyOf(futures).thenApplyAsync((resultObj)->(Match)resultObj);
        Match match = result.join();
        if(match==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Match not found");
        }
        return match;

    }
    public Page<Match> showAll(){
        CompletableFuture<Page<Match>>[] futures = new CompletableFuture[]{
                CompletableFuture.supplyAsync(matchElasticSearchService::findAll),
                CompletableFuture.supplyAsync(matchMongoService::findAll)
        };

        CompletableFuture<Page<Match>> result = CompletableFuture.anyOf(futures).thenApplyAsync((resultObj)->(Page<Match>)resultObj);
        return result.join();
    }

    public Match startMatch(){
        game.startGame(match);
        insertMatch(match);
        return match;
    }
    public void insertMatch(Match match){
        try{
            matchElasticSearchService.save(match);
            matchMongoService.save(match);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Adding to db failed");
        }
    }
    public List<Match> partialSearch(String name){
        return matchElasticSearchService.partialSearch(name);
    }
    public void deleteAll(){
        matchElasticSearchService.deleteAll();
        matchMongoService.deleteAll();
    }

    // for validation
    public Page<Match> showAllES(){
        return matchElasticSearchService.findAll();
    }
    public Page<Match> showAllMongo(){
        return matchMongoService.findAll();
    }

}
//
//@Service
//@RequiredArgsConstructor
//public class MatchServiceImp implements MatchService {
//
//    @Autowired
//    private MatchElasticSearchService matchElasticSearchService;
//
//    @Autowired
//    private MatchMongoService matchMongoService;
//    @Autowired
//    private final Match match;
//
//    private final Game game;
//    @Override
//    public Page<Match> viewByTeam(String teamName) {
//        return matchElasticSearchService.findByTeam(teamName);
//    }
//
//    @Override
//    public Match viewById(String id) {
//        Match match = matchElasticSearchService.findById(id);
//        if (match == null) {
//            match = matchMongoService.findById(id);
//        }
//        return match;
//    }
//
//    @Override
//    public Page<Match> showAll() {
//        Page<Match> page = matchElasticSearchService.findAll();
//        if (page.isEmpty()) {
//            page = matchMongoService.findAll();
//        }
//        return page;
//    }
//
//    @Override
//    public Match startMatch() {
//        game.startGame(match);
//        insertMatch(match);
//        return match;
//    }
//
//    @Override
//    public void insertMatch(Match match) {
//        matchElasticSearchService.save(match);
//        matchMongoService.save(match);
//    }
//
//    @Override
//    public void deleteAll() {
//        matchElasticSearchService.deleteAll();
//        matchMongoService.deleteAll();
//    }
//
//    @Override
//    public Page<Match> showAllES() {
//        return matchElasticSearchService.findAll();
//    }
//
//    @Override
//    public Page<Match> showAllMongo() {
//        return matchMongoService.findAll();
//    }
//
//    @Override
//    public List<Match> partialSearch(String name) {
//        return matchElasticSearchService.partialSearch(name);
//    }
//
//}