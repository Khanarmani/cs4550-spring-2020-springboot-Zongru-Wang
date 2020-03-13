package com.example.myapp.services;

import com.example.myapp.models.Topic;
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



    public int updateWidget(int widgetId, Widget updatedWidget) {
        Widget oldWidget = widgetRepository.findWidgetById(widgetId);
        oldWidget.setTitle(updatedWidget.getTitle());
        oldWidget.setSize(updatedWidget.getSize());
        oldWidget.setType(updatedWidget.getType());
        //oldWidget.setwidgetOrder(updatedWidget.getwidgetOrder());
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
    }


    public List<Widget> updateWidgetUp(Widget widget) {
        int topicId = widget.getTopic().getId();
        int order = widget.getwidgetOrder();

        List<Widget> widgetsForTopic = widgetRepository.findWidgetsForTopic(topicId);

        if (widgetsForTopic.size() > 0 && order > 0) {
            for (int i = 0; i <= widgetsForTopic.size(); i += 1) {
                if (i == order - 1) {
                    Widget newWidget = widgetsForTopic.get(order - 1);
                    newWidget.setwidgetOrder(order+1);
                    widget.setwidgetOrder(order-1);
                    widgetRepository.save(newWidget);
                    widgetRepository.save(widget);
                }
            }
        }
        return widgetRepository.findWidgetsForTopic(topicId);
    }

    public List<Widget> updateWidgetDown(Integer topicId, Widget widget) {
        int order = widget.getwidgetOrder();
        Topic topic = topicRepository.findTopicById(topicId);
        List<Widget> widgetList = widgetRepository.findWidgetsForTopic(topicId);
        // if there are widgets in this topic and the widget is not the lowest
        if (widgetList.size() > 0 && order < widgetList.size() - 1) {

                    Widget newWidget = widgetList.get(order + 1);

                    newWidget.setwidgetOrder(order);

                    // some questions here to ask, if I don't set the topic, the topicId will be zero
                    // after update... Found the bug but don't know why.
                    newWidget.setTopic(topic);
                    widget.setTopic(topic);
                    widget.setwidgetOrder(order + 1);
                    widgetRepository.save(newWidget);
                    widgetRepository.save(widget);
        }
        return widgetRepository.findWidgetsForTopic(topicId);
    }
}