package com.example.myapp.controllers;

import com.example.myapp.models.Widget;
import com.example.myapp.services.TopicService;
import com.example.myapp.services.WidgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
public class WidgetController {

    @Autowired
    WidgetService service;

    @Autowired
    TopicService topicService;

    @GetMapping("/api/widgets/{wid}/update")
    public int updateWidgetNotRestfulBad(
            @PathVariable("wid") int widgetId) {
        Widget widget = new Widget();
        widget.setTitle("New and Improved Title");
        widget.setSize(123);
        return service.updateWidget(widgetId, widget);
    }

    @PutMapping("/api/widgets/{wid}")
    public int updateWidget(@PathVariable("wid") int widgetId,
                            @RequestBody Widget widget) {
        return service.updateWidget(widgetId, widget);
    }

    @GetMapping("/api/widgets/{wid}/delete")
    public int deleteWidgetNotRestful(@PathVariable("wid") int widgetId) {
        return service.deleteWidget(widgetId);
    }

    @DeleteMapping("/api/widgets/{wid}")
    public int deleteWidget(@PathVariable("wid") int widgetId) {
        return service.deleteWidget(widgetId);
    }

    @GetMapping("/api/widgets/create")
    public Widget createWidgetNotRest() {
        Widget newWidget = new Widget();
        newWidget.setTitle("Not RESTful");
        newWidget.setSize(45);
        return service.createWidget(newWidget);
    }

    @PostMapping("/api/topics/{topicId}/widgets")
    public Widget createWidget(
            @PathVariable("topicId") Integer topicId,
            @RequestBody Widget newWidget) {
        return topicService.createWidgetForTopic(topicId, newWidget);
//        return service.createWidget(topicId, newWidget);
    }

    @GetMapping("/api/widgets")
    public List<Widget> findAllWidgets() {
        return service.findAllWidgets();
    }

    @GetMapping("/api/widgets/{widgetId}")
    public Widget findWidgetById(@PathVariable("widgetId") int wid) {
        return service.findWidgetById(wid);
    }

    @GetMapping("/api/topics/{tid}/widgets")
    public List<Widget> findWidgetsForTopic(@PathVariable("tid") int tid) {
        return service.findWidgetsForTopic(tid);
    }

}