package com.example.myapp.repositories;

import com.example.myapp.models.Widget;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WidgetRepository
        extends CrudRepository<Widget, Integer> {

    // added a oder by widget order in query field.
    @Query("SELECT widget FROM Widget widget WHERE widget.id=:widgetId ORDER BY widget.widgetOrder")
    public Widget findWidgetById(
            @Param("widgetId") Integer widgetId);

    @Query("SELECT widget FROM Widget widget")
    public List<Widget> findAllWidgets();

    // "SELECT * FROM widgets WHERE topic_id=topicId
    //@Query(value = "SELECT * FROM widgets WHERE topic_id=:tid", nativeQuery = true)
    @Query("select widget from Widget widget where widget.topic.id=:tid")
    public List<Widget> findWidgetsForTopic(
            @Param("tid") int topicId);


    // the update methods are through online resource and looked up and do the similar work.
    @Modifying
    @Query("UPDATE Widget widget SET widget.widgetOrder=:order WHERE widget.id=:widgetId")
    public int updateWidgetOrder(@Param("widgetId") Integer widgetId, @Param("order") Integer order);




}