package com.example.myapp.services;

import com.example.myapp.models.Widget;

import java.util.*;
import java.util.stream.Collectors;

import com.example.myapp.repositories.TopicRepository;
import com.example.myapp.repositories.WidgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WidgetService {

    @Autowired
    WidgetRepository widgetRepository;

    @Autowired
    TopicRepository topicRepository;

    public int deleteWidget(Integer widgetId) {
        widgetRepository.deleteById(widgetId);
        return 1;
    }

    public Widget createWidget(Widget newWidget) {
        return widgetRepository.save(newWidget);
    }

    public int updateWidget(int widgetId, Widget updatedWidget) {
        Widget oldWidget = widgetRepository.findWidgetById(widgetId);
        oldWidget.setTitle(updatedWidget.getTitle());
        oldWidget.setSize(updatedWidget.getSize());
        widgetRepository.save(oldWidget);
        return 1;
    }

    public List<Widget> findAllWidgets() {
        return widgetRepository.findAllWidgets();
    }

    public List<Widget> findWidgetsForTopic(int topicId) {
        return widgetRepository.findWidgetsForTopic(topicId);
    }

    public Widget findWidgetById(int wid) {
        return widgetRepository.findWidgetById(wid);
//        return widgetRepository.findById(wid).get();
    }
}