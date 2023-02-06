package com.learnSpring.learnSpring.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController

public class TopicController {
    @Autowired

    private TopicService topicService;
    @RequestMapping("/topic")
    public List<Topic> getAllTopics(){
        return topicService.getAllTopics();
    }
    @RequestMapping("/topic/{my_id}") // yaha niche id ("my_id") isliye pass ki kyu ki name same nhi h same rkhnege to nhi krna pdega
    public Topic getTopic(@PathVariable("my_id") String id){
        return topicService.getTopic(id);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/topic")
    public void addTopic(@RequestBody Topic topic ){
         topicService.addTopic(topic);
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/topic/{id}")
    public void updateTopic(@RequestBody Topic topic,@PathVariable String id){
        topicService.updateTopic(id,topic);
    }
    public void deleteTopic(@PathVariable String id){
        topicService.deleteTopic(id);
    }
}
