package com.example.myapp.controllers;

import com.example.myapp.models.Widget;
import com.example.myapp.services.WidgetService;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class WidgetController {

    WidgetService service = new WidgetService();

    @PostMapping("/api/widgets")
    public Widget createWidget(
            @RequestBody Widget newWidget) {
        return service.createWidget(newWidget);
    }

    @DeleteMapping("/api/widgets/{widgetId}")
    public int deleteWidget(
            @PathVariable("widgetId") String wid) {
        return service.deleteWidget(wid);
    }

    @PutMapping("/api/widgets/{widgetId}")
    public int updateWidget(
            @PathVariable("widgetId") String wid,
            @RequestBody Widget updatedWidget) {
        return service.updateWidget(wid, updatedWidget);
    }

    @GetMapping("/api/widgets/{widgetId}")
    public Widget findWidgetById(
            @PathVariable("widgetId") String wid) {
        return service.findWidgetById(wid);
    }

    @GetMapping("/api/widgets")
    public Map<String, List<Widget>> findAllWidgets() {
        return service.findAllWidgets();
    }

    @GetMapping("/api/topics/{tid}/widgets")
    public List<Widget> findWidgetsForTopic(
            @PathVariable("tid") String topicId) {
        return service.findWidgetsForTopic(topicId);
    }


    @PostMapping("/api/widgets/up")
    public List<Widget> updateWidgetUp(
            @RequestBody Widget widget) {
        return service.updateWidgetUp(widget);
    }
    @PostMapping("/api/widgets/down")
    public List<Widget> updateWidgetDown(
            @RequestBody Widget widget) {
        return service.updateWidgetDown(widget);
    }

}