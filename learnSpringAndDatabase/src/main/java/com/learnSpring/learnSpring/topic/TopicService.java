package com.learnSpring.learnSpring.topic;

import org.springframework.beans.factory.annotation.Autowired;
import com.learnSpring.learnSpring.topic.TopicRepository;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@ComponentScan(basePackageClasses = "com.learnSpring.learnSpring.topic.")
public class TopicService {
    @Autowired
    private TopicRepository topicRepository;

    private final List<Topic> topics= new ArrayList<>(Arrays.asList(new Topic("Spring", "Spring Framework", "Spring Description"),
                                              new Topic("Java", "Java Framework", "Java Description"),
                                              new Topic("JavaScript", "JavaScript Framework", "JavaScript Description")));

    public List<Topic> getAllTopics() {

        //return topics;
        List<Topic> topics=new ArrayList<>();
        topicRepository.findAll().forEach(topics::add) ;
        return topics;
    }
    public Topic getTopic(String id){
          return topics.stream().filter(topic -> topic.getId().equals(id)).findFirst().get();
    }
    public void addTopic(Topic topic){
        //topics.add(topic);
        topicRepository.save(topic);
    }

    public void updateTopic(String id, Topic topic){
        for(int i=0;i<topics.size();i++)
        {
            Topic t=topics.get(i);
            if(t.getId().equals(id)){
                 topics.set(i,topic);
                 return;
            }
        }
    }
    public void deleteTopic(String id) {
        topics.removeIf(t-> t.getId().equals((id)));
    }
}
