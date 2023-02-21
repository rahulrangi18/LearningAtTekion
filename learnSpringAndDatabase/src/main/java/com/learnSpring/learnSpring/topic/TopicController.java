package com.learnSpring.learnSpring.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController("/topic")
public class TopicController {
    @Autowired
    private TopicService topicService;
    @RequestMapping("/")
    public List<Topic> getAllTopics(){
        return topicService.getAllTopics();
    }
    @RequestMapping("/{my_id}") // yaha niche id ("my_id") isliye pass ki kyu ki name same nhi h same rkhnege to nhi krna pdega
    public Topic getTopic(@PathVariable("my_id") String id){
        return topicService.getTopic(id);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/")
    public void addTopic(@RequestBody Topic topic ){
         topicService.addTopic(topic);
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/{id}")
    public void updateTopic(@RequestBody Topic topic,@PathVariable String id){
        topicService.updateTopic(id,topic);
    }
    public void deleteTopic(@PathVariable String id){
        topicService.deleteTopic(id);
    }
}
