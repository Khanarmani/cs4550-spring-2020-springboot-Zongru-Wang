package com.example.myapp.services;

import com.example.myapp.models.Topic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.example.myapp.models.Widget;
import com.example.myapp.repositories.TopicRepository;
import com.example.myapp.repositories.WidgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    WidgetRepository widgetRepository;

    public List<Topic> findAllTopics() {
        return (List<Topic>)topicRepository.findAll();
    }

    // TODO: implement findTopicById
    public Topic findTopicById(int tid) {
        return null;
    }

    // TODO: delete topic
    public int deleteTopic(int tid) {
        return 1;
    }

    public int updateTopic(int tid, Topic newTopic) {
        return 1;
    }

    public Topic createTopic(Topic newTopic) {
        return topicRepository.save(newTopic);
    }

    public Widget createWidgetForTopic(
            Integer tid,
            Widget newWidget) {
        Topic topic = topicRepository.findById(tid).get();
        newWidget.setTopic(topic);
        return widgetRepository.save(newWidget);
    }

    public List<Topic> findTopicsForLesson(String lessonId) {
        return topicRepository.findTopicsForLesson(lessonId);
    }
}

//public class TopicService {
//    // local copy of topics
//    Map<String, List<Topic>> topics = new HashMap<>();
//
//    // Creates new Topic with a unique ID and add to existing topics collection
//    public Topic createTopic(String lessonId, Topic topic) {
//        topic.setLessonId(lessonId);
//        if (topics.containsKey(lessonId)) {
//            topics.get(lessonId).add(topic);
//        } else {
//            List<Topic> topicList = new ArrayList<>();
//            topicList.add(topic);
//            topics.put(lessonId, topicList);
//        }
//        return topic;
//    }
//
//    // Returns topic collection for the given lesson
//    public List<Topic> findTopicsForLesson(String lessonId) {
//        if (topics.containsKey(lessonId)) {
//            return topics.get(lessonId);
//        } else {
//            return new ArrayList<>();
//        }
//    }
//
//    // Removes the topic with the given ID
//    // Returns 1 if successful, 0 otherwise
//    public int deleteTopic(String topicId) {
//        for (List<Topic> topicList : topics.values()) {
//            for(int i = 0; i < topicList.size(); i += 1)  {
//                if(topicList.get(i).getId().equals(topicId)) {
//                    topicList.remove(i);
//                    return 1;
//                }
//            }
//        }
//        return 0;
//    }
//
//    // Update the topic with the given ID
//    // Returns 1 if successful, 0 otherwise
//    public int updateTopic(String topicId, Topic updatedTopic) {
//        for (List<Topic> topicList : topics.values()) {
//            for(int i = 0; i < topicList.size(); i += 1)  {
//                if(topicList.get(i).getId().equals(topicId)) {
//                    topicList.set(i, updatedTopic);
//                    return 1;
//                }
//            }
//        }
//        return 0;
//    }
//
//    // Returns all the topics
//    public List<Topic> findAllTopics() {
//        return new ArrayList(topics.values());
//    }
//
//    // Finds the topic given the topic ID
//    public Topic findTopicById(String topicId) {
//        for (List<Topic> topicList : topics.values()) {
//            for(int i = 0; i < topicList.size(); i += 1)  {
//                if(topicList.get(i).getId().equals(topicId)) {
//                    return topicList.get(i);
//                }
//            }
//        }
//        throw new IllegalArgumentException("No topic with the given ID");
//    }
//}