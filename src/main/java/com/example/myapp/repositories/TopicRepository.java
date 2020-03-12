package com.example.myapp.repositories;

import com.example.myapp.models.Topic;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TopicRepository
        extends CrudRepository<Topic, Integer> {

    @Query(value = "SELECT * FROM topics WHERE topics.lesson_id=:lessonId", nativeQuery = true)
    public List<Topic> findTopicsForLesson(@Param("lessonId") String lessonId);
}
