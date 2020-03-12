package com.example.myapp.controllers;

import com.example.myapp.models.Topic;
import com.example.myapp.models.Widget;
import com.example.myapp.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class TopicController {
    @Autowired
    TopicService service = new TopicService();


    @GetMapping("/api/topics")
    public List<Topic> findALlTopics() {
        return service.findAllTopics();
    }


    @PostMapping("/api/lessons/{lessonId}/topics")
    public Topic createTopic(
            @PathVariable("lessonId") String lessonId,
            @RequestBody Topic topic) {
        return service.createTopic(lessonId, topic);
    }


    @PutMapping("/api/topics/{topicId}")
    public int updateTopic(@PathVariable("topicId") Integer topicId, @RequestBody Topic topic) {
        return service.updateTopic(topicId, topic);
    }

    @DeleteMapping("/api/topics/{topicId}")
    public int deleteTopic(@PathVariable("topicId") Integer topicId) {
        return service.deleteTopic(topicId);
    }

    @GetMapping("/api/topics/{topicId}")
    public Topic findTopicById(@PathVariable("topicId") Integer topicId) {
        return service.findTopicById(topicId);
    }

    @GetMapping("/api/lessons/{lessonId}/topics")
    public List<Topic> findTopicsForLesson(
            @PathVariable("lessonId") String lessonId) {
        return service.findTopicsForLesson(lessonId);
    }


}